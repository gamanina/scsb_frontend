package com.scsb.controller.preview;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.scsb.config.Constants;
import com.scsb.config.MessageConstants;
import com.scsb.model.Sheet;
import com.scsb.service.CommonService;
import com.scsb.service.SheetService;
import com.scsb.util.LogUtil;

@Controller
@RequestMapping("/")
public class PreviewController {
	
	@Autowired
    private CommonService commonService;
	@Autowired
	private SheetService sheetService;
	
	@Value("${web.upload-path}")
	private String uploadPath;
	
	@RequestMapping("/previewBusinessBanner")
	 public String previewBusinessBanner(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","businessBanner");
			return "views/common/alert";
		}
		return Constants.PREVIEW_BUSINESS_BANNER;
	 }

	@RequestMapping("/previewBusinessAd")
	public String previewBusinessAd(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request) 
	{
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","businessAd");
			return "views/common/alert";
		}
		return Constants.PREVIEW_BUSINESS_AD;
	}
	
	@RequestMapping("/previewDepositBanner")
	 public String previewDepositBanner(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","depositBanner");
			return "views/common/alert";
		}
		return Constants.PREVIEW_DEPOSIT_BANNER;
	 }
	
	@RequestMapping("/previewDepositAd")
	public String previewDepositAd(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request) 
	{
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","businessAd");
			return "views/common/alert";
		}
		return Constants.PREVIEW_DEPOSIT_AD;
	}
	
	@RequestMapping("/previewDigitBanner")
	 public String previewDigitBanner(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","digitBanner");
			return "views/common/alert";
		}
		return Constants.PREVIEW_DIGIT_BANNER;
	 }
	
	@RequestMapping("/previewDigitAd")
	public String previewDigitAd(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request) 
	{
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","businessAd");
			return "views/common/alert";
		}
		return Constants.PREVIEW_DIGIT_AD;
	}
	
	@RequestMapping("/previewPersonalBanner")
	 public String previewPersonalBanner(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","personalBanner");
			return "views/common/alert";
		}
		return Constants.PREVIEW_PERSONAL_BANNER;
	 }
	
	@RequestMapping("/previewPersonalAd")
	public String previewPersonalAd(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request) 
	{
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","businessAd");
			return "views/common/alert";
		}
		return Constants.PREVIEW_PERSONAL_AD;
	}
	
	private String convertToBase64(MultipartFile imageFile) throws Exception
	{
		if(imageFile == null) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		try {
			byte[] imageByteArray = imageFile.getBytes();
			sb.append("data:image/png;base64,");
			sb.append(StringUtils.newStringUtf8(Base64.encodeBase64(imageByteArray, false)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	@RequestMapping("/previewImgUrl")
	 public String previewImgUrl(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request)
	{	
		Sheet sheet = sheetService.getSheetById(Integer.parseInt(form.getScsbSheetId()));
		//TODO: image路徑待確認
		model.addAttribute("image",sheet.getImage());
		
		//取得預覽頁面
		String previewPage="/";
		
		switch(sheet.getType()) {
		
	    case Constants.BUSINESS_BANNER_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_BUSINESS_BANNER;
	        break;
	    case Constants.BUSINESS_AD_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_BUSINESS_AD;
	        break;
	    case Constants.PERSONAL_BANNER_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_PERSONAL_BANNER;
	        break;
	    case Constants.PERSONAL_AD_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_PERSONAL_AD;
	        break;
	    case Constants.DEPOSIT_BANNER_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_DEPOSIT_BANNER;
	        break;
	    case Constants.DEPOSIT_AD_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_DEPOSIT_AD;
	        break;
	    case Constants.DIGIT_BANNER_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_DIGIT_BANNER;
	        break;
	    case Constants.DIGIT_AD_SHEET_TYPE:
	    	previewPage = Constants.PREVIEW_DIGIT_AD;
	        break;
	        
	    default:
	        	model.addAttribute("result",Constants.RESULT_ERROR);
				model.addAttribute("msg",MessageConstants.MESSAGE_LOAD_PREVIEW_PAGE_ERROR);
				model.addAttribute("redirectUrl","/");
			    LogUtil.setActionLog(" previewImgUrl loadPage: ", "id: " + sheet.getId() + " type: " + sheet.getType());
				return "views/common/alert";
		}
		
		return previewPage;
	}
	
	@RequestMapping("/previewIndexAnnounce")
	 public String previewIndexAnnounce(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			System.out.println(form.getContent());
			model.addAttribute("image",base64);
			model.addAttribute("content",form.getContent());
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","indexAnnounce");
			return "views/common/alert";
		}
		return Constants.PREVIEW_INDEX_ANNOUNCE;
	 }
	
	@RequestMapping("/previewIndexActivity")
	 public String previewIndexActivity(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
			model.addAttribute("content",form.getContent());
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","indexActivity");
			return "views/common/alert";
		}
		return Constants.PREVIEW_INDEX_ACTIVITY;
	 }
	
	@RequestMapping("/previewIndexBanner")
	 public String previewIndexBanner(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","indexBanner");
			return "views/common/alert";
		}
		return Constants.PREVIEW_INDEX_BANNER;
	 }
	
	@RequestMapping("/previewIndexWinners")
	 public String previewIndexWinners(@ModelAttribute PreviewForm form, Model model, HttpServletRequest request){
		try {
			String base64 = convertToBase64(form.getImageFile());
			model.addAttribute("image",base64);
			model.addAttribute("content",form.getContent());
		} catch (Exception e) {
			model.addAttribute("result",Constants.RESULT_ERROR);
			model.addAttribute("msg",MessageConstants.MESSAGE_CONVERT_IMAGE_ERROR);
			model.addAttribute("redirectUrl","indexWinners");
			return "views/common/alert";
		}
		return Constants.PREVIEW_INDEX_WINNERS;
	 }
}