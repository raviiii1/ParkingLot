package com.ravi.parkinglot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "slot_status")
public class SlotStatus {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "lot_id")
	Long lotId;
	
	@Column(name = "floor_id")
	Integer floorId;
	
	@Column(name = "slot_status")
	String slotStatus;
	
}
