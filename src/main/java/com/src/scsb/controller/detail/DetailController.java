package com.src.scsb.controller.detail;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.src.scsb.model.Sheet;
import com.src.scsb.service.SheetService;

@Controller
public class DetailController {
	
	@Autowired
	private SheetService sheetService;
	
	@RequestMapping("/detailPage/{id}")
	public String detail(Model model, HttpServletRequest request, @PathVariable Number id) 
	{
		LocalDateTime now = LocalDateTime.now();
		List<Sheet> detailList = sheetService.getDetail(now, id);
		Sheet detail = detailList.isEmpty() ? null : detailList.get(0);
		
		System.out.println(detailList.toString());
		
		model.addAttribute("detail", detail != null ? detail : Collections.emptyMap());
		
		if (detail != null) {
			String type = detail.getType();
			// 讓左邊的menu能跟著頁面顯示紅色字體
			switch (type) {
			case "0": // 本行公告
				model.addAttribute("menuIndex", "C01");
				break;
			case "1":// 最新公告
				model.addAttribute("menuIndex", "C02");
				break;
			case "3":// 中獎名單
				model.addAttribute("menuIndex", "C03");
				break;
			default:
				model.addAttribute("menuIndex", "C01");
				break;
			}
		} else {
			model.addAttribute("menuIndex", "C01");
		}
		
		// TODO 尚未實作表單來自哪個單位, 暫時顯示同樣的tag
		
		return "views/detail/index";
	}
	
	@RequestMapping("/detailList/{type}")
	public String detailList(Model model, HttpServletRequest request, @PathVariable String type) 
	{
		LocalDateTime now = LocalDateTime.now();
		List<Sheet> detailList = sheetService.getDetailList(now, type);
		model.addAttribute("listTitle",detailType(type));
		model.addAttribute("detailList", detailList);
		// 讓左邊的menu能跟著頁面顯示紅色字體
		switch (type) {
		case "0": // 本行公告
			model.addAttribute("menuIndex", "C01");
			break;
		case "1":// 最新公告
			model.addAttribute("menuIndex", "C02");
			break;
		case "3":// 中獎名單
			model.addAttribute("menuIndex", "C03");
			break;
		default:
			model.addAttribute("menuIndex", "C01");
			break;
		}
		
		// TODO 尚未實作表單來自哪個單位, 暫時顯示同樣的tag
		
		return "views/detailList/index";
	}
	
	private String detailType(String type) {
		
		switch (type) {
		case "0":
			return "本行公告";
		case "1":
			return "最新公告";
		case "3":
			return "中獎名單";
		default:
			return null;
		}
		
	}

}
