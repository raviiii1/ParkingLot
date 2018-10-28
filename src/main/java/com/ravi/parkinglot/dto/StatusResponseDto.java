package com.ravi.parkinglot.dto;

import java.util.List;

import com.ravi.parkinglot.model.Parking;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatusResponseDto {
	List<Parking> status;
}
