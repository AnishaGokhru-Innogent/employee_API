package com.innogent.employee.service;

import java.util.List;
import java.util.Map;

import com.innogent.employee.entity.Employee;

public interface EmployeeService {
	public void addEmployee(Employee employee);
	public List<Employee> getAllEmployees();
	public Employee getEmployeeById(Long id);
	public void deleteEmployee(Long id);
	public void updateEmployee(Long id, Employee employee);
	public Employee findEmployeeByName(String name);
	public Employee findEmployeeByNameAndAge(String name , Integer age);
	public List<Employee> findEmployeeBySalaryGreaterThan(Double Salary);
	
//	public List<Object[]> countEmployeesByDepartment();
	
	public List<Map<String, Long>> countEmployeesByDepartment();
	public List<Employee> getEmp();
	
	public List<Employee> sortEmployeeBySalary();
	public List<Employee> sortEmployeeByName();
	
	public Double getSalarySumByDepartment(String department);
	
	  
}
 