package com.room.reservation.api.domain.controllers;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.room.reservation.api.domain.dtos.ViewReservationResponseDto;
import com.room.reservation.api.domain.services.CustomerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ViewReservationController {

	
	@Autowired
	CustomerService customerservice;

	@QueryMapping
	public ViewReservationResponseDto viewReservation(@Valid @Argument("bookingId") int bookingId, @Argument("staffId" ) int staffId, @Argument("custId") int customerId)throws SQLException, ParseException {
		
			log.info("/view-api/view-reservation Method Name: viewReservation,  booking Id: {} staffId:{} and customerId:{}", bookingId, staffId, customerId);
			ViewReservationResponseDto response = customerservice.viewReservationDetails(bookingId);
			return response;

		
		

	}

}
