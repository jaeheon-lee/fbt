package com.biomans.fbt.search.dao.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.search.dao.SearchDAO;
import com.biomans.fbt.util.Attendance;
import com.biomans.fbt.util.Filter;

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
	public List<Search> showRegisteredSearchByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showRegisteredSearchByTeam", searchCon);
	}
	
	//M002-2
	@Override
	public List<Search> showRegisteredSearchAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return sqlSession.selectList(ns+"showRegisteredSearchAppliedByTeam", searchCon);
	}
	
	//M003
	@Override
	public List<Search> searchMatchByFilter(Filter filter) throws SQLException {
		return sqlSession.selectList(ns+"searchMatchByFilter", filter);
	}
	
	//M005
	@Override
	public void doApplySearch(SearchReservation searchRes) throws SQLException {
		sqlSession.insert(ns+"doApplySearch", searchRes);
	}
	
	//M006
	@Override
	public void updateResStatus(SearchReservation searchRes) throws SQLException {
		sqlSession.update(ns+"updateResStatus", searchRes);
	}
	
	//M009
	@Override
	public void deleteSearch(int searchId) throws SQLException {
		sqlSession.delete(ns+"deleteSearch", searchId);
		
	}
	
	//M010
	@Override
	public void renewSearch(int searchId) throws SQLException {
		sqlSession.update(ns+"renewSearch", searchId);
		
	}
	
	//M013
	public void deleteSeachRes(HashMap<String, Integer> searchCon) throws SQLException {
		sqlSession.delete(ns + "deleteSeachRes", searchCon);
	}
	
	//
	public Attendance checkMinNum(HashMap<String, String> con) throws SQLException {
		return sqlSession.selectOne(ns+"checkMinNum", con);
	}
	public void completeSearch(HashMap<String, String> con) throws SQLException {
		sqlSession.update(ns+"completeSearch", con);
	}
	public void failSearch(HashMap<String, String> con) throws SQLException {
		sqlSession.update(ns+"failSearch", con);
	}

	@Override
	public int checkSearchSuccessById(SearchReservation searchRes) throws SQLException {
		return sqlSession.selectOne(ns+"checkSearchSuccessById", searchRes);
	}

}
