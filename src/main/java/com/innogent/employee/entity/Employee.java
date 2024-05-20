package com.innogent.employee.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 30)
	private String name;
	
	@Column
	private Double salary;
	
	@Column(length = 50)
	private String department;
	
	@Column
	private Integer age;

	@Column 
	private String localAddress;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="cityId")
	private City city;
	
}
