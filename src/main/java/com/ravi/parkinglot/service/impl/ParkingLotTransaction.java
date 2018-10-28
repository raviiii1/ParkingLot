package com.ravi.parkinglot.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ravi.parkinglot.dto.CreateLotDto;
import com.ravi.parkinglot.dto.LeaveRequestDto;
import com.ravi.parkinglot.dto.ParkRequestDto;
import com.ravi.parkinglot.model.Lot;
import com.ravi.parkinglot.model.Parking;
import com.ravi.parkinglot.model.Slot;
import com.ravi.parkinglot.model.SlotStatus;
import com.ravi.parkinglot.repository.LotRepository;
import com.ravi.parkinglot.repository.ParkingRepository;
import com.ravi.parkinglot.repository.SlotStatusRepository;
import com.ravi.parkinglot.service.NearestSlotCalculator;

@Service
public class ParkingLotTransaction {

	@Autowired
	private LotRepository lotRepository;

	@Autowired
	private ParkingRepository parkingRepository;

	@Autowired
	private SlotStatusRepository slotStatusRepository;

	@Autowired
	private NearestSlotCalculator divideByEntryPoint;

	@Transactional
	public Lot createParkingLot(CreateLotDto dto, StringBuilder slotStatus) {
		Lot lot = lotRepository.save(new Lot(null, dto.getSlotCount(), dto.getEntryPoints(), dto.getFloor()));
		for (int i = 1; i <= dto.getFloor(); i++) {
			slotStatusRepository.save(new SlotStatus(null, lot.getId(), i, slotStatus.toString()));
		}
		return lot;
	}

	@Transactional
	public Slot parkCar(ParkRequestDto dto) {
		Optional<Lot> lotOpt = lotRepository.findById(dto.getLotId());
		if (!lotOpt.isPresent())
			return null;
		List<SlotStatus> slotStatusList = slotStatusRepository.findAllByLotId(dto.getLotId());
		Slot nearestSlot = divideByEntryPoint.calculate(dto.getEntryPoint(), slotStatusList,
				lotOpt.get().getEntryPonits());
		Optional<SlotStatus> statusOpt = slotStatusList.stream()
				.filter(status -> status.getFloorId() == nearestSlot.getFloorId()).findFirst();
		if (statusOpt.isPresent()) {
			SlotStatus status = statusOpt.get();
			String statusString = status.getSlotStatus();
			statusString = statusString.substring(0, nearestSlot.getId().intValue() - 1) + "1"
					+ statusString.substring(nearestSlot.getId().intValue());
			status.setSlotStatus(statusString);
			slotStatusRepository.save(status);
			parkingRepository.save(new Parking(null, dto.getLotId(), nearestSlot.getFloorId(), nearestSlot.getId(),
					dto.getCarRegNum(), dto.getCarColor(), "park", LocalDateTime.now(), null));
		} else {
			return null;
		}
		return nearestSlot;
	}

	@Transactional
	public Parking leaveSlot(LeaveRequestDto dto) {
		Parking parking = parkingRepository.findByLotIdAndFloorIdAndSlotIdAndStatus(dto.getLotId(), dto.getFloorId(),
				dto.getSlotId(), "park");
		if (parking != null) {
			SlotStatus slotStatus = slotStatusRepository.findByLotIdAndFloorId(dto.getLotId(), dto.getFloorId());
			String statusString = slotStatus.getSlotStatus();
			statusString = statusString.substring(0, dto.getSlotId().intValue() - 1) + "0"
					+ statusString.substring(dto.getSlotId().intValue());
			slotStatus.setSlotStatus(statusString);
			parking.setStatus("leave");
			parking.setLeaveDate(LocalDateTime.now());
			slotStatusRepository.save(slotStatus);
			parkingRepository.save(parking);
		}
		return parking;
	}
}
