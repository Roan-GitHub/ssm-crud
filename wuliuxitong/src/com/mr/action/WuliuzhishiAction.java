 package com.mr.action;
 import java.util.Date;

import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 import org.apache.struts2.ServletActionContext;
 import org.hibernate.Query;
 import com.mr.dao.UserDAO;
import com.mr.dynamic_info.Dynamic_info;
import com.mr.logistics_knowledge.Logistics_knowledge;
import com.opensymphony.xwork2.ActionContext;
 import com.opensymphony.xwork2.ActionSupport;

  public class WuliuzhishiAction extends ActionSupport {
 	 UserDAO userDAO;
 	Logistics_knowledge logistics_knowledge;
 	HttpServletRequest request = ServletActionContext.getRequest();
     public String show()
     {
     	    HttpServletRequest request = ServletActionContext.getRequest();
     		String logistics_knowledge_hql = "from Logistics_knowledge lg where lg.title='"+request.getParameter("knowtitle").trim()+"'";			
 			Query logistics_knowledge_q = userDAO.getSession().createQuery(logistics_knowledge_hql);					
 			List <Logistics_knowledge> logistics_knowledge_list = logistics_knowledge_q.list();	
 			String know_title = "";				
 			if(!logistics_knowledge_list.isEmpty()) 
 			{
 				for(Logistics_knowledge know:logistics_knowledge_list)
 				{
 					know_title = know.getTitle()+","+know.getDate()+","+know.getInfo();				
 				}
 			}		
 			ActionContext.getContext().getSession().put("know_title", know_title);			
 			return "success";
     }

     public String message() 
     {
     		String logistics_knowledge_hql = "from Logistics_knowledge logistics_knowledge";			
 			Query logistics_knowledge_q = userDAO.getSession().createQuery(logistics_knowledge_hql);					
 			List <Logistics_knowledge> logistics_knowledge_list = logistics_knowledge_q.list();	
 			String logistics_knowledge_message = "";				
 			if(!logistics_knowledge_list.isEmpty()) 
 			{
 				for(Logistics_knowledge lgt:logistics_knowledge_list)
 				{
 					logistics_knowledge_message += lgt.getId()+","+lgt.getTitle()+","+lgt.getDate()+","+lgt.getInfo()+";";
 				}
 			}
 			ActionContext.getContext().getSession().put("logistics_knowledge_message", logistics_knowledge_message);			
 			return "logistics_knowledge_message";
     }
     
     public String insert()
     {
     	try
     	{
     		   Date date = new Date();
     		   logistics_knowledge.setDate(date);
     		   userDAO.getSession().beginTransaction(); 
     		   userDAO.getSession().save(logistics_knowledge);
     		   userDAO.getSession().beginTransaction().commit();
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
 		    int logistics_knowledge_id = Integer.parseInt(request.getParameter("logistics_knowledge_id"));
 		    userDAO.getSession().beginTransaction();
 		    Query q = userDAO.getSession().createQuery("delete from Logistics_knowledge lg where lg.id="+logistics_knowledge_id);
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
     	String logistics_knowledge_hql = "from Logistics_knowledge lo where lo.id="+Integer.parseInt(request.getParameter("logistics_knowledge_id"));			
 		Query logistics_knowledge_q = userDAO.getSession().createQuery(logistics_knowledge_hql);					
 		List <Logistics_knowledge> logistics_knowledge_list = logistics_knowledge_q.list();	
 		String logistics_knowledge_update_message = "";				
 		if(!logistics_knowledge_list.isEmpty()) 
 		{
 			for(Logistics_knowledge lgt:logistics_knowledge_list)
 			{
 				logistics_knowledge_update_message = lgt.getTitle()+","+lgt.getInfo()+","+lgt.getId();
 			}
 		}
 		ActionContext.getContext().getSession().put("logistics_knowledge_update_message", logistics_knowledge_update_message);
     	return "update_jsp";
     }
     
     public String update()
     {
        try
        { 
     	   String hql="update Logistics_knowledge lgk set lgk.title=?,lgk.info=? where lgk.id="+logistics_knowledge.getId();
     	   Query query  = userDAO.getSession().createQuery(hql);  
     	   query.setString(0,logistics_knowledge.getTitle());
     	   query.setString(1,logistics_knowledge.getInfo());
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
     public Logistics_knowledge getLogistics_knowledge()
     {
		return logistics_knowledge;
	}

	public void setLogistics_knowledge(Logistics_knowledge logistics_knowledge) 
	{
		this.logistics_knowledge = logistics_knowledge;
	}

	public String zhishi()
     {
    	 return "zhishi";
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


