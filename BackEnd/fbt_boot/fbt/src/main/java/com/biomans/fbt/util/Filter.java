package com.biomans.fbt.util;

public class Filter {
	private String date;
	private String timeRange1;
	private String timeRange2;
	private String dateTime1;
	private String dateTime2;
	private String siDo;
	private String siGun;
	private String stadiumAddress;
	private String stadiumName;
	private String matchType;
	private String stadiumType;
	private int costType;
	private int order;
	private int page;
	
	public Filter() {}

	public Filter(String date, String timeRange1, String timeRange2, String dateTime1, String dateTime2, String siDo,
			String siGun, String stadiumAddress, String stadiumName, String matchType, String stadiumType, int costType,
			int order, int page) {
		super();
		this.date = date;
		this.timeRange1 = timeRange1;
		this.timeRange2 = timeRange2;
		this.dateTime1 = dateTime1;
		this.dateTime2 = dateTime2;
		this.siDo = siDo;
		this.siGun = siGun;
		this.stadiumAddress = stadiumAddress;
		this.stadiumName = stadiumName;
		this.matchType = matchType;
		this.stadiumType = stadiumType;
		this.costType = costType;
		this.order = order;
		this.page = page;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTimeRange1() {
		return timeRange1;
	}

	public void setTimeRange1(String timeRange1) {
		this.timeRange1 = timeRange1;
	}

	public String getTimeRange2() {
		return timeRange2;
	}

	public void setTimeRange2(String timeRange2) {
		this.timeRange2 = timeRange2;
	}

	public String getDateTime1() {
		return dateTime1;
	}

	public void setDateTime1(String dateTime1) {
		this.dateTime1 = dateTime1;
	}

	public String getDateTime2() {
		return dateTime2;
	}

	public void setDateTime2(String dateTime2) {
		this.dateTime2 = dateTime2;
	}

	public String getSiDo() {
		return siDo;
	}

	public void setSiDo(String siDo) {
		this.siDo = siDo;
	}

	public String getSiGun() {
		return siGun;
	}

	public void setSiGun(String siGun) {
		this.siGun = siGun;
	}

	public String getStadiumAddress() {
		return stadiumAddress;
	}

	public void setStadiumAddress(String stadiumAddress) {
		this.stadiumAddress = stadiumAddress;
	}

	public String getStadiumName() {
		return stadiumName;
	}

	public void setStadiumName(String stadiumName) {
		this.stadiumName = stadiumName;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getStadiumType() {
		return stadiumType;
	}

	public void setStadiumType(String stadiumType) {
		this.stadiumType = stadiumType;
	}

	public int getCostType() {
		return costType;
	}

	public void setCostType(int costType) {
		this.costType = costType;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@Override
	public String toString() {
		return "Filter [date=" + date + ", timeRange1=" + timeRange1 + ", timeRange2=" + timeRange2 + ", dateTime1="
				+ dateTime1 + ", dateTime2=" + dateTime2 + ", siDo=" + siDo + ", siGun=" + siGun + ", stadiumAddress="
				+ stadiumAddress + ", stadiumName=" + stadiumName + ", matchType=" + matchType + ", stadiumType="
				+ stadiumType + ", costType=" + costType + ", order=" + order + ", page=" + page + "]";
	}
	
}
