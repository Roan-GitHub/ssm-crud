package com.mr.action;

import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Transaction;

import com.mr.dao.UserDAO;
import com.mr.user.User;
import com.opensymphony.xwork2.ActionSupport;

public class UserInsertAction extends ActionSupport {
	UserDAO userDAO;
	User user;
	String repassword;

	public String Insert() 
	{
		try 
		{
			if (user != null)
			{
			   Date date = new Date();
			   user.setRegister(date);
			   String hql = "from User user2 where user2.username='"+user.getUsername()+"'";
			   Query q = userDAO.getSession().createQuery(hql);
			   List <User> list = q.list();
			   if(list.isEmpty()) 
				{
				   Transaction transaction = userDAO.getSession().beginTransaction();
				   userDAO.getSession().save(user);
				   transaction.commit();
				   return "success";
				}
			   else
			   {
				   addFieldError("", "该用户名已存在！");
				   return "user_login";
			   }
			} 
			else
			{
				return "user_login";
			}
			
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			userDAO.getSession().beginTransaction().rollback();
			return "user_login";
		}
		finally
    	{
    	 userDAO.getSession().close();
    	}

	}

	public void validate() 
	{
		if (user == null) 
		{
			//System.out.println("user为空");
		} 
		else 
		{
			if (user.getUsername() == null || user.getUsername().trim().equals("")) 
			{
				addFieldError("", "用户名不能为空");
			}
			if (user.getPassword() == null || user.getPassword().trim().equals("")) 
			{
				addFieldError("", "密码不能为空");
			}
			if (user.getSex() == null || user.getSex().trim().equals("")) 
			{
				addFieldError("", "性别不能为空");
			}
			if (user.getEmail() == null || user.getEmail().trim().equals("")) 
			{
				addFieldError("", "邮箱不能为空");
			}
			if (user.getPhone() == null || user.getPhone().trim().equals(""))
			{
				addFieldError("", "电话不能为空");
			}
			if (!user.getPassword().equals(repassword)) 
			{
				addFieldError("", "两次密码输入不一致");
			}
			String regex = "(13[0-9]|(15[012356789])|18[056789])[0-9]{8}";
			if (!user.getPhone().matches(regex))
			{
				addFieldError("", "请输入有效电话号码");
			}
			regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
			if (!user.getEmail().matches(regex))
			{
				addFieldError("", "邮箱格式不正确");
			}
	  }
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRepassword() {
		return repassword;
	}

	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}

}
