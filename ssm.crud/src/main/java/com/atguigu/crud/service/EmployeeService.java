package com.atguigu.crud.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atguigu.crud.bean.Employee;
import com.atguigu.crud.bean.EmployeeExample;
import com.atguigu.crud.bean.EmployeeExample.Criteria;
import com.atguigu.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {

	@Autowired
	EmployeeMapper employeeMapper;
	
	//查询所有员工数据
	public List<Employee> getAll()
	{
		return employeeMapper.selectByExampleWithDept(null);
	}

	//保存员工数据
	public void saveEmp(Employee employee)
    {
		employeeMapper.insertSelective(employee);
	}

	//检验用户名是否可用
	public boolean checkUser(String empName)
	{
		EmployeeExample example = new EmployeeExample();
		//创造查询条件
		Criteria criteria = example.createCriteria();
		//条件为员工的名字empName必须等于给定的值
		criteria.andEmpNameEqualTo(empName);
		//按照条件统计满足条件的记录数,有就返回大于0的数字,如果没有就等于0
		long count = employeeMapper.countByExample(example);
		return count == 0;
	}
	
	//按照id查询员工信息
	public Employee getEmp(Integer id)
	{
		Employee employee = employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	//根据员工id更新员工信息
	public void updateEmp(Employee employee)
	{
		//按照主键有选择的更新,不带姓名就不更新姓名
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	//根据员工id删除单个员工信息
	public void deleteEmp(Integer id)
	{
		employeeMapper.deleteByPrimaryKey(id);
	}

	//根据员工id连接的字符串删除多个员工信息
	public void deleteEmps(List<Integer> ids)
	{
		EmployeeExample example = new EmployeeExample();
		//创造删除条件
		Criteria criteria = example.createCriteria();
		//条件为员工的id组 delete from xx where emp_id in(1,2,3)
		criteria.andEmpIdIn(ids);
		employeeMapper.deleteByExample(example);	
	}

}
