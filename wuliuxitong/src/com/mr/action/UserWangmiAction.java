package com.mr.action;


 import java.util.List;

import org.hibernate.Query;

import com.mr.dao.UserDAO;
import com.mr.user.User;
import com.opensymphony.xwork2.ActionSupport;

 public class UserWangmiAction extends ActionSupport {
	 UserDAO userDAO;
 	 User user;
 	 String answer;
 	public String select() 
 	{
 		try 
 		{ 
 			if (user != null)
 			{
 				String hql = "from User user2 where user2.username='"+user.getUsername()+"'";
 				Query q = userDAO.getSession().createQuery(hql);
 				List <User> list = q.list();
 				if(list.isEmpty())
 				{
 					addFieldError("", "用户名不存在！");//返回错误提示信息
 					return "select_login";//返回后台登录页面
 				}
 				else
 				{		
 					for (User lt : list)
 					{
 						if(!lt.getQuestion().trim().equals(user.getQuestion().trim()))
 						{
 							addFieldError("", "密码问题错误！");//返回错误提示信息
 							return "select_login";
 						}
 						else if(!lt.getAnswer().trim().equals(user.getAnswer().trim()))
 						{
 							addFieldError("", "问题答案错误！");//返回错误提示信息
 							return "select_login";
 						}
 						answer = lt.getPassword();
 					}
 					   addFieldError("","您的正确密码为："+answer);
	 					return "select_login";
 				} 
 		   }
 			else
 			{
 				return "select_login";
 			}
 			
 		} 
 		catch (Exception e)
 		{
 			e.printStackTrace();
 			return "select_login";
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
 			if (user.getQuestion() == null || user.getQuestion().trim().equals("")) 
 			{
 				addFieldError("", "密码问题不能为空");
 			}
 			if (user.getAnswer() == null || user.getAnswer().trim().equals("")) 
 			{
 				addFieldError("", "问题答案不能为空");
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

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

 	
 }

