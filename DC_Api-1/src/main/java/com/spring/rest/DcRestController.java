package com.spring.rest;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.binding.Education;
import com.spring.binding.Income;
import com.spring.binding.Kids;
import com.spring.binding.PlanSelection;
import com.spring.binding.Summary;
import com.spring.service.DcService;

@RestController
public class DcRestController {

	@Autowired
	private DcService dcService;

	@GetMapping("/plan-names")
	public ResponseEntity<Map<Integer, String>> getPlanName() {
		Map<Integer, String> planNames = dcService.getPlanNames();
		return new ResponseEntity<>(planNames, HttpStatus.OK);
	}

	@PostMapping("/plan-selection")
	public ResponseEntity<String> updatePlanSelection(@RequestBody PlanSelection request) {
		boolean status = dcService.updatePlanSelection(request);
		if (status) {
			return new ResponseEntity<>("Plan Selection Sucess", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Plan Selection Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/income")
	public ResponseEntity<String> saveIncome(@RequestBody Income income) {
		boolean status = dcService.saveIncome(income);
		if (status) {
			return new ResponseEntity<>("Income Data Saved", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/education")
	public ResponseEntity<String> saveEducation(@RequestBody Education education) {
		boolean status = dcService.saveEducation(education);
		if (status) {
			return new ResponseEntity<>("Education Data Saved", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/kids")
	public ResponseEntity<String> savekids(@RequestBody Kids kids) {
		boolean status = dcService.saveKids(kids);
		if (status) {
			return new ResponseEntity<>("Kids Data Saved", HttpStatus.CREATED);
		} else {
			return new ResponseEntity<>("Failed", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/summary/{caseNum}")
	public ResponseEntity<Summary> getSummary(@PathVariable Integer caseNum) {
		 Summary summary = dcService.getSummary(caseNum);
		return new ResponseEntity<>(summary, HttpStatus.OK);
	}

}
