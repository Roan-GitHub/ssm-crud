package com.atguigu.crud.test;
import java.util.UUID;



import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.atguigu.crud.bean.Department;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.dao.DepartmentMapper;
import com.atguigu.crud.dao.EmployeeMapper;   
     
	/* 测试Dao层
	 * 推荐Spring的项目就可以使用Spring的单元测试，可以自动注入需要的组件
	 * 1.导入Spring-test的jar包
	 * 2.使用@ContextConfiguration指定配置文件的位置，自动创建IOC容器
	 * 3.@RunWith单元测试模块，所有的@Test运行都是由SpringJUnit4ClassRunner来运行
	 * 4.直接@Autowired自动注入
	 * */	


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})  
public class MapperTest { 
	
	@Autowired
	SqlSession sqlSession;
	
	@Autowired  
	DepartmentMapper departmentMapper;
	
	@Autowired  
	EmployeeMapper employeeMapper;

	@Test
 	public void testCRUD()
	{   		
		//常规的方法获取bean
		/*ApplicationContext ioc = new ClassPathXmlApplicationContext("applicationContext.xml");
		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class);
		System.out.println(bean);	 
 		System.out.println(departmentMapper);		
		插入数据都是封装为一个对象再插入该对象
		departmentMapper接口调用insertSelective方法(Department对象)插入数据
		departmentMapper.insertSelective(new Department(null,"测试部"));
		employeeMapper接口调用insertSelective方法(Employee对象)插入数据
		employeeMapper.insertSelective(new Employee(null,"luo","M","lup@qq.com",1));*/
		
		//批量插入，通过Spring配置文件applicationContext.xml中定配置一个可以执行批量的sqlSession
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0; i<1000; i++)
		{	
			//截取前5个字符作为员工姓名
			String uid = UUID.randomUUID().toString().substring(0, 5);
			mapper.insertSelective(new Employee(null,uid,"M",uid+"@qq.com",1));
		}
	}
}
