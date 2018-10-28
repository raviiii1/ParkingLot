package com.ravi.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slot {

	Long id;
	Integer floorId;
}
