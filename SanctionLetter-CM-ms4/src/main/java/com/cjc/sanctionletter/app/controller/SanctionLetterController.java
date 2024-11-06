package com.cjc.sanctionletter.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.sanctionletter.app.servicei.SanctionLetterI;

@RestController
public class SanctionLetterController 
{
  @Autowired
  SanctionLetterI sli;
}
