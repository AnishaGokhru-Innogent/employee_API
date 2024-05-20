package com.innogent.employee.repository;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.innogent.employee.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	Employee findByName(String name);
	Employee findByNameAndAge(String name, Integer age);
	List<Employee>  findBySalaryGreaterThan(Double salary);
	
	List<Employee> findAllByOrderByNameAsc();
	
	@Query(value="select * from employee", nativeQuery = true)
	List<Employee> getEmp();
	
	@Query(value="SELECT e.department, COUNT(*) AS employee_count FROM employee e GROUP BY e.department",nativeQuery = true)
	List<Object[]> countEmployeesByDepartment();
	
	List<Employee> findByDepartment(String Department);
	
}
