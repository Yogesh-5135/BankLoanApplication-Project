package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.LocalAddressRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.LocalAddressServiceI;

@Service
public class LocalAddressServiceImpl implements LocalAddressServiceI
{
  @Autowired
  LocalAddressRepoI lar;
}
