package com.iu.s7;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.iu.util.FileDTO;
import com.iu.util.FileSaver;


@Controller
public class FileController {
	@RequestMapping(value="/fileUpload1", method=RequestMethod.POST)
	public void fileTest(FileDTO fileDTO)throws Exception{
		System.out.println(fileDTO.getId());
		System.out.println(fileDTO.getF1().getName());
		System.out.println(fileDTO.getF1().getOriginalFilename());
		System.out.println(fileDTO.getF2().getName());
		System.out.println(fileDTO.getF2().getOriginalFilename());
	}
	
	@RequestMapping(value="/fileUpload4", method=RequestMethod.POST)
	public void fileTest3(MultipartHttpServletRequest request)throws Exception{
		Iterator<String> it = request.getFileNames();
		MultipartFile f =null;
		while(it.hasNext()){
			f = request.getFile(it.next());
			System.out.println(f.getOriginalFilename());
		}
	}
	
	@RequestMapping(value="/fileUpload3", method=RequestMethod.POST)
	public void fileTest2(MultipartHttpServletRequest request)throws Exception{
	MultipartFile f = request.getFile("f1");
		System.out.println(f.getName());
		System.out.println(f.getOriginalFilename());
		System.out.println(f.getSize());
		
	}
	
	
	@RequestMapping(value="/fileUpload2", method=RequestMethod.POST)
	public void fileTest(String id, MultipartFile f1, MultipartFile f2)throws Exception{
		System.out.println(id);
		System.out.println(f1.getName());
		System.out.println(f1.getOriginalFilename());
		System.out.println(f2.getName());
		System.out.println(f2.getOriginalFilename());
	}
	
	
	@RequestMapping(value="/fileUpload5", method=RequestMethod.POST)
	public void fileTest(MultipartHttpServletRequest request)throws Exception{	
		request.getSession().getServletContext().getRealPath("");
		List<MultipartFile> ar = request.getFiles("f1");
		for(int i=0; i<ar.size(); i++)
			//(MultipartFile file : ar) (변수 ,이름 : 리스트이름)
			System.out.println(ar.get(i).getOriginalFilename());

	}
	
	@RequestMapping(value="/fileUpload", method=RequestMethod.POST)
	public void fileUpload(MultipartFile [] f1, HttpSession session)throws Exception{	
		//Service class로 session, file들을 넘겨줌
		String filePath = session.getServletContext().getRealPath("resources/upload");
		File f = new File(filePath);
		if(!f.exists()){
			f.mkdirs();
		}
		FileSaver fileSaver = new FileSaver();
		System.out.println(filePath);
		for(int i=0; i<f1.length; i++){
			//(MultipartFile file : ar) (변수 ,이름 : 리스트이름)
			String s = fileSaver.save3(f1[i], filePath);
			System.out.println(s);
		}
		
	}
	
	
	
}
