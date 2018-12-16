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
 					addFieldError("", "�û��������ڣ�");//���ش�����ʾ��Ϣ
 					return "select_login";//���غ�̨��¼ҳ��
 				}
 				else
 				{		
 					for (User lt : list)
 					{
 						if(!lt.getQuestion().trim().equals(user.getQuestion().trim()))
 						{
 							addFieldError("", "�����������");//���ش�����ʾ��Ϣ
 							return "select_login";
 						}
 						else if(!lt.getAnswer().trim().equals(user.getAnswer().trim()))
 						{
 							addFieldError("", "����𰸴���");//���ش�����ʾ��Ϣ
 							return "select_login";
 						}
 						answer = lt.getPassword();
 					}
 					   addFieldError("","������ȷ����Ϊ��"+answer);
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
			//System.out.println("userΪ��");
		} 
		else 
		{
			if (user.getUsername() == null || user.getUsername().trim().equals("")) 
 			{
 				addFieldError("", "�û�������Ϊ��");
 			}
 			if (user.getQuestion() == null || user.getQuestion().trim().equals("")) 
 			{
 				addFieldError("", "�������ⲻ��Ϊ��");
 			}
 			if (user.getAnswer() == null || user.getAnswer().trim().equals("")) 
 			{
 				addFieldError("", "����𰸲���Ϊ��");
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

