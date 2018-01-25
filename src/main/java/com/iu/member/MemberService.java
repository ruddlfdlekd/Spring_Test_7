package com.iu.member;

import java.io.File;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.util.FileSaver;

@Service
public class MemberService {
	
	@Inject
	private MemberDAO memberDAO;
	@Inject
	private FileDAO fileDAO;

	public int Join(MemberDTO memberDTO,MultipartFile file,HttpSession session) throws Exception {
		FileSaver fileSaver = new FileSaver();
		String filepath = session.getServletContext().getRealPath("resources/upload");
		File f = new File(filepath);
		if(!f.exists()){
			f.mkdirs();
		}
		int result = memberDAO.Join(memberDTO);
		
		String name = fileSaver.saver(file, filepath);
		if(result>0){
		FileDTO fileDTO = new FileDTO();
		fileDTO.setFname(name);
		fileDTO.setOname(file.getOriginalFilename());
		fileDTO.setId(memberDTO.getId());
	    fileDAO.insert2(fileDTO);
		}
		return result;
	}
	public MemberDTO Login(MemberDTO memberDTO)throws Exception{
		return memberDAO.Login(memberDTO);
	}
	
	public int Update(MemberDTO memberDTO,MultipartFile file, HttpSession session)throws Exception{
		int result = memberDAO.Update(memberDTO);
		
		FileSaver fileSaver = new FileSaver();
		String filepath = session.getServletContext().getRealPath("resources/upload");
		File f = new File(filepath);
		if(!f.exists()){
			f.mkdirs();
		}
		FileDTO fileDTO=fileDAO.selectOne(memberDTO);
		if(fileDTO!=null){
			File f2 = new File(filepath, fileDTO.getFname());
			if(file.getOriginalFilename()!=""){
			String name = fileSaver.saver(file, filepath);
			if(result>0){
			FileDTO fileDTO2 = new FileDTO();
			fileDTO2.setFname(name);
			fileDTO2.setOname(file.getOriginalFilename());
			fileDTO2.setId(memberDTO.getId());
		    int result2 = fileDAO.update(fileDTO2);
			if(result2>0){
				f2.delete();
			}
			}
			}
		}
		else{
			if(file.getOriginalFilename()!=""){
			String name = fileSaver.saver(file, filepath);
			if(result>0){
			fileDTO = new FileDTO();
			fileDTO.setFname(name);
			fileDTO.setOname(file.getOriginalFilename());
			fileDTO.setId(memberDTO.getId());
			if(file.getOriginalFilename()!=null)
			fileDAO.insert2(fileDTO);
			}
			}
		}
			return result;
	}
	public int Delete(MemberDTO memberDTO)throws Exception{
		int result = memberDAO.Delete(memberDTO);
		if(result>0){
			result = fileDAO.delete2(memberDTO);
		}
		return result;
	}
	public String Check(String id)throws Exception{
		return memberDAO.Check(id);
	}
	
	public FileDTO View(MemberDTO memberDTO)throws Exception{
		return fileDAO.selectOne(memberDTO);
	}
	
}
