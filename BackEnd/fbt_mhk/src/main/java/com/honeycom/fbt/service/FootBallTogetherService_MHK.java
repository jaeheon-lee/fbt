package com.honeycom.fbt.service;

import java.util.List;

import com.honeycom.fbt.dto.Assignment;
import com.honeycom.fbt.dto.Employment;
import com.honeycom.fbt.dto.MatchInfo;
import com.honeycom.fbt.dto.ReviewFromTeam;
import com.honeycom.fbt.dto.MatchSchedule;
import com.honeycom.fbt.dto.Search;
import com.honeycom.fbt.dto.Team;
import com.honeycom.fbt.dto.TeamScore;
import com.honeycom.fbt.dto.TeamMember;
import com.honeycom.fbt.dto.User;
import com.honeycom.fbt.dto.Vote;
import com.honeycom.fbt.dto.VoteResult;

public interface FootBallTogetherService_MHK {
	public User selectUser(User user);
	
	public boolean insertUser(User user) throws Exception;
	public boolean updateUser(User user) throws Exception;
	public boolean deleteUser(User user) throws Exception;
	
	public boolean insertTeam(Team team) throws Exception;
	public boolean insertTeamMember(TeamMember tm) throws Exception;
	public boolean deleteTeamMember(TeamMember tm) throws Exception;
	public boolean updateTeamMember_allow(TeamMember tm) throws Exception;
	public boolean updateTeamMember_quit(TeamMember tm) throws Exception;
	public boolean updateTeamMember_appoint(TeamMember tm) throws Exception;

	public List<User> selectUsers(User user) throws Exception;
	public List<Team> selectTeams(Team team) throws Exception;
	public List<TeamMember> selectTeamMembers(TeamMember teamMember) throws Exception;	
	public TeamScore selectTeamMannerScore(TeamScore tms) throws Exception;
	public List<Vote> selectVotes(Vote vote) throws Exception;
	public List<MatchSchedule> selectMatchSchedules(MatchSchedule ms) throws Exception;
	public List<Assignment> selectAssignments(Assignment assignment) throws Exception;
	public List<Employment> selectEmployments(Employment emp) throws Exception;
	public List<Search> selectSearchs(Search search) throws Exception;
	public List<MatchInfo> selectMatchInfos(MatchInfo mi) throws Exception;
	
	
	// 평가하기 관련 로직
	public boolean insertReviewFromTeam(ReviewFromTeam mr) throws Exception;
	public boolean upsertReviewFromTeam(TeamScore tms) throws Exception;

	public List<MatchSchedule> selectMatchSchedulesByTeamId(MatchSchedule ms) throws Exception;

	public List<MatchSchedule> getMyTeamMatchSchedules(MatchSchedule ms) throws Exception;
	
	// 팀매칭, 용병, 양도 관련 로직
	public boolean insertMatchInfo(MatchInfo matchInfo) throws Exception;

	public int insertVote(Vote vote) throws Exception;
	public boolean insertVoteResult(VoteResult voteResult) throws Exception;
	public List<VoteResult> selectVoteResults(VoteResult voteResult) throws Exception;
	public boolean updateVoteResult(VoteResult voteResult) throws Exception;

	//이건 하다가 그만 두신건가요?
	int upsertSearch(Search search) throws Exception;
	
	
}
