package com.cjc.ledger_loan_ms5.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.ledger_loan_ms5.servicei.LedgerServiceI;

@RestController
public class LedgerController 
{
  @Autowired
  LedgerServiceI lsi;
}
