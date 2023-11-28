package com.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.AppEntity;

public interface AppRepo extends JpaRepository<AppEntity, Integer> {

}
