package com.src.scsb.controller.card;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.src.scsb.config.Constants;
import com.src.scsb.model.Sheet;
import com.src.scsb.parameters.PageParams;
import com.src.scsb.service.SheetService;

@RequestMapping("/card/debit")
@Controller
public class CardDebitController {
	
	@Autowired
	private SheetService sheetService;
	
	/**
	 * 信用卡 > Debit卡 > 刷卡優惠(清單)(不分類)
	 * @param model		
	 * @param request
	 * @param page		分頁參數
	 * @return
	 */
	@RequestMapping("/discountList")
	public String discountList(Model model, HttpServletRequest request, @Validated PageParams page) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Page<Sheet> detailList = sheetService.getDetailPageByTypeAndCategory(
				now,
				Constants.CARD_DEBIT_DISCOUNT_SHEET_TYPE,// 信用卡 - Debit卡 > 刷卡優惠表單TYPE
				"",// 分類
				PageRequest.of(page.getPage() - 1, Constants.CARD_SWIPE_HOT_SIZE_PER_PAGE)// 預設每頁顯示8筆
				);
		
		detailList.forEach(hot -> {
			// 將html標籤移除
			hot.setContent(hot.getContent().replaceAll("<\\s*\\/?\\s*[a-zA-z_]([^>]*?[\"][^\"]*[\"])*[^>\"]*>", ""));
		});
		
		model.addAttribute("detailList", detailList.getContent());// 結果清單
		model.addAttribute("totalPage", detailList.getTotalPages());// 總分頁數
		model.addAttribute("page", page.getPage());// 目前頁數
		
		return "views/card/debit/discountList";
	}
	
	
	/**
	 * 信用卡 > 刷卡優惠 > 刷卡優惠(內文)
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/discountPage/{id}")
	public String detailHot(Model model, HttpServletRequest request, @PathVariable Number id) 
	{
		LocalDateTime now = LocalDateTime.now();
		List<Sheet> detailList = sheetService.getDetail(now, id);
		
		Sheet sheet = detailList.get(0);
		model.addAttribute("detail", sheet);
		
		return "views/card/debit/discount";
	}

}
