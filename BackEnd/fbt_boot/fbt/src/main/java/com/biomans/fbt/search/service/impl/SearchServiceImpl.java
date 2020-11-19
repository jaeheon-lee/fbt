package com.biomans.fbt.search.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomans.fbt.domain.Search;
import com.biomans.fbt.search.dao.SearchDAO;
import com.biomans.fbt.search.service.SearchService;

@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
	private SearchDAO searchDAO;

	//M001
	@Override
	public void addSearch(Search search) throws SQLException {
		searchDAO.addSearch(search);
		
	}
	
	//M002-1
	@Override
	public List<Search> showSearchByTeam(int teamId) throws SQLException {
		return searchDAO.showSearchByTeam(teamId);
	}
	
	//M009
	@Override
	public void deleteSearch(int searchId) throws SQLException {
		searchDAO.deleteSearch(searchId);
		
	}

	@Override
	public void renewSearch(int searchId) throws SQLException {
		searchDAO.renewSearch(searchId);
		
	}
}
