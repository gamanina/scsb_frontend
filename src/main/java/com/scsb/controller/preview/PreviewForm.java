package com.scsb.controller.preview;

import org.springframework.web.multipart.MultipartFile;

public class PreviewForm {
	private String scsbSheetId;
    private String image;
    private MultipartFile imageFile;
    private String content;
	
	public String getScsbSheetId() {
		return scsbSheetId;
	}
	public void setScsbSheetId(String scsbSheetId) {
		this.scsbSheetId = scsbSheetId;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public MultipartFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MultipartFile imageFile) {
		this.imageFile = imageFile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
