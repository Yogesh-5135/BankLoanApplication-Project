package com.cjc.cibil.app.exception;

public class CustomerNotFoundException extends RuntimeException 
{
  public CustomerNotFoundException(String msg)
  {
	  super(msg);
  }
}
