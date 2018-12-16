package com.mr.action;
import java.util.Date;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.mr.company.Company;
import com.mr.dao.UserDAO;
import com.mr.notice.Notice;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

 public class NoticeAction extends ActionSupport {
	UserDAO userDAO;
	Notice notice;
	HttpServletRequest request = ServletActionContext.getRequest();
    public String show()
    {
    	    HttpServletRequest request = ServletActionContext.getRequest();
    		String notice_hql = "from Notice notice where notice.title='"+request.getParameter("noticetitle").trim()+"'";			
			Query notice_q = userDAO.getSession().createQuery(notice_hql);					
			List <Notice> notice_list = notice_q.list();	
			String notice_title = "";				
			if(!notice_list.isEmpty()) 
			{
				for(Notice noti:notice_list)
				{
					notice_title = noti.getTitle()+","+noti.getDate()+","+noti.getInfo();			
				}
			}
			ActionContext.getContext().getSession().put("notice_title", notice_title);			
			return "success";
    }
    
    public String message() 
    {
    		String notice_hql = "from Notice notice";			
			Query notice_q = userDAO.getSession().createQuery(notice_hql);					
			List <Notice> notice_list = notice_q.list();	
			String notice_message = "";				
			if(!notice_list.isEmpty()) 
			{
				for(Notice nti:notice_list)
				{
					notice_message += nti.getId()+","+nti.getTitle()+","+nti.getDate()+","+nti.getInfo()+";";
				}
			}
			ActionContext.getContext().getSession().put("notice_message", notice_message);			
			return "notice_message";
    }
    
    public String insert()
    {
    	try
    	{
    		   Date date = new Date();
    		   notice.setDate(date);
    		   userDAO.getSession().beginTransaction(); 
    		   userDAO.getSession().save(notice);
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
		    int notice_id = Integer.parseInt(request.getParameter("notice_id"));
		    userDAO.getSession().beginTransaction();
		    Query q = userDAO.getSession().createQuery("delete from Notice nt where nt.id="+notice_id);
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
    	String notice_hql = "from Notice n where n.id="+Integer.parseInt(request.getParameter("notice_id"));			
		Query notice_q = userDAO.getSession().createQuery(notice_hql);					
		List <Notice> notice_list = notice_q.list();	
		String notice_update_message = "";				
		if(!notice_list.isEmpty()) 
		{
			for(Notice no:notice_list)
			{
				notice_update_message = no.getTitle()+","+no.getInfo()+","+no.getId();
			}
		}
		ActionContext.getContext().getSession().put("notice_update_message", notice_update_message);
    	return "update_jsp";
    }
    
    public String update()
    {
       try
       { 
    	   String hql="update Notice nt set nt.title=?,nt.info=? where nt.id="+notice.getId();
    	   Query query  = userDAO.getSession().createQuery(hql);  
    	   query.setString(0,notice.getTitle());
    	   query.setString(1,notice.getInfo());
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
	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
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

