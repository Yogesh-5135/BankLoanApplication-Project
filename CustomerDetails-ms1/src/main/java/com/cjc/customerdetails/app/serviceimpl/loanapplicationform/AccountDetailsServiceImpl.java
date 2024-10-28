package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.exception.InvalidDataException;
import com.cjc.customerdetails.app.exception.MobNoException;
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
	
	AccountDetails ad1 = new AccountDetails();
	
	String a = ad.getAccounType();
	char[]b = a.toCharArray();
	for(int i=0;i<b.length;i++)
	{
	if (b[i]>='a'&& b[i]<='z'|| b[i]>='A'&& b[i]<='Z'|| b[i]==32)
	{
		ad1.setAccounType(a); 	
	}
	else
	{
		throw new InvalidDataException("Account type does not contain any special character or Number");
	}
	}
	
	double c = ad.getAccountBalance();
	
	if(c >= 1000 || c <= 100000)
	{
		ad1.setAccountBalance(c);
	}
	else
	{
		throw new InvalidDataException("Your Account Balance Ranges Between 1000 to 100000");
	}
	
	String d = ad.getAccountHolderName();
	char[]e = d.toCharArray();
	for(int i=0;i<e.length;i++)
	{
	if (e[i]>='a'&& e[i]<='z'|| e[i]>='A'&& e[i]<='Z'|| e[i]==32)
	{
		ad1.setAccountHolderName(d);
	}
	else
	{
		throw new InvalidDataException("AccountHolder Name does not contain any special character or number");
	}
	}
		
	String f = ad1.getAccountStatus();
	char[]g = f.toCharArray();
	for(int i=0;i<g.length;i++)
	{
	if (g[i]>='a'&& g[i]<='z'|| g[i]>='A'&& g[i]<='Z'|| g[i]==32)
	{
		ad1.setAccountStatus(f); 	
	}
	else
	{
		throw new InvalidDataException("Account status does not contain any special character or Number");
	}
	}

	long h = ad1.getAccountNumber();
	int count = 0;
	for (long no = h; no > 0; no = no / 10) 
	{
		count++;
	}
	if (count > 12 || count<12) 
	{
		throw new InvalidDataException("Account No Is Invalid ..Enter only 12 numbers");
	}
	else 
	{
		ad1.setAccountNumber(h);
	}
	
	adr.save(ad1);
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
