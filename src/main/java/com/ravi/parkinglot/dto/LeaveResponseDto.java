package com.ravi.parkinglot.dto;

import com.ravi.parkinglot.model.Parking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LeaveResponseDto {
	Parking left;
}
