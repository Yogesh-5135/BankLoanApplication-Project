package com.cjc.customerdetails.app.controller.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.customerdetails.app.servicei.loanapplicationform.AllPersonalDocumentsServiceI;

@RestController
public class AllPersonalDocumentsController 
{

	@Autowired
	AllPersonalDocumentsServiceI apd;
	
}
