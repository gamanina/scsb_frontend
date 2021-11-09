package com.scsb.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DataOption {
		
	/**
	 * 取得狀態option
	 * @return Map<String, String>
	 */
	public Map<String, String> getStatusMap()
	{
		Map<String,String> statusMap = new HashMap<String,String>();
		statusMap.put("0", "關閉");
		statusMap.put("1", "開啟");
		return statusMap;
	}
	
	/**
	 * 取得表單狀態option
	 * @return Map<String, String>
	 */
	public Map<String, String> getSheetStatusMap()
	{
		Map<String,String> statusMap = new HashMap<String,String>();
		statusMap.put("0", "處理中-刊登");
		statusMap.put("1", "已通過");
		statusMap.put("2", "已退回");
		statusMap.put("3", "已停刊");
		statusMap.put("4", "處理中-停刊");
		return statusMap;
		
	}
	
	/**
	 * 取得表單名稱option
	 * @return Map<String, String>
	 */
	public Map<String, String> getSheetTypeMap()
	{
		Map<String,String> typeMap = new LinkedHashMap<String,String>();
		typeMap.put("0", "本行公告");
		typeMap.put("1", "最新活動");
		typeMap.put("2", "廣告輪播");
		typeMap.put("3", "中獎名單");
		typeMap.put("4", "企金輪播");
		typeMap.put("5", "企金廣告");
		typeMap.put("6", "個金輪播");
		typeMap.put("7", "個金廣告");
		typeMap.put("8", "台外幣輪播");
		typeMap.put("9", "台外幣廣告");
		typeMap.put("10", "數金輪播");
		typeMap.put("11", "數金廣告");
		return typeMap;
	}
	
	DataOption()
	{
		
	}

}
