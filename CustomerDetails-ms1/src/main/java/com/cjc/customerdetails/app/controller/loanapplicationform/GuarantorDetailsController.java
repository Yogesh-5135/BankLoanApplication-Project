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

import com.cjc.customerdetails.app.model.loanapplicationform.DependentInforamtion;
import com.cjc.customerdetails.app.model.loanapplicationform.GuarantorDetails;
import com.cjc.customerdetails.app.servicei.loanapplicationform.GuarantorDetailsServiceI;

@RestController
public class GuarantorDetailsController 
{
  @Autowired
  GuarantorDetailsServiceI gds;
  
  
  @PostMapping("/saveGuarantor")
 	public ResponseEntity<GuarantorDetails> addGuarantor(@RequestBody GuarantorDetails s)
 	{
	  GuarantorDetails ss=gds.saveData(s);
 		return new ResponseEntity<GuarantorDetails> (ss,HttpStatus.CREATED);
 	}
 	
 		
 	@PutMapping("/editGuarantor/{GuarantorId}")
 	public ResponseEntity<String> updateGuarantor(@PathVariable int GuarantorId , @RequestBody GuarantorDetails c)
 	{
 		gds.editData(GuarantorId , c);
 		return new ResponseEntity<String>("Data Updated" , HttpStatus.OK);
 	}
 	
 	@GetMapping("/getGuarantor/{dependentInfoId}")
 	public ResponseEntity<GuarantorDetails> getSingleGuarantor(@PathVariable int GuarantorId )
 	{
 		GuarantorDetails cd = gds.getData(GuarantorId);
 		return new ResponseEntity<GuarantorDetails>(cd , HttpStatus.OK);
 	}


 	@DeleteMapping("/deleteGuarantor/{GuarantorId}")
 	public ResponseEntity<String> deleteSingleData(@PathVariable int GuarantorId)
 	{
 		gds.deleteData(GuarantorId);
 		return new ResponseEntity<String>( HttpStatus.NO_CONTENT);
 	}
 	
  
  
}