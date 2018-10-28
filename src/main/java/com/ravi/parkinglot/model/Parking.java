package com.ravi.parkinglot.model;

import java.time.LocalDateTime;

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
@Table(name = "parking")
public class Parking {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	
	@Column(name = "lot_id")
	Long lotId;
	
	@Column(name = "floor_id")
	Integer floorId;
	
	@Column(name = "slot_id")
	Long slotId;
	
	@Column(name = "car_reg_num")
	String carRegNum;
	
	@Column(name = "car_color")
	String carColor;
	
	@Column(name = "status")
	String status;
	
	@Column(name = "parking_date")
	LocalDateTime parkDate;
	
	@Column(name = "leave_date")
	LocalDateTime leaveDate;
}
