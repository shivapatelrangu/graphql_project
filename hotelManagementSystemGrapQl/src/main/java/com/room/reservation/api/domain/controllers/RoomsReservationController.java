
package com.room.reservation.api.domain.controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.room.reservation.api.domain.dtos.HeadersDto;
import com.room.reservation.api.domain.dtos.RoomsReservartionResponseDto;
import com.room.reservation.api.domain.dtos.RoomsReservationRequestDto;
import com.room.reservation.api.domain.enums.CustomHttpStatus;
import com.room.reservation.api.domain.exceptions.ValidationException;
import com.room.reservation.api.domain.services.ReservationService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class RoomsReservationController {

	@Autowired
	ReservationService service;


	@MutationMapping
	public RoomsReservartionResponseDto reserveHotelRooms(
			@Valid @Argument("input") RoomsReservationRequestDto requestdto, @Argument("staffId") int staffId,
			@Argument("custId") int customerId) throws SQLException, ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = dateFormat.parse(requestdto.getCheckInDate());
		Date toDate = dateFormat.parse(requestdto.getCheckOutDate());
		log.info(
				"/reservations/api/reserve-rooms  method name: saveReservation "
						+ " recived request to save reservation {}  and staffId={} and customerId={}",
				requestdto, staffId, customerId);
		
		
//		kafkaProducer.sendMessage("sending message from RoomsReservationController");
		
		HeadersDto.custId = customerId;
		HeadersDto.staffId = staffId;
		SearchRoomsController searchRooms = new SearchRoomsController();
		if (searchRooms.vaidateRequest(fromDate, toDate)) {

			return service.reserveHotelRooms(requestdto,fromDate,toDate);
		}
		log.warn(
				"/reservations/api/reserve-rooms Method Name :saveReservation, message:checkIndate should be before checkOutdate");
		throw new ValidationException("checkIndate should be before checkOutdate", CustomHttpStatus.Ok);

	}

}
