package com.src.scsb.controller.detail;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.src.scsb.config.Constants;
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
		
		System.out.println(detailList.toString());
		
		model.addAttribute("detailList", detailList);
		
		return "views/detail/index";
	}
	
	@RequestMapping("/detailList/{type}")
	public String detailList(Model model, HttpServletRequest request, @PathVariable String type) 
	{
		LocalDateTime now = LocalDateTime.now();
		List<Sheet> detailList = sheetService.getDetailList(now, type);
		model.addAttribute("listTitle",detailType(type));
		model.addAttribute("detailList", detailList);
		
		
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
