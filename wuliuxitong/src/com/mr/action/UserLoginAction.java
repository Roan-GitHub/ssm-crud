package com.mr.action;


 import java.util.List;


import org.hibernate.Query;

import com.mr.car.Car;
import com.mr.company.Company;
import com.mr.dao.AdminDAO;
import com.mr.dao.UserDAO;
import com.mr.dynamic_info.Dynamic_info;
import com.mr.goods.Goods;
import com.mr.logistics_knowledge.Logistics_knowledge;
import com.mr.notice.Notice;
import com.mr.user.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

 public class UserLoginAction extends ActionSupport {
	 UserDAO userDAO;
 	 User user;
 	public String yanzheng() 
 	{
 		try 
 		{ 
 			if (user != null) 
 			{
 				String hql = "from User user2 where user2.username='"+user.getUsername()+"' and user2.password='"+user.getPassword()+"'";
 				Query q = userDAO.getSession().createQuery(hql);
 				List <User> list = q.list();
 				if(!list.isEmpty()) 
 				{
 				    return show();
 				}
 				else
 				{
 					addFieldError("", "用户名或密码不正确！");//返回错误提示信息
 					return "user_login";//返回后台登录页面
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
 			return "user_login";
 		}

 	}
 	
    public String show()
    {
    		String goods_hql = "from Goods goods";
			String company_hql = "from Company company";
			String car_hql = "from Car car";
			String notice_hql = "from Notice notice";
			String dynamic_info_hql = "from Dynamic_info dynamic_info";
			String logistics_knowledge_hql = "from Logistics_knowledge logistics_knowledge";
				
			Query goods_q = userDAO.getSession().createQuery(goods_hql);
			Query company_q = userDAO.getSession().createQuery(company_hql);
			Query car_q = userDAO.getSession().createQuery(car_hql);
			Query notice_q = userDAO.getSession().createQuery(notice_hql);
			Query dynamic_info_q = userDAO.getSession().createQuery(dynamic_info_hql);
			Query logistics_knowledge_q = userDAO.getSession().createQuery(logistics_knowledge_hql);
			
			List <Goods> goods_list = goods_q.list();
			List <Company> company_list = company_q.list();
			List <Car> car_list = car_q.list();
			List <Notice> notice_list = notice_q.list();
			List <Dynamic_info> dynamic_info_list = dynamic_info_q.list();
			List <Logistics_knowledge> logistics_knowledge_list = logistics_knowledge_q.list();
			
			String goods = ""; 
			String company="";
			String car="";
			String notice="";
			String dynamic_info="";
			String logistics_knowledge="";
			
			if(!goods_list.isEmpty()) 
			{
				for(Goods gs:goods_list)
				{
					goods += gs.getName()+",";						
				}
			}
			if(!company_list.isEmpty()) 
			{
				for(Company comp:company_list)
				{
					company += comp.getName()+",";						
				}
			}
			if(!car_list.isEmpty()) 
			{
				for(Car c:car_list)
				{
					car += c.getInfo()+",";						
				}
			}
			if(!notice_list.isEmpty()) 
			{
				for(Notice noti:notice_list)
				{
					notice += noti.getTitle()+",";						
				}
			}
			if(!dynamic_info_list.isEmpty()) 
			{
				for(Dynamic_info dynamic:dynamic_info_list)
				{
					dynamic_info += dynamic.getTitle()+",";						
				}
			}
			if(!logistics_knowledge_list.isEmpty()) 
			{
				for(Logistics_knowledge logistics:logistics_knowledge_list)
				{
					logistics_knowledge += logistics.getTitle()+",";						
				}
			}
			
			ActionContext.getContext().getSession().put("goods", goods);
			ActionContext.getContext().getSession().put("company", company);
			ActionContext.getContext().getSession().put("car", car);
			ActionContext.getContext().getSession().put("notice", notice);
			ActionContext.getContext().getSession().put("dynamic_info", dynamic_info);
			ActionContext.getContext().getSession().put("logistics_knowledge", logistics_knowledge);
			return "shouye";
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

 	
 }

