package com.honeycom.fbt;

import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.honeycom.fbt.dto.MatchReview;
import com.honeycom.fbt.dto.User;
import com.honeycom.fbt.dto.Vote;
import com.honeycom.fbt.dto.VoteResult;

public class footBallTogetherTest_LJH {

	@Test
	public void unit() throws Exception {
		Reader r = Resources.getResourceAsReader("config/SqlMapConfig.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(r);
		SqlSession session = factory.openSession();

		// ==================dto select 관련 query ==================

		/* <!-- 1. 회원: user --> */
		/*
		 * User uVo = new User();
		 * 
		 * uVo.setEmail("ggapdol2@gmail.com"); uVo.setPass("1234");
		 * 
		 * List<User> rVo = session.selectList("sql.footBAllTogether.mapper1.selectUser",
		 * uVo); System.out.println(rVo);
		 */
		/* <!-- 1. 회원: user 끝 --> */

		/* <!--2.팀멤버 : teamMember --> */
		/*
		 * TeamMember tVo = new TeamMember(); //tVo.setTeamMemberId(1);
		 * //tVo.setMemberLevel(1); //tVo.setRegStatus(2); //tVo.setTeamId(1);
		 * List<TeamMember> rVo =
		 * session.selectList("sql.footBAllTogether.mapper1.selectTeamMembers", tVo);
		 * System.out.println(rVo);
		 */
		/* <!--2.팀멤버 : teamMember 끝--> */

		/* <!--3. 팀 : team --> */
		/*
		 * Team tVo = new Team(); //tVo.setTeamId(1); //tVo.setTeamName("FC바르셀로나");
		 * //tVo.setArea("서울"); //tVo.setStadiumInfo("서울숲"); List<Team> rVo =
		 * session.selectList("sql.footBAllTogether.mapper1.selectTeams", tVo);
		 * System.out.println(rVo);
		 */
		/* <!--3. 팀 : team 끝--> */

		/* <!-- 4. 팀매너점수: TeamMannerScore --> */
		/*
		 * TeamMannerScore tVo = new TeamMannerScore(); tVo.setTeamId(1);
		 * List<TeamMannerScore> rVo =
		 * session.selectList("sql.footBAllTogether.mapper1.selectTeamMannerScore", tVo);
		 * System.out.println(rVo);
		 */
		/* <!-- 4. 팀매너점수: TeamMannerScore 끝!--> */

		/* <!-- 5.투표: Vote --> */
		/*
		 * Vote vVo = new Vote(); //vVo.setVoteId(1); //vVo.setTeamId(1);
		 * //vVo.setVoteStatus(2); //vVo.setMatchInfoId(1); List<Vote> rVo =
		 * session.selectList("sql.footBAllTogether.mapper1.selectVotes", vVo);
		 * System.out.println(rVo);
		 */
		/* <!-- 5.투표: Vote 끝--> */

		/* <!--6.투표결과:voteResult --> */
		
//		  VoteResult vVo = new VoteResult(); 
//		  vVo.setVoteId(1);
//		  vVo.setTeamMemberId(1); 
//		  vVo.setAttendance(1);
//		  List<VoteResult> rVo =
//		  session.selectList("sql.footBAllTogether.mapper1.selectVoteResults", vVo);
//		  System.out.println(rVo);
//		 
		/* <!--6.투표결과:voteResult 끝--> */

		/* <!-- 7. 매치 정보 : match_info --> */
		/*
		 * MatchInfo mVo = new MatchInfo(); //mVo.setMatchInfoId(1);
		 * mVo.setHomeTeamId(1); List<MatchInfo> rVo =
		 * session.selectList("sql.footBAllTogether.mapper1.selectMatchInfo", mVo);
		 * System.out.println(rVo);
		 */
		/* <!-- 7. 매치 정보 : match_info 끝 --> */

		/* <!--8.양도:assignment --> */
		/*
		 * Assignment asVo = new Assignment(); //asVo.setAssignmentId(1);
		 * //asVo.setTeamIdGiver(1); //asVo.setTeamMemberIdGiver(1);
		 * //asVo.setAssignmentStatus(2); //asVo.setMatchInfoId(1); List<Assignment> rVo
		 * = session.selectList("sql.footBAllTogether.mapper1.selectAssignment", asVo);
		 * System.out.println(rVo);
		 */
		/* <!--8.양도:assignment 끝--> */

		/* <!-- 9.용벙:employment --> */
		/*
		 * Employment eVo = new Employment(); //eVo.setEmail("ggapdol2@gmail.com");
		 * //eVo.setEmploymentStatus(1); //eVo.setMatchInfoId(1);
		 * //eVo.setTeamIdGiver(1); //eVo.setTeamMemberIdGiver(1); List<Employment> rVo
		 * = session.selectList("sql.footBAllTogether.mapper1.selectEmployments", eVo);
		 * System.out.println(rVo);
		 */
		/* <!-- 9.용벙:employment 끝 --> */

		/* <!-- 10. 상대팀 찾기:search--> */
		/*
		 * Search sVo = new Search(); // sVo.setSearchId(1); // sVo.setTeamIdGiver(1);
		 * // sVo.setTeamMemberIdGiver(1); // sVo.setSearchStatus(2); //
		 * sVo.setMatchInfoId(1); List<Search> rVo =
		 * session.selectList("sql.footBAllTogether.mapper1.selectSearch", sVo);
		 * System.out.println(rVo);
		 */
		/* <!-- 10. 상대팀 찾기:search 끝--> */

		/* <!-- 11.매치스케줄 :matchSchedule --> */
		/*
		 * MatchSchedule msVo = new MatchSchedule(); //msVo.setMatchScheduleId(1);
		 * msVo.setHomeTeamId(1); //msVo.setMatchInfoId(1); List<MatchSchedule> rVo =
		 * session.selectList("sql.footBAllTogether.mapper1.selectMatchSchedules", msVo);
		 * System.out.println(rVo); <!-- 11.매치스케줄 :matchSchedule 끝-->
		 */
		/* <!-- 11.매치스케줄 :matchSchedule 끝--> */

		/* <!-- 12.참석자: attendant --> */
		/*
		 * Attendant aVo = new Attendant(); //aVo.setAttendantId(1);
		 * //aVo.setMatchScheduleId(1); //aVo.setTeamMemberId(1);
		 * //aVo.setAttendance(1); List<Attendant> rVo =
		 * session.selectList("sql.footBAllTogether.mapper1.selectAttendants", aVo);
		 * System.out.println(rVo);
		 */
		//<!-- 12.참석자: attendant 끝-->

		//<!-- 13.매치 리뷰:matchReview -->
		/*
		 * MatchReview mVo = new MatchReview(); mVo.setAttendantId(1); List<MatchReview>
		 * rVo = session.selectList("sql.footBAllTogether.mapper1.selectMatchReviews",
		 * mVo); System.out.println(rVo);
		 */
		// <!-- 13.매치 리뷰:matchReview 끝--> 
		
		//<!--==================dto select 관련 query 끝==================-->

		
		//<!--========================= 기본 기능=================================== -->

		// 1. 회원 가입
		
		// 2. 회원 정보수정: userModify
//		User vo1 = new User();
//		vo1.setEmail("abc1@gmail.com");
//		vo1.setName("이재헌");
//		vo1.setPass("1234");
//		vo1.setMainFoot("Right");
//		vo1.setPhoneNum("1234567890");
//		vo1.setPosition("MF");
//		boolean result = session.update("sql.football.mapper1.user-update", vo1) ==1 ? true : false;
//		session.commit();
//		System.out.println(result);
		
		//3. 회원 탈퇴
				
		//<!--========================= 기본 기능 끝=================================== -->
		
		//<!--========================= 팀 생성 및 관리 기능===================================-->
		// 1.팀 생성: team-create
//		Team team1 = new Team();
//		team1.setTeamName("FC답십리");
//		team1.setEmblem("img/1.jpg");
//		team1.setArea("서울시 동대문구");
//		boolean teamInsert = session.insert("sql.football.mapper1.team-create", team1) == 1 ? true : false;
//		session.commit();
//		System.out.println(teamInsert);

		// 2. 팀 멤버 추가하기: team-member-create
//		TeamMember tm1 = new TeamMember(); // 매니저
//		tm1.setMemberLevel(1); // 매니저 팀 창단과 동시에 멤버레벨을 세팅(기본값 노노)
//		tm1.setRegStatus(2); // 매니저는 가입 상태도 변환(일반회원은 필요없음)
//		tm1.setTeamId(2);
//		tm1.setEmail("abc1@gmail.com");
//		boolean tmInsert = session.insert("sql.football.mapper1.team-member-create", tm1) == 1 ? true : false;
//		session.commit();
//		System.out.println(tmInsert);
//		
//		TeamMember tm2 = new TeamMember(); // 일반회원
//		tm2.setTeamId(2);
//		tm2.setEmail("abc@gmail.com");
//		boolean tmInsert2 = session.insert("sql.football.mapper1.team-member-create", tm2) == 1 ? true : false;
//		session.commit();
//		System.out.println(tmInsert2);		

		
		// 3. 팀멤버 가입 수락 : team-member-reg-update : 팀 가입 수락
//		boolean tmRegUpdate = session.update("sql.football.mapper1.team-member-reg-update", tm2) == 1 ? true : false;
//		session.commit();
//		System.out.println(tmRegUpdate);

		//4. 팀멤버 가입 거절: team-member-reg-delete : 팀 가입 거절
//		boolean tmDelete = session.delete("sql.football.mapper1.team-member-reg-delete", tm2) == 1 ? true : false;
//		session.commit();
//		System.out.println(tmDelete);

		// 5. 일반회원의 팀 탈퇴: team-member-quit-update : 팀 탈퇴
//		boolean tmQuitUpdate = session.update("sql.football.mapper1.team-member-quit-update", tm2) == 1 ? true : false;
//		session.commit();
//		System.out.println(tmQuitUpdate);
		
		//6. 부매니저 권한 임명: team-member-vice-manager : 부매니저 임명
//		boolean tmViceUpdate = session.update("sql.football.mapper1.team-member-vice-manager", tm2) == 1 ? true : false;
//		session.commit();
//		System.out.println(tmViceUpdate);

//		// user-info-update : 회원정보 수정
//		User user1 = new User();
//		user1.setEmail("1@gmail.com");
//		user1.setPass("1234");
//		user1.setName("Moon");
//		boolean userInfoUpdate = session.update("sql.football.mapper1.user-info-update", user1) == 1 ? true : false;
//		session.commit();
//		System.out.println(userInfoUpdate);
//
//		// allUser
//		List<User> list = session.selectList("sql.football.mapper1.allUser");
//		System.out.println(list);
		//<!--========================= 팀 생성 및 관리 기능 끝===================================-->
		
		//<!--=========================경기 참석 투표 기능=================================== -->
		//1. 투표 추가하기
//		Vote vote = new Vote();
//		vote.setContents("자자...");
//		vote.setDueDate("2020-08-30");
//		vote.setTeamId(1);
//		vote.setWriter("이재헌");
//		vote.setMatchInfoId(1);
//		session.insert("sql.footBAllTogether.mapper1.insertVote", vote);
//		session.commit();
//		System.out.println(vote);
		
		//2. 투표 결과 추가하기
//		VoteResult voteResult = new VoteResult();
//		voteResult.setAttendance(1);
//		voteResult.setTeamMemberId(2);
//		voteResult.setVoteId(30);
//		
//		session.insert("sql.footBAllTogether.mapper1.insertVoteResult", voteResult);
//		session.commit();
		
//		//3. 투표 결과 수정하기
//		VoteResult voteResult = new VoteResult();
//		voteResult.setAttendance(1);
//		voteResult.setTeamMemberId(2);
//		voteResult.setVoteId(30);
//		
//		session.insert("sql.footBAllTogether.mapper1.updateVoteResult", voteResult);
//		session.commit();
		
		//<!--=========================경기 참석 투표 끝=================================== -->
	}
}
