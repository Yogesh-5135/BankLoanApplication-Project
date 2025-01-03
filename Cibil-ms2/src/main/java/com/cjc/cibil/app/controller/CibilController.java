package com.cjc.cibil.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cjc.cibil.app.model.Enquiry;
import com.cjc.cibil.app.model.LoanApplication;
import com.cjc.cibil.app.servicei.CibilServiceI;


@RestController
@RequestMapping("/api/v2")
public class CibilController {

	@Autowired
	CibilServiceI csi;
	
	@Autowired
	RestTemplate rt;
	
	@PutMapping("/editCibil/{customerid}")
	public ResponseEntity<Enquiry> addCibil( @PathVariable int customerid )
	{
		
       String url = "http://localhost:9098/random/checkCibilScore";
		
		Integer i = rt.getForObject(url, Integer.class);
		
		Enquiry e = csi.getCustomer(customerid ,i);
		return new ResponseEntity<Enquiry> (e,HttpStatus.OK);
	}
	
	@GetMapping("/getAllDocumentVerifiedList")
	public List<LoanApplication> getDocumentVerifiedList()
	{
		String url = "http://localhost:9090/getAllLoanApplication";
		LoanApplication[] arr = rt.getForObject(url , LoanApplication[].class);
		
		List<LoanApplication> l = Arrays.asList(arr);
		
		List<LoanApplication> la1 = new ArrayList<LoanApplication>();
		String ss = "Verified";
		for(LoanApplication s:l)
		{
			System.out.println("Checking loanStatus: " + s.getLoanStatus());
			if(s.getLoanStatus().equals(ss))
			{
				la1.add(s);
			}
		}

		return la1;
		
	}
	
}







