package com.training.service;

import java.util.List;

import com.training.vo.EmployeeVO;

public interface EmployeeService {
	public List<EmployeeVO> getAllEmployees();
	public EmployeeVO createEmployee(EmployeeVO employee);
	public EmployeeVO getEmployeeById(int employeeId);
	public EmployeeVO updateEmployee(int employeeId,EmployeeVO employeeDetails);
	public EmployeeVO deleteEmployee(int employeeId);
}
