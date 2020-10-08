package com.honeycom.fbt.dto;

import java.util.List;

public class MatchInfo {
	private int matchInfoId;
	private int homeTeamId;
	private String schedule;
	private String stadium;
	private String stadiumType;
	private double cost;

	private List<Vote> votes;
	private List<Assignment> assignments;
	private List<Employment> employeements;
	private List<Search> searchs;
	private MatchSchedule matchSchedule;

	public MatchInfo() {
		super();
	}

	public MatchInfo(int matchInfoId, int homeTeamId, String schedule, String stadium, String stadiumType, double cost,
			List<Vote> votes, List<Assignment> assignments, List<Employment> employeements, List<Search> searchs,
			MatchSchedule matchSchedule) {
		super();
		this.matchInfoId = matchInfoId;
		this.homeTeamId = homeTeamId;
		this.schedule = schedule;
		this.stadium = stadium;
		this.stadiumType = stadiumType;
		this.cost = cost;
		this.votes = votes;
		this.assignments = assignments;
		this.employeements = employeements;
		this.searchs = searchs;
		this.matchSchedule = matchSchedule;
	}

	public int getMatchInfoId() {
		return matchInfoId;
	}

	public void setMatchInfoId(int matchInfoId) {
		this.matchInfoId = matchInfoId;
	}

	public int getHomeTeamId() {
		return homeTeamId;
	}

	public void setHomeTeamId(int homeTeamId) {
		this.homeTeamId = homeTeamId;
	}

	public String getSchedule() {
		return schedule;
	}

	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}

	public String getStadium() {
		return stadium;
	}

	public void setStadium(String stadium) {
		this.stadium = stadium;
	}

	public String getStadiumType() {
		return stadiumType;
	}

	public void setStadiumType(String stadiumType) {
		this.stadiumType = stadiumType;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public List<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

	public List<Employment> getEmployeements() {
		return employeements;
	}

	public void setEmployeements(List<Employment> employeements) {
		this.employeements = employeements;
	}

	public List<Search> getSearchs() {
		return searchs;
	}

	public void setSearchs(List<Search> searchs) {
		this.searchs = searchs;
	}

	public MatchSchedule getMatchSchedule() {
		return matchSchedule;
	}

	public void setMatchSchedule(MatchSchedule matchSchedule) {
		this.matchSchedule = matchSchedule;
	}

	@Override
	public String toString() {
		return "MatchInfo [matchInfoId=" + matchInfoId + ", homeTeamId=" + homeTeamId + ", schedule=" + schedule
				+ ", stadium=" + stadium + ", stadiumType=" + stadiumType + ", cost=" + cost + ", votes=" + votes
				+ ", assignments=" + assignments + ", employeements=" + employeements + ", searchs=" + searchs
				+ ", matchSchedule=" + matchSchedule + "]";
	}
	
	
}
