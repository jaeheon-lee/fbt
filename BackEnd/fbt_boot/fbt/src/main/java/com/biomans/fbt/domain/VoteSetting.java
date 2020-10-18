package com.biomans.fbt.domain;

public class VoteSetting extends VoteMatch{
	private int status;
	private int cancelNumber;
	private int isFirst;
	private int waiting;
	private int friendEmp;
	private int selfMinNumber;
	private int selfMaxNumber;
	private String empDueDate;
	private int empMinNumber;
	private int awayMinNumber;
	private String awayDueDate;
	private int assgignCost;
	private int empCost;
	private int searchCost;
	
	public VoteSetting() {
		super();
	}

	public VoteSetting(int voteMatchId, String contents, String dueDate, int voteStatus, String voteRegDate,
			String writer, Team team, MatchSchedule matchSchedule, int status, int cancelNumber, int isFirst, int waiting, int friendEmp, int selfMinNumber,
			int selfMaxNumber, String empDueDate, int empMinNumber, int awayMinNumber, String awayDueDate,
			int assgignCost, int empCost, int searchCost) {
		super(voteMatchId, contents, dueDate, voteStatus, voteRegDate, writer, team, matchSchedule);
		this.status = status;
		this.cancelNumber = cancelNumber;
		this.isFirst = isFirst;
		this.waiting = waiting;
		this.friendEmp = friendEmp;
		this.selfMinNumber = selfMinNumber;
		this.selfMaxNumber = selfMaxNumber;
		this.empDueDate = empDueDate;
		this.empMinNumber = empMinNumber;
		this.awayMinNumber = awayMinNumber;
		this.awayDueDate = awayDueDate;
		this.assgignCost = assgignCost;
		this.empCost = empCost;
		this.searchCost = searchCost;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getCancelNumber() {
		return cancelNumber;
	}

	public void setCancelNumber(int cancelNumber) {
		this.cancelNumber = cancelNumber;
	}

	public int getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(int isFirst) {
		this.isFirst = isFirst;
	}

	public int getWaiting() {
		return waiting;
	}

	public void setWaiting(int waiting) {
		this.waiting = waiting;
	}

	public int getFriendEmp() {
		return friendEmp;
	}

	public void setFriendEmp(int friendEmp) {
		this.friendEmp = friendEmp;
	}

	public int getSelfMinNumber() {
		return selfMinNumber;
	}

	public void setSelfMinNumber(int selfMinNumber) {
		this.selfMinNumber = selfMinNumber;
	}

	public int getSelfMaxNumber() {
		return selfMaxNumber;
	}

	public void setSelfMaxNumber(int selfMaxNumber) {
		this.selfMaxNumber = selfMaxNumber;
	}

	public String getEmpDueDate() {
		return empDueDate;
	}

	public void setEmpDueDate(String empDueDate) {
		this.empDueDate = empDueDate;
	}

	public int getEmpMinNumber() {
		return empMinNumber;
	}

	public void setEmpMinNumber(int empMinNumber) {
		this.empMinNumber = empMinNumber;
	}

	public int getAwayMinNumber() {
		return awayMinNumber;
	}

	public void setAwayMinNumber(int awayMinNumber) {
		this.awayMinNumber = awayMinNumber;
	}

	public String getAwayDueDate() {
		return awayDueDate;
	}

	public void setAwayDueDate(String awayDueDate) {
		this.awayDueDate = awayDueDate;
	}

	public int getAssgignCost() {
		return assgignCost;
	}

	public void setAssgignCost(int assgignCost) {
		this.assgignCost = assgignCost;
	}

	public int getEmpCost() {
		return empCost;
	}

	public void setEmpCost(int empCost) {
		this.empCost = empCost;
	}

	public int getSearchCost() {
		return searchCost;
	}

	public void setSearchCost(int searchCost) {
		this.searchCost = searchCost;
	}

	@Override
	public String toString() {
		return "VoteSetting [status=" + status + ", cancelNumber=" + cancelNumber + ", isFirst=" + isFirst
				+ ", waiting=" + waiting + ", friendEmp=" + friendEmp + ", selfMinNumber=" + selfMinNumber
				+ ", selfMaxNumber=" + selfMaxNumber + ", empDueDate=" + empDueDate + ", empMinNumber=" + empMinNumber
				+ ", awayMinNumber=" + awayMinNumber + ", awayDueDate=" + awayDueDate + ", assgignCost=" + assgignCost
				+ ", empCost=" + empCost + ", searchCost=" + searchCost + "]";
	}
}
