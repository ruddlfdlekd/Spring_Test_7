package com.iu.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileSaver {
	
	//3.transfer
	public String save3(MultipartFile f , String filePath)throws Exception{
		String fileName=f.getOriginalFilename();
		fileName=fileName.substring(fileName.lastIndexOf("."));
		fileName=UUID.randomUUID().toString()+"_"+fileName;
		File file = new File(filePath, fileName);
		
		f.transferTo(file);
		
		return fileName;
	}
	
	//2.FileCopyUtil
	public String save2(MultipartFile f , String filePath)throws Exception{
		String fileName=f.getOriginalFilename();
		fileName=fileName.substring(fileName.lastIndexOf("."));
		fileName=UUID.randomUUID().toString()+"_"+fileName;
		File file = new File(filePath, fileName);
		
		
		FileCopyUtils.copy(f.getBytes(), file);
		
		return fileName;
	}
	
	
	
	//1. outputStream
	public String save1(MultipartFile f , String filePath)throws Exception{
		String fileName=f.getOriginalFilename();
		fileName=fileName.substring(fileName.lastIndexOf("."));
		fileName=UUID.randomUUID().toString()+"_"+fileName;
		File file = new File(filePath, fileName);
		
		
		byte [] fileDate = f.getBytes();
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(fileDate);
		fo.close();
		
		return fileName;
	}
}
