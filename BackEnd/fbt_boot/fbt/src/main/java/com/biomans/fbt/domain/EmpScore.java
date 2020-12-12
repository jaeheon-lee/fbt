package com.biomans.fbt.domain;

public class EmpScore {
	private int empScoreId;
	private int mannerPromise;
	private int mannerContact;
	private int mannerRule;
	private int mannerBodyFight;
	private int mannerSlang;
	private int mannerSmoking;
	private int mannerUniform;
	private int mannerPayment;
	private int mannerArrangement;
	private int mannerReferee;
	private int mannerTackle;
	private double empAbility;
	private double empManner;
	private int forward;
	private int middle;
	private int defence;
	private MatchSchedule matchSchedule;
	private Team teamGiver;
	private User user;
	
	public EmpScore() {}

	public EmpScore(int empScoreId, int mannerPromise, int mannerContact, int mannerRule, int mannerBodyFight,
			int mannerSlang, int mannerSmoking, int mannerUniform, int mannerPayment, int mannerArrangement,
			int mannerReferee, int mannerTackle, double empAbility, double empManner, int forward, int middle,
			int defence, MatchSchedule matchSchedule, Team teamGiver, User user) {
		super();
		this.empScoreId = empScoreId;
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
		this.empAbility = empAbility;
		this.empManner = empManner;
		this.forward = forward;
		this.middle = middle;
		this.defence = defence;
		this.matchSchedule = matchSchedule;
		this.teamGiver = teamGiver;
		this.user = user;
	}

	public int getEmpScoreId() {
		return empScoreId;
	}

	public void setEmpScoreId(int empScoreId) {
		this.empScoreId = empScoreId;
	}

	public int getMannerPromise() {
		return mannerPromise;
	}

	public void setMannerPromise(int mannerPromise) {
		this.mannerPromise = mannerPromise;
	}

	public int getMannerContact() {
		return mannerContact;
	}

	public void setMannerContact(int mannerContact) {
		this.mannerContact = mannerContact;
	}

	public int getMannerRule() {
		return mannerRule;
	}

	public void setMannerRule(int mannerRule) {
		this.mannerRule = mannerRule;
	}

	public int getMannerBodyFight() {
		return mannerBodyFight;
	}

	public void setMannerBodyFight(int mannerBodyFight) {
		this.mannerBodyFight = mannerBodyFight;
	}

	public int getMannerSlang() {
		return mannerSlang;
	}

	public void setMannerSlang(int mannerSlang) {
		this.mannerSlang = mannerSlang;
	}

	public int getMannerSmoking() {
		return mannerSmoking;
	}

	public void setMannerSmoking(int mannerSmoking) {
		this.mannerSmoking = mannerSmoking;
	}

	public int getMannerUniform() {
		return mannerUniform;
	}

	public void setMannerUniform(int mannerUniform) {
		this.mannerUniform = mannerUniform;
	}

	public int getMannerPayment() {
		return mannerPayment;
	}

	public void setMannerPayment(int mannerPayment) {
		this.mannerPayment = mannerPayment;
	}

	public int getMannerArrangement() {
		return mannerArrangement;
	}

	public void setMannerArrangement(int mannerArrangement) {
		this.mannerArrangement = mannerArrangement;
	}

	public int getMannerReferee() {
		return mannerReferee;
	}

	public void setMannerReferee(int mannerReferee) {
		this.mannerReferee = mannerReferee;
	}

	public int getMannerTackle() {
		return mannerTackle;
	}

	public void setMannerTackle(int mannerTackle) {
		this.mannerTackle = mannerTackle;
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

	public int getForward() {
		return forward;
	}

	public void setForward(int forward) {
		this.forward = forward;
	}

	public int getMiddle() {
		return middle;
	}

	public void setMiddle(int middle) {
		this.middle = middle;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public MatchSchedule getMatchSchedule() {
		return matchSchedule;
	}

	public void setMatchSchedule(MatchSchedule matchSchedule) {
		this.matchSchedule = matchSchedule;
	}

	public Team getTeamGiver() {
		return teamGiver;
	}

	public void setTeamGiver(Team teamGiver) {
		this.teamGiver = teamGiver;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "EmpScore [empScoreId=" + empScoreId + ", mannerPromise=" + mannerPromise + ", mannerContact="
				+ mannerContact + ", mannerRule=" + mannerRule + ", mannerBodyFight=" + mannerBodyFight
				+ ", mannerSlang=" + mannerSlang + ", mannerSmoking=" + mannerSmoking + ", mannerUniform="
				+ mannerUniform + ", mannerPayment=" + mannerPayment + ", mannerArrangement=" + mannerArrangement
				+ ", mannerReferee=" + mannerReferee + ", mannerTackle=" + mannerTackle + ", empAbility=" + empAbility
				+ ", empManner=" + empManner + ", forward=" + forward + ", middle=" + middle + ", defence=" + defence
				+ ", matchSchedule=" + matchSchedule + ", teamGiver=" + teamGiver + ", user=" + user + "]";
	}

	
}
