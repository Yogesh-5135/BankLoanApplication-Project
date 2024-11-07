package com.cjc.sanctionletter.app.controller;

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

import com.cjc.sanctionletter.app.model.LoanApplication;
import com.cjc.sanctionletter.app.servicei.SanctionLetterI;

@RestController
@RequestMapping("/api/v3")
public class SanctionLetterController 
{
  @Autowired
  SanctionLetterI sli;
  
	@Autowired
	RestTemplate rt;
	
	 List<LoanApplication> l =new ArrayList<LoanApplication>();	 
  
  @GetMapping("/getAllVerified")
  public ResponseEntity<List<LoanApplication>> getAllVerified()
  {
	  String url = "http://localhost:9095/api/v2/getAllDocumentVerifiedList";  
	  
	  LoanApplication[] arr = rt.getForObject(url , LoanApplication[].class);
		
	  l = Arrays.asList(arr);
	  System.out.println(l);
	  return new ResponseEntity<List<LoanApplication>>(l,HttpStatus.OK);
	  
  }
  
  @PutMapping("/generateCreditLimit/{loanid}")
  public ResponseEntity<String> generateCreditLimit(@PathVariable int loanid)
  {
	 sli.generateLimit(loanid , l);
	  
	 return new ResponseEntity<String>("Credit limit generated",HttpStatus.OK);
  }
  
  @PutMapping("/getIntRate/{sanctionId}")
  public ResponseEntity<String> getInterestrate(@PathVariable int sanctionId)
  {
	  sli.generateIntRate( sanctionId);
	  return new ResponseEntity<String>("Interest rate generated",HttpStatus.OK);
  }	 
  
  @PutMapping("/getmonthlyEmi/{loanid}/{sanctionId}")
  public ResponseEntity<String> monthlyEmi(@PathVariable int loanid ,@PathVariable int sanctionId)
  {	  
	  sli.getMonthlyEmi( sanctionId);
	  return new ResponseEntity<String>("Monthly emi generated",HttpStatus.OK);
  }
  
  @PutMapping("/generateSanctionLetter/{sanctionId}")
  public ResponseEntity<String> generateSanctionLetter(@PathVariable int sanctionId)
  {
	 sli.generateSanctionLetter(l,sanctionId);	  
	return new ResponseEntity<String>("SanctionLetter Generated Successfully",HttpStatus.OK);
	  
  }
}
