import pandas as pd
import networkx as nx
import matplotlib
matplotlib.use('Agg')
from matplotlib import pyplot as plt
import numpy as np
from joblib import load
import random

def update_dynamic_reachability(G, threshold=0.0, max_iter=1):
    # 初始化动态可达概率
    for node in G.nodes:
        if G.nodes[node].get('state', 0) == 1:
            G.nodes[node]['dynamic_p'] = 1
        else:
            G.nodes[node]['dynamic_p'] = G.nodes[node]['static_reachability']

    # 迭代更新
    for _ in range(max_iter):
        print(1)
        changed = False
        new_dynamic_p_values = {}
        for node in G.nodes:
            if "state" not in G.nodes[node]:
                not_controlled_prob = 1
                for neighbor in G.neighbors(node):
                    not_controlled_prob *= (1 - G.nodes[neighbor]['dynamic_p'] * G[node][neighbor]['weight'])
                new_dynamic_p = G.nodes[node]["attac_p"]*(1-not_controlled_prob)
                new_dynamic_p_values[node] = new_dynamic_p
                # 计算基于邻居节点的动态可达概率
#                 new_dynamic_p = sum(G.nodes[neighbor]['dynamic_p'] * G[node][neighbor].get('weight', 1)for neighbor in G.neighbors(node))
#                 new_dynamic_p_values[node] = new_dynamic_p
            else:
                new_dynamic_p = 1
                new_dynamic_p_values[node] = new_dynamic_p
        # 更新节点的动态可达概率
        for node, new_dynamic_p in new_dynamic_p_values.items():
            if abs(new_dynamic_p - G.nodes[node]['dynamic_p']) > threshold:
                G.nodes[node]['dynamic_p'] = new_dynamic_p
                changed = True

        # 如果没有变化，停止迭代
        if not changed:
            break

    return G

def find_attack_path_by_dynamic_p(G, source, target):
    # 确保源节点和目标节点在图中
    if source not in G.nodes or target not in G.nodes:
        return None

    visited = set()  # 记录访问过的节点
    path = [source]  # 当前路径

    # 当前节点初始化为源节点
    current_node = source

    while current_node != target:
        visited.add(current_node)
        neighbors = G.neighbors(current_node)

        # 挑选动态可达概率最高的邻居节点
        next_node = None
        max_dynamic_p = -1
        for neighbor in neighbors:
            if neighbor not in visited and G.nodes[neighbor]['dynamic_p'] > max_dynamic_p:
                next_node = neighbor
                max_dynamic_p = G.nodes[neighbor]['dynamic_p']

        if next_node is None:
            # 没有更多可遍历的节点，路径搜索失败
            path = nx.algorithms.shortest_paths.weighted.dijkstra_path(G, source, target)
            return path

        path.append(next_node)
        current_node = next_node
    if path == None:
        path = nx.algorithms.shortest_paths.weighted.dijkstra_path(G, source, target)

    return path


def get_attack(csv_file):
    network_data = pd.read_csv(csv_file)
    # 加载模型
    model = load('model.pkl')
    corrected_features = [
        ' Source Port', ' Destination Port', ' Protocol',
        ' Flow Duration', ' Total Fwd Packets', ' Total Backward Packets',
        'Total Length of Fwd Packets', ' Total Length of Bwd Packets',
        ' Fwd Packet Length Max', ' Fwd Packet Length Min',
        'Bwd Packet Length Max', ' Bwd Packet Length Min',
        'Flow Bytes/s', ' Flow Packets/s', ' Flow IAT Mean',
        ' Flow IAT Std', ' Flow IAT Max', ' Flow IAT Min'
    ]
    # 创建一个空的有向图
    network_graph = nx.DiGraph()

    # 根据'Gender'列的值进行分类，并按照数量排序
    sorted_counts = network_data[' Source IP'].value_counts().sort_values(ascending=False)
    sorted_counts = sorted_counts.head(30)

    filtered_df = network_data[network_data[' Source IP'].isin(sorted_counts.index)]
    filtered_df = filtered_df[filtered_df[' Destination IP'].isin(sorted_counts.index)]
    # filtered_df = filtered_df.sample(n=5000)

    # 数据预处理
    X_new = filtered_df[corrected_features]
    X_new = X_new.fillna(0)
    X_new.replace([np.inf, -np.inf], 0, inplace=True)  # 替换无穷值
    # 根据pcap预测攻击
    filtered_df['predicate'] = model.predict(X_new)

    for index, row in filtered_df.iterrows():
        source_ip = row[' Source IP']
        dest_ip = row[' Destination IP']
        if network_graph.has_edge(source_ip, dest_ip):
            network_graph[source_ip][dest_ip]['weight'] += 1
        else:
            network_graph.add_edge(source_ip, dest_ip, weight=1)

        # 正确设置节点的状态
        if row['predicate'] != 0:
            network_graph.nodes[dest_ip]['state'] = 1

    for node in network_graph.nodes:
        attac_p = 1
        network_graph.nodes[node]["attac_p"] = attac_p

    # 找到所有根节点
    root_nodes = [node for node in network_graph.nodes() if network_graph.in_degree(node) == 0]
    # 设置布局（使用Spring布局）
    layout = nx.spring_layout(network_graph)
    # # 打印根节点
    # print("根节点：", root_nodes)
    # 绘制图形
    nx.draw(network_graph, with_labels=True, node_color='lightblue', node_size=800, font_size=12, alpha=0.8)
    # 在图的中心位置标记根节点
    for root_node in root_nodes:
        x, y = layout[root_node]
        plt.text(x, y + 0.05, root_node, ha='center', va='bottom', color='red')
    # 保存图片
    plt.savefig(csv_file[:-3]+'png')
    # 计算权重
    total_weights = sum([network_graph[node][neighbor]['weight'] for node, neighbor in network_graph.out_edges()])
    for node, neighbor in network_graph.out_edges():
        network_graph[node][neighbor]['weight'] /= total_weights
    num_iterations = 1000
    damping_factor = 0.85
    current_probabilities = nx.get_node_attributes(network_graph, "attac_p")

    # 随机游走计算静态可概率
    for _ in range(num_iterations):
        new_probabilities = {}
        for node in network_graph.nodes:
            if "state" in network_graph[node]:
                new_probabilities[node] = 1
            # 计算每个节点根据邻居节点的概率而获得的概率
            new_probabilities[node] = (1 - damping_factor)
            for neighbor in network_graph.neighbors(node):
                edge_weight = network_graph[node][neighbor]['weight']
                new_probabilities[node] += damping_factor * current_probabilities[neighbor] * edge_weight

        current_probabilities = new_probabilities
    # 将静态可达概率保存在节点属性中
    nx.set_node_attributes(network_graph, current_probabilities, "static_reachability")

    network_graph = update_dynamic_reachability(network_graph)

    # 打印节点的动态可达概率
    # for node in network_graph.nodes(data=True):
    #     print(node)
    # 获取节点属性的最大值
    targets = sorted(network_graph.nodes(data=True), key=lambda x: x[1]['dynamic_p'], reverse=True)[1:4]
    source = [n for n, d in network_graph.nodes(data=True) if d['dynamic_p'] == 1]
    paths = []
    for target in targets:
        path = find_attack_path_by_dynamic_p(network_graph, source[0], target[0])
        path = '==>'.join(path)
        # print("根据动态可达概率的攻击路径:\n", path, "\n攻击发生概率:", target[1]['dynamic_p'])
        paths.append({'target':target[0],'path':path,'dynamic_p':target[1]['dynamic_p']})
    return paths,'.'+csv_file[41:-3]+'png'
if __name__ == '__main__':

    csv_file = 'Pcap_dataset/attackpredicaiton_data_5.csv'
    paths,img = get_attack(csv_file)
    print(paths)
    print(img)