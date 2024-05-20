package com.innogent.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.innogent.employee.entity.Country;
import com.innogent.employee.entity.State;

public interface StateRepo extends JpaRepository<State,Integer> {
	State findByName(String name);
	List<State> findByCountry(Country country);
	State findByNameAndCountry(String stateName, Country country);
}
