package com.innogent.employee.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innogent.employee.entity.Employee;
import com.innogent.employee.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository empRepository;

	@Override
	public void addEmployee(Employee employee) {
		empRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return this.empRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(Long id) {
         return this.empRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteEmployee(Long id) {
		empRepository.deleteById(id);
	}

	@Override
	public void updateEmployee(Long id, Employee employee) {
		Employee existingEmployee = empRepository.findById(id).orElse(null);
		existingEmployee.setName(employee.getName());
		existingEmployee.setSalary(employee.getSalary());
		existingEmployee.setDepartment(employee.getDepartment());
		existingEmployee.setAge(employee.getAge());
		
		empRepository.save(existingEmployee);
	}
	
	 public Employee findEmployeeByName(String name) {
	        return empRepository.findByName(name);
	 }

	@Override
	public Employee findEmployeeByNameAndAge(String name, Integer age) {
		return empRepository.findByNameAndAge(name, age);
	}

	@Override
	public List<Employee> findEmployeeBySalaryGreaterThan(Double Salary) {
		return empRepository.findBySalaryGreaterThan(Salary);
	}

	@Override
	    public List<Map<String, Long>> countEmployeesByDepartment() {
	        List<Map<String, Long>> departmentCounts = new ArrayList<>();
	        List<Object[]> result = empRepository.countEmployeesByDepartment();

	        // Iterate over the result and convert it to a list of maps
	        for (Object[] row : result) {
	            String department = (String) row[0];
	            Long count = (Long) row[1];

	            Map<String, Long> departmentCountMap = new HashMap<>();
	            departmentCountMap.put(department, count);

	            departmentCounts.add(departmentCountMap);
	        }

	        return departmentCounts;
	    }

	@Override
	public List<Employee> getEmp() {	
		return empRepository.getEmp();
	}

	@Override
	public List<Employee> sortEmployeeBySalary() {
		List<Employee> employee = empRepository.findAll();
		employee.sort(Comparator.comparing(Employee::getSalary));
		return employee;
	}

	@Override
	public List<Employee> sortEmployeeByName() {
		List<Employee> employee = empRepository.findAllByOrderByNameAsc();
		return employee;
	}

	@Override
	public Double getSalarySumByDepartment(String department) {
		List<Employee> employee = empRepository.findByDepartment(department);
		return employee.stream().mapToDouble(Employee::getSalary).sum();
	}
	
	
	
	

}
