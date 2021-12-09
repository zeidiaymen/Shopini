package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Client;
import com.example.demo.repository.RepClient;
@Service
public class addClientForTestIMPL implements IaddClientForTest {
@Autowired
RepClient c ;
	
	public Client addClient(Client a) {
		// TODO Auto-generated method stub
		return c.save(a);
	}

	public Client getClient(String i) {
		// TODO Auto-generated method stub
		return c.findByIds(i);
	}

}
