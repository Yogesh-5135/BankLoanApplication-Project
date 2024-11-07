package com.cjc.ledger_loan_ms5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.ledger_loan_ms5.servicei.LedgerServiceI;

@RestController
public class LedgerController {
	@Autowired
	LedgerServiceI lsi;

	@PutMapping("updateLedger/{loanid}")
	public ResponseEntity<String> updateLedger(@PathVariable int loanid) 
	{
		lsi.updateLedger(loanid);
		return new ResponseEntity<String>("Updated", HttpStatus.OK);
	}

}
