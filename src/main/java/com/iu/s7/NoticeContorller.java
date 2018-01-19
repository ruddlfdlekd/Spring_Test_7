package com.iu.s7;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.iu.board.BoardDTO;
import com.iu.notice.NoticeDTO;
import com.iu.notice.NoticeService;
import com.iu.util.ListData;

@Controller
@RequestMapping(value="/notice/**")
public class NoticeContorller {
	@Inject
	private NoticeService noticeService;

	@RequestMapping(value="noticeView")
	public ModelAndView SelectOne(int num)throws Exception{
		BoardDTO boardDTO = noticeService.selectOne(num);
		ModelAndView mv = new ModelAndView();
		mv.addObject("view", boardDTO);
		mv.addObject("board", "notice");
		mv.setViewName("board/boardView");
		return mv;
	}
	
	@RequestMapping(value="noticeList")
	public ModelAndView selectList(ListData listData)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		List<BoardDTO> ar =noticeService.selectList(listData);
		mv.addObject("list",ar);
		mv.addObject("page",listData);
		mv.addObject("board","notice");
		mv.setViewName("board/boardList");
		return mv;
	}
	
	@RequestMapping(value="noticeWrite", method=RequestMethod.GET)
	public String insert(Model model){
		model.addAttribute("board", "notice");
		return "board/boardWrite";
	}
	
	
	
	@RequestMapping(value="noticeWrite",  method=RequestMethod.POST)
	public String insert(NoticeDTO noticeDTO,MultipartFile [] f1,HttpSession session, RedirectAttributes re) throws Exception{
		int result=noticeService.insert(noticeDTO, f1, session);
		String message="Write Fail";
		if(result>0){
			message="Write Success";
		}
		
		re.addFlashAttribute("message", message);
		return "redirect:./noticeList";
	}
	
	
}
