package com.iu.member;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


@Repository
public class MemberDAO {
	
	@Inject
	private SqlSession sqlSession;
	private final String NAMESPACE="MemberMapper.";
	
	public int Join(MemberDTO memberDTO) throws Exception {
		return sqlSession.insert(NAMESPACE+"Join", memberDTO);
	}
	public MemberDTO Login(MemberDTO memberDTO)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"Login", memberDTO);
	}
	public int Update(MemberDTO memberDTO)throws Exception{
		return sqlSession.update(NAMESPACE+"Update", memberDTO);
	}
	public int Delete(MemberDTO memberDTO)throws Exception{
		return sqlSession.delete(NAMESPACE+"Delete", memberDTO);
	}
	public String Check(String id)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"Check", id);
	}
	
	
}
