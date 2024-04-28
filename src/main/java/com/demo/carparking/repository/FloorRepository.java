package com.demo.carparking.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.carparking.model.Floor;

@Repository
public interface FloorRepository extends CrudRepository<Floor, Long>{

}
