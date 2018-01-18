package com.iu.qna;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.util.ListData;


@Repository
public class QnaDAO implements BoardDAO {

	@Inject
	private SqlSession sqlSession;
	private final String NAMESPACE="QnaMapper.";
	
	public QnaDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	 @Override
		public int totalCount(ListData listData) throws Exception {
			return sqlSession.selectOne(NAMESPACE+"totalCount", listData);
		}
		@Override
		public List<BoardDTO> selectList(ListData listData) throws Exception {
			return sqlSession.selectList(NAMESPACE+"selectList", listData);
		}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(NAMESPACE+"selectOne", num);
	}

	@Override
	public int insert(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.insert(NAMESPACE+"insert", boardDTO);
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(NAMESPACE+"update", boardDTO);
	}

	@Override
	public int delete(int num) throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.delete(NAMESPACE+"delete", num);
	}

	public int replyUpdate(BoardDTO boardDTO)throws Exception {
		// TODO Auto-generated method stub
		return sqlSession.update(NAMESPACE+"replyUpdate", boardDTO);
	}
	public int replyInsert(BoardDTO boardDTO)throws Exception{
		// TODO Auto-generated method stub
	return sqlSession.insert(NAMESPACE+"replyInsert", boardDTO);
	}



}
