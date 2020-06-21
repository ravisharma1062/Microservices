package com.application.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.application.jpa.dto.LoginDetails;

public interface LoginRepository extends JpaRepository<LoginDetails, String> {

}
