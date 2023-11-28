package com.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.IncomeEntity;

public interface DcIncomeRepo extends JpaRepository<IncomeEntity,Integer>{
	
	public IncomeEntity findByCaseNum(Integer caseNum);

}
