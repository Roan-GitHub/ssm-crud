package com.mr.action;


import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;

import com.mr.company.Company;
import com.mr.dao.UserDAO;
import com.mr.user.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

 public class CompanyAction extends ActionSupport {
	UserDAO userDAO;
	Company company;
	HttpServletRequest request = ServletActionContext.getRequest();
    public String show()
    {
    	    HttpServletRequest request = ServletActionContext.getRequest();
    		String company_hql = "from Company company where company.name='"+request.getParameter("companyname").trim()+"'";			
			Query company_q = userDAO.getSession().createQuery(company_hql);					
			List <Company> company_list = company_q.list();	
			String company_name = "";				
			if(!company_list.isEmpty()) 
			{
				for(Company cmp:company_list)
				{
					company_name = cmp.getName()+","+cmp.getAddress()+","+cmp.getPhone()+","+cmp.getLinkman()+","+cmp.getInfo();						
				}
			}
			ActionContext.getContext().getSession().put("company_name", company_name);			
			return "success";
    }
    
    public String message() 
    {
    		String company_hql = "from Company company";			
			Query company_q = userDAO.getSession().createQuery(company_hql);					
			List <Company> company_list = company_q.list();	
			String company_message = "";				
			if(!company_list.isEmpty()) 
			{
				for(Company cmp:company_list)
				{
					company_message += cmp.getId()+","+cmp.getName()+","+cmp.getAddress()+","+cmp.getPhone()+","+cmp.getLinkman()+","+cmp.getInfo()+";";
				}
			}
			ActionContext.getContext().getSession().put("company_message", company_message);			
			return "company_message";
    }
    
    public String insert()
    {
    	try
    	{
    		 userDAO.getSession().beginTransaction(); 
    		 userDAO.getSession().save(company);
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
		    int company_id = Integer.parseInt(request.getParameter("company_id"));
		    userDAO.getSession().beginTransaction();
		    Query q = userDAO.getSession().createQuery("delete from Company cmp where cmp.id="+company_id);
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
    	String company_hql = "from Company cm where cm.id="+Integer.parseInt(request.getParameter("company_id"));			
		Query company_q = userDAO.getSession().createQuery(company_hql);					
		List <Company> company_list = company_q.list();	
		String company_update_message = "";				
		if(!company_list.isEmpty()) 
		{
			for(Company cp:company_list)
			{
				company_update_message = cp.getName()+","+cp.getAddress()+","+cp.getPhone()+","+cp.getLinkman()+","+cp.getInfo()+","+cp.getId();
			}
		}
		ActionContext.getContext().getSession().put("company_update_message", company_update_message);
    	return "update_jsp";
    }
    
    public String update()
    {
       try
       { 
    	   String hql="update Company cmp set cmp.name=?,cmp.address=?,cmp.phone=?,cmp.linkman=?,cmp.info=? where cmp.id="+company.getId();
    	   Query query  = userDAO.getSession().createQuery(hql);  
    	   query.setString(0,company.getName());
    	   query.setString(1,company.getAddress());
    	   query.setString(2,company.getPhone());
    	   query.setString(3,company.getLinkman());
    	   query.setString(4,company.getInfo());
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
    
    public String company()
    {
    	return "company";
    }
    
	public Company getCompany() 
	{
		return company;
	}

	public void setCompany(Company company) 
	{
		this.company = company;
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

