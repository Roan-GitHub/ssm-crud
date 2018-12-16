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
 					//adminDAO.getSession().save("admin", admin);//������Ա��Ϣ������Session������
 					return "success";
 				}
 				else
 				{
 					addFieldError("", "�û��������벻��ȷ��");//���ش�����ʾ��Ϣ
 					return "admin_login";//���غ�̨��¼ҳ��
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

