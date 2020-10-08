package com.honeycom.fbt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.honeycom.fbt.dao.FootBallTogetherDAO_MHK;
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

@Service
public class FootBallTogetherServiceImpl_MHK implements FootBallTogetherService_MHK{

	@Autowired
	private FootBallTogetherDAO_MHK footBallTogetherDAO_MHK; 
	
	@Override
	public User selectUser(User user) {
		return footBallTogetherDAO_MHK.selectUser(user);
	}

	@Override
	public boolean insertUser(User user) throws Exception {
		return footBallTogetherDAO_MHK.insertUser(user);
	}

	@Override
	public boolean updateUser(User user) throws Exception {
		return footBallTogetherDAO_MHK.updateUser(user);
	}

	@Override
	public boolean deleteUser(User user) throws Exception {
		return footBallTogetherDAO_MHK.deleteUser(user);
	}

	@Override
	public boolean insertTeam(Team team) throws Exception {
		return footBallTogetherDAO_MHK.insertTeam(team);
	}

	@Override
	public boolean insertTeamMember(TeamMember tm) throws Exception {
		return footBallTogetherDAO_MHK.insertTeamMember(tm);
	}

	@Override
	public boolean updateTeamMember_allow(TeamMember tm) throws Exception {
		return footBallTogetherDAO_MHK.updateTeamMember_allow(tm);
	}

	@Override
	public boolean deleteTeamMember(TeamMember tm) throws Exception {
		return footBallTogetherDAO_MHK.deleteTeamMember(tm);
	}

	@Override
	public boolean updateTeamMember_quit(TeamMember tm) throws Exception {
		return footBallTogetherDAO_MHK.updateTeamMember_quit(tm);
	}

	@Override
	public boolean updateTeamMember_appoint(TeamMember tm) throws Exception {
		return footBallTogetherDAO_MHK.updateTeamMember_appoint(tm);
	}

	@Override
	public List<User> selectUsers(User user) throws Exception {
		return footBallTogetherDAO_MHK.selectUsers(user);
	}

	@Override
	public List<Team> selectTeams(Team team) throws Exception {
		return footBallTogetherDAO_MHK.selectTeams(team);
	}

	@Override
	public List<TeamMember> selectTeamMembers(TeamMember teamMember) throws Exception {
		return footBallTogetherDAO_MHK.selectTeamMembers(teamMember);
	}

	@Override
	public boolean insertReviewFromTeam(ReviewFromTeam mr) throws Exception {
		return footBallTogetherDAO_MHK.insertReviewFromTeam(mr);
	}
	
	@Override
	public boolean upsertReviewFromTeam(TeamScore tms) throws Exception {
		return footBallTogetherDAO_MHK.upsertReviewFromTeam(tms);
	}
	

	@Override
	public List<MatchSchedule> selectMatchSchedulesByTeamId(MatchSchedule ms) throws Exception {
		return footBallTogetherDAO_MHK.selectMatchSchedulesByTeamId(ms);
	}

	@Override
	public List<MatchSchedule> getMyTeamMatchSchedules(MatchSchedule ms) throws Exception {
		return footBallTogetherDAO_MHK.getMyTeamMatchSchedules(ms);
	}

	@Override
	public TeamScore selectTeamMannerScore(TeamScore tms) throws Exception {
		return footBallTogetherDAO_MHK.selectTeamMannerScore(tms);
	}

	@Override
	public List<Vote> selectVotes(Vote vote) throws Exception {
		return footBallTogetherDAO_MHK.selectVotes(vote);
	}

	@Override
	public List<MatchSchedule> selectMatchSchedules(MatchSchedule ms) throws Exception {
		return footBallTogetherDAO_MHK.selectMatchSchedules(ms);
	}

	@Override
	public List<Assignment> selectAssignments(Assignment assignment) throws Exception {
		return footBallTogetherDAO_MHK.selectAssignments(assignment);
	}

	@Override
	public List<Employment> selectEmployments(Employment emp) throws Exception {
		return footBallTogetherDAO_MHK.selectEmployments(emp);
	}

	@Override
	public List<Search> selectSearchs(Search search) throws Exception {
		return footBallTogetherDAO_MHK.selectSearchs(search);
	}

	@Override
	public List<MatchInfo> selectMatchInfos(MatchInfo mi) throws Exception {
		return footBallTogetherDAO_MHK.selectMatchInfos(mi);
	}

	@Override
	public boolean insertMatchInfo(MatchInfo matchInfo) throws Exception {
		return footBallTogetherDAO_MHK.insertMatchInfo(matchInfo);
	}

	@Override
	public int upsertSearch(Search search) throws Exception {
		return footBallTogetherDAO_MHK.upsertSearch(search);
	}

	@Override
	@Transactional
	public int insertVote(Vote vote) throws Exception{
		return footBallTogetherDAO_MHK.insertVote(vote);
	}

	@Override
	@Transactional
	public boolean insertVoteResult(VoteResult voteResult) throws Exception{
		return footBallTogetherDAO_MHK.insertVoteResult(voteResult);
	}

	@Override
	public List<VoteResult> selectVoteResults(VoteResult voteResult) throws Exception{
		return footBallTogetherDAO_MHK.selectVoteResults(voteResult);
	}

	@Override
	public boolean updateVoteResult(VoteResult voteResult) throws Exception{
		return footBallTogetherDAO_MHK.updateVoteResult(voteResult);
	}

}
