package com.src.scsb.controller.card;

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

@RequestMapping("/card")
@Controller
public class CardController {
	@Autowired
	private SheetService sheetService;

	@RequestMapping(value = { "", "/" })
	public String card(Model model, HttpServletRequest request) {
		LocalDateTime now = LocalDateTime.now();
		List<Sheet> bannerList = sheetService.getPassedListByType(now, Constants.CARD_BANNER_SHEET_TYPE);
		List<Sheet> adList = sheetService.getPassedListByType(now, Constants.CARD_AD_SHEET_TYPE);
		List<Sheet> newsList = sheetService.getListByNewsLimit3(now);
		List<Sheet> hotsList = sheetService.getDetailList(now, Constants.CARD_SWIPE_HOT_SHEET_TYPE);

		hotsList.forEach(hot -> {
			hot.setContent(hot.getContent().replaceAll("<\\s*\\/?\\s*[a-zA-z_]([^>]*?[\"][^\"]*[\"])*[^>\"]*>", ""));
			hot.setCategoryName(Constants.CARD_SWIPE_HOT_CATEGORY_MAP.get(hot.getCategory()));
		});

		model.addAttribute("bannerList", bannerList);
		model.addAttribute("newsList", newsList);
		model.addAttribute("adList", adList);
		model.addAttribute("hotsList", hotsList);
		return "views/card/index";
	}
}