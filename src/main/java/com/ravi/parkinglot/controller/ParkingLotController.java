package com.ravi.parkinglot.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ravi.parkinglot.dto.CreateLotDto;
import com.ravi.parkinglot.dto.LeaveRequestDto;
import com.ravi.parkinglot.dto.LeaveResponseDto;
import com.ravi.parkinglot.dto.ParkRequestDto;
import com.ravi.parkinglot.dto.ParkResponseDto;
import com.ravi.parkinglot.dto.StatusResponseDto;
import com.ravi.parkinglot.model.Parking;
import com.ravi.parkinglot.service.ParkingLotService;

@Controller
@RequestMapping("/parking")
public class ParkingLotController {
	
	@Autowired
	private ParkingLotService parkingLotService;

	@PostMapping("/lot/create")
	@ResponseBody
	public Long createLot(@Valid @RequestBody CreateLotDto request, Errors errors) throws Exception {
		if(errors.hasErrors())
			throw new Exception("Invalid request.");
		return parkingLotService.createLot(request);
	}
	
	@PostMapping("/park")
	@ResponseBody
	public ParkResponseDto park(@RequestBody ParkRequestDto request) {
		return parkingLotService.park(request);
	}
	@PostMapping("/leave")
	@ResponseBody
	public LeaveResponseDto leave(@RequestBody LeaveRequestDto request) {
		return parkingLotService.leave(request);
	}
	@GetMapping("/status")
	@ResponseBody
	public StatusResponseDto status(@RequestParam Long request) {
		return parkingLotService.status(request);
	}
	@GetMapping("/search")
	@ResponseBody
	public List<Parking> search(@RequestParam(required=false, name="lot_id") Long lotId,
			@RequestParam(required=false) String color,
			@RequestParam(required=false, name="car_reg_num") String carRegNum) {
		return parkingLotService.search(lotId, color, carRegNum);
	}
	
}
