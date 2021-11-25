package com.src.scsb.controller.card;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.src.scsb.config.Constants;
import com.src.scsb.model.Sheet;
import com.src.scsb.service.SheetService;

/**
 * <pre>
		map.put("ticket", "票券/航空");
		map.put("point", "點數/捐款");
		map.put("airport", "機場接送");
		map.put("discount", "費用折抵");
		map.put("exclusive", "獨家商品");
 * </pre>
 * @author Arvin
 *
 */
@RequestMapping("/card/reward")
@Controller
public class CardRewardController {
	
	@Autowired
	private SheetService sheetService;
	
	Map<String, String> categoryMap = Constants.CARD_REWARD_CATEGORY_MAP;

	/**
	 * 信用卡 > 紅利積點 > 紅利兌換
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/all")
	public String all(Model model, HttpServletRequest request) {
		return "views/card/reward/reward_all";
	}
	
	/**
	 * 信用卡 > 紅利積點 > 紅利兌換 > 商品商城
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/shop")
	public String exShop(Model model, HttpServletRequest request) {
		return "views/card/reward/warms";
	}

	/**
	 * 信用卡 > 紅利積點 > 紅利兌換 > 票券/航空
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/ti")
	public String ti(Model model, HttpServletRequest request) {
//		return "views/card/reward/reward_ti_01";
		return getDetailListAndReturnView(model, "ticket");// 分類(Constants.CARD_REWARD_CATEGORY_MAP)
	}

	/**
	 * 信用卡 > 紅利積點 > 紅利兌換 > 點數/捐款
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/po")
	public String po(Model model, HttpServletRequest request) {
//		return "views/card/reward/reward_po_01";
		return getDetailListAndReturnView(model, "point");// 分類(Constants.CARD_REWARD_CATEGORY_MAP)
	}

	/**
	 * 信用卡 > 紅利積點 > 紅利兌換 > 機場接送
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/ai")
	public String ai(Model model, HttpServletRequest request) {
//		return "views/card/reward/reward_ai_01";
		return getDetailListAndReturnView(model, "airport");// 分類(Constants.CARD_REWARD_CATEGORY_MAP)
	}

	/**
	 * 信用卡 > 紅利積點 > 紅利兌換 > 費用折抵(內含紅利金)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/se")
	public String se(Model model, HttpServletRequest request) {
//		return "views/card/reward/reward_se_01";
		return getDetailListAndReturnView(model, "discount");// 分類(Constants.CARD_REWARD_CATEGORY_MAP)
	}

	/**
	 * 信用卡 > 紅利積點 > 紅利兌換 > 獨家商品
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/ex")
	public String ex(Model model, HttpServletRequest request) {
//		return "views/card/reward/reward_ex_01";
		return getDetailListAndReturnView(model, "exclusive");// 分類(Constants.CARD_REWARD_CATEGORY_MAP)
	}
	
	private String getDetailListAndReturnView(Model model, String type) {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		List<Sheet> detailList = sheetService.getDetailListByTypeAndCategory(
				now,
				Constants.CARD_REWARD_SHEET_TYPE,// 信用卡紅利兌換表單TYPE
				type// 分類(Constants.CARD_REWARD_CATEGORY_MAP)
				);
		
		
		model.addAttribute("sheetTag", categoryMap.get(type));// 分類代碼中文
		model.addAttribute("detailList", detailList);// 結果清單
		return "views/card/reward/reward_detail";
	}
	
}