package com.biomans.fbt.util;

public class ScoreDescInput {
	private int teamId;
	private String email;
	private double teamAbility;
	private double teamManner;
	private double empAbility;
	private double empManner;
	
	public ScoreDescInput() {}
	public ScoreDescInput(int teamId, String email, double teamAbility, double teamManner, double empAbility,
			double empManner) {
		super();
		this.teamId = teamId;
		this.email = email;
		this.teamAbility = teamAbility;
		this.teamManner = teamManner;
		this.empAbility = empAbility;
		this.empManner = empManner;
	}
	
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getTeamAbility() {
		return teamAbility;
	}
	public void setTeamAbility(double teamAbility) {
		this.teamAbility = teamAbility;
	}
	public double getTeamManner() {
		return teamManner;
	}
	public void setTeamManner(double teamManner) {
		this.teamManner = teamManner;
	}
	public double getEmpAbility() {
		return empAbility;
	}
	public void setEmpAbility(double empAbility) {
		this.empAbility = empAbility;
	}
	public double getEmpManner() {
		return empManner;
	}
	public void setEmpManner(double empManner) {
		this.empManner = empManner;
	}
	@Override
	public String toString() {
		return "ScoreDescInput [teamId=" + teamId + ", email=" + email + ", teamAbility=" + teamAbility
				+ ", teamManner=" + teamManner + ", empAbility=" + empAbility + ", empManner=" + empManner + "]";
	}
	
	
}
