package com.biomans.fbt.search.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.util.Filter;

public interface SearchService {
	//M001
	public void addSearch(Search search) throws SQLException;
	//M002-1
	public List<Search> showRegisteredSearchByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//M002-2
	public List<Search> showRegisteredSearchAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//M003
	public List<Search> searchMatchByFilter(Filter filter) throws SQLException;
	//M005
	public void doApplySearch(SearchReservation searchRes) throws SQLException;
	//M006
	public void updateResStatus(Search search, SearchReservation searchRes) throws SQLException;
	//M009
	public void deleteSearch(int searchId) throws SQLException;
	//M010
	public void renewSearch(int searchId) throws SQLException;
	//M013
	public void deleteSeachRes(HashMap<String, Integer> searchCon) throws SQLException;
	//
	public Future<Integer> timeSearchFailure(Search search, SearchReservation searchRes) throws SQLException;
}
