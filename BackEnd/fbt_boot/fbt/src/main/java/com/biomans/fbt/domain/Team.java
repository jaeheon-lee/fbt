package com.biomans.fbt.domain;

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
	
	public Team() {}

	public Team(int teamId, String teamName, String emblem, String area, String stadiumInfo, String uniformColor,
			int uniformType, String foundingDate, String teamRegDate, String account, String bank) {
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

	@Override
	public String toString() {
		return "Team [teamId=" + teamId + ", teamName=" + teamName + ", emblem=" + emblem + ", area=" + area
				+ ", stadiumInfo=" + stadiumInfo + ", uniformColor=" + uniformColor + ", uniformType=" + uniformType
				+ ", foundingDate=" + foundingDate + ", teamRegDate=" + teamRegDate + ", account=" + account + ", bank="
				+ bank + "]";
	}
}
