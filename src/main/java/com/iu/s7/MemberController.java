package com.iu.s7;



import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.file.FileDTO;
import com.iu.member.MemberDTO;
import com.iu.member.MemberService;

@Controller
@RequestMapping(value="/member/**")
public class MemberController {

	@Inject
	private MemberService memberService;
	
	
	
	
	@RequestMapping(value="memberJoin")
	public void Join()throws Exception{
	}
	
	
	@RequestMapping(value="memberJoin", method=RequestMethod.POST)
	public String Join2(MemberDTO memberDTO,MultipartFile file,HttpSession session)throws Exception{
		int result = memberService.Join(memberDTO,file,session);
		if(result>0)
			return "redirect:../";
		else
			return "redirect:./memberJoin";
	}
	
	
	@RequestMapping(value="memberLogin")
	public void Login()throws Exception{
	}
	
	
	@RequestMapping(value="memberLogin", method=RequestMethod.POST)
	public ModelAndView Login2(MemberDTO memberDTO, HttpSession session)throws Exception{
		MemberDTO memberDTO2 =null;
		memberDTO2 = memberService.Login(memberDTO);
		ModelAndView mv = new ModelAndView();
		if(memberDTO2!=null){
		session.setAttribute("member", memberDTO2);
		mv.addObject("message", "로그인 성공");
		mv.addObject("path", "../");
		mv.setViewName("common/result");
		}
		else{
			mv.addObject("message", "로그인 실패");
			mv.addObject("path", "memberLogin");
			mv.setViewName("common/result");
		}
		return mv;
	}
	
	
	@RequestMapping(value="memberLogout")
	public String Logout(HttpSession session){
		session.invalidate();
		return "redirect:../";
	}
	
	
	@RequestMapping(value="memberView")
	public void View(HttpSession session,Model model)throws Exception{
		FileDTO fileDTO= memberService.View((MemberDTO)session.getAttribute("member"));
		if(fileDTO!=null)
		model.addAttribute("file", fileDTO);
	}
	
	
	@RequestMapping(value="memberUpdate")
	public void Update(HttpSession session,Model model)throws Exception{
		FileDTO fileDTO= memberService.View((MemberDTO)session.getAttribute("member"));
		model.addAttribute("file", fileDTO);
	}
	
	
	@RequestMapping(value="memberUpdate", method=RequestMethod.POST)
	public ModelAndView Update2(MemberDTO memberDTO,MultipartFile file, HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		int result = memberService.Update(memberDTO,file,session);
		if(result>0){
			session.setAttribute("member", memberDTO);
			mv.addObject("message", "수정 성공");
			}
			else{
			mv.addObject("message", "수정 실패");
			}
		mv.addObject("path", "memberView");
		mv.setViewName("common/result");
		return mv;
	}
	
	
	@RequestMapping(value="memberDelete")
	public String Delete(HttpSession session)throws Exception{
		int result = memberService.Delete((MemberDTO)session.getAttribute("member"));
		if(result>0)
		session.invalidate();
		
		return "redirect:../";
	}
	
	@RequestMapping(value="memberIdCheck")
	public void Check(String id, Model model)throws Exception{
		System.out.println(id);
		String check = memberService.Check(id);
		System.out.println(check);
		if(check==null){
		 	check="사용 가능한 ID입니다";
		}
		else
			check="중복된 ID입니다.";

			model.addAttribute("check", check);
	}
	
	
	
}
