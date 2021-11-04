package com.src.scsb.config;

/**
 * 
 * 建立日期：2020/04/15
 * 程式摘要：com.scsb.config
 * 類別名稱：Constants.java
 * 程式內容說明：共用常數
 * @author Louis
 * @version 1.0
 * @since 1.0
 */
public class Constants 
{
	/** 啟用狀態  0:關閉 1:開啟 2:封存 3:未開通 **/
    public static final String STATUS_ENABLE = "1";
    public static final String STATUS_DISABLE = "0";
	
	/** 時間格式 **/
	public static final String TIME_FORMAT_YYYYMMDD_HHMMSS = "yyyy/MM/dd HH:mm:ss";
	public static final String TIME_FORMAT_YYYYMMDD = "yyyy/MM/dd";
	public static final String TIME_FORMAT_YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	/** 系統常用 **/
	public static final String CHARSET_UTF8 = "UTF-8";
	public static final String SYSTEM_ADMIN = "SYSTEM";
    
    /** 表單狀態 狀態0:處理中-刊登,1:已通過,2:已退回,3:已下架,4:處理中-停刊 **/
    public static final String SHEET_STATUS_PROCESSING = "0";
    public static final String SHEET_STATUS_PASSED = "1";
    public static final String SHEET_STATUS_RETURNED = "2";
    public static final String SHEET_STATUS_OFF_SHELF = "3";
    public static final String SHEET_STATUS_OFF_SHELF_PROCESSING = "4";
    
    /** 表單型態 **/
    public static final String INDEX_ANNOUNCE_SHEET_TYPE = "0";
    public static final String INDEX_ACTIVITY_SHEET_TYPE = "1";
    public static final String INDEX_BANNER_SHEET_TYPE = "2";
    public static final String INDEX_WINNERS_SHEET_TYPE = "3";
    public static final String BUSINESS_BANNER_SHEET_TYPE = "4";
    public static final String BUSINESS_AD_SHEET_TYPE = "5";
    public static final String PERSONAL_BANNER_SHEET_TYPE = "6";
    public static final String PERSONAL_AD_SHEET_TYPE = "7";
    public static final String DEPOSIT_BANNER_SHEET_TYPE = "8";
    public static final String DEPOSIT_AD_SHEET_TYPE = "9";
    public static final String DIGIT_BANNER_SHEET_TYPE = "10";
    public static final String DIGIT_AD_SHEET_TYPE = "11";
}