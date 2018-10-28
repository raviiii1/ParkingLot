package com.ravi.parkinglot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.parkinglot.model.Lot;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long>{

}
