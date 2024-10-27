package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.repoi.loanapplicationform.LoanApplyRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.LoanApplyServiceI;

@Service
public class LoanApplyServiceImpl implements LoanApplyServiceI{
	
	@Autowired
	LoanApplyRepoI lri;

	
	
}
