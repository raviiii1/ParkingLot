package com.ravi.parkinglot.service;

import java.util.List;

import com.ravi.parkinglot.dto.CreateLotDto;
import com.ravi.parkinglot.dto.LeaveRequestDto;
import com.ravi.parkinglot.dto.LeaveResponseDto;
import com.ravi.parkinglot.dto.ParkRequestDto;
import com.ravi.parkinglot.dto.ParkResponseDto;
import com.ravi.parkinglot.dto.StatusResponseDto;
import com.ravi.parkinglot.model.Parking;

public interface ParkingLotService {

	Long createLot(CreateLotDto request) throws Exception;

	ParkResponseDto park(ParkRequestDto dto);

	LeaveResponseDto leave(LeaveRequestDto dto);

	StatusResponseDto status(Long request);

	List<Parking> search(Long lotId, String color, String carRegNum);

}
