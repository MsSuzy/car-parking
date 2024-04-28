package com.demo.carparking.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.carparking.common.Constant;
import com.demo.carparking.model.Slot;
import com.demo.carparking.model.RentSlot;
import com.demo.carparking.repository.SlotRepository;
import com.demo.carparking.repository.RentSlotRepository;

@Service
public class SlotService {

	@Autowired
	SlotRepository slotRepository;

	@Autowired
	RentSlotRepository rentSlotRepository;

	public List<Slot> findAll() {
		return (List<Slot>) slotRepository.findAll();
	}
	
	public List<Slot> findByVehicleSpaceIdIn(List<Long> vehicleSpaceIds) {
		return (List<Slot>) slotRepository.findByVehicleSpaceIdIn(vehicleSpaceIds);
	}

	/*
	 * public List<Slot> findByWidthAndHeightWithRange(double width, double height){
	 * return (List<Slot>) slotRepository.findByWidthAndHeightWithRange(width,
	 * height); }
	 */

	public void save(Slot s) {
		slotRepository.save(s);
	}

	public Optional<Slot> findById(Long id) {
		return slotRepository.findById(id);
	}

	public List<Slot> findUsableSlot() {
		return slotRepository.findByStatus(0);
	}

	public Slot findBySlotNumber(String slotNumber) {
		return slotRepository.findBySlotNumber(slotNumber);
	}

	public Slot findBySlotNumberAndStatus(String slotNumber, int status) {
		return slotRepository.findBySlotNumberAndStatus(slotNumber, status);
	}

	public void updateStautsBySlotId(int status, Long slotId) {
		Slot s = slotRepository.findById(slotId).get();
		s.setStatus(1);
		slotRepository.save(s);
		RentSlot rs = new RentSlot();
		rs.setSlot(s);
		rs.setStartTime(Constant.SDF.format(Calendar.getInstance().getTime()));
		rentSlotRepository.save(rs);
	}
}
