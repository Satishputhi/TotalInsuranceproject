package com.spring.binding;

import java.time.LocalDate;

public class Kid {
	private Integer caseNum;

	private Integer userId;

	private String kidName;

	private LocalDate kidDob;

	private Long kidSsn;
	
	public Integer getCaseNum() {
		return caseNum;
	}

	public void setCaseNum(Integer caseNum) {
		this.caseNum = caseNum;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getKidName() {
		return kidName;
	}

	public void setKidName(String kidName) {
		this.kidName = kidName;
	}

	public LocalDate getKidDob() {
		return kidDob;
	}

	public void setKidDob(LocalDate kidDob) {
		this.kidDob = kidDob;
	}

	public Long getKidSsn() {
		return kidSsn;
	}

	public void setKidSsn(Long kidSsn) {
		this.kidSsn = kidSsn;
	}

}
