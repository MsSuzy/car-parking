package com.demo.carparking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="VehicleSpace")
public class CarSpace extends VehicleSpace{

	public CarSpace() {
	}
}
