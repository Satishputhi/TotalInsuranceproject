package com.spring.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.entity.UserEntity;

public interface UserRepo extends JpaRepository<UserEntity,Integer>{

}
