package com.atguigu.crud.test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.atguigu.crud.bean.Employee;
import com.github.pagehelper.PageInfo;

/*
	 * 使用Spring测试模块提供的测试请求功能，测试CRUD请求的正确性
	 * Spring4测试需要servlet3.0的支持
	 * */
@RunWith(SpringJUnit4ClassRunner.class)  
@WebAppConfiguration
@ContextConfiguration(locations={"classpath:applicationContext.xml","file:src/main/webapp/WEB-INF/dispatcherServlet-servlet.xml"})
public class MvcTest {

	//@Autowired只能注解IOC容器里面的东西，但不能注解本身，所以引入@WebAppConfiguration
	@Autowired
	WebApplicationContext context;
	//虚拟mvc请求，获取到处理结果 
	MockMvc	mockMvc;
	
	//每次用都要初始化
	@Before
	public void initMockMvc()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testPage() throws Exception
	{
		//模拟发送请求，请求成功后请求域中有PageInfo，我们取出PageInfo进行验证
		//get(url:/emps)请求，带参数pn值为1，拿到返回值给result
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/emps").param("pn", "1")).andReturn();
		MockHttpServletRequest request = result.getRequest();
		PageInfo pi = (PageInfo)request.getAttribute("PageInfo");
		System.out.println("当前页码:"+pi.getPageNum());
		System.out.println("总页码:"+pi.getPages());
		System.out.println("总记录数:"+pi.getTotal());
		int [] num = pi.getNavigatepageNums(); 
		for(int i:num)
		{
			System.out.print(" "+i);
		}
		System.out.print("\n");
		//获取员工数据
		List<Employee> list = pi.getList();
		for(Employee employee:list)
		{
			System.out.println("ID:"+employee.getdId()+",name:"+employee.getEmpName());
		}
	}
	
}
