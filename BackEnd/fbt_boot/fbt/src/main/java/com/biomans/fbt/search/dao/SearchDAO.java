package com.biomans.fbt.search.dao;

import java.sql.SQLException;
import java.util.List;

import com.biomans.fbt.domain.Search;

public interface SearchDAO {
	//M001
	public void addSearch(Search search) throws SQLException;
	//M002-1
	public List<Search> showSearchByTeam(int teamId) throws SQLException;
	//M009
	public void deleteSearch(int searchId) throws SQLException;
	//M010
	public void renewSearch(int searchId) throws SQLException;
}
