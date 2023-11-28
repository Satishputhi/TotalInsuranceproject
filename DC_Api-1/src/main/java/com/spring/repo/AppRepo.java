package com.spring.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.AppEntity;

@Repository
public interface AppRepo extends JpaRepository<AppEntity,Integer>{

	//public List<AppEntity> fetchUserApps();
	
	@Query(value ="from AppEntity where userId =:userId")
	public List<AppEntity> fetchCwApps(Integer userId);
}
