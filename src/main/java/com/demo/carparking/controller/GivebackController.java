package com.demo.carparking.controller;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.demo.carparking.common.Constant;
import com.demo.carparking.model.RentSlot;
import com.demo.carparking.model.Slot;
import com.demo.carparking.service.RentSlotService;
import com.demo.carparking.service.SlotService;

@Controller
public class GivebackController {

	@Autowired
	SlotService slotService;

	@Autowired
	RentSlotService rentSlotService;

	String pageName = "giveBack";

	Logger log = LoggerFactory.getLogger(GivebackController.class);
	
	@PostMapping("/search_slot")
	public String search_slot(@ModelAttribute Slot slot, Model model) {
		log.info("******search_slot start******");
		//　エラーチェック
		if (null == slot.getSlotNumber() || slot.getSlotNumber().equals("")) {
			model.addAttribute("error", "番号は必要です。");
			model.addAttribute("success", null);
			return pageName;
		}
		//　番号とステイタスで検索
		Slot s = slotService.findBySlotNumberAndStatus(slot.getSlotNumber(), 1);
		if (null == s) {
			model.addAttribute("error", "対象データがありません。");
			model.addAttribute("success", null);
		} else {
			RentSlot rs = rentSlotService.findBySlotIdAndDeleteFlg(s.getId(), 0);
			model.addAttribute("error", "");
			String endTime = Constant.SDF.format(Calendar.getInstance().getTime());
			rs.setEndTime(endTime);
			try {
				Date startDate = Constant.SDF.parse(rs.getStartTime());
				Date endDate = Constant.SDF.parse(endTime);
				long difference = ((endDate.getTime() - startDate.getTime()) / 1000) / 60;
				
				// 利用時間表示のため
				int hour = (int) difference / 60;
				int minute = (int) difference % 60;
				String utilizationTime = hour + "時 " + minute + "分";
				rs.setUtilizationTime(utilizationTime);
				
				// 合計金額を計算する
				int diff1 = (int) difference / 30;
				int diff2 = difference % 30 == 0 ? 0 : 1;
				rs.setTotalPrice((int) (diff1 + diff2) * rs.getSlot().getVehicleSpace().getPrice());
				rentSlotService.save(rs);
				model.addAttribute("error", null);
				model.addAttribute("rentSlot", rs);
			} catch (ParseException e) {
				log.error(e.getMessage());
				
			}
		}
		log.info("******search_slot end******");
		return pageName;
	}

	@PostMapping("/giveback")
	public String giveback(@ModelAttribute RentSlot rentSlot, Model model) {
		log.info("******giveback start******");
		Optional<RentSlot> optRentSlot = rentSlotService.findById(rentSlot.getId());
		Optional<Slot> optSlot = null;
		if (optRentSlot.isPresent()) {
			RentSlot rs = optRentSlot.get();
			optSlot = slotService.findById(rs.getSlot().getId());
			if (optSlot.isPresent()) {
				Slot s = optSlot.get();
				// スペースを再利用する
				s.setStatus(0);
				// 出車した行の削除フラグを１に変更
				rs.setDeleteFlg(1);
				slotService.save(s);
				model.addAttribute("slot", new Slot());
				model.addAttribute("rentSlot", null);
				model.addAttribute("success", "ご利用ありがとうございます。\nまたのご利用をお待ちしております。");
				log.info("******giveback end******");
				return pageName;
			} else {
				model.addAttribute("slot", new Slot());
				model.addAttribute("rentSlot", null);
				model.addAttribute("error", "管理者に問い合わせください");
				model.addAttribute("success", null);
				log.info("******giveback end******");
				return null;
			}
		} else {
			model.addAttribute("slot", new Slot());
			model.addAttribute("rentSlot", null);
			model.addAttribute("error", "管理者に問い合わせください");
			model.addAttribute("success", null);
			log.info("******giveback end******");
			return null;
		}
	}
}
