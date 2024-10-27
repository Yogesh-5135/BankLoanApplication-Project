package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.GuarantorDetailsRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.GuarantorDetailsServiceI;

@Service
public class GuarantorDetailsServiceImpl implements GuarantorDetailsServiceI
{
  @Autowired
  GuarantorDetailsRepoI gdr;
}
