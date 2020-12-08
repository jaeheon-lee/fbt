package com.biomans.fbt.search.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchSetting;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.search.controller.SearchController;
import com.biomans.fbt.search.dao.SearchDAO;
import com.biomans.fbt.search.service.SearchService;
import com.biomans.fbt.util.Filter;
import com.biomans.fbt.votematch.dao.VoteMatchDAO;

@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
	private SearchDAO searchDAO;
	
	@Autowired
	private MatchScheduleDAO matchScheduleDAO;
	
	@Autowired VoteMatchDAO voteMatchDAO;

	//M001
	@Override
	public void addSearch(Search search) throws SQLException {
		searchDAO.addSearch(search);
		
	}
	
	//M002-1
	@Override
	public List<Search> showRegisteredSearchByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return searchDAO.showRegisteredSearchByTeam(searchCon);
	}
	
	//M002-2
	@Override
	public List<Search> showRegisteredSearchAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return searchDAO.showRegisteredSearchAppliedByTeam(searchCon);
	}
	
	//M003
	@Override
	public List<Search> searchMatchByFilter(Filter filter) throws SQLException {
		return searchDAO.searchMatchByFilter(filter);
	}
	
	//M005
	@Override
	public void doApplySearch(SearchReservation searchRes) throws SQLException {
		searchDAO.doApplySearch(searchRes);
	}
	
	//M006
	@Override
	@Transactional
	public void updateResStatus(Search search, SearchReservation searchRes) throws SQLException {
		searchDAO.updateResStatus(searchRes);
		//인원파악수락을 했으면, 자동으로 투표를 생성한다.
		if(searchRes.getReservationStatus() == 1) {
			addVoteMatchBySearch(search, searchRes);
		}
	}
	
	public void addVoteMatchBySearch(Search search, SearchReservation searchRes) throws SQLException {
		MatchSchedule matchSchedule = search.getMatchSchedule();
		// 투표를 생성한다.
		VoteMatch voteMatch = new VoteMatch();
		voteMatch.setMatchSchedule(matchSchedule);
		// 투표 마감시간을 현재 시간에서 + waitingTime으로 설정한다.
		int waitingTime = search.getWaitingTime();
		Long current = System.currentTimeMillis();
		Timestamp t = new Timestamp(current);  
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(t.getTime());
		cal.add(Calendar.HOUR, waitingTime);
		Timestamp dueDate = new Timestamp(cal.getTime().getTime());
		voteMatch.setDueDate(dueDate.toString());
		// 투표 작성자 설정
		voteMatch.setWriter(matchSchedule.getWriter());
		// 투표 팀아이디 설정
		Team team = new Team();
		team.setTeamId(searchRes.getTeamTaker().getTeamId());
		voteMatch.setTeam(team);
		// 투표 메모 설정
		voteMatch.setMemo("상대팀 있는 투표입니다. 마감시간이 중요하니 마감시간 전에 빠른 투표 부탁드립니다.");
		// 투표 생성
		voteMatchDAO.addVoteMatch(voteMatch);
		// 해당 팀의, 갓 생성한 투표Id를 가져온다
		int teamId = searchRes.getTeamTaker().getTeamId();
		int voteMatchId = voteMatchDAO.getLatestVoteMatchIdByTeam(teamId);
		// 투표 설정을 지인 추가 가능으로 설정한다 & 투표 설정 생성
		VoteMatchSetting voteSet = 
				new VoteMatchSetting(voteMatchId, 0, search.getMinNumber(), false, 
						false, true, -1, -1, null, -1, -1, null, -1, -1, -1);
		voteMatchDAO.addVoteMatchSetting(voteSet);	
	}
	
	//
	@Async("threadPoolTaskExecutor")
	@Transactional
	@Override
	public Future<Integer> timeSearchFailure(Search search, SearchReservation searchRes) throws SQLException {
		int number=2;
		int waitingTime = search.getWaitingTime();
		try {
			Thread.sleep(1000 * 60 * 60 * waitingTime); // 시간을 한 시간 단위로 잡는다 : 1000 * 60 * 60 * var
			int status = searchDAO.checkSearchSuccessById(searchRes);
			if(status != 3) { // 마감시간까지도 완료되지 않았다면
				searchRes.setReservationStatus(-1);
				searchDAO.updateResStatus(searchRes);
				System.out.println("==================done====================");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e2) {
			System.out.println(e2);
		}
		return new AsyncResult<Integer>(number);
	}
	
	//M009
	@Override
	public void deleteSearch(int searchId) throws SQLException {
		searchDAO.deleteSearch(searchId);
		
	}

	//M010
	@Override
	public void renewSearch(int searchId) throws SQLException {
		searchDAO.renewSearch(searchId);
		
	}
	
	//M013
	@Override
	public void deleteSeachRes(HashMap<String, Integer> searchCon) throws SQLException {
		searchDAO.deleteSeachRes(searchCon);
	}
	
}
