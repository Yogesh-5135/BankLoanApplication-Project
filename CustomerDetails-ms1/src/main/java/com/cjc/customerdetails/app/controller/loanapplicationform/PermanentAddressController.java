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

import com.cjc.customerdetails.app.model.loanapplicationform.LocalAddress;
import com.cjc.customerdetails.app.model.loanapplicationform.PermanentAddress;
import com.cjc.customerdetails.app.servicei.loanapplicationform.PermanentAddressServiceI;

@RestController
public class PermanentAddressController  
{
  @Autowired
  PermanentAddressServiceI pas;
  
  @PostMapping("/savePermanentAddrerss/{customerAddressId}")
  public ResponseEntity<PermanentAddress> savePermanentAddress(@RequestBody PermanentAddress a, @PathVariable int customerAddressId) {
      try {
          
    	  PermanentAddress a1 = pas.savePermanentAddress(a, customerAddressId);
        
          return new ResponseEntity<>(a1, HttpStatus.CREATED);
          
      } 
      catch (Exception e) 
      {
          
          throw e; 
      }
  }
  @GetMapping("/getSinglePermanentAddress/{permanentAddressId}")
  public ResponseEntity<PermanentAddress> getSinglePermanentAddress(@PathVariable int permanentAddressId)
  {
	  PermanentAddress a = pas.getSinglePermanentAddress(permanentAddressId);
	  
	  return new ResponseEntity<PermanentAddress>(a,HttpStatus.OK);
  }
  
  @PutMapping("/updatePermanentAddress/{permanentAddressId}")
  public ResponseEntity<PermanentAddress> updatePermanentAddress(@PathVariable int permanentAddressId,@RequestBody PermanentAddress a)
  {
	  PermanentAddress a1 = pas.updatePermanentAddress(permanentAddressId,a);
	  
	  return new ResponseEntity<PermanentAddress>(a1,HttpStatus.OK);
  }
  
  @DeleteMapping("/deletePermanentAddress/{permanentAddressId}")
  public ResponseEntity<String> deletePermanentAddress(@PathVariable int permanentAddressId)
  {
	  pas.deletePermanentAddress(permanentAddressId);
	  
	  return new ResponseEntity<String>("PermanentAddress Deleted Successfully",HttpStatus.OK);
  }
}
