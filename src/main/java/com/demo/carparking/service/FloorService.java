package com.demo.carparking.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.carparking.model.Floor;
import com.demo.carparking.repository.FloorRepository;

@Service
public class FloorService {

	@Autowired
	FloorRepository floorRepository;
	
	public List<Floor> findAll(){
		return (List<Floor>) floorRepository.findAll();
	}

	public void save(Floor f) {
		floorRepository.save(f);
	}
}
