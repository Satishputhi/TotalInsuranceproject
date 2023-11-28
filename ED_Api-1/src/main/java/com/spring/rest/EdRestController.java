package com.spring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.spring.binding.EligInfo;
import com.spring.service.EdService;

@RestController
public class EdRestController {
	
	@Autowired
	private EdService edservice;
	
	public ResponseEntity<EligInfo> fetEligInfo(@PathVariable Integer caseNum){
		EligInfo eligData = edservice.determineEligibility(caseNum);
		return new ResponseEntity<>(eligData, HttpStatus.OK);
	}

}
