package com.innogent.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innogent.employee.entity.City;
import com.innogent.employee.entity.State;

public interface CityRepo extends JpaRepository<City,Integer>{
	 City findByName(String name);
	 List<City> findByState(State state);
	 City findByNameAndState(String name, State state);
}
