package com.demo.carparking.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.carparking.model.Slot;
import com.demo.carparking.model.VehicleSpace;
import com.demo.carparking.service.SlotService;
import com.demo.carparking.service.VehicleService;


@Controller
public class RentController {

	@Autowired
	SlotService slotService;
	
	@Autowired
	VehicleService vehicleService;
	
	String pageName = "rent";
	
	Logger log = LoggerFactory.getLogger(RentController.class);
	
	@GetMapping(value="/choose_type", params="action=cycleSpace")
	public String cycleSpace(Model model) {
		log.info("******cycleSpace start******");
		// 単車ボタンを押す処理
		model.addAttribute("slots", slotService.findUsableSlot());
		log.info("******cycleSpace end******");
		return pageName;
	}

	@GetMapping(value="/choose_type", params="action=carSpace")
	public String carSpace(Model model) {
		log.info("******carSpace start******");
		// 普通車ボタンを押す処理
		model.addAttribute("slots", null);
		model.addAttribute("showCarSpaceSearchForm", true);
		model.addAttribute("vehicleSpace", new VehicleSpace());
		log.info("******carSpace end******");
		return pageName;
	}
	
	@GetMapping(value="/choose_type", params="action=truckSpace")
	public String truckSpace(Model model) {
		log.info("******truckSpace start******");
		// 大型車ボタンを押す処理
		model.addAttribute("slots", null);
		model.addAttribute("showTruckSpaceSearchForm", true);
		model.addAttribute("vehicleSpace", new VehicleSpace());
		log.info("******truckSpace end******");
		return pageName;
	}
	
	@GetMapping("/rent_slot/{slotId}")
	public String useSlot(@PathVariable String slotId) {
		log.info("******useSlot start******");
		// 利用ボタンを押す処理
		slotService.updateStautsBySlotId(1, Long.parseLong(slotId));
		log.info("******useSlot end******");
	    return "index";
	}
	
	@PostMapping("/search_car_space")
	public String searchCarSpace(@ModelAttribute VehicleSpace vs, Model model) {
		log.info("******searchCarSpace start******");
		// 普通車を検索処理
		model.addAttribute("showCarSpaceSearchForm", true);
		List<Long> vehicleSpaces = vehicleService.findCarSpace(vs.getWidth(), vs.getHeight());
		model.addAttribute("vehicleSpace", vs);
		List<Slot> slots = slotService.findByVehicleSpaceIdIn(vehicleSpaces);
		model.addAttribute("slots", slots);
		if(null == slots || slots.size() == 0) {
			model.addAttribute("error", "対象データがありません。");
		}
		log.info("******searchCarSpace end******");
		return pageName;
	}
	
	@PostMapping("/search_truck_space")
	public String searchTruckSpace(@ModelAttribute VehicleSpace vs, Model model) {
		log.info("******searchTruckSpace start******");
		// 大型車を検索処理
		model.addAttribute("showTruckSpaceSearchForm", true);
		List<Long> vehicleSpaces = vehicleService.findTruckSpace(vs.getWidth(), vs.getHeight());
		model.addAttribute("vehicleSpace", vs);
		List<Slot> slots = slotService.findByVehicleSpaceIdIn(vehicleSpaces);
		model.addAttribute("slots", slots);
		if(null == slots || slots.size() == 0) {
			model.addAttribute("error", "対象データがありません。");
		}
		log.info("******searchTruckSpace end******");
		return pageName;
	}
}
