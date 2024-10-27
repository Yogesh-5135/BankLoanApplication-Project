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

import com.cjc.customerdetails.app.model.loanapplicationform.CustomerAddress;
import com.cjc.customerdetails.app.model.loanapplicationform.LocalAddress;
import com.cjc.customerdetails.app.servicei.loanapplicationform.LocalAddressServiceI;

@RestController
public class LocalAddressController 
{
  @Autowired
  LocalAddressServiceI las ;
  
  @PostMapping("/saveLocalAddrerss/{loanid}")
  public ResponseEntity<LocalAddress> saveLocalAddress(@RequestBody LocalAddress a,int loanid)
  {
	  LocalAddress a1 = las.saveLocalAddress(a,loanid);
	  
	  return new ResponseEntity<LocalAddress>(a1,HttpStatus.CREATED);
  }
  
  @GetMapping("/getSingleLocalAddress/{localAddressId}")
  public ResponseEntity<LocalAddress> getSingleLocalAddress(@PathVariable int localAddressId)
  {
	  LocalAddress a = las.getSingleLocalAddress(localAddressId);
	  
	  return new ResponseEntity<LocalAddress>(a,HttpStatus.OK);
  }
  
  @PutMapping("/updateLocalAddress/{localAddressId}")
  public ResponseEntity<LocalAddress> updateLocalAddress(@PathVariable int localAddressId,@RequestBody LocalAddress a)
  {
	  LocalAddress a1 = las.updateLocalAddress(localAddressId,a);
	  
	  return new ResponseEntity<LocalAddress>(a1,HttpStatus.OK);
  }
  
  @DeleteMapping("/deleteLocalAddress/{localAddressId}")
  public ResponseEntity<String> deleteLocalAddress(@PathVariable int localAddressId)
  {
	  las.deleteLocalAddress(localAddressId);
	  
	  return new ResponseEntity<String>("LocalAddress Deleted Successfully",HttpStatus.OK);
  }
}