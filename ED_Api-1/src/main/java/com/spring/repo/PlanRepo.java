package com.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.PlanEntity;

public interface PlanRepo extends JpaRepository<PlanEntity,Integer> {

}
