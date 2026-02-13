package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.entities.Register;


@Repository
public interface RegisterRepo extends JpaRepository<Register, Integer>{
	boolean existsByMobileNumber(String mobileNumber);
	boolean existsByEmail(String email);

}
