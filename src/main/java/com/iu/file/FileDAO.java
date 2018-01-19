package com.iu.file;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import org.springframework.stereotype.Repository;

@Repository
public class FileDAO {
	
	private SqlSession sqlSession;
	private final String NAMESPACE="FileMapper.";
	public FileDAO(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public int insert(FileDTO fileDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"insert",fileDTO);
	}
	
	public List<FileDTO> selectFile(int num)throws Exception{
		return sqlSession.selectList(NAMESPACE+"selectFile", num);
	}
	
}
