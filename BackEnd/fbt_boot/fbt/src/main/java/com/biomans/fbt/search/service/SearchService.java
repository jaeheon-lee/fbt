package com.biomans.fbt.search.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.util.Filter;

public interface SearchService {
	//FM01
	public void addSearch(Search search) throws SQLException;
	//FM02
	public List<Search> searchMatchByFilter(Filter filter) throws SQLException;
	//FM03
	public void doApplySearch(Search search) throws SQLException;
	//FM04, FM12
	public List<Search> showRegisteredSearchByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//FM05, FM13
	public void deleteSearch(int searchId) throws SQLException;
	//FM06
	public void renewSearch(int searchId) throws SQLException;
	//FM07, FM10, FM16
	public List<Search> showRegisteredSearchAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException;
	//FM08, FM09, FM11
	public void updateResStatus(Search search, SearchReservation searchRes) throws SQLException;
	//FM08
	public Future<Integer> timeSearchFailure(Search search, SearchReservation searchRes) throws SQLException;
	//FM15
	public void completeSearch(Search search) throws SQLException;
	//FM17
	public void deleteSeachRes(HashMap<String, Integer> searchCon) throws SQLException;
	//FM18
	public void updateSearch(Search search) throws SQLException;
	//FM15
	public Search getSearchSearchResById(int searchId) throws SQLException;
}
