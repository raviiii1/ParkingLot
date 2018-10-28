package com.ravi.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveRequestDto {

	@JsonProperty("lot_id")
	Long lotId;
	
	@JsonProperty("floor_id")
	Integer floorId;
	
	@JsonProperty("slot_id")
	Long slotId;
	
}
