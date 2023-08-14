package com.eci;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.eci.config.FileStorageProperties;
import com.eci.model.EmployeeDetails;
import com.eci.repository.EmployeeDetailsRepository;
@SpringBootApplication
public class CrudApplication implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(TechnothanApplication.class, args);
	}

	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepository;
	@Override
	public void run(String... args) throws Exception {

	}  
}
