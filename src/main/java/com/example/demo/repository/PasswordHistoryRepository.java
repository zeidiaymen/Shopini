package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.PasswordHistory;
import com.example.demo.entity.User;

public interface PasswordHistoryRepository extends JpaRepository <PasswordHistory,Integer> {
	
	List<PasswordHistory> findByUser(User user);

}
