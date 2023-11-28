package com.spring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name ="CO_NOTISE")
public class CoEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer coid;
	
	private String noticeStatus;
	
	private String noticeGenDate;
	
	private String noticeS3Url;
	
	@OneToOne
	@JoinColumn(name ="case_num")
	private AppEntity app;

	public Integer getCoid() {
		return coid;
	}

	public void setCoid(Integer coid) {
		this.coid = coid;
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public String getNoticeGenDate() {
		return noticeGenDate;
	}

	public void setNoticeGenDate(String noticeGenDate) {
		this.noticeGenDate = noticeGenDate;
	}

	public String getNoticeS3Url() {
		return noticeS3Url;
	}

	public void setNoticeS3Url(String noticeS3Url) {
		this.noticeS3Url = noticeS3Url;
	}

	public AppEntity getApp() {
		return app;
	}

	public void setApp(AppEntity app) {
		this.app = app;
	}
	
	
	
	

}
