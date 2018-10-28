package com.ravi.parkinglot.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column
	String color;
	
	@Column
	String registation;
}
