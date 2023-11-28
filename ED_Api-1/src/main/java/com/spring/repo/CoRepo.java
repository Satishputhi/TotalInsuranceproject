package com.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.CoEntity;

public interface CoRepo extends JpaRepository<CoEntity, Integer> {

}
