package com.patient_management.patient_service;


import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PatientServiceApplication {


	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.configure().load();
		dotenv.entries()
				.forEach(dotenvEntry -> System.setProperty(dotenvEntry.getKey(),dotenvEntry.getValue()));
		SpringApplication.run(PatientServiceApplication.class, args);
	}

}
