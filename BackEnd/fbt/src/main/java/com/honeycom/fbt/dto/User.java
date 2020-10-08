package com.honeycom.fbt.dto;

import java.util.List;

public class User {
	private String email;
	private String pass;
	private String name;
	private String phoneNum;
	private String userRegDate;
	private String recentLogin;
	private String mainFoot;
	private String position;
	
	private List<TeamMember> teamMembers;
	private List<Employment> employments;
	
	public User() {
		super();
	}

	public User(String email, String pass, String name, String phoneNum, String userRegDate, String recentLogin,
			String mainFoot, String position, List<TeamMember> teamMembers, List<Employment> employments) {
		super();
		this.email = email;
		this.pass = pass;
		this.name = name;
		this.phoneNum = phoneNum;
		this.userRegDate = userRegDate;
		this.recentLogin = recentLogin;
		this.mainFoot = mainFoot;
		this.position = position;
		this.teamMembers = teamMembers;
		this.employments = employments;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getUserRegDate() {
		return userRegDate;
	}

	public void setUserRegDate(String userRegDate) {
		this.userRegDate = userRegDate;
	}

	public String getRecentLogin() {
		return recentLogin;
	}

	public void setRecentLogin(String recentLogin) {
		this.recentLogin = recentLogin;
	}

	public String getMainFoot() {
		return mainFoot;
	}

	public void setMainFoot(String mainFoot) {
		this.mainFoot = mainFoot;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public List<TeamMember> getTeamMembers() {
		return teamMembers;
	}

	public void setTeamMembers(List<TeamMember> teamMembers) {
		this.teamMembers = teamMembers;
	}

	public List<Employment> getEmployments() {
		return employments;
	}

	public void setEmployments(List<Employment> employments) {
		this.employments = employments;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", pass=" + pass + ", name=" + name + ", phoneNum=" + phoneNum
				+ ", userRegDate=" + userRegDate + ", recentLogin=" + recentLogin + ", mainFoot=" + mainFoot
				+ ", position=" + position + ", teamMembers=" + teamMembers + ", employments=" + employments + "]";
	}

} 
