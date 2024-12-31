package com.cjc.customerdetails.app.controller.loanapplicationform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;
import com.cjc.customerdetails.app.servicei.loanapplicationform.LoanApplyServiceI;

@RestController
public class LoanApplyController {

	@Autowired 
	LoanApplyServiceI lsi;
	
	@PostMapping("/saveLoanApplication")
	public ResponseEntity<LoanApplication> saveAccountDetails(@RequestBody LoanApplication ad )
	{
		 LoanApplication da = lsi.saveLoanApplication(ad);
		
		return new ResponseEntity<LoanApplication>(da,HttpStatus.CREATED);
	}
	
	@GetMapping("/getSingleLoanApplication/{loanid}")
	public ResponseEntity<LoanApplication> getSingleAccountDetails(@PathVariable("loanid") Integer loanid)
	{
		LoanApplication l = lsi.getSingleLoanApplication(loanid);
		
		return new ResponseEntity<LoanApplication>(l,HttpStatus.OK);
	}
	
	
	@GetMapping("/getAllLoanApplication")
	public ResponseEntity<List<LoanApplication>> getAllAccountDetails()
	{
		List<LoanApplication> l = lsi.getAllLoanApplication();
		
		return new ResponseEntity<List<LoanApplication>>(l,HttpStatus.OK);
	}
	
	
	
	@PutMapping("/updateLoanApplication/{loanid}")
	public ResponseEntity<LoanApplication> updateLoanApplication(@PathVariable Integer loanid,@RequestBody LoanApplication ld)
	{
		LoanApplication l = lsi.updateLoanApplication(loanid,ld);
		
		return new ResponseEntity<LoanApplication>(l,HttpStatus.OK);
	}
	

	@PutMapping("/updateLoanStatusAsVerified/{loanid}")
	public ResponseEntity<LoanApplication> updateLoanStatus(@PathVariable Integer loanid)
	{
		LoanApplication l = lsi.updateLoanStatus(loanid);
		
		return new ResponseEntity<LoanApplication>(l,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteLoanApplication/{loanid}")
	public ResponseEntity<String> deleteLoanApplication(@PathVariable int loanid)
	{
		lsi.deleteLoanApplication(loanid);
		
		return new ResponseEntity<String>("LoanApplication Deleted Successfully",HttpStatus.OK);
	}
	
	 @GetMapping("/getAllAccepted")
	  public ResponseEntity<List<LoanApplication>> getAllaccpted()
	  {
		List<LoanApplication> l =  lsi.getAccepted();
		  return new ResponseEntity<List<LoanApplication>>(l,HttpStatus.OK);
		  
	  }
}
