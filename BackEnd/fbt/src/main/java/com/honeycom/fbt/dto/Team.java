package com.honeycom.fbt.dto;

import java.util.List;

public class Team {
	private int teamId;
	private String teamName;
	private String emblem;
	private String area;
	private String stadiumInfo;
	private String uniformColor;
	private int uniformType;
	private String foundingDate;
	private String teamRegDate;
	
	private TeamMannerScore teamMannerScore;
	private List<TeamMember> teamMembers;
	private List<Vote> votes;
	private List<MatchSchedule> matchSchedules;
	private List<Assignment> assignments;
	private List<Employment> employements;
	private List<Search> searchs;
	private List<MatchInfo> matchInfo; 
	
	public Team() {}

	public Team(String teamName, String emblem, String area, String stadiumInfo, String uniformColor, int uniformType,
			String foundingDate, String teamRegDate, TeamMannerScore teamMannerScore, List<TeamMember> teamMembers,
			List<Vote> votes, List<MatchSchedule> matchSchedules, List<Assignment> assignments,
			List<Employment> employements, List<Search> searchs, List<MatchInfo> matchInfo) {
		super();
		this.teamName = teamName;
		this.emblem = emblem;
		this.area = area;
		this.stadiumInfo = stadiumInfo;
		this.uniformColor = uniformColor;
		this.uniformType = uniformType;
		this.foundingDate = foundingDate;
		this.teamRegDate = teamRegDate;
		this.teamMannerScore = teamMannerScore;
		this.teamMembers = teamMembers;
		this.votes = votes;
		this.matchSchedules = matchSchedules;
		this.assignments = assignments;
		this.employements = employements;
		this.searchs = searchs;
		this.matchInfo = matchInfo;
	}

	public Team(int teamId, String teamName, String emblem, String area, String stadiumInfo, String uniformColor,
			int uniformType, String foundingDate, String teamRegDate, TeamMannerScore teamMannerScore,
			List<TeamMember> teamMembers, List<Vote> votes, List<MatchSchedule> matchSchedules,
			List<Assignment> assignments, List<Employment> employements, List<Search> searchs,
			List<MatchInfo> matchInfo) {
		super();
		this.teamId = teamId;
		this.teamName = teamName;
		this.emblem = emblem;
		this.area = area;
		this.stadiumInfo = stadiumInfo;
		this.uniformColor = uniformColor;
		this.uniformType = uniformType;
		this.foundingDate = foundingDate;
		this.teamRegDate = teamRegDate;
		this.teamMannerScore = teamMannerScore;
		this.teamMembers = teamMembers;
		this.votes = votes;
		this.matchSchedules = matchSchedules;
		this.assignments = assignments;
		this.employements = employements;
		this.searchs = searchs;
		this.matchInfo = matchInfo;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getEmblem() {
		return emblem;
	}

	public void setEmblem(String emblem) {
		this.emblem = emblem;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getStadiumInfo() {
		return stadiumInfo;
	}

	public void setStadiumInfo(String stadiumInfo) {
		this.stadiumInfo = stadiumInfo;
	}

	public String getUniformColor() {
		return uniformColor;
	}

	public void setUniformColor(String uniformColor) {
		this.uniformColor = uniformColor;
	}

	public int getUniformType() {
		return uniformType;
	}

	public void setUniformType(int uniformType) {
		this.uniformType = uniformType;
	}

	public String getFoundingDate() {
		return foundingDate;
	}

	public void setFoundingDate(String foundingDate) {
		this.foundingDate = foundingDate;
	}

	public String getTeamRegDate() {
		return teamRegDate;
	}

	public void setTeamRegDate(String teamRegDate) {
		this.teamRegDate = teamRegDate;
	}

	public TeamMannerScore getTeamMannerScore() {
		return teamMannerScore;
	}

	public void setTeamMannerScore(TeamMannerScore teamMannerScore) {
		this.teamMannerScore = teamMannerScore;
	}

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public List<MatchSchedule> getMatchSchedules() {
		return matchSchedules;
	}

	public void setMatchSchedules(List<MatchSchedule> matchSchedules) {
		this.matchSchedules = matchSchedules;
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

	public List<MatchInfo> getMatchInfo() {
		return matchInfo;
	}

	public void setMatchInfo(List<MatchInfo> matchInfo) {
		this.matchInfo = matchInfo;
	}

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamName=" + teamName + ", emblem=" + emblem + ", area=" + area
				+ ", stadiumInfo=" + stadiumInfo + ", uniformColor=" + uniformColor + ", uniformType=" + uniformType
				+ ", foundingDate=" + foundingDate + ", teamRegDate=" + teamRegDate + ", teamMannerScore="
				+ teamMannerScore + ", teamMembers=" + teamMembers + ", votes=" + votes + ", matchSchedules="
				+ matchSchedules + ", assignments=" + assignments + ", employements=" + employements + ", searchs="
				+ searchs + ", matchInfo=" + matchInfo + "]";
	}
	
}
