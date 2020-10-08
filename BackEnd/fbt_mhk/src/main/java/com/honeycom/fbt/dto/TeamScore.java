package com.honeycom.fbt.dto;

public class TeamScore {
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
	private double mannerReferee;
	private double mannerTackle;
	private double teamAbility;
	private int counter;
	
	
	public TeamScore() {
		super();
	}


	public TeamScore(int teamId, double mannerPromise, double mannerContact, double mannerRule,
			double mannerBodyFight, double mannerSlang, double mannerSmoking, double mannerUniform,
			double mannerPayment, double mannerArrangement, double mannerReferee, double mannerTackle,
			double teamAbility, int counter) {
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
		this.mannerReferee = mannerReferee;
		this.mannerTackle = mannerTackle;
		this.teamAbility = teamAbility;
		this.counter = counter;
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


	public double getMannerReferee() {
		return mannerReferee;
	}


	public void setMannerReferee(double mannerReferee) {
		this.mannerReferee = mannerReferee;
	}


	public double getMannerTackle() {
		return mannerTackle;
	}


	public void setMannerTackle(double mannerTackle) {
		this.mannerTackle = mannerTackle;
	}


	public double getTeamAbility() {
		return teamAbility;
	}


	public void setTeamAbility(double teamAbility) {
		this.teamAbility = teamAbility;
	}


	public int getCounter() {
		return counter;
	}


	public void setCounter(int counter) {
		this.counter = counter;
	}


	@Override
	public String toString() {
		return "TeamMannerScore [teamId=" + teamId + ", mannerPromise=" + mannerPromise + ", mannerContact="
				+ mannerContact + ", mannerRule=" + mannerRule + ", mannerBodyFight=" + mannerBodyFight
				+ ", mannerSlang=" + mannerSlang + ", mannerSmoking=" + mannerSmoking + ", mannerUniform="
				+ mannerUniform + ", mannerPayment=" + mannerPayment + ", mannerArrangement=" + mannerArrangement
				+ ", mannerReferee=" + mannerReferee + ", mannerTackle=" + mannerTackle + ", teamAbility=" + teamAbility
				+ ", counter=" + counter + "]";
	}
	
	
}
