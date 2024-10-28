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
import com.cjc.customerdetails.app.servicei.loanapplicationform.CustomerAddressServiceI;

@RestController
public class CustomerAddressController  
{
  @Autowired
  CustomerAddressServiceI cas;
  
  @PostMapping("/saveCustomerAddrerss/{loanid}")
  public ResponseEntity<CustomerAddress> saveCustomerAddress(@PathVariable int loanid)
  {
	  CustomerAddress ca1 = cas.saveCustomerAddress(loanid);
	  
	  return new ResponseEntity<CustomerAddress>(ca1,HttpStatus.CREATED);
  }
  
  @GetMapping("/getSingleCustomerAddress/{customerAddressId}")
  public ResponseEntity<CustomerAddress> getSingleCustomerAddress(@PathVariable int customerAddressId)
  {
	  CustomerAddress ca = cas.getSingleCustomerAddress(customerAddressId);
	  
	  return new ResponseEntity<CustomerAddress>(ca,HttpStatus.OK);
  }
  
  @PutMapping("/updateCustomerAddress/{customerAddressId}")
  public ResponseEntity<CustomerAddress> updateCustomerAddress(@PathVariable int customerAddressId,@RequestBody CustomerAddress ca)
  {
	  CustomerAddress c = cas.updateCustomerAddress(customerAddressId,ca);
	  
	  return new ResponseEntity<CustomerAddress>(c,HttpStatus.OK);
  }
  
  @DeleteMapping("/deleteCustomerAddress/{customerAddressId}")
  public ResponseEntity<String> deleteCustomerAddress(@PathVariable int customerAddressId)
  {
	  cas.deleteCustomerAddress(customerAddressId);
	  
	  return new ResponseEntity<String>("CustomerAddress Deleted Successfully",HttpStatus.OK);
  }
}
