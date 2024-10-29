package com.room.reservation.api.domain.exceptions;

import java.util.HashMap;
import java.util.Map;
import graphql.GraphQLError;


import graphql.GraphqlErrorBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.room.reservation.api.domain.dtos.ApiResponseDto;
import com.room.reservation.api.domain.enums.CustomHttpStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {



	@GraphQlExceptionHandler(ValidationException.class)
	public GraphQLError validationExceptionHandler(ValidationException ex) {
		String message = ex.getMessage();
		log.error("Validation exception: {}", message, ex);
		return GraphQLError.newError()
				.message(message)
				.build();
	}
	@GraphQlExceptionHandler(ReservationException.class)
	public GraphQLError handleReservationException(ReservationException ex) {
		String message = ex.getMessage();
		log.error("Validation exception: {}", message, ex);
		return GraphQLError.newError()
				.message(message)
				.build();
	}

//	@GraphQlExceptionHandler(MissingServletRequestParameterException.class)
//	public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex) {
//		String parameterName = ex.getParameterName();
//		Map<String, String> errorMap = new HashMap<>();
//		errorMap.put(parameterName, parameterName + " is required but not provided");
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMap);
//	}

//	@GraphQlExceptionHandler(MethodArgumentNotValidException.class)
//	public ResponseEntity<Map<String, String>> handleMethodArgsNotvalidException(MethodArgumentNotValidException ex) {
//		Map<String, String> response = new HashMap<>();
//		ex.getBindingResult().getAllErrors().forEach((error) -> {
//			String filedName =  "message";        //((FieldError) error).getField();
//			String errorMessage = error.getDefaultMessage();
//			response.put(filedName, errorMessage);
//		});
//
//		log.error("Validation failed for method arguments: {}", response, ex);
//
//		return new ResponseEntity<>(response, HttpStatus.valueOf(CustomHttpStatus.BAD_REQUEST.getCode()));
//	}




}
