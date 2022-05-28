package com.arjun.multithreading;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.TaskExecutor;

import com.arjun.multithreading.configuration.ResumeConfiguration;
import com.arjun.multithreading.service.ResumeService;

@ComponentScan
public class ResumeUploader {
	@Autowired
	public static  TaskExecutor te;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Running Resume Uploader");
		ApplicationContext context = new AnnotationConfigApplicationContext(ResumeConfiguration.class);
		ResumeService rs = context.getBean(ResumeService.class);
		CountDownLatch cdl = new CountDownLatch(4);
		List<String> emailIds = new ArrayList<>();
		for(int i=0;i<10;i++) {
			emailIds.add("arjunganta007@gmail.com");	
		}
		for(String email : emailIds) {
			te.execute(new ResumeService(email,cdl));
		}
		Instant start = Instant.now();
		rs.sendResume();
		Instant end = Instant.now();
		long elapse = Duration.between(start, end).toSeconds();
		cdl.await();
		System.out.println("Time taken to send mails "+elapse +"seconds");
		
	

	}

}
