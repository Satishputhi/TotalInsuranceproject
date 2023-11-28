package com.spring.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.binding.Education;
import com.spring.binding.Income;
import com.spring.binding.Kid;
import com.spring.binding.Kids;
import com.spring.binding.PlanSelection;
import com.spring.binding.Summary;
import com.spring.entity.AppEntity;

import com.spring.entity.EducationEntity;
import com.spring.entity.IncomeEntity;
import com.spring.entity.KidEntity;
import com.spring.entity.PlanEntity;
import com.spring.entity.UserEntity;
import com.spring.repo.AppRepo;
import com.spring.repo.DcEducationRepo;
import com.spring.repo.DcIncomeRepo;
import com.spring.repo.DcKidRepo;
import com.spring.repo.PlanRepo;
import com.spring.repo.UserRepo;



@Service
public class DcServiceImpl implements DcService {
	
	@Autowired
	private PlanRepo planRepo;
	
	@Autowired
	private AppRepo appRepo;
	
	@Autowired
	private DcIncomeRepo incomeRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private DcEducationRepo eduRepo;
	
	@Autowired
	private DcKidRepo kidRepo;

	@Override
	public Map<Integer, String> getPlanNames() {
		
		
		List<PlanEntity> plans = planRepo.findAll();
		
		Map<Integer,String> plansMap = new HashMap<>();
		
		for (PlanEntity entity : plans) {
			plansMap.put(entity.getPlanId(), entity.getPlanName());
		}
		return plansMap;
	}

	@Override
	public boolean updatePlanSelection(PlanSelection planSel) {
	        Integer caseNum = planSel.getCaseNum();
	        
	        Optional<AppEntity> findById = appRepo.findById(caseNum);
	        UserEntity userEntity = userRepo.findById(planSel.getUserId()).get();
	        if(findById.isPresent()) {
	        	AppEntity appEntity = findById.get();
	        	appEntity.setPlanId(planSel.getPlanId());
	           
	        	appEntity.setUser(userEntity);
	        	
	        	appRepo.save(appEntity);
	            return true;
	        
	        }
		return false;
	}

	@Override
	public boolean saveIncome(Income income) {
		
		IncomeEntity entity = new IncomeEntity();
		BeanUtils.copyProperties(income, entity);
		
		Integer caseNum = income.getCaseNum();
		Integer userId = income.getUserId();
		
		AppEntity appEntity = appRepo.findById(caseNum).get();
		UserEntity userEntity = userRepo.findById(userId).get();
		 
		entity.setApp(appEntity);
		entity.setUser(userEntity);
		
		incomeRepo.save(entity);
		
		
		return true;
	}

	@Override
	public boolean saveEducation(Education edu) {
		EducationEntity entity = new EducationEntity();
		BeanUtils.copyProperties(edu, entity);
		
		Integer caseNum = edu.getCaseNum();
		Integer userId = edu.getUserId();
		
		AppEntity appEntity = appRepo.findById(caseNum).get();
		UserEntity userEntity = userRepo.findById(userId).get();
		 
		entity.setApp(appEntity);
		entity.setUser(userEntity);
		
		eduRepo.save(entity);
		
		
		return true;
	}

	@Override
	public boolean saveKids(Kids kids) {
		Integer caseNum = kids.getCaseNum();
		Integer userId = kids.getUserId();
		
		AppEntity appEntity = appRepo.findById(caseNum).get();
		UserEntity userEntity = userRepo.findById(userId).get();
		
		List<Kid> kidsList =kids.getKidsList();
		
		for(Kid kid : kidsList) {
			KidEntity entity = new KidEntity();
			BeanUtils.copyProperties(kid, entity);
			
			entity.setApp(appEntity);
			entity.setUser(userEntity);
			
			kidRepo.save(entity);
			
		}
		
		return true;
	}

	@Override
	public Summary getSummary(Integer caseNum) {
		
		Summary summary = new Summary();
		
		AppEntity appEntity = appRepo.findById(caseNum).get();
		
		PlanEntity planEntity = planRepo.findById(appEntity.getPlanId()).get();
		
		IncomeEntity incomeEntity = incomeRepo.findByCaseNum(caseNum);
		
		EducationEntity educationEntity = eduRepo.findByCaseNum(caseNum);
		
		List<KidEntity> kidEntities  = kidRepo.findByCaseNum(caseNum);
		
		summary.setCaseNum(caseNum);
		summary.setPlanName(planEntity.getPlanName());
		
		Income income = new Income();
		BeanUtils.copyProperties(incomeEntity, income);
		summary.setIncome(null);
		
		Education edu = new Education();
		BeanUtils.copyProperties(educationEntity, edu);
		summary.setEducation(null);
		
		List<Kid> list = new ArrayList<>();
		
		for(KidEntity entity : kidEntities) {
			Kid kid = new Kid();
			BeanUtils.copyProperties(entity, kid);
			list.add(kid);
		}
		
		Kids kids = new Kids();
		kids.setKidsList(list);
		summary.setKids(null);
		
		
		
		return summary;
	}

}
