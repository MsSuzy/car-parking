package com.demo.carparking.model;

import com.demo.carparking.common.Constant;

public class VehicleSpaceFactory{

	public VehicleSpaceFactory() {
	}
	
	public VehicleSpace getVehicle(String type) {
		if(type.equals(Constant.CYCLE_SPACE)) {
			return new CycleSpace();
		}
		else if(type.equals(Constant.CAR_SPACE)) {
			return new CarSpace();
		}
		else {
			return new TruckSpace();
		}
	}	
}
