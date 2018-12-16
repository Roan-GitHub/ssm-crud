package com.mr.action;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import com.mr.car.Car;
import com.mr.company.Company;
import com.mr.dao.UserDAO;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

 public class CarAction extends ActionSupport{ 
	 UserDAO userDAO;
	 Car car;
	 HttpServletRequest request = ServletActionContext.getRequest();
    public String show()
    {
    	    HttpServletRequest request = ServletActionContext.getRequest();
    		String car_hql = "from Car car where car.info='"+request.getParameter("carinfo").trim()+"'";			
			Query car_q = userDAO.getSession().createQuery(car_hql);					
			List <Car> car_list = car_q.list();	
			String car_info = "";				
			if(!car_list.isEmpty()) 
			{
				for(Car ca:car_list)
				{
					car_info = ca.getCapcity()+","+ca.getStart()+","+ca.getInfo()+","+ca.getDate();						
				}
			}
			ActionContext.getContext().getSession().put("car_info", car_info);			
			return "success";
    }
    
    public String message() 
    {
    		String car_hql = "from Car car";			
			Query car_q = userDAO.getSession().createQuery(car_hql);					
			List <Car> car_list = car_q.list();	
			String car_message = "";				
			if(!car_list.isEmpty()) 
			{
				for(Car cr:car_list)
				{
					car_message += cr.getId()+","+cr.getCapcity()+","+cr.getStart()+","+cr.getInfo()+","+cr.getDate()+";";
				}
			}
			ActionContext.getContext().getSession().put("car_message", car_message);			
			return "car_message";
    }
    
    public String delete()
    {
    	try
    	{
		    int car_id = Integer.parseInt(request.getParameter("car_id"));
		    userDAO.getSession().beginTransaction();
		    Query q = userDAO.getSession().createQuery("delete from Car c where c.id="+car_id);
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
   
    public String insert()
    {
    	try
    	{
    		 //System.out.println(car.getCapcity()+car.getStart()+car.getInfo()+car.getDate());
    		 userDAO.getSession().beginTransaction(); 
    		 userDAO.getSession().save(car);
    		 userDAO.getSession().beginTransaction().commit();
    		 System.out.println("添加成功");
    		 return "input";
    	}
    	catch(Exception e)
    	{
    		//userDAO.getSession().beginTransaction().rollback();
    		//e.printStackTrace(); 
    		System.out.println("数据添加失败");
    		return "input";
    	}
    	finally 
    	{
    		userDAO.getSession().close();
    	}
    }
    
    public String update_jsp()
    {
    	String car_hql = "from Car c where c.id="+Integer.parseInt(request.getParameter("car_id"));			
		Query car_q = userDAO.getSession().createQuery(car_hql);					
		List <Car> car_list = car_q.list();	
		String car_update_message = "";				
		if(!car_list.isEmpty()) 
		{
			for(Car ca:car_list)
			{
				car_update_message = ca.getCapcity()+","+ca.getStart()+","+ca.getInfo()+","+ca.getDate()+","+ca.getId();
			}
		}
		ActionContext.getContext().getSession().put("car_update_message", car_update_message);
    	return "update_jsp";
    }
    
    public String update()
    {
       try
       { 
    	   String hql="update Car car set car.capcity=?,car.start=?,car.info=?,car.date=? where car.id="+car.getId();
    	   Query query  = userDAO.getSession().createQuery(hql);  
    	   query.setDouble(0,car.getCapcity());
    	   query.setString(1,car.getStart());
    	   query.setString(2,car.getInfo());
    	   query.setDate(3,car.getDate());
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
    public String car()
    {
    	return "car";
    }
	public UserDAO getUserDAO()
	{
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO)
	{
		this.userDAO = userDAO;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}


 }

