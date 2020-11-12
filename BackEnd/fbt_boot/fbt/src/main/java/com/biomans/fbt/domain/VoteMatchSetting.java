package com.biomans.fbt.domain;

public class VoteMatchSetting {
	private String voteMatchId;
	private int type;
	private int cancelNumber;
	private boolean isFirst;
	private boolean waiting;
	private boolean friendEmp;
	private int selfMinNumber;
	private int selfMaxNumber;
	private String empDueDate;
	private int empMinNumber;
	private int awayMinNumber;
	private String awayDueDate;
	private int assignCost;
	private int empCost;
	private int searchCost;
	
	public VoteMatchSetting() {
		super();
	}

	public VoteMatchSetting(String voteMatchId, int type, int cancelNumber, boolean isFirst, boolean waiting, boolean friendEmp,
			int selfMinNumber, int selfMaxNumber, String empDueDate, int empMinNumber, int awayMinNumber,
			String awayDueDate, int assignCost, int empCost, int searchCost) {
		super();
		this.voteMatchId = voteMatchId;
		this.type = type;
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
		this.assignCost = assignCost;
		this.empCost = empCost;
		this.searchCost = searchCost;
	}

	public String getVoteMatchId() {
		return voteMatchId;
	}

	public void setVoteMatchId(String voteMatchId) {
		this.voteMatchId = voteMatchId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCancelNumber() {
		return cancelNumber;
	}

	public void setCancelNumber(int cancelNumber) {
		this.cancelNumber = cancelNumber;
	}

	public boolean getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean getWaiting() {
		return waiting;
	}

	public void setWaiting(boolean waiting) {
		this.waiting = waiting;
	}

	public boolean getFriendEmp() {
		return friendEmp;
	}

	public void setFriendEmp(boolean friendEmp) {
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

	public int getAssignCost() {
		return assignCost;
	}

	public void setAssignCost(int assignCost) {
		this.assignCost = assignCost;
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
		return "VoteMatchSetting [voteMatchId=" + voteMatchId + ", type=" + type + ", cancelNumber=" + cancelNumber
				+ ", isFirst=" + isFirst + ", waiting=" + waiting + ", friendEmp=" + friendEmp + ", selfMinNumber="
				+ selfMinNumber + ", selfMaxNumber=" + selfMaxNumber + ", empDueDate=" + empDueDate + ", empMinNumber="
				+ empMinNumber + ", awayMinNumber=" + awayMinNumber + ", awayDueDate=" + awayDueDate + ", assignCost="
				+ assignCost + ", empCost=" + empCost + ", searchCost=" + searchCost + "]";
	}
}
