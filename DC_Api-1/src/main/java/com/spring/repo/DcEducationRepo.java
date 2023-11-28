package com.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.EducationEntity;

public interface DcEducationRepo extends JpaRepository<EducationEntity,Integer> {

	public EducationEntity findByCaseNum(Integer caseNum);
}
