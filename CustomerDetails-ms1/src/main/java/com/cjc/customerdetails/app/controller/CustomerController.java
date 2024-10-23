package com.cjc.customerdetails.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PostMapping("/saveCustomer")
	public ResponseEntity<Enquiry> addStudent(@RequestBody Enquiry s)
	{
		Enquiry ss=csi.saveStudent(s);
		return new ResponseEntity<Enquiry> (ss,HttpStatus.CREATED);
	}
	
		
	@PutMapping("/editCustomer/{customerid}")
	public ResponseEntity<String> update(@PathVariable int customerid , @RequestBody Enquiry c)
	{
		csi.editCustomer(customerid , c);
		return new ResponseEntity<String>("Data Updated" , HttpStatus.OK);
	}
	
	@GetMapping("/getCustomer/{customerid}")
	public ResponseEntity<Enquiry> getSingleCustomer(@PathVariable int customerid )
	{
		Enquiry cd = csi.getData(customerid);
		return new ResponseEntity<Enquiry>(cd , HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomer")
	public ResponseEntity<List<Enquiry>> getAllCustomer( )
	{
		List<Enquiry> cd = csi.getAllData();
		return new ResponseEntity<List<Enquiry>>( cd , HttpStatus.OK);
	}

	
}
