package com.honeycom.fbt.dto;

public class TeamMannerScore {
	private int teamId;
	private double mannerPromise;
	private double mannerContact;
	private double mannerRule;
	private double mannerBodyFight;
	private double mannerSlang;
	private double mannerSmoking;
	private double mannerUniform;
	private double mannerPayment;
	private double mannerArrangement;
	
	
	public TeamMannerScore() {
		super();
	}


	public TeamMannerScore(int teamId, double mannerPromise, double mannerContact, double mannerRule,
			double mannerBodyFight, double mannerSlang, double mannerSmoking, double mannerUniform,
			double mannerPayment, double mannerArrangement) {
		super();
		this.teamId = teamId;
		this.mannerPromise = mannerPromise;
		this.mannerContact = mannerContact;
		this.mannerRule = mannerRule;
		this.mannerBodyFight = mannerBodyFight;
		this.mannerSlang = mannerSlang;
		this.mannerSmoking = mannerSmoking;
		this.mannerUniform = mannerUniform;
		this.mannerPayment = mannerPayment;
		this.mannerArrangement = mannerArrangement;
	}


	public int getTeamId() {
		return teamId;
	}


	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}


	public double getMannerPromise() {
		return mannerPromise;
	}


	public void setMannerPromise(double mannerPromise) {
		this.mannerPromise = mannerPromise;
	}


	public double getMannerContact() {
		return mannerContact;
	}


	public void setMannerContact(double mannerContact) {
		this.mannerContact = mannerContact;
	}


	public double getMannerRule() {
		return mannerRule;
	}


	public void setMannerRule(double mannerRule) {
		this.mannerRule = mannerRule;
	}


	public double getMannerBodyFight() {
		return mannerBodyFight;
	}


	public void setMannerBodyFight(double mannerBodyFight) {
		this.mannerBodyFight = mannerBodyFight;
	}


	public double getMannerSlang() {
		return mannerSlang;
	}


	public void setMannerSlang(double mannerSlang) {
		this.mannerSlang = mannerSlang;
	}


	public double getMannerSmoking() {
		return mannerSmoking;
	}


	public void setMannerSmoking(double mannerSmoking) {
		this.mannerSmoking = mannerSmoking;
	}


	public double getMannerUniform() {
		return mannerUniform;
	}


	public void setMannerUniform(double mannerUniform) {
		this.mannerUniform = mannerUniform;
	}


	public double getMannerPayment() {
		return mannerPayment;
	}


	public void setMannerPayment(double mannerPayment) {
		this.mannerPayment = mannerPayment;
	}


	public double getMannerArrangement() {
		return mannerArrangement;
	}


	public void setMannerArrangement(double mannerArrangement) {
		this.mannerArrangement = mannerArrangement;
	}


	@Override
	public String toString() {
		return "TeamMannerScore [teamId=" + teamId + ", mannerPromise=" + mannerPromise + ", mannerContact="
				+ mannerContact + ", mannerRule=" + mannerRule + ", mannerBodyFight=" + mannerBodyFight
				+ ", mannerSlang=" + mannerSlang + ", mannerSmoking=" + mannerSmoking + ", mannerUniform="
				+ mannerUniform + ", mannerPayment=" + mannerPayment + ", mannerArrangement=" + mannerArrangement + "]";
	}
	
}
