package com.cjc.customerdetails.app.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler 
{

	@ExceptionHandler(MobNoException.class)
	   public ResponseEntity<ApiResponse> MobNoExceptionHandler(MobNoException e,HttpServletRequest request)
	   {
			ApiResponse error = new ApiResponse();
			error.setDate(new Date());
			error.setMsg(e.getMessage());
			error.setStatusCode(HttpStatus.BAD_REQUEST.value());
			error.setErrorMessage(HttpStatus.BAD_REQUEST);
			error.setUrl(request.getRequestURI());
			return new ResponseEntity<ApiResponse>(error,HttpStatus.BAD_REQUEST);
	   }
	
	@ExceptionHandler(EmailNotValidException.class)
	   public ResponseEntity<ApiResponse> EmailNotValidExceptionHandler(EmailNotValidException e,HttpServletRequest request)
	   {
			ApiResponse error = new ApiResponse();
			error.setDate(new Date());
			error.setMsg(e.getMessage());
			error.setStatusCode(HttpStatus.BAD_REQUEST.value());
			error.setErrorMessage(HttpStatus.BAD_REQUEST);
			error.setUrl(request.getRequestURI());
			return new ResponseEntity<ApiResponse>(error,HttpStatus.BAD_REQUEST);
	   }
	
	@ExceptionHandler(AgeException.class)
	   public ResponseEntity<ApiResponse> AgeExceptionHandler(AgeException e,HttpServletRequest request)
	   {
			ApiResponse error = new ApiResponse();
			error.setDate(new Date());
			error.setMsg(e.getMessage());
			error.setStatusCode(HttpStatus.BAD_REQUEST.value());
			error.setErrorMessage(HttpStatus.BAD_REQUEST);
			error.setUrl(request.getRequestURI());
			return new ResponseEntity<ApiResponse>(error,HttpStatus.BAD_REQUEST);
	   }
	
	@ExceptionHandler(InvalidNameException.class)
	   public ResponseEntity<ApiResponse> InvalidNameExceptionHandler(InvalidNameException e,HttpServletRequest request)
	   {
			ApiResponse error = new ApiResponse();
			error.setDate(new Date());
			error.setMsg(e.getMessage());
			error.setStatusCode(HttpStatus.BAD_REQUEST.value());
			error.setErrorMessage(HttpStatus.BAD_REQUEST);
			error.setUrl(request.getRequestURI());
			return new ResponseEntity<ApiResponse>(error,HttpStatus.BAD_REQUEST);
	   }
	

	@ExceptionHandler(PanCardException.class)
	   public ResponseEntity<ApiResponse> PanCardExceptionHandler(PanCardException e,HttpServletRequest request)
	   {
			ApiResponse error = new ApiResponse();
			error.setDate(new Date());
			error.setMsg(e.getMessage());
			error.setStatusCode(HttpStatus.BAD_REQUEST.value());
			error.setErrorMessage(HttpStatus.BAD_REQUEST);
			error.setUrl(request.getRequestURI());
			return new ResponseEntity<ApiResponse>(error,HttpStatus.BAD_REQUEST);
	   }
	
	@ExceptionHandler(IdNotFountException.class)
	   public ResponseEntity<ApiResponse> IdNotFountExceptionHandler(IdNotFountException e,HttpServletRequest request)
	   {
			ApiResponse error = new ApiResponse();
			error.setDate(new Date());
			error.setMsg(e.getMessage());
			error.setStatusCode(HttpStatus.NOT_FOUND.value());
			error.setErrorMessage(HttpStatus.NOT_FOUND);
			error.setUrl(request.getRequestURI());
			return new ResponseEntity<ApiResponse>(error,HttpStatus.NOT_FOUND);
	   }
	
	@ExceptionHandler(InvalidDataException.class)
	   public ResponseEntity<ApiResponse> InvalidDataExceptionHandler(InvalidDataException e,HttpServletRequest request)
	   {
			ApiResponse error = new ApiResponse();
			error.setDate(new Date());
			error.setMsg(e.getMessage());
			error.setStatusCode(HttpStatus.BAD_REQUEST.value());
			error.setErrorMessage(HttpStatus.BAD_REQUEST);
			error.setUrl(request.getRequestURI());
			return new ResponseEntity<ApiResponse>(error,HttpStatus.BAD_REQUEST);
	   }
}
