import random
import time

import requests
from flask import Flask, request

from getFromXML import savedb
from model import get_attack

app = Flask(__name__)


@app.route('/jsprequest', methods=['POST'])
def handle_request():
    url = request.form.get('url')  # 获取名为 'name' 的参数值
    print(url)
    # 处理参数数据...
    paths, img = get_attack(url)
    resultid = generate_unique_id()
    savedb(resultid, img, paths[0]['dynamic_p'], paths[0]['target'], paths[0]['path'], paths[1]['dynamic_p'],
           paths[1]['target'], paths[1]['path'], paths[2]['dynamic_p'], paths[2]['target'], paths[2]['path'])
    print(paths[0]['dynamic_p'])
    print(paths[0]['path'])

    # pyPost(paths,img,'http://localhost:8080/login.jsp')
    # print(img)
    # print(paths)
    return resultid


def pyPost(paths, img, url):
    # 定义请求的 URL
    url = url
    # 定义请求的参数
    data = {
        "paths": paths,
        "img": img
    }
    # 发起 POST 请求
    response = requests.post(url, data=data)
    # 获取响应内容
    content = response.text
    # 打印响应内容
    print(content)


def generate_unique_id():
    timestamp = int(time.time())
    unique_id = str(timestamp)[-8:]
    return unique_id


if __name__ == '__main__':
    app.run(host='127.0.0.1', port=8000)
