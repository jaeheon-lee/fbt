package com.biomans.fbt.domain;

public class MatchSchedule {
	private int matchScheduleId;
	private String startTime;
	private int duration;
	private int cost;
	private String writer;
	private String regDate;
	private String content;
	private String stadiumName;
	private String stadiumType;
	private String stadiumAddress;
	private int stadiumX;
	private int stadiumY;
	private int stadiumParking;
	private int stadiumShower;
	private int matchType;
	private int isHome;
	private Team team;
	
	public MatchSchedule() {}

	public MatchSchedule(int matchScheduleId, String startTime, int duration, int cost, String writer, String regDate,
			String content, String stadiumName, String stadiumType, String stadiumAddress, int stadiumX, int stadiumY,
			int stadiumParking, int stadiumShower, int matchType, int isHome, Team team) {
		super();
		this.matchScheduleId = matchScheduleId;
		this.startTime = startTime;
		this.duration = duration;
		this.cost = cost;
		this.writer = writer;
		this.regDate = regDate;
		this.content = content;
		this.stadiumName = stadiumName;
		this.stadiumType = stadiumType;
		this.stadiumAddress = stadiumAddress;
		this.stadiumX = stadiumX;
		this.stadiumY = stadiumY;
		this.stadiumParking = stadiumParking;
		this.stadiumShower = stadiumShower;
		this.matchType = matchType;
		this.isHome = isHome;
		this.team = team;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public int getStadiumX() {
		return stadiumX;
	}

	public void setStadiumX(int stadiumX) {
		this.stadiumX = stadiumX;
	}

	public int getStadiumY() {
		return stadiumY;
	}

	public void setStadiumY(int stadiumY) {
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

	public int getMatchType() {
		return matchType;
	}

	public void setMatchType(int matchType) {
		this.matchType = matchType;
	}

	public int getIsHome() {
		return isHome;
	}

	public void setIsHome(int isHome) {
		this.isHome = isHome;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@Override
	public String toString() {
		return "MatchSchedule [matchScheduleId=" + matchScheduleId + ", startTime=" + startTime + ", duration="
				+ duration + ", cost=" + cost + ", writer=" + writer + ", regDate=" + regDate + ", content=" + content
				+ ", stadiumName=" + stadiumName + ", stadiumType=" + stadiumType + ", stadiumAddress=" + stadiumAddress
				+ ", stadiumX=" + stadiumX + ", stadiumY=" + stadiumY + ", stadiumParking=" + stadiumParking
				+ ", stadiumShower=" + stadiumShower + ", matchType=" + matchType + ", isHome=" + isHome + ", team="
				+ team + "]";
	}
}
