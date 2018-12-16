package com.mr.action;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import com.mr.dao.UserDAO;
import com.mr.goods.Goods;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

 public class GoodsAction extends ActionSupport {
	 UserDAO userDAO;
	 Goods goods;
	 Goods gs;
	 HttpServletRequest request = ServletActionContext.getRequest();
    public String show()
    {
    	    HttpServletRequest request = ServletActionContext.getRequest();
    		String goods_hql = "from Goods goods where goods.name='"+request.getParameter("goodsname").trim()+"'";			
			Query goods_q = userDAO.getSession().createQuery(goods_hql);					
			List <Goods> goods_list = goods_q.list();	
			String goods_name = "";				
			if(!goods_list.isEmpty()) 
			{
				for(Goods gd:goods_list)
				{
					goods_name = gd.getType()+","+gd.getName()+","+gd.getAmount()+","+gd.getStart()+","+gd.getEnd()+","
				 	+gd.getDate()+","+gd.getTran_type()+","+gd.getLinkman()+","+gd.getPhone()+","+gd.getTime()+","
				 	+gd.getInfo()+","+gd.getPeople();						
				}
			}		
			ActionContext.getContext().getSession().put("goods_name", goods_name);
			
			return "success";
    }
    
    public String insert()
    {
    	try
    	{
    		   Date date = new Date();
    		   goods.setTime(date);
    		   userDAO.getSession().beginTransaction(); 
    		   userDAO.getSession().save(goods);
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
    
    public String message() 
    {
    		String goods_hql = "from Goods goods";			
			Query goods_q = userDAO.getSession().createQuery(goods_hql);					
			List <Goods> goods_list = goods_q.list();	
			String goods_message = "";				
			if(!goods_list.isEmpty()) 
			{
				for(Goods gd:goods_list)
				{
					goods_message += gd.getId()+","+gd.getType()+","+gd.getName()+","+gd.getAmount()+","
					+gd.getStart()+","+gd.getEnd()+","+gd.getDate()+","+gd.getTran_type()+","+gd.getLinkman()+","
					+gd.getPhone()+","+gd.getTime()+","+gd.getInfo()+","+gd.getPeople()+";";
				}
			}
			ActionContext.getContext().getSession().put("goods_message", goods_message);			
			return "goods_message";
    }
    
    public String delete()
    {
    	try
    	{
		    int goods_id = Integer.parseInt(request.getParameter("goods_id"));
		    userDAO.getSession().beginTransaction();
		    Query q = userDAO.getSession().createQuery("delete from Goods gd where gd.id="+goods_id);
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
    
    public String insert_jsp()
    {
    	return "insert_jsp";
    }
    
    public String update_jsp()
    {
    	String goods_hql = "from Goods goods where goods.id="+Integer.parseInt(request.getParameter("good_id"));			
		Query goods_q = userDAO.getSession().createQuery(goods_hql);					
		List <Goods> goods_list = goods_q.list();	
		String goods_update_message = "";				
		if(!goods_list.isEmpty()) 
		{
			for(Goods gd:goods_list)
			{
				goods_update_message = gd.getType()+","+gd.getName()+","+gd.getAmount()+","
				+gd.getStart()+","+gd.getEnd()+","+gd.getDate()+","+gd.getTran_type()+","+gd.getLinkman()+","
				+gd.getPhone()+","+gd.getInfo()+","+gd.getPeople()+","+gd.getId();
			}
		}
		ActionContext.getContext().getSession().put("goods_update_message", goods_update_message);
    	return "update_jsp";
    }
    
    public String update()
    {
       try
       { 
    	   // Date date = new Date();
    	   // userDAO.getSession().beginTransaction();
    	    //Query q = userDAO.getSession().createSQLQuery("update goods set type='"+goods.getType()+"',name='"
           // +goods.getName()+"',amount='"+goods.getAmount()+"',start='"+goods.getStart()+"',end='"+goods.getEnd()
            //+"',date="+goods.getDate()+",tran_type='"+goods.getTran_type()+"',linkman='"+goods.getLinkman()+"'," 
            //+"phone='"+goods.getPhone()+"',time='"+date+"',info='"+goods.getInfo()+"',people='"+goods.getPeople()
           // +"' where id="+goods.getId()); 
		   // q.executeUpdate(); 
    	   String hql="update Goods g set g.type=?,g.name=?,g.amount=?,g.start=?,g.end=?,g.date=?,g.tran_type=?,g.linkman=?,"
    	   +"g.phone=?,g.info=?,g.people=? where g.id="+goods.getId();
    	   Query query  = userDAO.getSession().createQuery(hql);  
    	   query.setString(0,goods.getType());
    	   query.setString(1,goods.getName());
    	   query.setString(2,goods.getAmount());
    	   query.setString(3,goods.getStart());
    	   query.setString(4,goods.getEnd());
    	   query.setDate(5,goods.getDate());
    	   query.setString(6,goods.getTran_type());
    	   query.setString(7,goods.getLinkman());
    	   query.setString(8,goods.getPhone());
    	   query.setString(9,goods.getInfo());
    	   query.setString(10,goods.getPeople());
    	   query.executeUpdate();
    	  // userDAO.getSession().beginTransaction();
		   //userDAO.getSession().update(gs);  
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
    
	public Goods getGs() {
		return gs;
	}

	public void setGs(Goods gs) {
		this.gs = gs;
	}

	public Goods getGoods()
	{
		return goods;
	}

	public void setGoods(Goods goods)
	{
		this.goods = goods;
	}

	public String goods()
    {
    	return "goods";
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

