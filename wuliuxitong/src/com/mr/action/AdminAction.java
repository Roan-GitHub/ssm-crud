package com.mr.action;


 import java.util.List;


import org.hibernate.Query;

import com.mr.admin.Admin;
import com.mr.dao.AdminDAO;
import com.opensymphony.xwork2.ActionSupport;

 public class AdminAction extends ActionSupport {
 	AdminDAO adminDAO;
 	Admin admin;
 	public String denglu() 
 	{
 		try 
 		{ 
 			if (admin != null)
 			{
 				String hql = "from Admin admin2 where admin2.username='"+admin.getUsername()+"' and admin2.password='"+admin.getPassword()+"'";
 				Query q = adminDAO.getSession().createQuery(hql);
 				List <Admin> list = q.list();
 				if(!list.isEmpty()) 
 				{
 					//adminDAO.getSession().save("admin", admin);//将管理员信息保存在Session对象中
 					return "success";
 				}
 				else
 				{
 					addFieldError("", "用户名或密码不正确！");//返回错误提示信息
 					return "admin_login";//返回后台登录页面
 				}
 			} 
 			else
 			{
 				return "admin_login";
 			}
 			
 		} 
 		catch (Exception e)
 		{
 			e.printStackTrace();
 			return "admin_login";
 		}
 	}

 	public void validate() 
 	{
 		if (admin == null) 
		{
			//System.out.println("admin为空");
		} 
		else 
		{
			if (admin.getUsername() == null || admin.getUsername().trim().equals("")) 
 			{
 				addFieldError("", "用户名不能为空");
 			}
 			if (admin.getPassword() == null || admin.getPassword().trim().equals("")) 
 			{
 				addFieldError("", "密码不能为空");
 			}
		}
 	}

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
 	
 }

