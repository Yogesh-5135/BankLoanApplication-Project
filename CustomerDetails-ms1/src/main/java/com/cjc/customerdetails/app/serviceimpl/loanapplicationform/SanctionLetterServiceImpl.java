package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.SanctionLetterRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.SanctionLetterI;

@Service
public class SanctionLetterServiceImpl implements SanctionLetterI
{
 @Autowired 
 SanctionLetterRepoI slr;
}
