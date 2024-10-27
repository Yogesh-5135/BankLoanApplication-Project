package com.cjc.customerdetails.app.controller.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.customerdetails.app.model.loanapplicationform.AccountDetails;
import com.cjc.customerdetails.app.servicei.loanapplicationform.AccountDetailsServiceI;

@RestController
public class AccountDetailsController 
{

	@Autowired
	AccountDetailsServiceI ads;
	
	@PostMapping("/saveAccountDetails/{loanid}")
	public ResponseEntity<AccountDetails> saveAccountDetails(@RequestBody AccountDetails ad,int loanid)
	{
		AccountDetails ad1 = ads.saveAccountDetails(ad,loanid);
		
		return new ResponseEntity<AccountDetails>(ad1,HttpStatus.CREATED);
	}
	
	@GetMapping("/getSingleAccountDetails/{accountId}")
	public ResponseEntity<AccountDetails> getSingleAccountDetails(@PathVariable int accountId)
	{
		AccountDetails ad = ads.getSingleAccountDetails(accountId);
		
		return new ResponseEntity<AccountDetails>(ad,HttpStatus.OK);
	}
	
	@PutMapping("/updateAccountDetails/{accountId}")
	public ResponseEntity<AccountDetails> updateAccountDetails(@PathVariable int accountId,@RequestBody AccountDetails ad)
	{
		AccountDetails ad1 = ads.updateAccountDetails(accountId,ad);
		
		return new ResponseEntity<AccountDetails>(ad,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAccountDetails/{accountId}")
	public ResponseEntity<String> deleteAccountDetails(@PathVariable int accountId)
	{
		ads.deleteAccountDetails(accountId);
		
		return new ResponseEntity<String>("AccountDetails Deleted Successfully",HttpStatus.OK);
	}
}
