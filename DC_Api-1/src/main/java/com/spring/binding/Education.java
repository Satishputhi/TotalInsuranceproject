package com.spring.binding;

import java.time.LocalDate;

public class Education {
	private Integer caseNum;

	private Integer userId;

	private String hihestDegree;

	private LocalDate graduationYear;

	private String unversityName;

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

	public String getHihestDegree() {
		return hihestDegree;
	}

	public void setHihestDegree(String hihestDegree) {
		this.hihestDegree = hihestDegree;
	}

	public LocalDate getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(LocalDate graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getUnversityName() {
		return unversityName;
	}

	public void setUnversityName(String unversityName) {
		this.unversityName = unversityName;
	}

	
}
