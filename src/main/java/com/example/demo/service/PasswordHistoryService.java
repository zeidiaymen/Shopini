package com.example.demo.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.*;
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
	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	public Boolean checkPasswordHistory(Optional<User> user, String password) {

		List<PasswordHistory> passwordHistoryByUserList = passwordHistoryRepository.findByUser(user.orElse(null));

		for (PasswordHistory passwordHistoryByUser : passwordHistoryByUserList) {
			if (this.passwordEncoder.matches(password, passwordHistoryByUser.getPassword())) {

				return false;
			}

		}
		String encodedPassword = this.passwordEncoder.encode(password);

		PasswordHistory passwordHistory = new PasswordHistory(user.get(), encodedPassword, Utils.getCurrentDate());

		this.addPasswordHistory(passwordHistory);

		return true;

	}

	public void addPasswordHistory(PasswordHistory passwordHistory) {
		passwordHistoryRepository.save(passwordHistory);
	}

}
