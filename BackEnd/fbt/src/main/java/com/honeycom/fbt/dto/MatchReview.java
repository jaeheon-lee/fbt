package com.honeycom.fbt.dto;

public class MatchReview {
	private int attendantId;
	private int mannerPromise;
	private int mannerContact;
	private int mannerRule;
	private int mannerBodyFight;
	private int mannerSlang;
	private int mannerSmoking;
	private int mannerUniform;
	private int mannerPayment;
	private int mannerArrangement;

	public MatchReview() {
		super();
	}

	public MatchReview(int attendantId, int mannerPromise, int mannerContact, int mannerRule, int mannerBodyFight,
			int mannerSlang, int mannerSmoking, int mannerUniform, int mannerPayment, int mannerArrangement) {
		super();
		this.attendantId = attendantId;
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

	public int getAttendantId() {
		return attendantId;
	}

	public void setAttendantId(int attendantId) {
		this.attendantId = attendantId;
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

	@Override
	public String toString() {
		return "MatchReview [attendantId=" + attendantId + ", mannerPromise=" + mannerPromise + ", mannerContact="
				+ mannerContact + ", mannerRule=" + mannerRule + ", mannerBodyFight=" + mannerBodyFight
				+ ", mannerSlang=" + mannerSlang + ", mannerSmoking=" + mannerSmoking + ", mannerUniform="
				+ mannerUniform + ", mannerPayment=" + mannerPayment + ", mannerArrangement=" + mannerArrangement + "]";
	}

	
}
