package com.iu.notice;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.util.FileSaver;
import com.iu.util.ListData;
import com.iu.util.PageMaker;


public class NoticeService implements BoardService {

	private NoticeDAO noticeDAO;
	private FileDAO fileDAO;
	
	public void setNoticeDAO(NoticeDAO noticeDAO) {
		this.noticeDAO = noticeDAO;
	}

	public void setFileDAO(FileDAO fileDAO){
		this.fileDAO = fileDAO;
	}
	
	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {	
		int totalCount=noticeDAO.totalCount(listData);
		PageMaker pageMaker = new PageMaker();
		pageMaker.pageMaker(totalCount, listData);
		return noticeDAO.selectList(listData);
	}

	@Override
	public int insert(BoardDTO boardDTO, MultipartFile [] f1, HttpSession session) throws Exception {
		FileSaver fileSaver = new FileSaver();
		String filepath = session.getServletContext().getRealPath("resources/upload");
		File f = new File(filepath);
		if(!f.exists()){
			f.mkdirs();
		}
		int result = noticeDAO.insert(boardDTO);
		List<String> names = fileSaver.saver(f1, filepath);
		for(int i=0; i<names.size(); i++){
			FileDTO fileDTO = new FileDTO();
			fileDTO.setFname(names.get(i));
			fileDTO.setOname(f1[i].getOriginalFilename());
			fileDTO.setNum(boardDTO.getNum());
			fileDAO.insert(fileDTO);
		}
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return noticeDAO.update(boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		return noticeDAO.delete(num);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		return noticeDAO.selectOne(num);
	}

}
