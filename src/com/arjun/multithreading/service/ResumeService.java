package com.arjun.multithreading.service;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class ResumeService implements Runnable {
	
	@Autowired
	JavaMailSender jms;
	
	@Autowired
	SimpleMailMessage smm;
	
	public CountDownLatch cdl;
	String email;
	public ResumeService(String email,CountDownLatch cdl) {
		this.email = email;
		this.cdl = cdl;
		
	}
	public void sendResume() {
		try {
			System.out.println("Inside send resume");
		   smm.setTo(this.email);
	      jms.send(smm);		
		}
		catch(MailException m) {
			m.printStackTrace();
		}
		
		
	}

	@Override
	public void run() {
		System.out.println("Inside run");
		sendResume();
		
	}

}
