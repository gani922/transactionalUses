package com.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.service.entity.EntityTra;
import com.service.repo.RepoInt;

import jakarta.transaction.Transactional;

@Service

public class serviceClass {

	@Autowired
	private RepoInt repo;
	
	 @Transactional
	    public void transfer(Long fromId, Long toId, double amount) {

	        EntityTra from = repo.findById(fromId)
	                .orElseThrow(() -> new RuntimeException("Sender not found"));

	        EntityTra to = repo.findById(toId)
	                .orElseThrow(() -> new RuntimeException("Receiver not found"));

	        from.setBalance(from.getBalance() - amount);
	        to.setBalance(to.getBalance() + amount);

	        repo.save(from);
	        repo.save(to);

	        // Simulate failure
	        if (to.getBalance() > 10000) {
	            throw new RuntimeException("Limit exceeded! Rolling back transaction...");
	        }
	    }

	    public void createAccounts() {
	    	repo.save(new EntityTra("Ganesh", 5000));
	        repo.save(new EntityTra("Kalyan", 3000));
	    }

	    public Iterable<EntityTra> getAllAccounts() {
	        return repo.findAll();
	    }
	}
	

