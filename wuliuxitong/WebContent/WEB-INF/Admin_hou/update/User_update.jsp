<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<table align="left" border=1 width=1000px> 
 <tr>
 <td></td>
 <th width=120px align="right" ><a href="index.jsp" >退出 </a></th>
 </tr>
 <tr> 
 <td><table  border=1 height=350px>   
 <tr>
  <tr>
 <th width=120px align="center" ><a href="admin_user_message.action" >会员管理 </a></th>
 </tr>
 <tr>
 <th width=120px align="center" ><a href="user_goods_message.action" >货物信息管理 </a></th>
 </tr> 
 <tr>
 <th width=120px align="center" ><a href="user_company_message.action" >企业信息管理 </a></th>
 </tr>
 <tr>
 <th width=120px align="center" ><a href="user_car_message.action" >车辆信息管理 </a></th>
 </tr>
 <tr>
 <th width=120px align="center" ><a href="user_dynamic_info_message.action" >物流动态管理 </a></th>
 </tr>
 <tr>
 <th width=120px align="center" ><a href="user_logistics_knowledge_message.action" >物流知识管理 </a></th>
 </tr>
 <tr>
 <th width=120px align="center" ><a href="user_notice_message.action" >公告管理 </a></th>
 </tr>
 <tr>
 </table></td>
 <td>
  <%
    String user_update_message = (String)session.getAttribute("user_update_message");
    String [] ur_update = user_update_message.split(",");
 %> 
 <s:form action="admin_user_update" method="post"> 
 <table  border=1 width=800px  align="right"> 
 <tr><td width=100px></td><th width=120px align="center">修改会员信息</th><td></td></tr>  
 <tr><td align="center">用户名</td><td><input type="text" name="user.username" value=<%= ur_update[0] %>></td>    
 <td align="center">密码</td><td><input type="text" name="user.password" value=<%= ur_update[1] %>></td></tr> 
 <tr><td align="center">性别</td><td><input type="text" name="user.sex" value=<%= ur_update[2] %>></td>    
 <td align="center">联系方式</td><td><input type="text" name="user.phone" value=<%= ur_update[3] %>></td></tr> 
 <tr><td align="center">邮箱</td><td><input type="text" name="user.email" value=<%= ur_update[4] %>></td>
 <tr><td align="center">密码问题</td><td><input type="text" name="user.question" value=<%= ur_update[5] %>></td>  
 <tr><td align="center">问题答案</td><td><input type="text" name="user.answer" value=<%= ur_update[6] %>></td>  
 <tr><td><br><input type="hidden" name="user.id" value=<%= ur_update[7] %>></td></tr>    
 <tr><td><br></td></tr>
 <tr><td></td><td align="center"><input type="submit" value="提交"></td><td align="center"><input type="reset" value="重置"></td></tr>
 </table> 
 </s:form> 
 </td>
 </table>
</body>
</html>