<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>管理员登录信息</title>
</head>
<body>
	<s:form method="post" action="admin_denglu">
		<table>
			<tr>
				<td colspan="2" align="center">管理员登录</td>
			</tr>
			<tr>

			</tr>
			<tr>
				<td><s:textfield name="admin.username" label="用户名" id="username_id"></s:textfield></td>
			</tr>
			<tr>

				<td><s:password name="admin.password" label="密码" id="pwd1_id"></s:password></td>
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
