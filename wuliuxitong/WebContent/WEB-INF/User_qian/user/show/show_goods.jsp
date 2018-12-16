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
 <table align="center" border=1>
 <tr>
 <th width=120px><a href="user_login_show.action">本站首页</a></th>
 <th width=120px><a href="user_dynamic_info_dongtai.action">物流动态</a></th>
 <th width=120px><a href="user_logistics_knowledge_zhishi.action">物流知识</a></th>
 <th width=120px><a href="user_goods_goods.action">货物信息</a></th>
 <th width=120px><a href="user_car_car.action">车辆信息</a></th>
 <th width=120px><a href="user_company_company.action">企业信息</a></th>
 <th width=120px><a href="user_rukou.action">退出登录</a></th>
 </tr>
 </table>
  <div style="color: red;">
			<s:fielderror />
	</div>
 <s:form action="user_shouye_admin" method="post">
 <table align="left" border=1>
 <tr>
 <td></td>
 <td>管理员登录：</td></tr>
 <tr>
 <td>用户名：</td>
 <td>
 <input type="text" name="admin.username" size=19></td></tr>
 <tr>
 <td>密码：</td>
 <td><input type="password" name="admin.password"></td></tr>
 <tr>
 <td></td>
 <td><input type="submit" value="登录"></td><tr>
 <tr><td><br></td></tr>
 <tr>
 <td><a href="user_Insert.action">会员注册</a></td>
 <td><a href="user_select">找回密码</a></td>
 </tr>
 </table>
 </s:form>
 
  <table align="right" border=1>
 <tr>
 <th width=180px height=120px >公告</th>
 </tr>
  <% 
    String noticetitle = (String)session.getAttribute("notice");
    String [] noti = noticetitle.split(",");
    for(int i=0; i<noti.length; i++)
    {
      out.print("<tr><td><a href='user_notice_show?noticetitle="+noti[i]+"'>"+noti[i]+"</a></td></tr>");
    }
 %> 
 </table>

<table align="center" border=1 width=700px height=300px>
<tr><td><br></td></tr>
<%
   String goods = (String)session.getAttribute("goods_name");
   String [] gd = goods.split(","); 
%>
  <tr align="center"> 
    <th>货物类型</th>
    <td><%= gd[0] %></td>
    <th>货物名称</th>
    <td><%= gd[1] %></td>
  </tr>
  <tr align="center"> 
    <th>货物数量</th>
    <td><%= gd[2] %></td>
    <th>货物起始地址</th>
    <td><%= gd[3] %></td>
  </tr>
  <tr align="center"> 
    <th>货物最终地址</th>
    <td><%= gd[4] %></td>
    <th>货物运送开始时间</th>
    <td><%= gd[5] %></td>
  </tr>
  <tr align="center"> 
    <th>货物运输类型</th>
    <td><%= gd[6] %></td>
    <th>运输联系人</th>
    <td><%= gd[7] %></td>
  </tr>
  <tr align="center"> 
    <th>联系人手机号</th>
    <td><%= gd[8] %></td>
    <th>发布时间</th>
    <td><%= gd[9] %></td>
  </tr> 
  <tr align="center"> 
    <th>信息</th>
    <td><%= gd[10] %></td>
    <th>发布人</th>
    <td><%= gd[11] %></td>
  </tr>
</table>
</body>
</html>