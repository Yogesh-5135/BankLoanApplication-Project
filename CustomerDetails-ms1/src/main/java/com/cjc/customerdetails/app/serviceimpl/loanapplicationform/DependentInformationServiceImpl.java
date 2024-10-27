package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.DependentInformationRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.DependentInformationServiceI;

@Service
public class DependentInformationServiceImpl implements DependentInformationServiceI
{
 @Autowired
 DependentInformationRepoI dir;
}
