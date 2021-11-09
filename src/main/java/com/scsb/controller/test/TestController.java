package com.scsb.controller.test;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scsb.config.Constants;
import com.scsb.model.ManagerTask;
import com.scsb.service.CommonService;

@Controller
@RequestMapping("/")
public class TestController 
{	
	@Autowired
    private CommonService commonService;
	
	@RequestMapping("/test")
	public String subAgencyList(Model model, HttpServletRequest request) 
	{
		// 設置頁面功能表
		String result = commonService.setCommonInfo(model, request);
		
		request.getSession().getAttribute(Constants.SESSION_MEMBER_KEY);
		return "views/test";
	}
}