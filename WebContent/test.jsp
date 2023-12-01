<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.Part" %>
<%@ page import="java.util.Collection" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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

<%
    String message = (String) request.getAttribute("message");
    if (message != null) {
        out.println("<p>" + message + "</p>");
    }
%>

<form action="uploadfile.do" method="post" enctype="multipart/form-data">
    选择文件：<input type="file" name="file" size="50" /><br/>
    <input type="submit" value="上传文件" />
</form>

</body>
</html>