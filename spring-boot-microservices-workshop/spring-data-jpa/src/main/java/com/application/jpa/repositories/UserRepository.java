package com.application.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.application.jpa.dto.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer>{

}
