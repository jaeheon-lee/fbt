package com.biomans.fbt.domain;

public class TeamMember extends User {
	private String teamMemberId;
	private String memberRegDate;
	private int memberLevel;
	private int regStatus;
	private String nickName;
	private Team team;
	private User user;
	
	public TeamMember() {}
	
	public TeamMember(String email, String pass, String name, String phoneNum, String userRegDate, String recentLogin,
			String mainFoot, String position, String apiKey, int height, int weight, int wasPro, int gender,
			String born_date, String teamMemberId, String memberRegDate, int memberLevel, int regStatus, String nickName,
			Team team, User user) {
		super(email, pass, name, phoneNum, userRegDate, recentLogin, mainFoot, position, apiKey, height, weight, wasPro, gender,
				born_date);
		this.teamMemberId = teamMemberId;
		this.memberRegDate = memberRegDate;
		this.memberLevel = memberLevel;
		this.regStatus = regStatus;
		this.nickName = nickName;
		this.team = team;
		this.user = user;
	}

	public String getTeamMemberId() {
		return teamMemberId;
	}

	public void setTeamMemberId(String teamMemberId) {
		this.teamMemberId = teamMemberId;
	}

	public String getMemberRegDate() {
		return memberRegDate;
	}

	public void setMemberRegDate(String memberRegDate) {
		this.memberRegDate = memberRegDate;
	}

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	public int getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(int regStatus) {
		this.regStatus = regStatus;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TeamMember [teamMemberId=" + teamMemberId + ", memberRegDate=" + memberRegDate + ", memberLevel="
				+ memberLevel + ", regStatus=" + regStatus + ", nickName=" + nickName + ", team=" + team + ", user="
				+ user + "]";
	}
}
