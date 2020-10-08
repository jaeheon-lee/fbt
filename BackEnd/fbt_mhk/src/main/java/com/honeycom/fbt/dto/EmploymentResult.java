package com.honeycom.fbt.dto;

public class EmploymentResult {
	private int employmentResultId;
	private int employmentId;
	private String email;
	private int empResultStatus;
	
	
	public EmploymentResult() {
		super();
	}

	public EmploymentResult(int employmentResultId, int employmentId, String email, int empResultStatus) {
		super();
		this.employmentResultId = employmentResultId;
		this.employmentId = employmentId;
		this.email = email;
		this.empResultStatus = empResultStatus;
	}

	public int getEmploymentResultId() {
		return employmentResultId;
	}

	public void setEmploymentResultId(int employmentResultId) {
		this.employmentResultId = employmentResultId;
	}

	public int getEmploymentId() {
		return employmentId;
	}

	public void setEmploymentId(int employmentId) {
		this.employmentId = employmentId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getEmpResultStatus() {
		return empResultStatus;
	}

	public void setEmpResultStatus(int empResultStatus) {
		this.empResultStatus = empResultStatus;
	}

	@Override
	public String toString() {
		return "EmploymentResult [employmentResultId=" + employmentResultId + ", employmentId=" + employmentId
				+ ", email=" + email + ", empResultStatus=" + empResultStatus + "]";
	}
	
}
