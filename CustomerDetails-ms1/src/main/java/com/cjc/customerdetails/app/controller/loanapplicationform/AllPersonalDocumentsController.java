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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.customerdetails.app.model.Enquiry;
import com.cjc.customerdetails.app.model.loanapplicationform.AllPersonalDocuments;
import com.cjc.customerdetails.app.servicei.loanapplicationform.AllPersonalDocumentsServiceI;

@RestController
public class AllPersonalDocumentsController 
{

	@Autowired
	AllPersonalDocumentsServiceI apd;
	
	@PostMapping("/saveDocuments/{loanid}")
	public ResponseEntity<String> addDocument(@RequestPart ("ap") MultipartFile addressProof ,
				@RequestPart ("pc") MultipartFile panCard ,@RequestPart ("it") MultipartFile IncomeTax,
				@RequestPart ("ac") MultipartFile addharCard,@RequestPart ("ph") MultipartFile photo,
				@RequestPart ("sig") MultipartFile signature, @RequestPart ("bc") MultipartFile bankCheque,
				@RequestPart ("ss") MultipartFile salarySlips , int loanid)	
	{
		
		apd.saveDocument(addressProof,panCard,IncomeTax,addharCard,photo,signature,bankCheque,salarySlips,loanid);
		return new ResponseEntity<String> ("Data Added",HttpStatus.CREATED);
	}
	
		
	@PutMapping("/editDocuments/{loanid}/{documentID}")
	public ResponseEntity<String> update(@PathVariable int documentID ,int loanid ,@RequestPart ("ap") MultipartFile addressProof )
	{
		apd.editDocument(documentID  ,loanid ,addressProof);
		return new ResponseEntity<String>("Data Updated" , HttpStatus.OK);
	}
	
	@GetMapping("/getDocument/{documentID}")
	public ResponseEntity<AllPersonalDocuments> getSingleCustomer(@PathVariable int documentID )
	{
		AllPersonalDocuments cd = apd.getDocument(documentID);
		return new ResponseEntity<AllPersonalDocuments>(cd , HttpStatus.OK);
	}
	

	@DeleteMapping("/deleteDocument/{documentID}")
	public ResponseEntity<String> deleteSingleData(@PathVariable int documentID )
	{
		apd.deleteDocument(documentID);
		return new ResponseEntity<String>( HttpStatus.NO_CONTENT);
	}
	
	
	
}
