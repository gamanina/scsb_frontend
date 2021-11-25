package com.src.scsb.controller.card;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

@RequestMapping("/card")
@Controller
public class CardDetailController {
	
	@Autowired
	private SheetService sheetService;
	
	/**
	 * 信用卡 > 刷卡優惠 > 熱門活動(清單)(不分類)
	 * @param model		
	 * @param request
	 * @param page		分頁參數
	 * @return
	 */
	@RequestMapping("/hotList")
	public String detailHotListAll(Model model, HttpServletRequest request, @Validated PageParams page) {
		return detailHotList(model, request, "", page);
	}
	
	/**
	 * 信用卡 > 刷卡優惠 > 熱門活動(清單)
	 * @param model
	 * @param request
	 * @param category	分類
	 * @param page		分頁參數
	 * @return
	 */
	@RequestMapping("/hotList/{category}")
	public String detailHotList(Model model, HttpServletRequest request, @PathVariable String category, @Validated PageParams page)
	{
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Page<Sheet> detailList = sheetService.getDetailPageByTypeAndCategory(
				now,
				Constants.CARD_SWIPE_HOT_SHEET_TYPE,// 信用卡熱門活動表單TYPE
				category,// 分類
				PageRequest.of(page.getPage() - 1, Constants.CARD_SWIPE_HOT_SIZE_PER_PAGE)// 預設每頁顯示8筆
				);
		
		detailList.forEach(hot -> {
			// 設定tag名稱
			hot.setCategoryName(Constants.CARD_SWIPE_HOT_CATEGORY_MAP.get(hot.getCategory()));
			// 將html標籤移除
			hot.setContent(hot.getContent().replaceAll("<\\s*\\/?\\s*[a-zA-z_]([^>]*?[\"][^\"]*[\"])*[^>\"]*>", ""));
		});
		
		model.addAttribute("detailList", detailList.getContent());// 結果清單
		model.addAttribute("category", category);// 分類(更新下拉選單已選取項目)
		model.addAttribute("totalPage", detailList.getTotalPages());// 總分頁數
		model.addAttribute("page", page.getPage());// 目前頁數
		
		return "views/card/swipeDiscount/cardHotList";
	}
	
	/**
	 * 信用卡 > 刷卡優惠 > 熱門活動(內文)
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/hotPage/{id}")
	public String detailHot(Model model, HttpServletRequest request, @PathVariable Number id) 
	{
		LocalDateTime now = LocalDateTime.now();
		List<Sheet> detailList = sheetService.getDetail(now, id);
		
		Sheet sheet = detailList.get(0);
		model.addAttribute("tagClass", sheet.getCategory());
		// 設定tag名稱
		model.addAttribute("sheetTag", Constants.CARD_SWIPE_HOT_CATEGORY_MAP.get(sheet.getCategory()));
		model.addAttribute("detail", sheet);
		
		return "views/card/swipeDiscount/cardHot";
	}

	/**
	 * 信用卡 > 刷卡優惠 > 新戶禮(清單)(不分類)
	 * @param model		
	 * @param request
	 * @param page		分頁參數
	 * @return
	 */
	@RequestMapping("/giftList")
	public String detailGiftListAll(Model model, HttpServletRequest request, @Validated PageParams page) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		Page<Sheet> detailList = sheetService.getDetailPageByTypeAndCategory(
				now,
				Constants.CARD_SWIPE_GIFT_SHEET_TYPE,// 信用卡新戶禮表單TYPE
				"",// 分類
				PageRequest.of(page.getPage() - 1, Constants.CARD_SWIPE_GIFT_SIZE_PER_PAGE)// 預設每頁顯示8筆
				);
		
		detailList.forEach(hot -> {
			// 將html標籤移除
			hot.setContent(hot.getContent().replaceAll("<\\s*\\/?\\s*[a-zA-z_]([^>]*?[\"][^\"]*[\"])*[^>\"]*>", ""));
		});
		
		model.addAttribute("detailList", detailList.getContent());// 結果清單
		model.addAttribute("totalPage", detailList.getTotalPages());// 總分頁數
		model.addAttribute("page", page.getPage());// 目前頁數
		
		return "views/card/swipeDiscount/cardGiftList";
	}
	
	/**
	 * 信用卡 > 刷卡優惠 > 新戶禮(內文)
	 * @param model
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/giftPage/{id}")
	public String detailGift(Model model, HttpServletRequest request, @PathVariable Number id) 
	{
		LocalDateTime now = LocalDateTime.now();
		List<Sheet> detailList = sheetService.getDetail(now, id);
		
		Sheet sheet = detailList.get(0);
		model.addAttribute("tagClass", sheet.getCategory());
		// 設定tag名稱
		model.addAttribute("sheetTag", Constants.CARD_SWIPE_HOT_CATEGORY_MAP.get(sheet.getCategory()));
		model.addAttribute("detail", sheet);
		
		return "views/card/swipeDiscount/cardGift";
	}
	
	/**
	 * 信用卡 > 刷卡優惠 > 優惠商店(不分類)
	 * @param model
	 * @param request
	 * @param category	分類
	 * @param page		分頁參數
	 * @return
	 */
	@RequestMapping("/discountShopList")
	public String cardDiscountShopList(Model model, HttpServletRequest request)
	{
		Timestamp now = new Timestamp(System.currentTimeMillis());
		List<Sheet> detailList = sheetService.getDetailListByTypeAndCategory(
				now,
				Constants.CARD_SWIPE_DISCOUNT_SHOP_SHEET_TYPE,// 信用卡優惠商店表單TYPE
				""// 分類
				);
//		("travel", "旅遊休閒");
//		("shopping", "美食購物");
//		("movie", "藝文電影");
//		("installment", "分期付款優惠商店");
		List<Sheet> detailTravelList = detailList.stream()
				.filter(sheet -> "travel".equals(sheet.getCategory()))
				.collect(Collectors.toList());// 旅遊休閒
		List<Sheet> detailShoppingList = detailList.stream()
				.filter(sheet -> "shopping".equals(sheet.getCategory()))
				.collect(Collectors.toList());// 美食購物
		List<Sheet> detailMovieList = detailList.stream()
				.filter(sheet -> "movie".equals(sheet.getCategory()))
				.collect(Collectors.toList());// 藝文電影
		List<Sheet> detailInstallmentList = detailList.stream()
				.filter(sheet -> "installment".equals(sheet.getCategory()))
				.collect(Collectors.toList());;// 分期付款優惠商店
		// 回傳結果
		model.addAttribute("detailTravelList", detailTravelList);
		model.addAttribute("detailShoppingList", detailShoppingList);
		model.addAttribute("detailMovieList", detailMovieList);
		model.addAttribute("detailInstallmentList", detailInstallmentList);
		
		return "views/card/swipeDiscount/cardDiscountShopList";
	}

}
