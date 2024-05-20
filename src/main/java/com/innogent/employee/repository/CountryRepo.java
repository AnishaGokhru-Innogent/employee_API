package com.innogent.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innogent.employee.entity.Country;

public interface CountryRepo extends JpaRepository<Country,Integer>{
	Country findByName(String name);
}
