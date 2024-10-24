package com.cjc.cibil.app.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse 
{
	private int statusCode;
	private String msg;
	private Date date;
	private String url;
	private HttpStatus errorMessage;
}
