package com.cg.sprint1.proj.status.advice;

public enum ReplaceStatus {
	APPROVED("APPROVED"),REQUESTED("REQUESTED");
private final String status;
	
	ReplaceStatus(String status){
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
