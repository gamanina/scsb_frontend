package com.src.scsb.config;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

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
    /** 信用卡 - 廣告輪播 */
    public static final String CARD_BANNER_SHEET_TYPE = "12";
    /** 信用卡 - 廣告 */
    public static final String CARD_AD_SHEET_TYPE = "13";
    /** 信用卡 - 刷卡優惠 > 熱門活動 */
    public static final String CARD_SWIPE_HOT_SHEET_TYPE = "14";
    /** 信用卡 - 刷卡優惠 > 新戶禮 */
    public static final String CARD_SWIPE_GIFT_SHEET_TYPE = "15";
    /** 信用卡 - 刷卡優惠 > 優惠商店 */
    public static final String CARD_SWIPE_DISCOUNT_SHOP_SHEET_TYPE = "16";
    /** 信用卡 - 紅利積點 > 紅利兌換 */
    public static final String CARD_REWARD_SHEET_TYPE = "17";
    /** 信用卡 - 紅利積點 > 紅利折抵 */
    public static final String CARD_REWARD_REDEEM_SHEET_TYPE = "18";
    /** 信用卡 - Debit卡 > 刷卡優惠 */
    public static final String CARD_DEBIT_DISCOUNT_SHEET_TYPE = "19";
    
    /** ==表單類別代碼中文對照== **/
    static {
    	Map<String, String> map = new LinkedHashMap<String, String>();
    	// 信用卡 - 刷卡優惠 > 熱門活動分類
		map.put("bonus", "紅利點數");
		map.put("travel", "旅遊活動");
		map.put("shopping", "網購活動");
		map.put("installment", "分期付款");
		map.put("mobilepay", "行動支付");
		map.put("cdcard", "卡片優惠");
		CARD_SWIPE_HOT_CATEGORY_MAP = Collections.unmodifiableMap(map);
		
		map = new LinkedHashMap<String, String>();
		// 信用卡 - 刷卡優惠 > 優惠商店分類
		map.put("travel", "旅遊休閒");
		map.put("shopping", "美食購物");
		map.put("movie", "藝文電影");
		map.put("installment", "分期付款優惠商店");
		CARD_SWIPE_DISCOUNT_SHOP_CATEGORY_MAP = Collections.unmodifiableMap(map);
		
		// 信用卡 - 紅利積點 > 點數兌換
		map = new LinkedHashMap<String, String>();
		map.put("ticket", "票券/航空");
		map.put("point", "點數/捐款");
		map.put("airport", "機場接送");
		map.put("discount", "費用折抵");
		map.put("exclusive", "獨家商品");
		CARD_REWARD_CATEGORY_MAP = Collections.unmodifiableMap(map);
    }
    
    /** 信用卡 - 刷卡優惠 > 熱門活動分類代碼中文 
     * <pre>
     * 		map.put("bonus", "紅利點數");
     * 		map.put("travel", "旅遊活動");
     * 		map.put("shopping", "網購活動");
     * 		map.put("loan", "預借現金");
     * 		map.put("installment", "分期付款");;
     * 		map.put("mobilepay", "行動支付");
     * </pre>
     **/
    public static final Map<String, String> CARD_SWIPE_HOT_CATEGORY_MAP;
    /** 信用卡 - 刷卡優惠 > 優惠商店分類代碼中文
     * <pre>
     * 		map.put("travel", "旅遊休閒");
     * 		map.put("shopping", "美食購物");
     * 		map.put("movie", "藝文電影");
     * 		map.put("installment", "分期付款優惠商店");
     * </pre>
     **/
    public static final Map<String, String> CARD_SWIPE_DISCOUNT_SHOP_CATEGORY_MAP;
    /** 信用卡 - 紅利積點 > 點數兌換分類代碼中文 
     * <pre>
     * 		map.put("ticket", "票券/航空");
     * 		map.put("point", "點數/捐款");
     * 		map.put("airport", "機場接送");
     * 		map.put("discount", "費用折抵");
     * 		map.put("exclusive", "獨家商品");
     * </pre>
     **/
    public static final Map<String, String> CARD_REWARD_CATEGORY_MAP;
    
    /** ==清單分頁相關== **/
    /** 信用卡 - 刷卡優惠 > 熱門活動每頁最多8項 **/
    public static final int CARD_SWIPE_HOT_SIZE_PER_PAGE = 8;
    /** 信用卡 - 刷卡優惠 > 新戶禮每頁最多8項 **/
    public static final int CARD_SWIPE_GIFT_SIZE_PER_PAGE = 8;
    
    
}