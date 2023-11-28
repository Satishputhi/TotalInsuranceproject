package com.spring.service;

import java.util.Map;

import com.spring.binding.Education;
import com.spring.binding.Income;
import com.spring.binding.Kids;
import com.spring.binding.PlanSelection;
import com.spring.binding.Summary;

public interface DcService {

	public Map<Integer, String> getPlanNames();
	
	public boolean updatePlanSelection(PlanSelection planSel);

	public boolean saveIncome(Income income);

	public boolean saveEducation(Education edu);

	public boolean saveKids(Kids kids);
	
	public Summary getSummary(Integer caseNum);

}
