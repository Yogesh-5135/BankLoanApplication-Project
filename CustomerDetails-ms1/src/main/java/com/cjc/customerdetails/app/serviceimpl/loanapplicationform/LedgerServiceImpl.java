package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.LedgerRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.LedgerServiceI;

@Service
public class LedgerServiceImpl implements LedgerServiceI
{
 @Autowired
 LedgerRepoI lri;
}
