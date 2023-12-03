<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="styles/common03.css" />
<title>MailBox</title>
</head>
	
<body>
		
		<script>
        function checkInputs() {
            // 获取三个输入框的值
            var input1Value = document.getElementById('input1').value;
            var input2Value = document.getElementById('input2').value;
            var input3Value = document.getElementById('input3').value;

            // 获取提交按钮
            var submitButton = document.getElementById('submitButton');

            // 检查输入框的值是否都不为空，如果是则启用提交按钮，否则禁用
            if (input1Value !== '' && input2Value !== '' && input3Value !== '') {
                submitButton.disabled = false;
            } else {
                submitButton.disabled = true;
            }
        }
    </script>


		<!--注册表单-->
		<form id="registerForm" action="writemessage.do" method="post">
			<input type="hidden" name="action" value="register">
			<fieldset>
				<legend>写信📫</legend>
				<table border="0" align="center" class="page-content">
	
					<tr>
						<td><label for="target_uid">收件人:</label></td>
						<td><input id="input1" oninput="checkInputs()" type="text" style="width: 200px;"id="target_uid" name="target_uid" placeholder="请输入收件人ID"
							onblur="checkUserName()"></td>
					</tr>
					<tr>
						<td><label for="topic">主题:</label></td>
						<td><input id="input2" oninput="checkInputs()" type="text" style="width: 200px;"id="topic" name="topic" placeholder="请输入主题"
							onblur="checkPassword()"></td>
					</tr>
					<tr>
						<td class="td_left"><label for="content">正文:</label></td>
						<td class="td_right"><input id="input3" oninput="checkInputs()" style="width: 600px; height: 90px;" type="text" id="content" name="content"
							placeholder="请输入正文"></td>
					</tr>
					<tr>
					<td><input type="submit" class="submit" value="发送" id="submitButton" onclick="checkPassword()" disabled></td>
					</tr>
				
				</table>
			</fieldset>
		</form>
		<%
			String msg = (String) request.getAttribute("msg");
		%>
		<!-- 如果不为空才显示,为空就什么也不显示 -->
		<%
			if (msg != null && msg != "") {
		%>
	
		<style>
	div {
		text-align: center
	}
	</style>
		<div>
			<font style="color: red">提示信息:</font> <font color='red'> <%=msg%>
			</font>
		</div>
	
		<%
			}
		%>



</body>
</html>