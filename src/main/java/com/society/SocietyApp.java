package com.society;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Properties;
import java.util.concurrent.Executor;

import javax.mail.Session;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorMvcAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication(exclude = ErrorMvcAutoConfiguration.class)
@EnableAsync
public class SocietyApp extends SpringBootServletInitializer implements AsyncConfigurer {
	
	private static final Logger logger = LogManager.getLogger(SocietyApp.class);
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SocietyApp.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(SocietyApp.class, args);
	}
	
	@Bean
	public DozerBeanMapper getDozerBeanMapper() {
		return new DozerBeanMapper();
	}
	
	@Override
	public Executor getAsyncExecutor() {
		
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(2);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("society-");
        executor.setKeepAliveSeconds(10);
        executor.setAllowCoreThreadTimeOut(true);
        executor.initialize();
        return executor;
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new CustomAsyncExceptionHandler();
	}
	
	@Bean
	public JavaMailSenderImpl getJavaMailSender() {
		
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("mail.societysoft.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("admin@societysoft.com");
	    mailSender.setPassword("Suju@viku0207");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    
		OutputStream outputStream = new LoggerOutputStream(logger, Level.DEBUG);
		PrintStream printStream = new PrintStream(outputStream);
		
		Session session = mailSender.getSession(); 
		session.setDebugOut(printStream);
	    
	    return mailSender;
	}
}
