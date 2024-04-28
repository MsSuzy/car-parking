package com.demo.carparking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.demo.carparking.model.VehicleSpace;

@Repository
public interface VehicleRepository extends CrudRepository<VehicleSpace, Long>{

	@Query("select vs.id from VehicleSpace vs where width >= ?1 and height >= ?2 and type in ('CarSpace','TruckSpace') order by width,height limit 1") 
	List<Long> findCarSpace(double width, double height);
	 
	@Query("select vs.id from VehicleSpace vs where width >= ?1 and height >= ?2 and type='TruckSpace' order by width,height limit 1") 
	List<Long> findTruckSpace(double width, double height);
}
