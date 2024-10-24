package com.cjc.cibil.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.cibil.app.model.Cibil;
import com.cjc.cibil.app.model.Enquiry;
import com.cjc.cibil.app.servicei.CibilServiceI;


@RestController
@RequestMapping("/api/v2")
public class CibilController {

	@Autowired
	CibilServiceI csi;
	
	
	@PutMapping("/editCibil/{customerid}")
	public ResponseEntity<Enquiry> addCibil( @PathVariable int customerid , @RequestBody Cibil c)
	{
		Enquiry e = csi.getCustomer(customerid , c);	
		
		return new ResponseEntity<Enquiry> (e,HttpStatus.OK);
	}
	
	
}
