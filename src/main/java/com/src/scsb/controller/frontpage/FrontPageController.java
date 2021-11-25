package com.src.scsb.controller.frontpage;

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
public class FrontPageController {
	@Autowired
	private SheetService sheetService;
	
	@RequestMapping("/FrontPage")
	public String digit(Model model, HttpServletRequest request) 
	{
		LocalDateTime now = LocalDateTime.now();
		List<Sheet> bannerList = sheetService.getPassedListByType(now, Constants.PERSONAL_BANNER_SHEET_TYPE);
		List<Sheet> adList = sheetService.getPassedListByType(now, Constants.PERSONAL_AD_SHEET_TYPE);
		List<Sheet> announceList = sheetService.getPassedListByType(now,Constants.INDEX_ANNOUNCE_SHEET_TYPE);
		List<Sheet> activityList = sheetService.getPassedListByType(now, Constants.INDEX_ACTIVITY_SHEET_TYPE);
		List<Sheet> winnerList = sheetService.getPassedListByType(now, Constants.INDEX_WINNERS_SHEET_TYPE);
		List<Sheet> indexBannerList = sheetService.getIndexBanners();
		System.out.println("HomePage");
		System.out.println(indexBannerList.toString());
		model.addAttribute("indexBannerList",indexBannerList);
		model.addAttribute("bannerList", bannerList);
		model.addAttribute("announceList", announceList);
		model.addAttribute("activityList",activityList);
		model.addAttribute("adList", adList);
		model.addAttribute("winnerList", winnerList);
		return "views/FrontPage/index";
	}
	
	@RequestMapping("/")
	public String root(Model model, HttpServletRequest request) 
	{
		return "redirect:FrontPage";
	}
}
