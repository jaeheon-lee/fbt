package com.biomans.fbt.domain;

public class EmployResult {
	private int employResultId;
	private int employId;
	private String email;
	private User user;
	private int empResultStatus;
	
	public EmployResult() {}

	public EmployResult(int employResultId, int employId, String email, User user, int empResultStatus) {
		super();
		this.employResultId = employResultId;
		this.employId = employId;
		this.email = email;
		this.user = user;
		this.empResultStatus = empResultStatus;
	}

	public int getEmployResultId() {
		return employResultId;
	}

	public void setEmployResultId(int employResultId) {
		this.employResultId = employResultId;
	}

	public int getEmployId() {
		return employId;
	}

	public void setEmployId(int employId) {
		this.employId = employId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getEmpResultStatus() {
		return empResultStatus;
	}

	public void setEmpResultStatus(int empResultStatus) {
		this.empResultStatus = empResultStatus;
	}

	@Override
	public String toString() {
		return "EmployResult [employResultId=" + employResultId + ", employId=" + employId + ", email=" + email
				+ ", user=" + user + ", empResultStatus=" + empResultStatus + "]";
	}

}
