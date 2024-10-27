package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.PermanentAddressRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.PermanentAddressServiceI;

@Service
public class PermanentAddressServiceImpl implements PermanentAddressServiceI
{
 @Autowired
 PermanentAddressRepoI par;
}
