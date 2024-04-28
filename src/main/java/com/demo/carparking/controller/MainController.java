package com.demo.carparking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.demo.carparking.model.Slot;

@Controller
public class MainController {

	@GetMapping("/")
	public String main(Model model) {
		return "index";
	}
	
	@GetMapping(value="/car_parking", params="action=rent")
	public String rent(Model model) {
		// 入車ボタンを押す処理
		return "rent";
	}

	@GetMapping(value="/car_parking", params="action=giveback")
	public String giveback(Model model) {
		// 出車ボタンを押す処理
		model.addAttribute("slot", new Slot());
		return "giveBack";
	}
}
