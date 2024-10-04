package com.room.reservation.api.domain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.room.reservation.api"})
public class HotelManagementSystemGrapQlApplication {

	public static void main(String[] args) {
		SpringApplication.run(HotelManagementSystemGrapQlApplication.class, args);
	}

}
