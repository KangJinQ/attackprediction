<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="styles/common03.css" />
<title>show the prediction result</title>
</head>
<body>
	<fieldset>
		<legend>结果详情✔</legend>
		<table class="listtable" style="align: center;" border="1px" width="100%">
			<tr class="listheader">
				<td width="15%" nowrap="nowrap"><div align="center">📆消息日期</div></td>
				<td width="15%" nowrap="nowrap"><div align="center">^.^记录名称</div></td>
				<td width="75%" nowrap="nowrap"><div align="center">📄用户ID</div></td>
				
			</tr>
			<tr>
				<td><div align="center">${pred.time }</div></td>
				<td><div align="center">${pred.predId }</div></td>
				<td><div align="center">${pred.userId }</div></td>
			</tr>
		</table>
		
		<table class="listtable" style="align: center;" border="1px" width="100%">
			<tr class="listheader">
				<td width="15%" nowrap="nowrap"><div align="center">目标节点🖥</div></td>
				<td width="15%" nowrap="nowrap"><div align="center">攻击概率📉</div></td>
				<td width="75%" nowrap="nowrap"><div align="center">攻击路径🎢</div></td>
				
			</tr>
			<tr>
				<td><div align="center">${predResult.target1 }</div></td>
				<td><div align="center">${predResult.pathPro1 }</div></td>
				<td><div align="center">${predResult.path1 }</div></td>
			</tr>
			<tr>
				<td><div align="center">${predResult.target2 }</div></td>
				<td><div align="center">${predResult.pathPro2 }</div></td>
				<td><div align="center">${predResult.path2 }</div></td>
			</tr>
			<tr>
				<td><div align="center">${predResult.target3 }</div></td>
				<td><div align="center">${predResult.pathPro3 }</div></td>
				<td><div align="center">${predResult.path3 }</div></td>
			</tr>
			<tr>
				<td colspan="3"><div align="center"><img src="${predResult.imgUrl }" 
					 alt="Your Image"></div></td>
			</tr>
		</table>
	</fieldset>
</body>
</html>