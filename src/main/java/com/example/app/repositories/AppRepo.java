package com.example.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.app.entities.Register;
import com.example.app.entities.User;

@Repository
public interface AppRepo extends JpaRepository<User, Integer>{
	User findByEmail(String email);


}
