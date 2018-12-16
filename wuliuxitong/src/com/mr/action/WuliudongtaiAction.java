 package com.mr.action;
 import java.util.Date;
import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.struts2.ServletActionContext;
 import org.hibernate.Query;

import com.mr.car.Car;
import com.mr.company.Company;
import com.mr.dao.UserDAO;
import com.mr.dynamic_info.Dynamic_info;
import com.opensymphony.xwork2.ActionContext;
 import com.opensymphony.xwork2.ActionSupport;

  public class WuliudongtaiAction extends ActionSupport {
 	 UserDAO userDAO;
 	 Dynamic_info dynamic_info;
 	 HttpServletRequest request = ServletActionContext.getRequest();
     public String show()
     {
     	    HttpServletRequest request = ServletActionContext.getRequest();
     		String dynamic_info_hql = "from Dynamic_info dynamic_info where dynamic_info.title='"+request.getParameter("dynamictitle").trim()+"'";			
     		Query dynamic_info_q = userDAO.getSession().createQuery(dynamic_info_hql);					
 			List <Dynamic_info> dynamic_info_list = dynamic_info_q.list();	
 			String dynamic_title = "";				
 			if(!dynamic_info_list.isEmpty()) 
 			{
 				for(Dynamic_info dyna:dynamic_info_list)
 				{
 					dynamic_title = dyna.getTitle()+","+dyna.getDate()+","+dyna.getInfo();				
 				}
 			}		
 			ActionContext.getContext().getSession().put("dynamic_title", dynamic_title);
 			return "success";
     }
     
     public String message() 
     {
     		String dynamic_info_hql = "from Dynamic_info dynamic_info";			
 			Query dynamic_info_q = userDAO.getSession().createQuery(dynamic_info_hql);					
 			List <Dynamic_info> dynamic_info_list = dynamic_info_q.list();	
 			String dynamic_info_message = "";				
 			if(!dynamic_info_list.isEmpty()) 
 			{
 				for(Dynamic_info dym:dynamic_info_list)
 				{
 					dynamic_info_message += dym.getId()+","+dym.getTitle()+","+dym.getDate()+","+dym.getInfo()+";";
 				}
 			}
 			ActionContext.getContext().getSession().put("dynamic_info_message", dynamic_info_message);			
 			return "dynamic_info_message";
     }
     
     public String insert()
     {
     	try
     	{
     		   Date date = new Date();
     		   dynamic_info.setDate(date);
     		   userDAO.getSession().beginTransaction(); 
     		   userDAO.getSession().save(dynamic_info);
     		   System.out.println("添加成功");
     		   return "input";
     	}
     	catch(Exception e)
     	{
     		userDAO.getSession().beginTransaction().rollback();
     		System.out.println("数据添加失败");
     		return "input";
     	}
     	finally
     	{
     	userDAO.getSession().close();
     	}
     }
     
     public String delete()
     {
     	try
     	{
 		    int dynamic_info_id = Integer.parseInt(request.getParameter("dynamic_info_id"));
 		    userDAO.getSession().beginTransaction();
 		    Query q = userDAO.getSession().createQuery("delete from Dynamic_info dy where dy.id="+dynamic_info_id);
 		    q.executeUpdate();
  		    userDAO.getSession().beginTransaction().commit();
  		    request.getSession().setAttribute("mess","<script language='javascript'>alert('删除成功！');</script>");
  		    return message();
     	}
     	catch(Exception e)
     	{
     		userDAO.getSession().beginTransaction().rollback();
     		e.printStackTrace();
     		System.out.println("删除失败");
     		return message();
     	}
     	finally
     	{
     		userDAO.getSession().close();
     	}
     }
     
     public String update_jsp()
     {
     	String dynamic_info_hql = "from Dynamic_info d where d.id="+Integer.parseInt(request.getParameter("dynamic_info_id"));			
 		Query dynamic_info_q = userDAO.getSession().createQuery(dynamic_info_hql);					
 		List <Dynamic_info> dynamic_info_list = dynamic_info_q.list();	
 		String dynamic_info_update_message = "";				
 		if(!dynamic_info_list.isEmpty()) 
 		{
 			for(Dynamic_info dy:dynamic_info_list)
 			{
 				dynamic_info_update_message = dy.getTitle()+","+dy.getInfo()+","+dy.getId();
 			}
 		}
 		ActionContext.getContext().getSession().put("dynamic_info_update_message", dynamic_info_update_message);
     	return "update_jsp"; 
     }
     
     public String update()
     {
        try
        { 
     	   String hql="update Dynamic_info dym set dym.title=?,dym.info=? where dym.id="+dynamic_info.getId();
     	   Query query  = userDAO.getSession().createQuery(hql);  
     	   query.setString(0,dynamic_info.getTitle());
     	   query.setString(1,dynamic_info.getInfo());
     	   query.executeUpdate();  
     	   userDAO.getSession().beginTransaction().commit();
 		   System.out.println("修改成功");
 		   return message();
        }
        catch(Exception e)
    	  {
     	        userDAO.getSession().beginTransaction().rollback();
    			e.printStackTrace();
    			System.out.println("修改失败");
    			return message();
    	  }
    		finally
    	 {
    			userDAO.getSession().close();
    	 }
     }
     
     public String insert_jsp()
     {
     	return "insert_jsp";
     }
     public Dynamic_info getDynamic_info() 
     {
		return dynamic_info;
     }

	public void setDynamic_info(Dynamic_info dynamic_info)
	{
		this.dynamic_info = dynamic_info;
	}

	public String dongtai()
     {
    	 return "dongtai";
     }

 	public UserDAO getUserDAO()
 	{
 		return userDAO;
 	}

 	public void setUserDAO(UserDAO userDAO) 
 	{
 		this.userDAO = userDAO;
 	}

  }


