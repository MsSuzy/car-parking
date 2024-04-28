package com.demo.carparking.service;

import java.text.SimpleDateFormat;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.carparking.model.RentSlot;
import com.demo.carparking.repository.RentSlotRepository;

@Service
public class RentSlotService {

	@Autowired
	RentSlotRepository rentSlotRepository;
	
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	public RentSlot findBySlotIdAndDeleteFlg(Long slotId, int deleteFlg){
		return rentSlotRepository.findBySlotIdAndDeleteFlg(slotId, deleteFlg);
	}
	
	public Optional<RentSlot> findById(Long id){
		return rentSlotRepository.findById(id);
	}
	
	public void save(RentSlot s) {
		rentSlotRepository.save(s);
	}
	
}
