package com.ravi.parkinglot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ravi.parkinglot.model.Slot;
import com.ravi.parkinglot.model.SlotStatus;
import com.ravi.parkinglot.service.NearestSlotCalculator;

@Service
@Qualifier("divideByEntryPoint")
public class DivideByEntryPoint implements NearestSlotCalculator{

	@Override
	public Slot calculate(Integer entryPointId, List<SlotStatus> slotStatusList, Integer entryPointCount) {
		Slot slot = null;
		for(SlotStatus sts : slotStatusList) {
			slot = findNearestSlot(sts, entryPointId, entryPointCount);
			if(slot == null) {
				slot = findAnyOtherSlot(sts, entryPointId, entryPointCount);
			}
			if(slot != null) {
				slot.setFloorId(sts.getFloorId());
				break;
			}
		}
		return slot;
	}

	private Slot findAnyOtherSlot(SlotStatus status, Integer entryPointId, Integer entryPointCount) {
		String statusString = status.getSlotStatus();
		int slotId = statusString.indexOf("0");
		if(slotId > -1)
			return new Slot(new Long(slotId+1), null);
		return null;
	}

	private Slot findNearestSlot(SlotStatus status, Integer entryPointId, Integer entryPointCount) {
		String statusString = status.getSlotStatus();
		int x = statusString.length()/entryPointCount;
		int slotId = statusString.substring(x*(entryPointId-1), x*(entryPointId-1)+x).indexOf("0");
		if(slotId > -1)
			return new Slot(new Long(x*(entryPointId-1)+slotId+1), null);
		return null;
	}

}
