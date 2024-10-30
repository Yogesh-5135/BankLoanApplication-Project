package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.CustomerVerificationRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.CustomerVerificationI;

@Service
public class CustomerVerificationServiceImpl implements CustomerVerificationI
{
 @Autowired
 CustomerVerificationRepoI cvr;
}
