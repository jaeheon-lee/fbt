	package com.biomans.fbt.notice.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.biomans.fbt.domain.MatchSchedule;
import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.Team;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.User;
import com.biomans.fbt.domain.VoteMatch;
import com.biomans.fbt.notice.dao.NoticeDAO;
import com.biomans.fbt.notice.service.NoticeService;
import com.biomans.fbt.search.dao.SearchDAO;
import com.biomans.fbt.user.dao.UserDAO;
import com.biomans.fbt.util.NoticeFactor;

@Service
public class NoticeServiceImpl implements NoticeService {
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private SearchDAO searchDAO;
	
	//FV09
	@Override
	public void addNotice(Notice notice) throws SQLException {
		noticeDAO.addNotice(notice);
	}
	
	//FN01
	@Override
	public List<Notice> showNoticesByUser(String email) throws SQLException {
		List<Notice> notices = new ArrayList<Notice>();
		notices = noticeDAO.showNoticesByUser(email);
		return notices;
	}
	
	// 
	@Override
	public void addNoticeByCase(NoticeFactor noticeFactor) throws SQLException {
		// 알림 등록
		Notice notice = new Notice();
		
		Team giverTeam = new Team();
		User giverUser = new User();
		String pageName = "";
		String content = "";
		String teamMemberId = "";
		User takerUser = new User();
		List<User> takerUsers = new ArrayList<User>();
		Team takerTeam = new Team();
		
		switch (noticeFactor.getType()) {
		
		case "addVoteMatch": // 투표 등록
			giverTeam = noticeFactor.getVoteMatch().getTeam();
			pageName = "voteMatch-other";
			content = noticeFactor.getTeamName() + "에서 투표를 등록했습니다. 클릭하여 확인해주세요.";
			for(TeamMember teamMember : noticeFactor.getTeamMembers()) {
				User user = teamMember.getUser();
				takerUsers.add(user);
			}
			takerTeam = noticeFactor.getVoteMatch().getTeam();
			break;
			
		case "updateVoteMatch": // 투표 수정
			giverTeam = noticeFactor.getVoteMatch().getTeam();
			pageName = "voteMatch-other";
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getVoteMatch().getMatchSchedule().getStartTime().split(" ")[0]; 
			content += "일자 투표를 수정했습니다. 클릭하여 확인해주세요.";
			for(TeamMember teamMember : noticeFactor.getTeamMembers()) {
				User user = teamMember.getUser();
				takerUsers.add(user);
			}
			takerTeam = noticeFactor.getVoteMatch().getTeam();
			break;
			
		case "endVoteMatch": // 투표 마감
			giverTeam = noticeFactor.getVoteMatch().getTeam();
			pageName = "voteMatch-endVoteMatch";
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getVoteMatch().getMatchSchedule().getStartTime().split(" ")[0]; 
			content += "일자 투표를 마감했습니다. 클릭하여 확인해주세요.";
			for(TeamMember teamMember : noticeFactor.getTeamMembers()) {
				User user = teamMember.getUser();
				takerUsers.add(user);
			}
			takerTeam = noticeFactor.getVoteMatch().getTeam();
			break;
			
		case "applySearch":
			giverTeam = noticeFactor.getSearchRes().getTeamTaker();
			pageName = "search-applySearch";
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getSearch().getMatchSchedule().getStartTime().split(" ")[0];
			content += "일자 경기에 인원파악신청을 했습니다. 클릭하여 확인해주세요.";
			teamMemberId = noticeFactor.getSearch().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			takerTeam = noticeFactor.getSearch().getTeamGiver();
			break;
			
		case "acceptSearch":
			giverTeam = noticeFactor.getSearch().getTeamGiver();
			pageName = "search-acceptSearch";
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getSearch().getMatchSchedule().getStartTime().split(" ")[0]; 
			content +="일자 경기에 인원파악수락을 했습니다. 클릭하여 확인해주세요. ";
			content += "해당 일정에 투표가 자동으로 생성됐습니다.";
			teamMemberId = noticeFactor.getSearchRes().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			takerTeam = noticeFactor.getSearchRes().getTeamTaker();
			break;
		
		case "refuseSearch":
			giverTeam = noticeFactor.getSearch().getTeamGiver();
			pageName = "search-refuseSearch";
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getSearch().getMatchSchedule().getStartTime().split(" ")[0]; 
			content += "일자 경기에 인원파악거절/중단을 했습니다. 클릭하여 확인해주세요. ";
			content += "해당 경기에 생성된 투표는 삭제됩니다.";
			teamMemberId = noticeFactor.getSearchRes().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			takerTeam = noticeFactor.getSearchRes().getTeamTaker();
			break;
			
		case "fillNumber": // 매치에서 인원수를 채웠다면
			giverTeam = noticeFactor.getVoteMatch().getTeam();
			pageName = "search-fillNumber";
			content = noticeFactor.getTeamName() + "이 ";
			content += noticeFactor.getVoteMatch().getMatchSchedule().getStartTime().split(" ")[0]; 
			content += "일자 경기 최소인원을 채웠습니다. 클릭하여 진행해주세요.";
			Search s = searchDAO.getSearchById(noticeFactor.getSearch().getSearchId());
			noticeFactor.setSearch(s);
			teamMemberId = noticeFactor.getSearch().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			takerTeam = noticeFactor.getSearch().getTeamGiver();
			break;
			
		case "completeSearch": // 매치 확정
			giverTeam = noticeFactor.getSearch().getTeamGiver();
			pageName = "search-completeSearch";
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getSearch().getMatchSchedule().getStartTime().split(" ")[0]; 
			content +="일자 경기를 확정했습니다.";
			teamMemberId = noticeFactor.getSearchRes().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			takerTeam = noticeFactor.getSearchRes().getTeamTaker();
			break;
			
		case "failSearch": // 매치 실패
			giverTeam = noticeFactor.getSearch().getTeamGiver();
			pageName = "search-failSearch";
			content = noticeFactor.getTeamName() + "와 "; 
			content += noticeFactor.getSearch().getMatchSchedule().getStartTime().split(" ")[0]; 
			content +="일자 경기에서 다른 팀이 확정되어, 상대팀 매칭에 실패했습니다.";
			teamMemberId = noticeFactor.getSearchRes().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			takerTeam = noticeFactor.getSearchRes().getTeamTaker();
			break;
			
		case "applyAssign":
			giverTeam = noticeFactor.getAssignRes().getTeamTaker();
			pageName = "assign-applyAssign";
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getAssign().getMatchSchedule().getStartTime().split(" ")[0];
			content += "일자 경기에 양도신청을 했습니다. 클릭하여 확인해주세요.";
			teamMemberId = noticeFactor.getAssign().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			takerTeam = noticeFactor.getAssign().getTeamGiver();
			break;
			
		case "acceptAssign":
			giverTeam = noticeFactor.getAssign().getTeamGiver();
			pageName = "assign-acceptAssign";
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getAssign().getMatchSchedule().getStartTime().split(" ")[0]; 
			content +="일자 경기에 양도신청수락을 했습니다. 클릭하여 확인해주세요.";
			teamMemberId = noticeFactor.getAssignRes().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			takerTeam = noticeFactor.getAssignRes().getTeamTaker();
			break;
			
		case "refuseAssign":
			giverTeam = noticeFactor.getAssign().getTeamGiver();
			pageName = "assign-refuseAssign";
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getAssign().getMatchSchedule().getStartTime().split(" ")[0]; 
			content +="일자 경기에 양도신청거절을 했습니다. 클릭하여 확인해주세요.";
			teamMemberId = noticeFactor.getAssignRes().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			takerTeam = noticeFactor.getAssignRes().getTeamTaker();
			break;
			
		case "completeAssign":
			giverTeam = noticeFactor.getAssign().getTeamGiver();
			pageName = "assign-completeAssign";
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getAssign().getMatchSchedule().getStartTime().split(" ")[0]; 
			content +="일자 경기에 양도 확정했습니다.";
			teamMemberId = noticeFactor.getAssignRes().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			takerTeam = noticeFactor.getAssignRes().getTeamTaker();
			break;
			
		case "failAssign":
			giverTeam = noticeFactor.getAssign().getTeamGiver();
			pageName = "assign-failAssign";
			content = noticeFactor.getTeamName() + "와의 ";
			content += noticeFactor.getAssign().getMatchSchedule().getStartTime().split(" ")[0]; 
			content +="일자 경기에서 다른 팀이 양도 확정되면서, 자동 취소가 실패처리됐습니다.";
			teamMemberId = noticeFactor.getAssignRes().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			takerTeam = noticeFactor.getAssignRes().getTeamTaker();
			break;
			
		case "applyEmploy":
			giverTeam = null;
			giverUser = noticeFactor.getEmployRes().getUser();
			takerTeam = noticeFactor.getEmploy().getTeamGiver();
			teamMemberId = noticeFactor.getEmploy().getTeamMember().getTeamMemberId();
			takerUser.setEmail(teamMemberId.split("-")[1]);
			takerUsers.add(takerUser);
			content = noticeFactor.getEmploy().getMatchSchedule().getStartTime().split(" ")[0];
			content += "일자 경기에 용병신청이 있습니다.";
			pageName = "employ-applyEmploy";
			break;
			
		case "acceptEmployApply":
			giverTeam = noticeFactor.getEmploy().getTeamGiver();
			teamMemberId = noticeFactor.getEmploy().getTeamMember().getTeamMemberId();
			giverUser.setEmail(teamMemberId.split("-")[1]);
			takerTeam = null;
			takerUser = noticeFactor.getEmployRes().getUser();
			takerUsers.add(takerUser);
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getEmploy().getMatchSchedule().getStartTime().split(" ")[0];
			content += "일자 경기에 용병신청 수락했습니다.";
			pageName = "employ-acceptEmployApply";
			break;
			
		case "refuseEmployApply":
			giverTeam = noticeFactor.getEmploy().getTeamGiver();
			teamMemberId = noticeFactor.getEmploy().getTeamMember().getTeamMemberId();
			giverUser.setEmail(teamMemberId.split("-")[1]);
			takerTeam = null;
			takerUser = noticeFactor.getEmployRes().getUser();
			takerUsers.add(takerUser);
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getEmploy().getMatchSchedule().getStartTime().split(" ")[0];
			content += "일자 경기에 용병신청 거절했습니다.";
			pageName = "employ-refuseEmployApply";
			break;
		
		case "completeEmployApply":
			giverTeam = noticeFactor.getEmploy().getTeamGiver();
			teamMemberId = noticeFactor.getEmploy().getTeamMember().getTeamMemberId();
			giverUser.setEmail(teamMemberId.split("-")[1]);
			takerTeam = null;
			takerUser = noticeFactor.getEmployRes().getUser();
			takerUsers.add(takerUser);
			content = noticeFactor.getTeamName() + "에서 ";
			content += noticeFactor.getEmploy().getMatchSchedule().getStartTime().split(" ")[0];
			content += "일자 경기의 용병 확정했습니다. 일정을 확인하여 경기를 준비해주세요.";
			pageName = "employ-completeEmployApply";
			break;
			
		default:
			break;
		}
		notice.setGiverTeam(giverTeam);
		notice.setGiverUser(giverUser);
		notice.setPageName(pageName);
		notice.setContent(content);
		notice.setTakerUsers(takerUsers);
		notice.setTakerTeam(takerTeam);
		System.out.println(notice);
		noticeDAO.addNotice(notice);
		
	}
	
}
