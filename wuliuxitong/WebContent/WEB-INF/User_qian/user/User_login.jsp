<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
<title>会员登录信息</title>
</head>
<body>
	<s:form method="post" action="user_login_yanzheng">
		<table>
			<tr>
				<td colspan="2" align="center">会员登录</td>
			</tr>
			<tr>

			</tr>
			<tr>
				<td><s:textfield name="user.username" label="用户名" id="username_id"></s:textfield></td>
			</tr>
			<tr>

				<td><s:password name="user.password" label="密码" id="pwd1_id"></s:password></td>
			</tr>
			<tr>
				<s:submit value="登录"></s:submit>
			</tr>
		</table>
	</s:form>
	<div style="color: red;">
			<s:fielderror />
	</div>
</body>
</html>
