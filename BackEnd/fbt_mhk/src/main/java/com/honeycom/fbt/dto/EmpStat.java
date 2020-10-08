package com.honeycom.fbt.dto;

public class EmpStat {
	private String email;
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
	private double empAbility;
	private int counter;
	
	public EmpStat() {
		super();
	}

	public EmpStat(String email, double mannerPromise, double mannerContact, double mannerRule, double mannerBodyFight,
			double mannerSlang, double mannerSmoking, double mannerUniform, double mannerPayment,
			double mannerArrangement, double mannerReferee, double mannerTackle, double empAbility, int counter) {
		super();
		this.email = email;
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
		this.counter = counter;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public double getempAbility() {
		return empAbility;
	}

	public void setempAbility(double empAbility) {
		this.empAbility = empAbility;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Override
	public String toString() {
		return "EmpStat [email=" + email + ", mannerPromise=" + mannerPromise + ", mannerContact=" + mannerContact
				+ ", mannerRule=" + mannerRule + ", mannerBodyFight=" + mannerBodyFight + ", mannerSlang=" + mannerSlang
				+ ", mannerSmoking=" + mannerSmoking + ", mannerUniform=" + mannerUniform + ", mannerPayment="
				+ mannerPayment + ", mannerArrangement=" + mannerArrangement + ", mannerReferee=" + mannerReferee
				+ ", mannerTackle=" + mannerTackle + ", empAbility=" + empAbility + ", counter=" + counter + "]";
	}
	
	

}
