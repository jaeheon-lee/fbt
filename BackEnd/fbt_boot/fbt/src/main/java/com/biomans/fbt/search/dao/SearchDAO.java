package com.biomans.fbt.search.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSessionException;

import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.util.Attendance;
import com.biomans.fbt.util.Filter;

public interface SearchDAO {
	//M001
	public void addSearch(Search search) throws SQLException;
	//M002-1
	public List<Search> showRegisteredSearchByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//M002-2
	public List<Search> showRegisteredSearchAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//M002-3
	public Search getSearchById(int searchId) throws SQLException;
	//M002-4
	public Search getSearchSearchResById(int searchId) throws SQLException;
	//M003
	public List<Search> searchMatchByFilter(Filter filter) throws SQLException;
	//M004
	public void doApplySearch(SearchReservation searchRes) throws SQLException;
	//M005
	public void deleteSearch(int searchId) throws SQLException;
	//M006
	public void renewSearch(int searchId) throws SQLException;
	//M007
	public void updateResStatus(SearchReservation searchRes) throws SQLException;
	//M008
	public int checkSearchSuccessById(SearchReservation searchRes) throws SQLException;
	//M009
	public void deleteSeachRes(HashMap<String, Integer> searchCon) throws SQLException;
	//M010
	public Attendance checkMinNum(HashMap<String, String> con) throws SQLException;
	//M011
	public void completeSearch(HashMap<String, String> con) throws SQLException;
	//M012
	public void failSearch(HashMap<String, String> con) throws SQLException;
	//M013
	public void updateSearch(Search search) throws SQLException;
	
}
