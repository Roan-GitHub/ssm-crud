<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>找回密码信息</title>
</head>
<body>
	<s:form method="post" action="user_select">
		<table>
			<tr>
				<td colspan="2" align="center">找回密码</td>
			</tr>
			<tr>

			</tr>
			<tr>
				<td><s:textfield name="user.username" label="请输入找回的用户名" id="username_id"></s:textfield></td>
			</tr>
		    <tr>
				<td><s:textfield name="user.question" label="密码问题" id="username_id"></s:textfield></td>
			</tr>
			  <tr>
				<td><s:textfield name="user.answer" label="问题答案" id="username_id"></s:textfield></td>
			</tr>
			<tr>
				<s:submit value="找回密码"></s:submit>
			</tr>
		</table>
	</s:form>
	<div style="color: red;">
			<s:fielderror />
	</div>
</body>
</html>
