package com.mr.action;
import java.util.List;

import org.hibernate.Query;

import com.mr.admin.Admin;

import com.mr.dao.AdminDAO;
import com.mr.dao.UserDAO;
import com.mr.goods.Goods;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

 public class ShouyeAction extends ActionSupport {
 	AdminDAO adminDAO;
 	UserDAO userDAO;
 	Admin admin;
	
 	public String admin() 
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
 					return "admin_success";
 				}
 				else
 				{
 					addFieldError("", "�û��������벻��ȷ��");//���ش�����ʾ��Ϣ
 					return "shouye";//���غ�̨��¼ҳ��
 				}			
 			} 
 			else
 			{
 				return "shouye";
 			}
 			
 		} 
 		catch (Exception e)
 		{
 			e.printStackTrace();
 			return "shouye";
 		}

 	}

 	public void validate() 
 	{
 		if (admin == null) 
		{
			//System.out.println("adminΪ��");
		} 
		else 
		{
			if (admin.getUsername() == null || admin.getUsername().trim().equals("")) 
 			{
 				addFieldError("", "�û�������Ϊ��");
 			}
 			if (admin.getPassword() == null || admin.getPassword().trim().equals("")) 
 			{
 				addFieldError("", "���벻��Ϊ��");
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

