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
	
	 
  
  @GetMapping("/getAllVerified")
  public List<LoanApplication> getAllVerified()
  {
	  String url = "http://localhost:9095/api/v2/getAllDocumentVerifiedList";  
	  
		 List<LoanApplication> l =new ArrayList<LoanApplication>();
	  
	  LoanApplication[] arr = rt.getForObject(url , LoanApplication[].class);
		
	  l = Arrays.asList(arr);
	  System.out.println(l);
	  return l;
	  
  }
  
  @PutMapping("/generateCreditLimit/{loanid}")
  public ResponseEntity<String> generateCreditLimit(@PathVariable int loanid)
  {
 List<LoanApplication> al = getAllVerified();
	  
	 sli.generateLimit(loanid , al);
	  
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
  
  @PutMapping("/generateSanctionLetter/{sanctionId}/{loanid}")
  public ResponseEntity<String> generateSanctionLetter(@PathVariable int sanctionId , @PathVariable int loanid)
  {
//	 sli.generateSanctionLetter(l,sanctionId, loanid);	  
	return new ResponseEntity<String>("SanctionLetter Generated Successfully",HttpStatus.OK);
	  
  }
  
  @PutMapping("/add/{loanStatus}/{loanid}")
  public ResponseEntity<String> loanStatusChange(@PathVariable String loanStatus,@PathVariable int loanid)
  {
	  sli.loanStatusChange(loanStatus,loanid);
	  
	  return new ResponseEntity<String>("Loan Status Change",HttpStatus.OK);
  }
  

}
