package com.ParkeoX.ParkeoX;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ParkeoXApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkeoXApplication.class, args);
	}

}
