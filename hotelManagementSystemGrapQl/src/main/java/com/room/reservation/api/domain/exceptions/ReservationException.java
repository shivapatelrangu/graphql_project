package com.room.reservation.api.domain.exceptions;

import com.room.reservation.api.domain.enums.CustomHttpStatus;

import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
public class ReservationException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CustomHttpStatus customHttpStatus;

	
	

	public ReservationException(String message,CustomHttpStatus customHttpStatus)
	{
		super(message);
		this.customHttpStatus=customHttpStatus;
	}
	
	
}
