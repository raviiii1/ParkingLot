package com.ravi.parkinglot.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ravi.parkinglot.model.Slot;
import com.ravi.parkinglot.model.SlotStatus;

@Repository
public interface NearestSlotCalculator {

	public Slot calculate(Integer entryPontId, List<SlotStatus> slotStatusList, Integer entryPointCount);
}
