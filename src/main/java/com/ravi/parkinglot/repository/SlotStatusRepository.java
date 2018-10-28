package com.ravi.parkinglot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.parkinglot.model.SlotStatus;

@Repository
public interface SlotStatusRepository extends JpaRepository<SlotStatus, Long> {

	List<SlotStatus> findAllByLotId(Long lotId);

	SlotStatus findByLotIdAndFloorId(Long lotId, Integer floorId);

}
