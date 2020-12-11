package com.biomans.fbt.domain;

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
	private String account;
	private String bank;
	private List<TeamScore> teamScores;
	private List<TeamMember> teamMembers;
	private List<MatchSchedule> matchSchedules;
	private double teamManner;
	private double teamAbility;
	private int totalNum;

	public Team() {}

	public Team(int teamId, String teamName, String emblem, String area, String stadiumInfo, String uniformColor,
			int uniformType, String foundingDate, String teamRegDate, String account, String bank,
			List<TeamScore> teamScores, List<TeamMember> teamMembers, List<MatchSchedule> matchSchedules,
			double teamManner, double teamAbility, int totalNum) {
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
		this.account = account;
		this.bank = bank;
		this.teamScores = teamScores;
		this.teamMembers = teamMembers;
		this.matchSchedules = matchSchedules;
		this.teamManner = teamManner;
		this.teamAbility = teamAbility;
		this.totalNum = totalNum;
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

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public List<TeamScore> getTeamScores() {
		return teamScores;
	}

	public void setTeamScores(List<TeamScore> teamScores) {
		this.teamScores = teamScores;
	}

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public List<MatchSchedule> getMatchSchedules() {
		return matchSchedules;
	}

	public void setMatchSchedules(List<MatchSchedule> matchSchedules) {
		this.matchSchedules = matchSchedules;
	}

	public double getTeamManner() {
		return teamManner;
	}

	public void setTeamManner(double teamManner) {
		this.teamManner = teamManner;
	}

	public double getTeamAbility() {
		return teamAbility;
	}

	public void setTeamAbility(double teamAbility) {
		this.teamAbility = teamAbility;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamName=" + teamName + ", emblem=" + emblem + ", area=" + area
				+ ", stadiumInfo=" + stadiumInfo + ", uniformColor=" + uniformColor + ", uniformType=" + uniformType
				+ ", foundingDate=" + foundingDate + ", teamRegDate=" + teamRegDate + ", account=" + account + ", bank="
				+ bank + ", teamScores=" + teamScores + ", teamMembers=" + teamMembers + ", matchSchedules="
				+ matchSchedules + ", teamManner=" + teamManner + ", teamAbility=" + teamAbility + ", totalNum="
				+ totalNum + "]";
	}

	
}
