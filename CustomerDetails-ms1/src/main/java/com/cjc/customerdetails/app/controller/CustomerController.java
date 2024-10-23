package com.cjc.customerdetails.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.customerdetails.app.servicei.CustServiceI;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

	@Autowired
	CustServiceI csi;
	
	
}
