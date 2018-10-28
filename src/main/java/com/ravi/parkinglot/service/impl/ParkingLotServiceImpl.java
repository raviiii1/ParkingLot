package com.ravi.parkinglot.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.parkinglot.dto.CreateLotDto;
import com.ravi.parkinglot.dto.LeaveRequestDto;
import com.ravi.parkinglot.dto.LeaveResponseDto;
import com.ravi.parkinglot.dto.ParkRequestDto;
import com.ravi.parkinglot.dto.ParkResponseDto;
import com.ravi.parkinglot.dto.StatusResponseDto;
import com.ravi.parkinglot.model.Lot;
import com.ravi.parkinglot.model.Parking;
import com.ravi.parkinglot.model.Slot;
import com.ravi.parkinglot.repository.ParkingRepository;
import com.ravi.parkinglot.service.ParkingLotService;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

	@Autowired
	private ParkingRepository parkingRepository;

	@Autowired
	private ParkingLotTransaction parkingLotTransaction;

	@Override
	public Long createLot(CreateLotDto dto) throws Exception {
		StringBuilder slotStatus = new StringBuilder();
		for (int i = 0; i < dto.getSlotCount(); i++) {
			slotStatus.append("0");
		}
		Lot lot = parkingLotTransaction.createParkingLot(dto, slotStatus);
		if (lot == null)
			throw new Exception("Parking lot could not be created.");
		return lot.getId();
	}

	@Override
	public ParkResponseDto park(ParkRequestDto dto) {
		Slot nearestSlot = parkingLotTransaction.parkCar(dto);
		return new ParkResponseDto(nearestSlot);
	}



	@Override
	public LeaveResponseDto leave(LeaveRequestDto dto) {
		return new LeaveResponseDto(parkingLotTransaction.leaveSlot(dto));
	}

	@Override
	public StatusResponseDto status(Long lotId) {
		return new StatusResponseDto(parkingRepository.findAllByLotIdAndStatus(lotId, "park"));
	}

	@Override
	public List<Parking> search(Long lotId, String color, String carRegNum) {
		List<Parking> parkings = new ArrayList<>();
		if(lotId != null) {
				parkings = parkingRepository.findAllByLotId(lotId);
			if(color != null)
				parkings = parkings.stream().filter(p -> color.equalsIgnoreCase(p.getCarColor())).collect(Collectors.toList());
			if(carRegNum != null)
				parkings = parkings.stream().filter(p -> carRegNum.equalsIgnoreCase(p.getCarRegNum())).collect(Collectors.toList());
		}
		return parkings;
	}

}
