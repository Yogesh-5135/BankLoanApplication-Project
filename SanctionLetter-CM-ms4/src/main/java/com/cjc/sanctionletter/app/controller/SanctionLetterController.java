package com.cjc.sanctionletter.app.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
  
  @PutMapping("/generateCreditLimit")
  public ResponseEntity<String> getAllVerifiedData()
  {
	  String url = "http://localhost:9099/api/v3/getAllDocumentVerifiedList";  
	  
	  LoanApplication[] arr = rt.getForObject(url , LoanApplication[].class);
		
	  List<LoanApplication> l = Arrays.asList(arr);
		
	  
	  
	  
		
	  return null;
	  
  }
  
  
  
}
