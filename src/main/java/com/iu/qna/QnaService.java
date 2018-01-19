package com.iu.qna;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.board.BoardDTO;
import com.iu.board.BoardService;
import com.iu.file.FileDAO;
import com.iu.file.FileDTO;
import com.iu.util.FileSaver;
import com.iu.util.ListData;
import com.iu.util.PageMaker;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	@Autowired
	private FileDAO fileDAO;
	
	@Override
	public List<BoardDTO> selectList(ListData listData) throws Exception {
		int totalCount=qnaDAO.totalCount(listData);
		PageMaker pageMaker = new PageMaker();
		pageMaker.pageMaker(totalCount, listData);
		return qnaDAO.selectList(listData);
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.selectOne(num);
	}

	@Override
	public int insert(BoardDTO boardDTO,MultipartFile [] f1, HttpSession session) throws Exception {
		FileSaver fileSaver = new FileSaver();
		String filepath = session.getServletContext().getRealPath("resources/upload");
		File f = new File(filepath);
		if(!f.exists()){
			f.mkdirs();
		}
		int result = qnaDAO.insert(boardDTO);
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
		// TODO Auto-generated method stub
		return qnaDAO.update(boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return qnaDAO.delete(num);
	}
	
	public int replyInsert(BoardDTO boardDTO)throws Exception{
		qnaDAO.replyUpdate(boardDTO);
		return qnaDAO.replyInsert(boardDTO); 
	}


}
