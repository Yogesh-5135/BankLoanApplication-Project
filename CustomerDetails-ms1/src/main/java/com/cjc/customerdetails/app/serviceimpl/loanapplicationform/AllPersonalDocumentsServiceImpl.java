package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.AllPersonalDocumentsRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.AllPersonalDocumentsServiceI;

@Service
public class AllPersonalDocumentsServiceImpl implements AllPersonalDocumentsServiceI
{
  @Autowired
  AllPersonalDocumentsRepoI apd;
}
