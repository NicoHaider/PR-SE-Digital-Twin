package com.example.DigitalTwin;

import com.example.DigitalTwin.utils.CreateLogDirectory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DtApplication {

	public static void main(String[] args) {
		CreateLogDirectory.createLogDirectory();
		SpringApplication.run(DtApplication.class, args);
	}

}

