package com.biomans.fbt.domain;

import java.util.List;

public class Employ {
	private int employId;
	private Team teamGiver;
	private MatchSchedule matchSchedule;
	private TeamMember teamMember;
	private int cost;
	private int reqNumber;
	private String content;
	private String regDate;
	private String dueDate;
	private List<EmployResult> employResults;
	private int currentNum;
	
	public Employ() {}

	public Employ(int employId, Team teamGiver, MatchSchedule matchSchedule, TeamMember teamMember, int cost,
			int reqNumber, String content, String regDate, String dueDate, List<EmployResult> employResults,
			int currentNum) {
		super();
		this.employId = employId;
		this.teamGiver = teamGiver;
		this.matchSchedule = matchSchedule;
		this.teamMember = teamMember;
		this.cost = cost;
		this.reqNumber = reqNumber;
		this.content = content;
		this.regDate = regDate;
		this.dueDate = dueDate;
		this.employResults = employResults;
		this.currentNum = currentNum;
	}

	public int getEmployId() {
		return employId;
	}

	public void setEmployId(int employId) {
		this.employId = employId;
	}

	public Team getTeamGiver() {
		return teamGiver;
	}

	public void setTeamGiver(Team teamGiver) {
		this.teamGiver = teamGiver;
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

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getReqNumber() {
		return reqNumber;
	}

	public void setReqNumber(int reqNumber) {
		this.reqNumber = reqNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public List<EmployResult> getEmployResults() {
		return employResults;
	}

	public void setEmployResults(List<EmployResult> employResults) {
		this.employResults = employResults;
	}

	public int getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	@Override
	public String toString() {
		return "Employ [employId=" + employId + ", teamGiver=" + teamGiver + ", matchSchedule=" + matchSchedule
				+ ", teamMember=" + teamMember + ", cost=" + cost + ", reqNumber=" + reqNumber + ", content=" + content
				+ ", regDate=" + regDate + ", dueDate=" + dueDate + ", employResults=" + employResults + ", currentNum="
				+ currentNum + "]";
	}

	
}
