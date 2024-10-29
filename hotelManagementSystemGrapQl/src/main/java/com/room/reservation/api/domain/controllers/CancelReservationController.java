package com.room.reservation.api.domain.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.room.reservation.api.domain.dtos.CancelReservationRequestDto;
import com.room.reservation.api.domain.dtos.CancelReservationResponseDto;
import com.room.reservation.api.domain.exceptions.ResourceNotFoundException;
import com.room.reservation.api.domain.services.CustomerService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class CancelReservationController {

	
	@Autowired
	CustomerService customerservice;

	@MutationMapping
	public CancelReservationResponseDto cancelReservation(
			@Valid @Argument("input") CancelReservationRequestDto cancelreservationrequest, @Argument("staffId" ) int staffId, @Argument("custId") int customerId) throws SQLException {
		log.info(" Method Name:cancelReservation , Booking Id:{}  staffId:{} and custometId:{}", cancelreservationrequest.getBookingId(), staffId, customerId);

		CancelReservationResponseDto response = customerservice.cancelReservation(cancelreservationrequest);

		return response;
		}


	
	}


