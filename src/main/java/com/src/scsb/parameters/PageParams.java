package com.src.scsb.parameters;

import lombok.Data;

@Data
public class PageParams {
	private int page;
	private int size;
	
	public int getPage() {
		return page == 0 ? 1 : page;
	}
}
