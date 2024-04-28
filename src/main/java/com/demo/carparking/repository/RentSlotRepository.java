package com.demo.carparking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.demo.carparking.model.RentSlot;

@Repository
public interface RentSlotRepository extends CrudRepository<RentSlot, Long>{

	RentSlot findBySlotIdAndDeleteFlg(Long slotId,int deleteFlg);
}
