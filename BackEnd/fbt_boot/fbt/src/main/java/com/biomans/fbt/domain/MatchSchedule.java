package com.biomans.fbt.domain;

import java.util.List;

public class MatchSchedule {
	private int matchScheduleId;
	private String startTime;
	private String endTime;
	private int duration;
	private int cost;
	private String writer;
	private String regDate;
	private String homeContent;
	private String awayContent;
	private String stadiumName;
	private String stadiumType;
	private String stadiumAddress;
	private double stadiumX;
	private double stadiumY;
	private int stadiumParking;
	private int stadiumShower;
	private String matchType;
	private int isConfirmed;
	private Team homeTeam;
	private Team awayTeam;
	private VoteMatch voteMatch;
	private Search search;
	private Assignment assignment;
	private Employ employ;
	private MatchResult matchResult;
	private List<Entry> entries;
	private List<TeamScore> teamScores;
	
	public MatchSchedule() {}

	public MatchSchedule(int matchScheduleId, String startTime, String endTime, int duration, int cost, String writer,
			String regDate, String homeContent, String awayContent, String stadiumName, String stadiumType,
			String stadiumAddress, double stadiumX, double stadiumY, int stadiumParking, int stadiumShower,
			String matchType, int isConfirmed, Team homeTeam, Team awayTeam, VoteMatch voteMatch, Search search,
			Assignment assignment, Employ employ, MatchResult matchResult, List<Entry> entries,
			List<TeamScore> teamScores) {
		super();
		this.matchScheduleId = matchScheduleId;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
		this.cost = cost;
		this.writer = writer;
		this.regDate = regDate;
		this.homeContent = homeContent;
		this.awayContent = awayContent;
		this.stadiumName = stadiumName;
		this.stadiumType = stadiumType;
		this.stadiumAddress = stadiumAddress;
		this.stadiumX = stadiumX;
		this.stadiumY = stadiumY;
		this.stadiumParking = stadiumParking;
		this.stadiumShower = stadiumShower;
		this.matchType = matchType;
		this.isConfirmed = isConfirmed;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.voteMatch = voteMatch;
		this.search = search;
		this.assignment = assignment;
		this.employ = employ;
		this.matchResult = matchResult;
		this.entries = entries;
		this.teamScores = teamScores;
	}

	public int getMatchScheduleId() {
		return matchScheduleId;
	}

	public void setMatchScheduleId(int matchScheduleId) {
		this.matchScheduleId = matchScheduleId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getHomeContent() {
		return homeContent;
	}

	public void setHomeContent(String homeContent) {
		this.homeContent = homeContent;
	}

	public String getAwayContent() {
		return awayContent;
	}

	public void setAwayContent(String awayContent) {
		this.awayContent = awayContent;
	}

	public String getStadiumName() {
		return stadiumName;
	}

	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}

	public String getStadiumType() {
		return stadiumType;
	}

	public void setStadiumType(String stadiumType) {
		this.stadiumType = stadiumType;
	}

	public String getStadiumAddress() {
		return stadiumAddress;
	}

	public void setStadiumAddress(String stadiumAddress) {
		this.stadiumAddress = stadiumAddress;
	}

	public double getStadiumX() {
		return stadiumX;
	}

	public void setStadiumX(double stadiumX) {
		this.stadiumX = stadiumX;
	}

	public double getStadiumY() {
		return stadiumY;
	}

	public void setStadiumY(double stadiumY) {
		this.stadiumY = stadiumY;
	}

	public int getStadiumParking() {
		return stadiumParking;
	}

	public void setStadiumParking(int stadiumParking) {
		this.stadiumParking = stadiumParking;
	}

	public int getStadiumShower() {
		return stadiumShower;
	}

	public void setStadiumShower(int stadiumShower) {
		this.stadiumShower = stadiumShower;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public int getIsConfirmed() {
		return isConfirmed;
	}

	public void setIsConfirmed(int isConfirmed) {
		this.isConfirmed = isConfirmed;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
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

	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	public Employ getEmploy() {
		return employ;
	}

	public void setEmploy(Employ employ) {
		this.employ = employ;
	}

	public MatchResult getMatchResult() {
		return matchResult;
	}

	public void setMatchResult(MatchResult matchResult) {
		this.matchResult = matchResult;
	}

	public List<Entry> getEntries() {
		return entries;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
	}

	public List<TeamScore> getTeamScores() {
		return teamScores;
	}

	public void setTeamScores(List<TeamScore> teamScores) {
		this.teamScores = teamScores;
	}

	@Override
	public String toString() {
		return "MatchSchedule [matchScheduleId=" + matchScheduleId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", duration=" + duration + ", cost=" + cost + ", writer=" + writer + ", regDate=" + regDate
				+ ", homeContent=" + homeContent + ", awayContent=" + awayContent + ", stadiumName=" + stadiumName
				+ ", stadiumType=" + stadiumType + ", stadiumAddress=" + stadiumAddress + ", stadiumX=" + stadiumX
				+ ", stadiumY=" + stadiumY + ", stadiumParking=" + stadiumParking + ", stadiumShower=" + stadiumShower
				+ ", matchType=" + matchType + ", isConfirmed=" + isConfirmed + ", homeTeam=" + homeTeam + ", awayTeam="
				+ awayTeam + ", voteMatch=" + voteMatch + ", search=" + search + ", assignment=" + assignment
				+ ", employ=" + employ + ", matchResult=" + matchResult + ", entries=" + entries + ", teamScores="
				+ teamScores + "]";
	}
}
