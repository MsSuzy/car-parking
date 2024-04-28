package com.demo.carparking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.demo.carparking.model.VehicleSpace;
import com.demo.carparking.repository.VehicleRepository;

@Service
public class VehicleService {

	@Autowired
	VehicleRepository vehicleRepository;
	
	public List<VehicleSpace> findAll(){
		return (List<VehicleSpace>) vehicleRepository.findAll();
	}

	public void save(VehicleSpace v) {
		vehicleRepository.save(v);
	}
	
	public List<Long> findTruckSpace(double width, double height) {
		return (List<Long>) vehicleRepository.findTruckSpace(width, height);
	}
	
	public List<Long> findCarSpace(double width, double height) {
		return (List<Long>) vehicleRepository.findCarSpace(width, height);
	}
}
