package com.mr.action;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.mr.dao.AdminDAO;
import com.mr.goods.Goods;
import com.mr.user.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import jdk.management.resource.internal.inst.SocketOutputStreamRMHooks;

 public class UserAction extends ActionSupport {
	 AdminDAO adminDAO;
	 User user;
	 HttpServletRequest request = ServletActionContext.getRequest();
    public String message() 
    {
    		String user_hql = "from User user";			
			Query user_q = adminDAO.getSession().createQuery(user_hql);					
			List <User> user_list = user_q.list();	
			String user_message = "";				
			if(!user_list.isEmpty()) 
			{
				for(User us:user_list)
				{
					user_message += us.getId()+","+us.getUsername()+","+us.getPassword()+","+us.getSex()+","
					+us.getPhone()+","+us.getEmail()+","+us.getQuestion()+","+us.getAnswer()+","+us.getRegister()+";";					
				}
			}
			ActionContext.getContext().getSession().put("user_message", user_message);			
			return "success";
    }
    
    public String delete()
    {
    	try
    	{
    		HttpServletRequest request = ServletActionContext.getRequest();
		    int user_id = Integer.parseInt(request.getParameter("user_id"));
		    adminDAO.getSession().beginTransaction();
		    Query q = adminDAO.getSession().createQuery("delete from User us where us.id="+user_id);
		    q.executeUpdate();
		    adminDAO.getSession().beginTransaction().commit();
 		    request.getSession().setAttribute("mess","<script language='javascript'>alert('É¾³ý³É¹¦£¡');</script>");
 		    return message();
    	}
    	catch(Exception e)
    	{
    		adminDAO.getSession().beginTransaction().rollback();
    		e.printStackTrace();
    		System.out.println("É¾³ýÊ§°Ü");
    		return message();
    	}
    	finally
    	{
    		adminDAO.getSession().close();
    	}
    }
    
    public String update_jsp()
    {
    	String user_hql = "from User us where us.id="+Integer.parseInt(request.getParameter("user_id"));			
		Query user_q = adminDAO.getSession().createQuery(user_hql);					
		List <User> user_list = user_q.list();	
		String user_update_message = "";				
		if(!user_list.isEmpty()) 
		{ 
			for(User ur:user_list)
			{
				user_update_message = ur.getUsername()+","+ur.getPassword()+","+ur.getSex()+","+ur.getPhone()+","
				+ur.getEmail()+","+ur.getQuestion()+","+ur.getAnswer()+","+ur.getId();
			}
		}
		ActionContext.getContext().getSession().put("user_update_message", user_update_message);
    	return "update_jsp";
    }
    
    public String update()
    {
       try
       { 
    	   String hql="update User us set us.username=?,us.password=?,us.sex=?,us.phone=?,us.email=?,us.question=?,us.answer=? where us.id="+user.getId();
    	   Query query  = adminDAO.getSession().createQuery(hql);  
    	   query.setString(0,user.getUsername());
    	   query.setString(1,user.getPassword());
    	   query.setString(2,user.getSex());
    	   query.setString(3,user.getPhone());
    	   query.setString(4,user.getEmail());
    	   query.setString(5,user.getQuestion());
    	   query.setString(6,user.getAnswer());
    	   query.executeUpdate();  
		   adminDAO.getSession().beginTransaction().commit();
		   System.out.println("ÐÞ¸Ä³É¹¦");
		   return message();
       }
       catch(Exception e)
   	  {
    	adminDAO.getSession().beginTransaction().rollback();
   		e.printStackTrace();
   		System.out.println("ÐÞ¸ÄÊ§°Ü");
   		return message();
   	  }
   		finally
   	 {
   	   adminDAO.getSession().close();
   	 }
    }
    
    public String index()
    {
    	return "index";
    }
    
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AdminDAO getAdminDAO()
	{
		return adminDAO;
	}
	public void setAdminDAO(AdminDAO adminDAO)
	{
		this.adminDAO = adminDAO;
	}
 

 }

