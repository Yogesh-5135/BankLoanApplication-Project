package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.model.loanapplicationform.AccountDetails;
import com.cjc.customerdetails.app.model.loanapplicationform.CustomerAddress;
import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;
import com.cjc.customerdetails.app.repoi.loanapplicationform.AccountDetailsRepoI;
import com.cjc.customerdetails.app.repoi.loanapplicationform.LoanApplyRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.AccountDetailsServiceI;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsServiceI 
{
  @Autowired
  AccountDetailsRepoI adr;

 @Autowired
 LoanApplyRepoI lri;
  
@Override
public AccountDetails saveAccountDetails(AccountDetails ad, int loanid) 
{
	LoanApplication l = null;
	
	Optional<LoanApplication> oa = lri.findById(loanid);
	if(oa.isPresent())
	{
		l = oa.get();
	}
	else
	{
		throw new IdNotFountException("Id Not Found");
	}
	
	AccountDetails ad1 = null;
	
	try {
		ad1.setAccounType(ad.getAccounType());
		ad1.setAccountBalance(ad.getAccountBalance());
		ad1.setAccountHolderName(ad.getAccountHolderName());
		ad1.setAccountStatus(ad.getAccountStatus());
		ad1.setAccountNumber(ad.getAccountNumber());
		
	} 
	catch (Exception e)
	{
	    e.printStackTrace();	
	}
	l.setAccountdetails(ad1);
	lri.save(l);
	
	return ad1;
}

@Override
public AccountDetails getSingleAccountDetails(int accountId) 
{
	Optional<AccountDetails> oa = adr.findById(accountId);
	if(oa.isPresent())
	{
		AccountDetails a = oa.get();
		
		return a;
	}
	else
	{
		throw new IdNotFountException("AccountDetails Not Found");
	}
	
	
}

@Override
public AccountDetails updateAccountDetails(int accountId,AccountDetails ad) 
{
	Optional<AccountDetails> oa = adr.findById(accountId);
	if(oa.isPresent())
	{
		AccountDetails a = oa.get();
		a.setAccounType(ad.getAccounType());
		a.setAccountBalance(ad.getAccountBalance());
		a.setAccountHolderName(ad.getAccountHolderName());
		a.setAccountStatus(ad.getAccountStatus());
        a.setAccountNumber(ad.getAccountNumber());
        
        adr.save(a);
        
		return a;
	}
	else
	{
		throw new IdNotFountException("AccountDetails Not Found");
	}
	
	
}

@Override
public void deleteAccountDetails(int accountId) 
{
	
	adr.deleteById(accountId);
}
}
