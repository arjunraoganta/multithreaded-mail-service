package com.arjun.multithreading.configuration;

import java.util.Properties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@ComponentScan
public class ResumeConfiguration {
	
	@Bean
	public JavaMailSender getMailSender() {
		System.out.println("Creating MS bean");
		JavaMailSenderImpl jmsImpl = new JavaMailSenderImpl();
		jmsImpl.setHost("smtp.gmail.com");
        jmsImpl.setPort(587);
        jmsImpl.setUsername("arjunganta2305@gmail.com");
        jmsImpl.setPassword("#Arjun88979#");
        Properties props = jmsImpl.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.debug", "true");
	      
	   return jmsImpl;   
	}
	@Bean
	public SimpleMailMessage getSimpleMailMessage() {
	 System.out.println("Creating SMM bean");
		SimpleMailMessage smm = new SimpleMailMessage();
		smm.setFrom("arjunganta2305@gmail.com");
		smm.setText("Hai from Arjunganta 2305");
		
	  return smm;
		
		
		
	}
	
	
	@Bean
	public TaskExecutor getExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(4);
		pool.setMaxPoolSize(4);
		pool.setThreadNamePrefix("resume");
		pool.initialize();
		return pool;
	}

}
