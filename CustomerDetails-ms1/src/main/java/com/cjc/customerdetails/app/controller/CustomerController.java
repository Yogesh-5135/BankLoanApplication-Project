package com.cjc.customerdetails.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.customerdetails.app.model.Enquiry;
import com.cjc.customerdetails.app.servicei.CustServiceI;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	CustServiceI csi;
	
	@PostMapping("/add")
	public ResponseEntity<Enquiry> addStudent(@RequestBody Enquiry s)
	{
		Enquiry ss=csi.saveStudent(s);
		return new ResponseEntity<Enquiry> (ss,HttpStatus.CREATED);
	}
	
	
}
