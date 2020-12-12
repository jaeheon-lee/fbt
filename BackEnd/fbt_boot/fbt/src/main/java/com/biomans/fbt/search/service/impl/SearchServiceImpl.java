package com.biomans.fbt.search.service.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.domain.VoteMatchSetting;
import com.biomans.fbt.matchschedule.dao.MatchScheduleDAO;
import com.biomans.fbt.search.dao.SearchDAO;
import com.biomans.fbt.search.service.SearchService;
import com.biomans.fbt.team.dao.TeamDAO;
import com.biomans.fbt.teammember.dao.TeamMemberDAO;
import com.biomans.fbt.util.Filter;
import com.biomans.fbt.votematch.dao.VoteMatchDAO;

@Service
public class SearchServiceImpl implements SearchService{
	@Autowired
	private SearchDAO searchDAO;
	
	@Autowired
	private MatchScheduleDAO matchScheduleDAO;
	
	@Autowired 
	public VoteMatchDAO voteMatchDAO;
	
	@Autowired
	public TeamDAO teamDAO;
	
	@Autowired
	public TeamMemberDAO teamMemberDAO;

	//FM01
	@Override
	public void addSearch(Search search) throws SQLException {
		// 경기일정이 없으면 경기일정을 먼저 등록한다.
		MatchSchedule matchSchedule = search.getMatchSchedule();
		if(matchSchedule.getMatchScheduleId() == 0) {
			int teamId = search.getTeamGiver().getTeamId();
			matchSchedule.getHomeTeam().setTeamId(teamId);
			if(matchSchedule.getAwayTeam().getTeamId() == 0) matchSchedule.setAwayTeam(null);
			matchScheduleDAO.addMatchSchedule(matchSchedule);
			// 갓 등록하나 경기일정을 가져온다
			int matchScheduleId = matchScheduleDAO.showLatestMatchScheduleIdById(teamId);
			search.getMatchSchedule().setMatchScheduleId(matchScheduleId);
		}
		// 해당 Id에 매치글을 등록한다.
		searchDAO.addSearch(search);
		
	}
	//FM02
	@Override
	public List<Search> searchMatchByFilter(Filter filter) throws SQLException {
		return searchDAO.searchMatchByFilter(filter);
	}
	
	//FM03
	@Override
	public void doApplySearch(SearchReservation searchRes) throws SQLException {
		searchDAO.doApplySearch(searchRes);
	}
	
	//FM04, FM12
	@Override
	public List<Search> showRegisteredSearchByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		return searchDAO.showRegisteredSearchByTeam(searchCon);
	}
	
	//FM05, FM13
	@Override
	public void deleteSearch(int searchId) throws SQLException {
		searchDAO.deleteSearch(searchId);
		
	}
	
	//FM06
	@Override
	public void renewSearch(int searchId) throws SQLException {
		searchDAO.renewSearch(searchId);
		
	}
	
	//FM07, FM10, FM16
	@Override
	public List<Search> showRegisteredSearchAppliedByTeam(HashMap<String, Integer> searchCon) throws SQLException {
		// 매치 글 정보를 먼저 받아온다
		List<Search> searches = searchDAO.showRegisteredSearchAppliedByTeam(searchCon);
		// 신청 팀에 대한 정보, 연락처를 받아온다
		if(searches.size() > 0) {
			for(Search search : searches) {
				List<SearchReservation> searchReservations = search.getSearchReservations();
				for(SearchReservation searchRes : searchReservations) {
					// 신청팀에 대한 정보 출력
					int teamTakerId = searchRes.getTeamTaker().getTeamId();
					if(teamTakerId == 0) continue; 
					Team teamTaker = teamDAO.showTeamBasicInfo(teamTakerId);
					if(teamTaker == null) continue;
					searchRes.setTeamTaker(teamTaker);
				}
				
				
			}
		}
		return searches;
	}
	
	
	//FM08, FM09, FM11
	@Override
	@Transactional
	public void updateResStatus(Search search, SearchReservation searchRes) throws SQLException {
		searchDAO.updateResStatus(searchRes);
		//인원파악수락을 요청했다면, 자동으로 투표를 생성
		if(searchRes.getReservationStatus() == 1) {
			// 자동 투표 생성 메소드
			addVoteMatchBySearch(search, searchRes);
		} else if (searchRes.getReservationStatus() == -1) {
			int matchScheduleId = search.getMatchSchedule().getMatchScheduleId();
			int teamId = search.getSearchReservations().get(0).getTeamTaker().getTeamId();
			HashMap<String, Integer> searchCon = new HashMap<String, Integer>();
			searchCon.put("matchScheduleId", matchScheduleId);
			searchCon.put("teamId", teamId);
			voteMatchDAO.deleteVoteMatchByMatchScheduleId(searchCon);
		}
	}
	
	//FM08
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
	
	//FM08
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
	
	//FM15
	@Override
	@Transactional
	public void completeSearch(Search search) throws SQLException {
		// awayTeam 등록
		int takerTeamId = search.getSearchReservations().get(0).getTeamTaker().getTeamId();
		int matchScheduleId = search.getMatchSchedule().getMatchScheduleId();
		HashMap<String, Integer> con = new HashMap<String, Integer>();
		con.put("takerTeamId", takerTeamId);
		con.put("matchScheduleId", matchScheduleId);
		matchScheduleDAO.addAwayTeam(con);
		// 매치글 삭제
		int searchId = search.getSearchId();
		searchDAO.deleteSearch(searchId);
	}

	//FM17
	@Override
	public void deleteSeachRes(HashMap<String, Integer> searchCon) throws SQLException {
		searchDAO.deleteSeachRes(searchCon);
	}
	
	//FM18
	@Override
	public void updateSearch(Search search) throws SQLException {
		searchDAO.updateSearch(search);
	}
	
}
