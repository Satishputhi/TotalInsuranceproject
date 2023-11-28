package com.spring.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.binding.EligInfo;
import com.spring.entity.AppEntity;
import com.spring.entity.CoEntity;
import com.spring.entity.EducationEntity;
import com.spring.entity.IncomeEntity;
import com.spring.entity.KidEntity;
import com.spring.entity.PlanEntity;
import com.spring.repo.AppRepo;
import com.spring.repo.CoRepo;
import com.spring.repo.DcEducationRepo;
import com.spring.repo.DcIncomeRepo;
import com.spring.repo.DcKidRepo;
import com.spring.repo.PlanRepo;

@Service
public class EdserviceImpl implements EdService {

	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private AppRepo appRepo;
	
	@Autowired
	private DcKidRepo kidRepo;
	
	@Autowired
	private DcIncomeRepo incomeRepo;
	
	@Autowired
	private DcEducationRepo eduRepo;
	
	@Autowired
	private CoRepo coRepo;
	
	
	@Override
	public EligInfo determineEligibility(Integer caseNum) {
		
		EligInfo eligInfo = new EligInfo();
		AppEntity appEntity = appRepo.findById(caseNum).get();
		Integer planId = appEntity.getPlanId();
		PlanEntity planEntity= planRepo.findById(planId).get();
		String planName = planEntity.getPlanName();
		
		
		eligInfo.setPlanName(planName);
		eligInfo.setCaseNum(caseNum);
		
		IncomeEntity incomeInfo = incomeRepo.findByCaseNum(caseNum);
		
		if(planName.equals("SNAP")) {
			if(incomeInfo.getSalaryIncome()<=300) {
				eligInfo.setPlanStatus("Approved");
			}else {
				eligInfo.setPlanStatus("Denied");
				eligInfo.setDenialReason("High Income");
			}
			
		}else if (planName.equals("CCAP")) {
			Double income = incomeInfo.getSalaryIncome();
			List<KidEntity> kids= kidRepo.findByCaseNum(caseNum);
			
			boolean isValid = true;
			
			for(KidEntity kid : kids) {
				LocalDate kidDob = kid.getKidDob();
				int years = Period.between(kidDob, LocalDate.now()).getYears();
			if(years >16) {
				isValid = false;
				break;
			}
			
			}
			if(isValid && income <= 300 && !kids.isEmpty()) {
				eligInfo.setPlanStatus("Approved");
			}else {
				eligInfo.setPlanStatus("Denied");
				
			}
			
			if(!isValid) {
				eligInfo.setDenialReason("Kid age above 16");
			}
			
			if (income >300) {
				eligInfo.setDenialReason("High Income");
			}
			if (kids.isEmpty()) {
				eligInfo.setDenialReason("No Kids Available");
			}
			
		}else if (planName.equals("Medicaid")) {
			Double income = incomeInfo.getSalaryIncome();
			Double propertyIncome = incomeInfo.getPropertyIncome();
			Double rentIncome = incomeInfo.getRentIncome();
			
			if(income <= 300 && ((propertyIncome+rentIncome) <=0 )){
				eligInfo.setPlanStatus("Approved");
			}else {
				eligInfo.setPlanStatus("Denied");
				eligInfo.setDenialReason("High Income");
			}
			
		}else if (planName.equals("Medicare")) {
			LocalDate dob= appEntity.getDob();
			int years = Period.between(dob, LocalDate.now()).getYears();
		if(years >=65) {
			eligInfo.setPlanStatus("Approved");
		}else {
			eligInfo.setPlanStatus("Denied");
			eligInfo.setDenialReason("Age not Matched");
		}
		}else if (planName.equals("RIW")) {
			EducationEntity edu = eduRepo.findByCaseNum(caseNum);
			Integer graduationYear = edu.getGraduationYear();
			if(graduationYear != null && incomeInfo != null) {
				eligInfo.setPlanStatus("Approved");
			}else {
				eligInfo.setPlanStatus("Denied");
				}
			
			if(graduationYear == null) {
				eligInfo.setDenialReason("Under Graduate");
			}
			
			if(incomeInfo != null) {
				eligInfo.setDenialReason("Already Employee");
			}
		}
		
		if(eligInfo.getPlanStatus().equals("Approved")) {
			eligInfo.setPlanStartDate(LocalDate.now());
			eligInfo.setPlanEndDate(LocalDate.now().plusMonths(6));
			eligInfo.setBenefitAmt(2000.00);
			
		}
		
		generateCorrespondence(appEntity);
		return eligInfo;
	}
	
	private void generateCorrespondence(AppEntity app ) {
		CoEntity entity = new CoEntity();
		entity.setNoticeStatus("Pending");
		entity.setApp(app);
		coRepo.save(entity);
	}

}
