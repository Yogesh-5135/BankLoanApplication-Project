package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.CustomerAddressRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.CustomerAddressServiceI;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressServiceI
{
 @Autowired
 CustomerAddressRepoI car;
}
