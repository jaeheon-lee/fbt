package com.honeycom.fbt.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.honeycom.fbt.dto.User;

@Repository
public class FootBallTogetherDAOImpl_MHK implements FootBallTogetherDAO_MHK{
	
	@Autowired
	private SqlSession sqlSession;
	private final String NS = "sql.footBAllTogether.mapper2." ;
	
	@Override
	public User selectUser(User user) {
		return sqlSession.selectOne(NS+"selectUser", user);
	}
}
