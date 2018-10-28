package com.ravi.parkinglot.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateLotDto {

	@NotNull(message = "Slot count is mandatory.")
	@Min(value=1, message = "Slot count cannot be 0 or negative.")
	@JsonProperty("slot_count")
	Integer slotCount;

	@NotNull(message = "Entry -point is mandatory.")
	@Min(value=1, message = "Entry-points cannot be 0 or negative.")
	@JsonProperty("entry_ponits")
	Integer entryPoints;
	
	Integer floor;
	
}
