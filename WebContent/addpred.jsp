<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加比赛</title>
<link rel="stylesheet" href="styles/common.css" />
</head>
<body>
	<!--注册表单-->
	<form id="addmatchForm" action="addmatch.do" method="post">
		<input type="hidden" name="action" value="register">
		<fieldset>
			<legend>攻击路径预测😈</legend>
			<table align="center" class="page-content">
				<%
					String msg = (String) request.getAttribute("msg");
				%>
				<!-- 如果不为空才显示,为空就什么也不显示 -->
				<%
					if (msg != null) {
				%>
				<tr>
					<td>提示信息:</td>
					<td><font color='black'> <%=msg%>
					</font></td>
				</tr>
				<%
					}
				%>
				<tr>
					<td><label for="predname" class="xrequired">预测名称:</label></td>
					<td><input type="text" id="predname" name="predname"
						placeholder="请输入预测名字" onblur="checkUserName()"></td>
				</tr>
				<tr>
					<td><label class="xrequired">请选择预测类型：</label></td>
					<td><input type="radio" name="predtype" value="0">公有比赛<input
						type="radio" name="predtype" value="1">私有比赛</td>
				</tr>
				<!--
				<tr>
					<td><label class="xrequired">比赛赛制:</label></td>
					<td><select id="matchsystem" name="matchsystem"
						aria-placeholder="--请选择--">
							<option checked>--请选择--</option>
							<option value="联赛">联赛</option>
							<option value="杯赛">杯赛</option>
							<option value="淘汰赛">淘汰赛</option>
							<option value="锦标赛">锦标赛</option>
							<option value="团体赛">团体赛</option>
					</select></td>
				</tr>
				<tr>
					<td><label class="xrequired">比赛开始时间:</label></td>
					<td><input type="date" id="matchstarttime" min="2021-01-01"
						name="matchstarttime" required></td>
				</tr>
				<tr>
					<td><label class="xrequired">比赛结束时间:</label></td>
					<td><input type="date" id="matchendtime" min="2021-05-26"
						name="matchendtime" placeholder="xxxx" required></td>
				</tr>
				<tr>
					<td><label class="xrequired">人数/队伍上限:</label></td>
					<td><input type="text" id="matchnumber" name="matchnumber"
						placeholder="人数/队伍上限"></td>
				</tr>
				<tr>
					<td>比赛介绍:</td>
					<td><textarea id="matchinfo" name="matchinfo"
							placeholder="请输入比赛的相关介绍"></textarea></td>
				</tr>
				-->

				<tr>
					<td><input type="submit" class="submit" value="创建比赛🛏😁"></td>
					<td><button onclick="window.history.back()">返回◀</button></td>
				</tr>
			</table>
		</fieldset>
	</form>
	<script type="text/javascript">
		window.onload = function() {
			//得到当前时间
			var date_now = new Date();
			//得到当前年份
			var year = date_now.getFullYear();
			//得到当前月份
			//注：
			//  1：js中获取Date中的month时，会比当前月份少一个月，所以这里需要先加一
			//  2: 判断当前月份是否小于10，如果小于，那么就在月份的前面加一个 '0' ， 如果大于，就显示当前月份
			var month = date_now.getMonth() + 1 < 10 ? "0"
					+ (date_now.getMonth() + 1) : (date_now.getMonth() + 1);
			//得到当前日子（多少号）
			var date = date_now.getDate() < 10 ? "0" + date_now.getDate()
					: date_now.getDate();
			//设置input标签的max属性
			// 			$("matchstarttime").attr("min", year + "-" + month + "-" + date);
			// 			$("matchendtime").attr("min", year + "-" + month + "-" + date);
			document.getElementById("matchstarttime").setAttribute("min",
					year + "-" + month + "-" + date);
			document.getElementById("matchendtime").setAttribute("min",
					year + "-" + month + "-" + date);
		}
	</script>
</body>
</html>