package com.example.justinSpringBoot.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.*;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class resourseNotFound extends RuntimeException{

	public resourseNotFound(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	
	
}
