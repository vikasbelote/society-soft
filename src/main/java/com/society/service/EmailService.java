package com.society.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.society.repository.EmailRepository;

@Service
public class EmailService {
	
	@Autowired
	private EmailRepository emailRepository;
	
	@Async
	public void sendMail() {
		
		for(int i = 1; i <= 5; i++) {
			
			
			try {
				System.out.println(Thread.currentThread().getName());
				Thread.currentThread().sleep(10000);
				
				if(i == 3 && Thread.currentThread().getName().equals("society-1")) {
					throw new RuntimeException("This is exception throwing");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//EmailJPA email = new EmailJPA();
			//email.setMemberId(i);
			//emailRepository.saveEmailInformation(email);
		}
	}

}
