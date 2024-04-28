package com.demo.carparking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="VehicleSpace")
public class CycleSpace extends VehicleSpace{
	
	public CycleSpace() {
		setWidth(2);
		setHeight(1);
	}
}
