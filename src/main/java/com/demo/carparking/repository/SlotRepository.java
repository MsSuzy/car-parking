package com.demo.carparking.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.carparking.model.Slot;

@Repository
public interface SlotRepository extends CrudRepository<Slot, Long>{

	List<Slot> findByStatus(int status);
	
	List<Slot> findByVehicleSpaceIdInAndStatus(List<Long> vehicleSpaceIds, int status);
	
	Slot findBySlotNumber(String slotNumber);
	
	Slot findBySlotNumberAndStatus(String slotNumber, int status);
}
