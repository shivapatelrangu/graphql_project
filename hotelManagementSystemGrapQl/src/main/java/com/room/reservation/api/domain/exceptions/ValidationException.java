package com.room.reservation.api.domain.exceptions;

import org.springframework.http.HttpStatus;

import com.room.reservation.api.domain.enums.CustomHttpStatus;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ValidationException extends RuntimeException{
		

	
	private CustomHttpStatus customHttpStatus;

	public ValidationException(String message, CustomHttpStatus customHttpStatus)
	{
		super(message);
		this.customHttpStatus=customHttpStatus;
		
	}
}





