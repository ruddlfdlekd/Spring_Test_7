package com.iu.util;

import org.springframework.web.multipart.MultipartFile;

public class FileDTO {
	private String id;
	private MultipartFile f1;
	private MultipartFile f2;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public MultipartFile getF1() {
		return f1;
	}
	public void setF1(MultipartFile f1) {
		this.f1 = f1;
	}
	public MultipartFile getF2() {
		return f2;
	}
	public void setF2(MultipartFile f2) {
		this.f2 = f2;
	}
	
	
}
