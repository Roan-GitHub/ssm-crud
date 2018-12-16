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
<s:property value="#session.mess" escape="false"/>
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
 <table  border=1 width=850px  align="center">
  <tr><th align="center"><a href="user_logistics_knowledge_insert_jsp.action">添加物流知识</a></th></tr>
 <tr><th>id</th> <th>标题</th> <th>发布时间</th> <th>备注</th> <th>编辑</th> <th>删除</th></tr> 
 <%
    String logistics_knowledge_message = (String)session.getAttribute("logistics_knowledge_message");
    String [] logistics_knowledge_row = logistics_knowledge_message.split(";");
    for(int i=0; i <logistics_knowledge_row.length; i++)
    {
    	String [] logistics_knowledge_count = logistics_knowledge_row[i].split(",");
    	out.print("<tr>");
    	for(int j=0; j < logistics_knowledge_count.length; j++)
    	{
    		out.print("<td>"+logistics_knowledge_count[j]+"</td>");
    	}
    	out.print("<td><a href='user_logistics_knowledge_update_jsp?logistics_knowledge_id="+logistics_knowledge_count[0]+"'>编辑</a></td>"+"<td><a href='user_logistics_knowledge_delete?logistics_knowledge_id="+logistics_knowledge_count[0]+"'>删除</a></td>"+"</tr>");
    }
 %>
 <%
   session.setAttribute("mess", "");
 %>
 </table> 
 <table border=1 height=130px width=850px> 
 <tr><td></td></tr>
 </table> 
 </table>
</body>
</html>