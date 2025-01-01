package com.cjc.ledger_loan_ms5.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.ledger_loan_ms5.model.Ledger;
import com.cjc.ledger_loan_ms5.servicei.LedgerServiceI;

@RestController
@RequestMapping("/api/v4")
public class LedgerController {
	@Autowired
	LedgerServiceI lsi;

	@PutMapping("updateLedger/{loanid}")
	public ResponseEntity<List<Ledger>> updateLedger(@PathVariable int loanid) 
	{
		List<Ledger> l = lsi.updateLedger(loanid);
		return new ResponseEntity<List<Ledger>>(l, HttpStatus.OK);
	}
	
	 @PutMapping("updateLoanStatus/{loanid}/{ledgerId}")
	    public ResponseEntity<List<Ledger>> updateLoanStatus(@PathVariable int loanid,@PathVariable int ledgerId) {
	        List<Ledger> updatedLedgerList = lsi.updateLoanStatus(loanid,ledgerId);
	        return new ResponseEntity<>(updatedLedgerList, HttpStatus.OK);
	    }
    
	 @GetMapping("getLedger/{loanid}")
	 public ResponseEntity<List<Ledger>> getLedger(@PathVariable int loanid) 
		{
			List<Ledger> l = lsi.getLedger(loanid);
			
			return new ResponseEntity<List<Ledger>>(l, HttpStatus.OK);
		}
	 
	 @GetMapping("getLedgerOnlyEmiPaid/{loanid}")
	 public ResponseEntity<List<Ledger>> getLedgerOnlyEmiPaid(@PathVariable int loanid) 
		{
			List<Ledger> l = lsi.getLedgerOnlyEmiPaid(loanid);
			
			return new ResponseEntity<List<Ledger>>(l, HttpStatus.OK);
		}
}
