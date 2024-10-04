package com.room.reservation.api.domain.controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.room.reservation.api.domain.dtos.SearchRoomsResponseDto;
import com.room.reservation.api.domain.enums.CustomHttpStatus;
import com.room.reservation.api.domain.exceptions.ValidationException;
import com.room.reservation.api.domain.services.HotelRoomsService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class SearchRoomsController {

	@Autowired
	HotelRoomsService searchservice;

	@QueryMapping
	public ResponseEntity<List<SearchRoomsResponseDto>> searchAvailableRooms(

			@Valid @Argument("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
			@Valid @Argument("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate,
			@Argument("staffId") int staffId, @Argument("custId") int customerId)
			throws ParseException, SQLException {

		log.info(
				"/api/search-rooms/available  method name: searchAvailableRooms"
						+ "recieved request to search rooms from {} to {} , staffId={} and customerId={}",
				fromDate, toDate, staffId, customerId);
		if (vaidateRequest(fromDate, toDate)) {

			List<SearchRoomsResponseDto> response = searchservice.getAvailableRooms(fromDate, toDate, staffId,
					customerId);

			return ResponseEntity.ok(response);
		}
		log.warn("Method Name:searchAvailableRooms, please enter valid {} and {} ", fromDate, toDate);
		throw new ValidationException("please enter valid fromDate and toDate ", CustomHttpStatus.BAD_REQUEST);

	}

	boolean vaidateRequest(Date fromDate, Date toDate) throws ParseException {

		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateStr = dateFormat.format(date);
		Date date1 = dateFormat.parse(dateStr);

		Optional<Date> from_date = Optional.of(fromDate);
		Optional<Date> to_date = Optional.of(toDate);
		log.info("{},{}", date1, fromDate);

		if (fromDate.equals(toDate)) {
			log.warn("Method Name:vaidateRequest , message: please plan to stay at least a day");
			throw new ValidationException("please plan to stay at least a day ", CustomHttpStatus.Ok);

		}

		else if ((!from_date.isEmpty() & !to_date.isEmpty() & fromDate.before(toDate)
				& (fromDate.after(date1) || fromDate.equals(date1))))

			return true;

		else
			return false;

	}

}
