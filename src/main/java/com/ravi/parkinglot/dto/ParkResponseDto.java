package com.ravi.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ravi.parkinglot.model.Slot;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ParkResponseDto {

	@JsonProperty("allocated_slot")
	Slot allocatedSlot;
	
}
