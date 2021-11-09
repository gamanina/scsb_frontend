package com.scsb.config;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

/**
 * 
 * 建立日期：2020/08/03
 * 程式摘要：com.config
 * 類別名稱：MessageConstants.java
 * 程式內容說明：訊息常數
 * @author Stan
 * @version 1.0
 * @since 1.0
 */
public class MessageConstants {
	public final static String MESSAGE_INSERT_SUCCESS = "新增成功。";
	public final static String MESSAGE_UPDATE_SUCCESS = "更新資料成功。";
	public final static String MESSAGE_DATA_ERROR = "資料有誤，請重新確認。";
	public final static String MESSAGE_ACCOUNT_ERROR = "帳號或密碼不正確，請再檢查一次。";
	public final static String MESSAGE_SYSTEM_ERROR = "系統忙碌中，請稍後再試";
	public final static String MESSAGE_RIGHTS_ERROR = "無此權限";
	public final static String MESSAGE_SAME_DATA_ERROR = "資料重複";
	public final static String MESSAGE_LOGIN_LDAP_ERROR = "登入發生預期外錯誤。";
	public final static String MESSAGE_APPROVED_ERROR = "此表單已被簽核";
	
	public final static String EMPTY_ACCOUNT = "查無此帳號。";
	public final static String EMPTY_DATA = "查無資料。";
	public final static String EMPTY_APPLICANT = "查無申請資料。";
	
	public final static String MESSAGE_CONVERT_IMAGE_ERROR = "圖片轉換有誤，請重新確認。";
	public final static String MESSAGE_LOAD_PAGE_ERROR = "查無帳號紀錄，請重新登入。";
	public final static String MESSAGE_LOAD_PREVIEW_PAGE_ERROR = "預覽出現非預期錯誤，請重新再試。";

	
	public final static String MESSAGE_INDEX_BANNER_FULL_INFO = "廣告輪播版面已滿，請於";
	public final static String MESSAGE_INDEX_BANNER_REPEAT_INFO = "已有部門廣告輪播中，請於";
	public final static String MESSAGE_INDEX_BANNER_END_INFO = "後申請。";
	
	public final static String MESSAGE_INDEX_BANNER_PUBLISH = "(可刊登)";
	public final static String MESSAGE_INDEX_BANNER_UNPUBLISH = "不可刊登，最近可刊登時間為：";
	
	public static String getRepeatUploadTimeMessage(Timestamp time)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT_YYYYMMDD_HHMMSS);
		return MESSAGE_INDEX_BANNER_REPEAT_INFO + sdf.format(time) + MESSAGE_INDEX_BANNER_END_INFO;
	}
	
	public static String getFullUploadTimeMessage(Timestamp time)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT_YYYYMMDD_HHMMSS);
		return MESSAGE_INDEX_BANNER_FULL_INFO + sdf.format(time) + MESSAGE_INDEX_BANNER_END_INFO;
	}
	
	public static String getPublishTimeMessage(Timestamp time)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(Constants.TIME_FORMAT_YYYYMMDD_HHMMSS);
		return "(" + MESSAGE_INDEX_BANNER_UNPUBLISH + sdf.format(time) + ")";
	}
}
