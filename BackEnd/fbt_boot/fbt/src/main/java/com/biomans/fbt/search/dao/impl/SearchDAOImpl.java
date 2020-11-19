package com.biomans.fbt.search.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.Search;
import com.biomans.fbt.search.dao.SearchDAO;

@Repository
public class SearchDAOImpl implements SearchDAO{
	final String ns = "SearchMapper.";
	
	@Autowired
	private SqlSession sqlSession;
	
	//M001
	@Override
	public void addSearch(Search search) throws SQLException {
		sqlSession.insert(ns+"addSearch", search);
	}
	
	//M002-1
	@Override
	public List<Search> showSearchByTeam(int teamId) throws SQLException {
		return sqlSession.selectList(ns+"showSearchByTeam", teamId);
	}
	
	//M009
	@Override
	public void deleteSearch(int searchId) throws SQLException {
		sqlSession.delete(ns+"deleteSearch", searchId);
		
	}

	@Override
	public void renewSearch(int searchId) throws SQLException {
		sqlSession.update(ns+"renewSearch", searchId);
		
	}

}
