package com.biomans.fbt.domain;

public class Entry {
	private int entryId;
	private int attendance;
	private int type;
	private int matchScore;
	private int goal;
	private int assist;
	private MatchSchedule matchSchedule;
	private TeamMember teamMember;
	private User user;
	private Team team;
	private EmpScore empScore;
	
	public Entry() {}

	public Entry(int entryId, int attendance, int type, int matchScore, int goal, int assist,
			MatchSchedule matchSchedule, TeamMember teamMember, User user, Team team, EmpScore empScore) {
		super();
		this.entryId = entryId;
		this.attendance = attendance;
		this.type = type;
		this.matchScore = matchScore;
		this.goal = goal;
		this.assist = assist;
		this.matchSchedule = matchSchedule;
		this.teamMember = teamMember;
		this.user = user;
		this.team = team;
		this.empScore = empScore;
	}

	public int getEntryId() {
		return entryId;
	}

	public void setEntryId(int entryId) {
		this.entryId = entryId;
	}

	public int getAttendance() {
		return attendance;
	}

	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getMatchScore() {
		return matchScore;
	}

	public void setMatchScore(int matchScore) {
		this.matchScore = matchScore;
	}

	public int getGoal() {
		return goal;
	}

	public void setGoal(int goal) {
		this.goal = goal;
	}

	public int getAssist() {
		return assist;
	}

	public void setAssist(int assist) {
		this.assist = assist;
	}

	public MatchSchedule getMatchSchedule() {
		return matchSchedule;
	}

	public void setMatchSchedule(MatchSchedule matchSchedule) {
		this.matchSchedule = matchSchedule;
	}

	public TeamMember getTeamMember() {
		return teamMember;
	}

	public void setTeamMember(TeamMember teamMember) {
		this.teamMember = teamMember;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public EmpScore getEmpScore() {
		return empScore;
	}

	public void setEmpScore(EmpScore empScore) {
		this.empScore = empScore;
	}

	@Override
	public String toString() {
		return "Entry [entryId=" + entryId + ", attendance=" + attendance + ", type=" + type + ", matchScore="
				+ matchScore + ", goal=" + goal + ", assist=" + assist + ", matchSchedule=" + matchSchedule
				+ ", teamMember=" + teamMember + ", user=" + user + ", team=" + team + ", empScore=" + empScore + "]";
	}

	
}
