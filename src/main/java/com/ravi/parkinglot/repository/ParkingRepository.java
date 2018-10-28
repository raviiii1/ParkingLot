package com.ravi.parkinglot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.parkinglot.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long>{

	List<Parking> findAllByLotIdAndStatus(Long lotId, String string);

	List<Parking> findAllByLotId(Long lotId);

	List<Parking> findAllByCarColor(String color);

	List<Parking> findAllByCarRegNum(String carRegNum);

	Parking findByLotIdAndFloorIdAndSlotIdAndStatus(Long lotId, Integer floorId, Long slotId, String string);

}
