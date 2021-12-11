package com.example.demo.service;

import java.util.List;



import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PasswordHistory;
import com.example.demo.entity.User;
import com.example.demo.repository.PasswordHistoryRepository;
import com.example.demo.utils.Utils;

@Service
public class PasswordHistoryService {

	@Autowired
	PasswordHistoryRepository passwordHistoryRepository;
	
	@Autowired
	Utils utils;
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public Boolean checkPasswordHistory(User user, String password) {
		
		List<PasswordHistory> passwordHistoryByUserList = passwordHistoryRepository.findByUser(user);

		for (PasswordHistory passwordHistoryByUser : passwordHistoryByUserList) {
			if (this.passwordEncoder.matches(password, passwordHistoryByUser.getPassword())) {

				return false;
			}

		}
		String encodedPassword = this.passwordEncoder.encode(password);

		PasswordHistory passwordHistory = new PasswordHistory(user, encodedPassword, Utils.getCurrentDate());

		this.addPasswordHistory(passwordHistory);

		return true;

	}

	public void addPasswordHistory(PasswordHistory passwordHistory) {
		passwordHistoryRepository.save(passwordHistory);
	}
	
	
	@Scheduled(cron = "0 0 0 * * *")
	public void deleteOldPasswordHistory(){
		
		List<PasswordHistory> passwordHistoryByUserList = passwordHistoryRepository.findAll();
		
		for (PasswordHistory passwordHistory : passwordHistoryByUserList) {
			
			if(utils.compareDateByMonths(passwordHistory.getCreatedAt(), 2)){
				this.passwordHistoryRepository.delete(passwordHistory);
			}
		}


		
		
		
		
	}
	
	
	
	

}
