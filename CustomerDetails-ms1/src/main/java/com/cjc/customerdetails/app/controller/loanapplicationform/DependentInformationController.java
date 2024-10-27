package com.cjc.customerdetails.app.controller.loanapplicationform;

import java.util.List;

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

import com.cjc.customerdetails.app.model.Enquiry;
import com.cjc.customerdetails.app.model.loanapplicationform.DependentInforamtion;
import com.cjc.customerdetails.app.servicei.loanapplicationform.DependentInformationServiceI;

@RestController
public class DependentInformationController 
{
  @Autowired
  DependentInformationServiceI dis;
  
  
  @PostMapping("/saveDependent/{loanid}")
	public ResponseEntity<DependentInforamtion> addDependent(@RequestBody DependentInforamtion s,int loanid)
	{
	  DependentInforamtion ss=dis.saveData(s ,  loanid);
		return new ResponseEntity<DependentInforamtion> (ss,HttpStatus.CREATED);
	}
	
		
	@PutMapping("/editDependent/{dependentInfoId}")
	public ResponseEntity<String> update(@PathVariable int dependentInfoId , @RequestBody DependentInforamtion c)
	{
		dis.editData(dependentInfoId , c);
		return new ResponseEntity<String>("Data Updated" , HttpStatus.OK);
	}
	
	@GetMapping("/getDependent/{dependentInfoId}")
	public ResponseEntity<DependentInforamtion> getSingleDependent(@PathVariable int dependentInfoId )
	{
		DependentInforamtion cd = dis.getData(dependentInfoId);
		return new ResponseEntity<DependentInforamtion>(cd , HttpStatus.OK);
	}


	@DeleteMapping("/deleteDependent/{dependentInfoId}")
	public ResponseEntity<String> deleteSingleData(@PathVariable int dependentInfoId)
	{
		dis.deleteData(dependentInfoId);
		return new ResponseEntity<String>( HttpStatus.NO_CONTENT);
	}
	

}	