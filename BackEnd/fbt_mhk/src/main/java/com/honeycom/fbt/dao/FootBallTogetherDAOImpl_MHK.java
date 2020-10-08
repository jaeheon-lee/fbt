package com.honeycom.fbt.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

@Repository
public class FootBallTogetherDAOImpl_MHK implements FootBallTogetherDAO_MHK{
	
	@Autowired
	private SqlSession sqlSession;
	private final String NS = "sql.footBAllTogether.mapper2." ;
	
	@Override
	public User selectUser(User user) {
		return sqlSession.selectOne(NS+"selectUser", user);
	}

	@Override
	public boolean insertUser(User user) throws Exception {
		return sqlSession.insert(NS + "insertUser", user) == 1 ? true : false;
	}

	@Override
	public boolean updateUser(User user) throws Exception {
		return sqlSession.update(NS + "updateUser", user) == 1 ? true : false;
	}

	@Override
	public boolean deleteUser(User user) throws Exception {
		return sqlSession.update(NS + "deleteUser", user) == 1 ? true : false;
	}

	@Override
	public boolean insertTeam(Team team) throws Exception {
		return sqlSession.update(NS + "insertTeam", team) == 1 ? true : false;
	}

	@Override
	public boolean insertTeamMember(TeamMember tm) throws Exception {
		return sqlSession.update(NS + "insertTeamMember", tm) == 1 ? true : false;
	}

	@Override
	public boolean updateTeamMember_allow(TeamMember tm) throws Exception {
		return sqlSession.update(NS + "updateTeamMember_allow", tm) == 1 ? true : false;
	}

	@Override
	public boolean deleteTeamMember(TeamMember tm) throws Exception {
		return sqlSession.update(NS + "deleteTeamMember", tm) == 1 ? true : false;
	}

	@Override
	public boolean updateTeamMember_quit(TeamMember tm) throws Exception {
		return sqlSession.update(NS + "updateTeamMember_quit", tm) == 1 ? true : false;
	}

	@Override
	public boolean updateTeamMember_appoint(TeamMember tm) throws Exception {
		return sqlSession.update(NS + "updateTeamMember_appoint", tm) == 1 ? true : false;
	}

	@Override
	public List<User> selectUsers(User user) throws Exception {
		return sqlSession.selectList(NS + "selectUser", user);
	}

	@Override
	public List<Team> selectTeams(Team team) throws Exception {
		return sqlSession.selectList(NS + "selectTeams", team);
	}

	@Override
	public List<TeamMember> selectTeamMembers(TeamMember teamMember) throws Exception {
		return sqlSession.selectList(NS + "selectTeamMembers", teamMember);
	}

	@Override
	public boolean insertReviewFromTeam(ReviewFromTeam mr) throws Exception {
		return sqlSession.insert(NS + "insertReviewFromTeam", mr) == 1 ? true : false ;
	}
	
	@Override
	public boolean upsertReviewFromTeam(TeamScore tms) throws Exception {
		return sqlSession.insert(NS + "upsertReviewFromTeam", tms) == 1 ? true : false ;
	}

	@Override
	public List<MatchSchedule> selectMatchSchedulesByTeamId(MatchSchedule ms) throws Exception {
		return sqlSession.selectList(NS + "selectMatchSchedules", ms);
	}

	@Override
	public List<MatchSchedule> getMyTeamMatchSchedules(MatchSchedule ms) throws Exception {
		return sqlSession.selectList(NS + "getMyTeamMatchSchedules", ms);
	}

	@Override
	public TeamScore selectTeamMannerScore(TeamScore tms) throws Exception {
		return sqlSession.selectOne(NS + "selectTeamMannerScore", tms);
	}

	@Override
	public List<Vote> selectVotes(Vote vote) throws Exception{
		return sqlSession.selectList(NS + "selectVotes", vote);
	}

	@Override
	public List<MatchSchedule> selectMatchSchedules(MatchSchedule ms) throws Exception {
		return sqlSession.selectList(NS + "selectMatchSchedules", ms);
	}

	@Override
	public List<Assignment> selectAssignments(Assignment assignment) throws Exception {
		return sqlSession.selectList(NS + "selectAssignment", assignment);
	}

	@Override
	public List<Employment> selectEmployments(Employment emp) throws Exception {
		return sqlSession.selectList(NS + "selectEmployments", emp);
	}

	@Override
	public List<Search> selectSearchs(Search search) throws Exception {
		return sqlSession.selectList(NS + "selectSearch", search);
	}

	@Override
	public List<MatchInfo> selectMatchInfos(MatchInfo mi) throws Exception {
		return sqlSession.selectList(NS + "selectMatchInfo", mi);
	}

	@Override
	public boolean insertMatchInfo(MatchInfo matchInfo) throws Exception {
		return sqlSession.insert(NS + "insertMatchInfo", matchInfo) == 1 ? true : false;
	}

	@Override
	public int upsertSearch(Search search) throws Exception {
		return sqlSession.insert(NS + "upsertMatchInfo", search);
	}

	
	
	
	@Override
	public int insertVote(Vote vote) {
		return sqlSession.insert(NS+"insertVote", vote);
	}

	@Override
	public boolean insertVoteResult(VoteResult voteResult) {
		return sqlSession.insert(NS+"insertVoteResult", voteResult) == 1? true : false;
	}

	@Override
	public List<VoteResult> selectVoteResults(VoteResult voteResult) {
		return sqlSession.selectList(NS+"selectVoteResults", voteResult);
	}

	@Override
	public boolean updateVoteResult(VoteResult voteResult) {
		return sqlSession.update(NS+"updateVoteResult", voteResult)==1?true:false;
	}

}
