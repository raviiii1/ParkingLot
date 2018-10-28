package com.ravi.parkinglot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkRequestDto {

	@JsonProperty("lot_id")
	Long lotId;
	
	@JsonProperty("car_reg_num")
	String carRegNum;
	
	@JsonProperty("car_color")
	String carColor;
	
	@JsonProperty("entry_point")
	Integer entryPoint;
	
}
