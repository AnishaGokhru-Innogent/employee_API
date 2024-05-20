package com.innogent.employee.entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name= "state")
@Setter
@Getter
@ToString
public class State {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    
	    private String name;
	    
	    @ManyToOne(cascade = CascadeType.ALL)
//	    @JoinColumn(name = "countryId")
	    private Country country;
}
