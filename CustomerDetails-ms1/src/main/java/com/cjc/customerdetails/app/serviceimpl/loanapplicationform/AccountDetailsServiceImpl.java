package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.AccountDetailsRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.AccountDetailsServiceI;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsServiceI 
{
  @Autowired
  AccountDetailsRepoI adr;
}
