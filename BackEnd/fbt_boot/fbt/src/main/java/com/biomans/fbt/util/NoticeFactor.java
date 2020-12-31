package com.biomans.fbt.util;

import java.util.List;

import com.biomans.fbt.domain.Assignment;
import com.biomans.fbt.domain.AssignmentReservation;
import com.biomans.fbt.domain.Employ;
import com.biomans.fbt.domain.EmployResult;
import com.biomans.fbt.domain.Notice;
import com.biomans.fbt.domain.Search;
import com.biomans.fbt.domain.SearchReservation;
import com.biomans.fbt.domain.TeamMember;
import com.biomans.fbt.domain.VoteMatch;

public class NoticeFactor {
	private String type;
	private String teamName;
	private VoteMatch voteMatch;
	private Search search;
	private SearchReservation searchRes;
	private Assignment assign;
	private AssignmentReservation assignRes;
	private List<TeamMember> teamMembers;
	private Notice notice;
	private Employ employ;
	private EmployResult employRes;
	
	public NoticeFactor() {}

	public NoticeFactor(String type, String teamName, VoteMatch voteMatch, Search search, SearchReservation searchRes,
			Assignment assign, AssignmentReservation assignRes, List<TeamMember> teamMembers, Notice notice,
			Employ employ, EmployResult employRes) {
		super();
		this.type = type;
		this.teamName = teamName;
		this.voteMatch = voteMatch;
		this.search = search;
		this.searchRes = searchRes;
		this.assign = assign;
		this.assignRes = assignRes;
		this.teamMembers = teamMembers;
		this.notice = notice;
		this.employ = employ;
		this.employRes = employRes;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public VoteMatch getVoteMatch() {
		return voteMatch;
	}

	public void setVoteMatch(VoteMatch voteMatch) {
		this.voteMatch = voteMatch;
	}

	public Search getSearch() {
		return search;
	}

	public void setSearch(Search search) {
		this.search = search;
	}

	public SearchReservation getSearchRes() {
		return searchRes;
	}

	public void setSearchRes(SearchReservation searchRes) {
		this.searchRes = searchRes;
	}

	public Assignment getAssign() {
		return assign;
	}

	public void setAssign(Assignment assign) {
		this.assign = assign;
	}

	public AssignmentReservation getAssignRes() {
		return assignRes;
	}

	public void setAssignRes(AssignmentReservation assignRes) {
		this.assignRes = assignRes;
	}

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public Employ getEmploy() {
		return employ;
	}

	public void setEmploy(Employ employ) {
		this.employ = employ;
	}

	public EmployResult getEmployRes() {
		return employRes;
	}

	public void setEmployRes(EmployResult employRes) {
		this.employRes = employRes;
	}

	@Override
	public String toString() {
		return "NoticeFactor [type=" + type + ", teamName=" + teamName + ", voteMatch=" + voteMatch + ", search="
				+ search + ", searchRes=" + searchRes + ", assign=" + assign + ", assignRes=" + assignRes
				+ ", teamMembers=" + teamMembers + ", notice=" + notice + ", employ=" + employ + ", employRes="
				+ employRes + "]";
	}
}
