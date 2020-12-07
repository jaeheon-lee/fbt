package com.biomans.fbt.domain;

public class ApiCategory {
	private int apiType;
	private String apiName;
	
	public ApiCategory() {}
	public ApiCategory(int apiType, String apiName) {
		super();
		this.apiType = apiType;
		this.apiName = apiName;
	}
	public int getApiType() {
		return apiType;
	}
	public void setApiType(int apiType) {
		this.apiType = apiType;
	}
	public String getApiName() {
		return apiName;
	}
	public void setApiName(String apiName) {
		this.apiName = apiName;
	}
	@Override
	public String toString() {
		return "ApiCategory [apiType=" + apiType + ", apiName=" + apiName + "]";
	}
	
	
}
