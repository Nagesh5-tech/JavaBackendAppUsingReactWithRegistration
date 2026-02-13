package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.entities.Otp;
@Repository
public interface OtpRepo extends JpaRepository<Otp, Integer>{
	Otp findTopByUsersEmailAndOtpvalueOrderByCreatedatDesc(String email, int otp);

}
