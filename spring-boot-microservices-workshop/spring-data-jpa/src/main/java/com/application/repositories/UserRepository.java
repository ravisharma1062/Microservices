package com.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.dto.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{
	Users findByUserId(int id);
}
