package com.src.scsb.controller.dig;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.src.scsb.config.Constants;
import com.src.scsb.model.Sheet;
import com.src.scsb.service.SheetService;

@Controller
public class DigController 
{
	@Autowired
	private SheetService sheetService;
	
	@RequestMapping("/dig")
	public String digit(Model model, HttpServletRequest request) 
	{
		LocalDateTime now = LocalDateTime.now();
		List<Sheet> bannerList = sheetService.getPassedListByType(now, Constants.DIGIT_BANNER_SHEET_TYPE);
		List<Sheet> adList = sheetService.getPassedListByType(now, Constants.DIGIT_AD_SHEET_TYPE);
		List<Sheet> newsList = sheetService.getListByNewsLimit3(now);
		System.out.println(adList);
		model.addAttribute("bannerList", bannerList);
		model.addAttribute("newsList", newsList);
		model.addAttribute("adList", adList);
		return "views/dig/index";
	}
}