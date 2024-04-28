package com.demo.carparking;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.demo.carparking.common.Constant;
import com.demo.carparking.controller.RentController;
import com.demo.carparking.model.VehicleSpaceFactory;
import com.demo.carparking.model.Floor;
import com.demo.carparking.model.Slot;
import com.demo.carparking.model.VehicleSpace;
import com.demo.carparking.service.SlotService;
import com.demo.carparking.service.VehicleService;
import com.demo.carparking.service.FloorService;

@SpringBootApplication
public class CarParkingApplication {
	
	 static VehicleService vehicleService;
	 static FloorService floorService;
	 static SlotService slotService;
	 static VehicleSpace cycleSpace1;
	 static VehicleSpace carSpace1;
	 static VehicleSpace carSpace2;
	 static VehicleSpace carSpace3;
	 static VehicleSpace truckSpace1;
	 static VehicleSpace truckSpace2;
	 static Floor floor;
	 static Slot slot;
	
	static Logger log = LoggerFactory.getLogger(CarParkingApplication.class);
	
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CarParkingApplication.class, args);
		
		vehicleService = context.getBean(VehicleService.class);
		floorService = context.getBean(FloorService.class);
		slotService = context.getBean(SlotService.class);
		createData();
	}

	private static void createData() {
		try {
			VehicleSpaceFactory vehicleSpaceFactory = new VehicleSpaceFactory();
			// CycleSpace 
			cycleSpace1 = vehicleSpaceFactory.getVehicle(Constant.CYCLE_SPACE);
			cycleSpace1.setPrice(100);
			vehicleService.save(cycleSpace1);

			// CarSpace
			carSpace1 = vehicleSpaceFactory.getVehicle(Constant.CAR_SPACE);
			carSpace1.setWidth(3);
			carSpace1.setHeight(1.5);
			carSpace1.setPrice(200);
			vehicleService.save(carSpace1);
			carSpace2 = vehicleSpaceFactory.getVehicle(Constant.CAR_SPACE);
			carSpace2.setWidth(4);
			carSpace2.setHeight(2);
			carSpace2.setPrice(300);
			vehicleService.save(carSpace2);
			carSpace3 = vehicleSpaceFactory.getVehicle(Constant.CAR_SPACE);
			carSpace3.setWidth(5);
			carSpace3.setHeight(2.5);
			carSpace3.setPrice(400);
			vehicleService.save(carSpace3);

			// TruckSpace
			truckSpace1 = vehicleSpaceFactory.getVehicle(Constant.TRUCK_SPACE);
			truckSpace1.setWidth(6);
			truckSpace1.setHeight(3);
			truckSpace1.setPrice(500);
			vehicleService.save(truckSpace1);
			truckSpace2 = vehicleSpaceFactory.getVehicle(Constant.TRUCK_SPACE);
			truckSpace2.setWidth(7);
			truckSpace2.setHeight(3.5);
			truckSpace2.setPrice(600);
			vehicleService.save(truckSpace2);

			// FloorSpace
			createFloorAndSlot();
			
		} catch (Exception e) {
			log.info(e.getMessage());
		}
	}

	private static void createFloorAndSlot() {
		floor = new Floor("1階");
		List<Slot> slots = new ArrayList<>();
		// 単車スペース
		slot = new Slot(cycleSpace1, "101");
		slot.setFloor(floor);
		slots.add(slot);
		slot = new Slot(cycleSpace1, "102");
		slot.setFloor(floor);
		slots.add(slot);
		slot = new Slot(cycleSpace1, "103");
		slot.setFloor(floor);
		slots.add(slot);
		slot = new Slot(cycleSpace1, "104");
		slot.setFloor(floor);
		slots.add(slot);
		floor.setSlots(slots);
		floorService.save(floor);
		
		floor = new Floor("2階");
		slots = new ArrayList<>();
		
		// 普通車スペース
		slot = new Slot(carSpace1, "201");
		slot.setFloor(floor);
		slots.add(slot);
		slot = new Slot(carSpace1, "202");
		slot.setFloor(floor);
		slots.add(slot);
		slot = new Slot(carSpace2, "203");
		slot.setFloor(floor);
		slots.add(slot);
		slot = new Slot(carSpace2, "204");
		slot.setFloor(floor);
		slots.add(slot);
		floor.setSlots(slots);
		floorService.save(floor);
		
		floor = new Floor("3階");
		slots = new ArrayList<>();
		
		// 普通車スペース
		slot = new Slot(carSpace3, "301");
		slot.setFloor(floor);
		slots.add(slot);
		slot = new Slot(carSpace3, "302");
		slot.setFloor(floor);
		slots.add(slot);
		
		// 大型車スペース
		slot = new Slot(truckSpace1, "303");
		slot.setFloor(floor);
		slots.add(slot);
		slot = new Slot(truckSpace1, "304");
		slot.setFloor(floor);
		slots.add(slot);
		slot = new Slot(truckSpace2, "305");
		slot.setFloor(floor);
		slots.add(slot);
		slot = new Slot(truckSpace2, "306");
		slot.setFloor(floor);
		slots.add(slot);
		floor.setSlots(slots);
		floorService.save(floor);
		
	}

}
