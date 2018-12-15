package com.atguigu.crud.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.Msg;
import com.atguigu.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

	/*处理员工CRUD(增删改查)请求
	 * 
	 * */

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	//根据id删除员工信息(单个或批量),批量删除传的id加-,如1-2-3
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody 
	public Msg delete_Emp(@PathVariable("ids")String ids)
	{
		//批量删除
		if(ids.contains("-"))
		{
			//创建Integer类型的List集合
			List<Integer> dele_ids = new ArrayList<>();
			//分割员工的id组ids为Integer类型的List集合
			String[] str_ids = ids.split("-");
			for(String string : str_ids)
			{
				dele_ids.add(Integer.parseInt(string));
			}
			employeeService.deleteEmps(dele_ids);
		}
		//单个删除
		else
		{
			Integer id = Integer.parseInt(ids);
			employeeService.deleteEmp(id);
		}
		return Msg.success();
	}
	
	
	/*
	 * 注意：如果直接发送ajax=PUT形式的请求,请求体中有数据,但是Employee对象封装失败！
	 * 原因是Tomcat！将请求体中的数据封装为一个map对象,request.getParameter("empName")就是从map中取值
	 * 而Springmvc封装POJO对象的时候,会把POJO中每个属性的值request.getParameter("empName")拿到。
	 * Tomcat一看是PUT请求,就不会封装请求体中的数据为map对象！只有POST形式的请求才封装为map对象！	
	 * 根据员工id更新员工信息的方法:/emp_change/{empId} PUT
	 * 传入的为empId即Employee对象的属性,否则为空值,因为封装为一个Employee对象所以要对应字段 
	*/
	@RequestMapping(value="/emp_change/{empId}",method=RequestMethod.GET)
	@ResponseBody 
	public Msg save_Emp(Employee employee)
	{
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	//查出指定的id查询员工信息:/emp/{id} GET
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id)
	{
		Employee employee = employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	
	
	//检查用户名是否可用的方法
	@RequestMapping("/checkuser")
	@ResponseBody
	public Msg checkuser(@RequestParam("empName")String empName)
	{
		//先判断用户名是否合法的表达式
		String regex = "(^[a-zA-Z0-9_-]{6,16}$)|(^[\u2E80-\u9FFF]{2,5})";
		if(!empName.matches(regex))
		{
			return Msg.fail().add("val_msg","用户名必须是6-16位英文和数字的组合或者2-5位中文！");
		}
		//数据库检查用户名是否重复
		boolean b = employeeService.checkUser(empName);
		if(b)
		{ 
			return Msg.success();
		}
		else
		{
			return Msg.fail().add("val_msg", "用户名已经存在！");
		}
		
	}	
	
	//保存员工的方法:/emp POST
	//支持JSR303校验,导入hibernate-validator包,@Valid加上表示封装数据的时候需要校验
	@ResponseBody
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public Msg saveEmp(@Valid Employee employee,BindingResult result)
	{
		//校验失败,模态框中显示校验失败信息
		if(result.hasErrors())
		{
			//取出所有的校验错误信息进行遍历
			List<FieldError> error = result.getFieldErrors();
			//定义一个map对象保存错误的字段名和对应的错误信息返回给浏览器
			Map<String,Object> map = new HashMap<>();
			for(FieldError fieldError : error)
			{
			//	System.out.println("错误的字段名："+fieldError.getField());
			//	System.out.println("错误信息："+fieldError.getDefaultMessage());
				map.put(fieldError.getField(),fieldError.getDefaultMessage());
			}
			return Msg.fail().add("error_field", map);
		}
		else
		{
			employeeService.saveEmp(employee);
			return Msg.success();
		}
	}
	
	//导入JackSon包,该方法返回Json字符串到页面,@ResponseBody自动把返回的对象转为Json对象	
	//不具有一定的通用性,比如删除修改,执行完是否成功不知道？(通知给用户浏览器),所以需要设置一个通用的返回
	//带有当前请求信息的,创建一个返回类Msg,最终返回的对象是Msg对象(自定义对象)
	//查询员工的方法:/emps GET
	@ResponseBody
	@RequestMapping("/emp")
	public Msg getEmpsWithJson(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model)
	{
		 PageHelper.startPage(pn, 5);//传入第几页，每页显示多少条记录
		 List<Employee> emps = employeeService.getAll();
		 //使用PageInfo包装查询后的结果，只需要将PageInfo将给页面(封装了分页的所有信息,包括查询的数据)
		 //这种只适合于浏览器与服务器的交互模型(B-S)，所以比较局限，用Json返回(客户端与服务器解析比较容易),平台无关性
		 PageInfo page = new PageInfo(emps,5); //传入连续显示的页数，如1,2,3,4,5
		 //return page; //直接返回该page对象
		//返回处理成功的状态码和提示信息,追加查询的结果到Msg的属性extend<String,Object>中
		 return Msg.success().add("PageInfo",page); 
	}
	
			
	//发送/emps请求，调用该方法传入pn即第几页，将查询到结果封装为pageInfo传递给页面处理
	//@RequestMapping("/emps")
	/*public String getEmps(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model)
	{
		//查出来的所有记录(1000多条)，不是分页查询
		//引入PageHelper分页插件，查询之前只需要调用PageHelper.startPage(第几页,每页的记录数)
		PageHelper.startPage(pn, 5);//传入第几页，每页显示多少条记录
		List<Employee> emps = employeeService.getAll();
		//使用PageInfo包装查询后的结果，只需要将PageInfo将给页面(封装了分页的所有信息,包括查询的数据)
		//这种只适合于浏览器与服务器的交互模型(B-S)，所以比较局限，用Json返回(客户端与服务器解析比较容易),平台无关性
		PageInfo page = new PageInfo(emps,5); //传入连续显示的页数，如1,2,3,4,5
		model.addAttribute("PageInfo",page);
		return "list";
	}*/
}
