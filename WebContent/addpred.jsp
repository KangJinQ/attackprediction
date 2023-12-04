<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Part" %>
<%@ page import="java.util.Collection" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加比赛</title>
<link rel="stylesheet" href="styles/common.css" />
</head>
<body>

<%@ page import="java.io.File" %>
<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.FileUploadException" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<!-- <%@ page import="org.apache.commons.io.IOUtils" %> -->
<%@ page import="java.io.InputStream" %>

	<!--注册表单-->
	<form action="uploadfile.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="action" value="register">
		<fieldset>
			<legend>攻击路径预测😈</legend>
	    				
	    				<!-- <input type="submit" value="上传文件" /> -->
					<!-- </form> -->
			<!-- <form id="addpredForm" action="uploadfile.do" method="post"> -->
		
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
					<td><label for="f" class="xrequired">选择文件：</label> 
						<input type="file" name="f" id="f" size="50" /><br/>
					</td>
				
				</tr>

				<tr>
					<td><input type="submit" class="submit" value="开始推理攻击路径🛏"></td>
					<td><button onclick="window.history.back()">返回◀</button></td>
				</tr>
			</table>
		</fieldset>
	</form>
	
 	<c:if test="${not empty msg}">
        <script>
            alert("${msg}");
        </script>
    </c:if>
    
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