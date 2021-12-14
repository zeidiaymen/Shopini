package com.example.demo.repository.user;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.user.PasswordHistory;
import com.example.demo.entity.user.User;

public interface PasswordHistoryRepository extends JpaRepository <PasswordHistory,Integer> {
	
	List<PasswordHistory> findByUser(User user);

}
