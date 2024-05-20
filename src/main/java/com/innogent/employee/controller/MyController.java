package com.innogent.employee.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.innogent.employee.entity.City;
import com.innogent.employee.entity.Country;
import com.innogent.employee.entity.Employee;
import com.innogent.employee.entity.State;
import com.innogent.employee.repository.CityRepo;
import com.innogent.employee.repository.CountryRepo;
import com.innogent.employee.repository.EmployeeRepository;
import com.innogent.employee.repository.StateRepo;
import com.innogent.employee.service.EmployeeService;

@RestController
public class MyController {
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Autowired
	CityRepo cityRepo;

	@Autowired
	private CountryRepo countryRepo;

	@Autowired
	private StateRepo stateRepo;

	
	@GetMapping("/home")
	public String home() {
		return "Welcome to the Application";
	}
	
	//Add the Employee 
	@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping("/addEmp")
	public ResponseEntity<String> add(@RequestBody Employee employee){
		this.empService.addEmployee(employee);
		return ResponseEntity.ok("Employee Added Succesfully ");
	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	 @GetMapping("/getAllEmp")
	    public ResponseEntity<List<Employee>> getAllEmployees() {
	        List<Employee> employees = empService.getAllEmployees();
	        return ResponseEntity.status(HttpStatus.OK).body(employees);
	 }
	
	 @GetMapping("/getEmpById/{id}")
	 public ResponseEntity<Employee> getEmployeeById(@PathVariable String id){
		 Employee employee = this.empService.getEmployeeById(Long.parseLong(id));
		 return ResponseEntity.ok(employee);
	 }
	 
	 @CrossOrigin(origins = "http://localhost:3000")
	 @DeleteMapping("/deleteEmp/{id}")
	 public ResponseEntity<String> deleteEmployee(@PathVariable String id){
		 this.empService.deleteEmployee(Long.parseLong(id));
		 return ResponseEntity.ok("Employee Deleted Successfully");
	 }
	 
	 @CrossOrigin(origins = "http://localhost:3000")
	 @PutMapping("/updateEmp/{id}")
	 public ResponseEntity<String> updateEmployee(@PathVariable String id,@RequestBody Employee employee){
		  this.empService.updateEmployee(Long.parseLong(id),employee);
		  return ResponseEntity.ok("Employee Updated Sucessfully");
	 }
	 
	 @GetMapping("/byname/{name}")
	    public ResponseEntity<Employee> findEmployeeByName(@PathVariable String name) {
	        Employee employee = empService.findEmployeeByName(name);
	        if (employee != null) {
	            return ResponseEntity.ok(employee);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 
	 @GetMapping("byNameAndAge/{name}/{age}")
	 public ResponseEntity<Employee> findEmployeeByNameAndAge(@PathVariable String name, @PathVariable Integer age){
		 Employee employee = this.empService.findEmployeeByNameAndAge(name,age);
		 if (employee != null) {
	            return ResponseEntity.ok(employee);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
		 
	 }
	 
	 @GetMapping("/salayGreaterThan/{salary}")
	 public ResponseEntity<List<Employee>> findEmployeeBySalaryGreaterThan(@PathVariable String salary){
		 List<Employee> employee = empService.findEmployeeBySalaryGreaterThan(Double.parseDouble(salary));
		 if(employee!=null) {
			 return ResponseEntity.ok(employee);
		 }
		 else {
			 return ResponseEntity.notFound().build();
		 }
	 }
	 

	 @GetMapping("/groupbydept")
	 public ResponseEntity<List<Map<String, Long>>> countEmployeeByDepartment(){
		 List<Map<String, Long>> result = empService.countEmployeesByDepartment();
		 return ResponseEntity.ok(result);
	 }
	 
	 @GetMapping("/getEmp")
	 public ResponseEntity<List<Employee>> getEmployee(){
		 List<Employee> employee = empService.getEmp();
		 return ResponseEntity.ok(employee);
	 }
	 
	 @GetMapping("/sortBySalary")
	 public ResponseEntity<List<Employee>> sortBySalary(){
		 List<Employee> employee = empService.sortEmployeeBySalary();
		 return ResponseEntity.ok(employee);
	 }
	 
	 @GetMapping("/sortByName")
	 public ResponseEntity<List<Employee>> sortByName(){
		 List<Employee> employee = empService.sortEmployeeByName();
		 return ResponseEntity.ok(employee);
	 }
	 
	 @GetMapping("/getSalarySumByDept/{Department}")
	 public ResponseEntity<String> getSalarySumByDepartment(@PathVariable String Department){
		 Double salary = empService.getSalarySumByDepartment(Department);
		 if(salary!=0) {
			 return ResponseEntity.ok("Sum of Salary of Department " +Department +" is "+salary);
		 }
		 else {
			 return ResponseEntity.notFound().build(); 
		 }
	 }
	 
//	 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~New Method~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//		@PostMapping("/add")
//		public ResponseEntity<String> add(@RequestBody City city) {
//
//			Country existingCountry = countryRepo.findByName(city.getState().getCountry().getName());
//			if(existingCountry !=null) {
//				city.getState().setCountry(existingCountry);
//			}
//			State existingState = stateRepo.findByName(city.getState().getName());
//			if (existingState != null) {
//				city.setState(existingState);
//			}
//
//			City existingCity = cityRepo.findByName(city.getName());
//			if (existingCity == null) {
//				cityRepo.save(city);
//			}
//
//			return ResponseEntity.ok("data added successfully");
//		}

		@GetMapping("/getAll")
		public ResponseEntity<List<Employee>> get() {
			List<Employee> employee = empRepo.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(employee);
		}
		
//		@PostMapping("/addEmpWithAddress")
//		public ResponseEntity<String> addEmpWithAddress(@RequestBody Employee emp){	
//
//			Country existingCountry = countryRepo.findByName(emp.getCity().getState().getCountry().getName());
//			if(existingCountry !=null) {
//						emp.getCity().getState().setCountry(existingCountry);
//			}
//			
//			State existingState = stateRepo.findByName(emp.getCity().getState().getName());
//			if(existingState !=null) {
//					emp.getCity().setState(existingState);
//			}
//			
//			City existingCity = cityRepo.findByName(emp.getCity().getName());
//			if(existingCity !=null) {
//				emp.setCity(existingCity);
//			}
//			
//			empRepo.save(emp);
//			return ResponseEntity.ok("data added successfully");
//		}
	
		@CrossOrigin(origins = "http://localhost:3000")
		@PostMapping("/addEmpWithAddress")
		public ResponseEntity<String> addEmpWithAddress(@RequestBody Employee emp){
			String city=emp.getCity().getName();
			String state=emp.getCity().getState().getName();
			String country=emp.getCity().getState().getCountry().getName();
			System.out.println(city+" "+state+" "+country);
			
		    Country existingCountry = countryRepo.findByName(country);
		    if (existingCountry == null) {
		    	existingCountry = new Country();
		        existingCountry.setName(country);
		        existingCountry = countryRepo.save(existingCountry);
//		        return ResponseEntity.badRequest().body("Country does not exist: " + country);
		    }

		    State existingState = stateRepo.findByNameAndCountry(state, existingCountry);
		    if (existingState == null) {
		    	existingState = new State();
		        existingState.setName(state);
		        existingState.setCountry(existingCountry);
		        existingState = stateRepo.save(existingState);
//		        return ResponseEntity.badRequest().body("State does not exist in country " + country + ": " + state);
		    }

		    City existingCity = cityRepo.findByNameAndState(city, existingState);
		    if (existingCity == null) {
		    	existingCity = new City();
		        existingCity.setName(city);
		        existingCity.setState(existingState);
		        existingCity = cityRepo.save(existingCity);
//		        return ResponseEntity.badRequest().body("City does not exist in state " + state + ": " + city);
		    }
		    emp.setCity(existingCity);
		    empRepo.save(emp);
		    return ResponseEntity.ok("Data added successfully");
		}
		
		 @CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/getAllCity")
		public ResponseEntity<List<City>> getAllCity(){
			List<City> cityList = cityRepo.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(cityList);
		}
		 @CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/getAllState")
		public ResponseEntity<List<State>> getAllState(){
			List<State> stateList = stateRepo.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(stateList);
		}
		 @CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/getAllCountry")
		public ResponseEntity<List<Country>> getAllCountry(){
			List<Country> countryList = countryRepo.findAll();
			return ResponseEntity.status(HttpStatus.OK).body(countryList);
		}
		 
		@CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/{countryName}/states")
		public ResponseEntity <List<State>> getStatesByCountry(@PathVariable String countryName){
			Country country1 = countryRepo.findByName(countryName);
			   if (country1 == null) {
		            return ResponseEntity.notFound().build();
		        }
			   List<State> particularStateList = stateRepo.findByCountry(country1);

			return ResponseEntity.status(HttpStatus.OK).body(particularStateList);
		}
		
		
		@CrossOrigin(origins = "http://localhost:3000")
		@GetMapping("/{stateName}/cities")
		public ResponseEntity<List<City>> getCitiesByState(@PathVariable String stateName) {
		    State state = stateRepo.findByName(stateName);
		    if (state == null) {
		        return ResponseEntity.notFound().build();
		    }
		    List<City> cityList = cityRepo.findByState(state);
		    
		    if (cityList.isEmpty()) {
		        return ResponseEntity.notFound().build(); 
		    }
		    
		    return ResponseEntity.ok(cityList);
		}
		
		

		
}



