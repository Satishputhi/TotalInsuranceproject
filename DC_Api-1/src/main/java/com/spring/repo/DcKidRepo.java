package com.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.KidEntity;

public interface DcKidRepo extends JpaRepository<KidEntity,Integer>{

	public List<KidEntity> findByCaseNum(Integer caseNum);
}
