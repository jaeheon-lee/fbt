package com.honeycom.fbt.dto;

import java.util.List;

public class TeamMember {
	private int teamMemberId;
	private String memberRegDate;
	private int memberLevel;
	private int participationCount;
	private int regStatus;
	private int teamId;
	private String email;
	private double sincerity;
	
	private List<Attendant> attendants;
	private List<VoteResult> voteResults;
	private List<Assignment> assignments;
	private List<Employment> employements;
	private List<Search> searchs;
	
	public TeamMember() {
		super();
	}

	public TeamMember(String memberRegDate, int memberLevel, int participationCount, int regStatus, int teamId,
			String email, double sincerity, List<Attendant> attendants, List<VoteResult> voteResults,
			List<Assignment> assignments, List<Employment> employements, List<Search> searchs) {
		super();
		this.memberRegDate = memberRegDate;
		this.memberLevel = memberLevel;
		this.participationCount = participationCount;
		this.regStatus = regStatus;
		this.teamId = teamId;
		this.email = email;
		this.sincerity = sincerity;
		this.attendants = attendants;
		this.voteResults = voteResults;
		this.assignments = assignments;
		this.employements = employements;
		this.searchs = searchs;
	}

	public TeamMember(int teamMemberId, String memberRegDate, int memberLevel, int participationCount, int regStatus,
			int teamId, String email, double sincerity, List<Attendant> attendants, List<VoteResult> voteResults,
			List<Assignment> assignments, List<Employment> employements, List<Search> searchs) {
		super();
		this.teamMemberId = teamMemberId;
		this.memberRegDate = memberRegDate;
		this.memberLevel = memberLevel;
		this.participationCount = participationCount;
		this.regStatus = regStatus;
		this.teamId = teamId;
		this.email = email;
		this.sincerity = sincerity;
		this.attendants = attendants;
		this.voteResults = voteResults;
		this.assignments = assignments;
		this.employements = employements;
		this.searchs = searchs;
	}

	public int getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(int teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public String getMemberRegDate() {
		return memberRegDate;
	}

	public void setMemberRegDate(String memberRegDate) {
		this.memberRegDate = memberRegDate;
	}

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	public int getParticipationCount() {
		return participationCount;
	}

	public void setParticipationCount(int participationCount) {
		this.participationCount = participationCount;
	}

	public int getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(int regStatus) {
		this.regStatus = regStatus;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSincerity() {
		return sincerity;
	}

	public void setSincerity(double sincerity) {
		this.sincerity = sincerity;
	}

	public List<Attendant> getAttendants() {
		return attendants;
	}

	public void setAttendants(List<Attendant> attendants) {
		this.attendants = attendants;
	}

	public List<VoteResult> getVoteResults() {
		return voteResults;
	}

	public void setVoteResults(List<VoteResult> voteResults) {
		this.voteResults = voteResults;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<Employment> getEmployements() {
		return employements;
	}

	public void setEmployements(List<Employment> employements) {
		this.employements = employements;
	}

	public List<Search> getSearchs() {
		return searchs;
	}

	public void setSearchs(List<Search> searchs) {
		this.searchs = searchs;
	}

	@Override
	public String toString() {
		return "TeamMember [teamMemberId=" + teamMemberId + ", memberRegDate=" + memberRegDate + ", memberLevel="
				+ memberLevel + ", participationCount=" + participationCount + ", regStatus=" + regStatus + ", teamId="
				+ teamId + ", email=" + email + ", sincerity=" + sincerity + ", attendants=" + attendants
				+ ", voteResults=" + voteResults + ", assignments=" + assignments + ", employements=" + employements
				+ ", searchs=" + searchs + "]";
	}
	
}
