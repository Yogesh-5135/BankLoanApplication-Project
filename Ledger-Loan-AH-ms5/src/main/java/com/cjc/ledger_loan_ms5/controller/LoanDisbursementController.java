package com.cjc.ledger_loan_ms5.controller;

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

import com.cjc.ledger_loan_ms5.model.LoanApplication;
import com.cjc.ledger_loan_ms5.servicei.LoanDisbursementI;


@RestController
@RequestMapping("/api/v4")
public class LoanDisbursementController 
{
	@Autowired
	LoanDisbursementI ldi;
	@Autowired
	RestTemplate rt;
	
	
	 @GetMapping("/getAllAcceptedData")	
	 public List<LoanApplication> getAllAccepted()
	 {
		 
		 String url = "http://localhost:9090/applyLoan/getAllAccepted"; 
		 
		 LoanApplication[] lp = rt.getForObject(url, LoanApplication[].class);
		 
		 List<LoanApplication> l = new ArrayList<LoanApplication>();
		 
		 l = Arrays.asList(lp);
		 
		 return l;
		 
	 }
	
	
	 @PutMapping("/update/{loanid}")
	 public ResponseEntity<String> updateloandiburse(@PathVariable int loanid)
	 {
		 List<LoanApplication> la = getAllAccepted();
		 
		 ldi.updateLoan(loanid,la);
		 
		 return new ResponseEntity<String>("Updated" , HttpStatus.OK); 
		 
	 }
	 
}
