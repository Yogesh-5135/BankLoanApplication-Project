package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.LoanDisbursementRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.LoanDisbursementI;

@Service
public class LoanDisbursementServiceImpl implements LoanDisbursementI
{
 @Autowired
 LoanDisbursementRepoI ldr;
}
