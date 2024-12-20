package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.exception.InvalidDataException;
import com.cjc.customerdetails.app.model.loanapplicationform.AccountDetails;
import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;
import com.cjc.customerdetails.app.repoi.loanapplicationform.AccountDetailsRepoI;
import com.cjc.customerdetails.app.repoi.loanapplicationform.LoanApplyRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.AccountDetailsServiceI;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsServiceI {
	@Autowired
	AccountDetailsRepoI adr;

	@Autowired
	LoanApplyRepoI lri;

	private static final Logger log = LoggerFactory.getLogger(AccountDetailsServiceImpl.class);

	@Override
	public AccountDetails saveAccountDetails(AccountDetails ad, int loanid) {
		
		LoanApplication l = null;

		Optional<LoanApplication> oa = lri.findById(loanid);
		if (oa.isPresent()) {
			l = oa.get();
		} else {
			log.error("Please Enter Valid Id");
			throw new IdNotFountException("Id Not Found");
		}

		AccountDetails ad1 = new AccountDetails();

		String a = ad.getAccountType();
		char[] b = a.toCharArray();
		for (int i = 0; i < b.length; i++) {
			if (b[i] >= 'a' && b[i] <= 'z' || b[i] >= 'A' && b[i] <= 'Z')
			{
				ad1.setAccountType(a);
			} else {
				log.error("Please Use Letters Only");
				throw new InvalidDataException("Account type does not contain any special character or Number");
			}
		}

		double c = ad.getAccountBalance();

		if (c >= 1000 || c <= 100000) {
			ad1.setAccountBalance(c);
		} else {
			log.error("please use Numbers Only");
			throw new InvalidDataException("Your Account Balance Ranges Between 1000 to 100000");
		}

		String d = ad.getAccountHolderName();
		char[] e = d.toCharArray();
		for (int i = 0; i < e.length; i++) {
			if (e[i] >= 'a' && e[i] <= 'z' || e[i] >= 'A' && e[i] <= 'Z' || e[i] == 32) {
				ad1.setAccountHolderName(d);
			} else {
				log.error("Please Use Letters Only");
				throw new InvalidDataException("AccountHolder Name does not contain any special character or number");
			}
		}

		String f = ad.getAccountStatus();
		char[] g = f.toCharArray();
		for (int i = 0; i < g.length; i++) {
			if (g[i] >= 'a' && g[i] <= 'z' || g[i] >= 'A' && g[i] <= 'Z' || g[i] == 32) {
				ad1.setAccountStatus(f);
			} else {
				log.error("Please Use Letters Only");
				throw new InvalidDataException("Account status does not contain any special character or Number");
			}
		}

		long h = ad.getAccountNumber();
		int count = 0;
		for (long no = h; no > 0; no = no / 10) {
			count++;
		}
		if (count > 12 || count < 12) {
			log.error("Please Use Numbers Only");
			throw new InvalidDataException("Account No Is Invalid ..Enter only 12 numbers");
		} else {
			ad1.setAccountNumber(h);
		}

		adr.save(ad1);
		l.setAccountdetails(ad1);
		lri.save(l);

		return ad1;
	}

	@Override
	public AccountDetails getSingleAccountDetails(int accountId) {
		Optional<AccountDetails> oa = adr.findById(accountId);
		if (oa.isPresent()) {
			AccountDetails a = oa.get();

			return a;
		} else {
			log.error("Please Enter Valid Id");
			throw new IdNotFountException("AccountDetails Not Found");
		}

	}

	@Override
	public AccountDetails updateAccountDetails(int accountId, AccountDetails ad) {
		Optional<AccountDetails> oa = adr.findById(accountId);
		if (oa.isPresent()) {
			AccountDetails a = oa.get();
			a.setAccounType(ad.getAccounType());
			a.setAccountBalance(ad.getAccountBalance());
			a.setAccountHolderName(ad.getAccountHolderName());
			a.setAccountStatus(ad.getAccountStatus());
			a.setAccountNumber(ad.getAccountNumber());

			adr.save(a);

			return a;
		} else {
			log.error("Please Enter Valid Id");
			throw new IdNotFountException("AccountDetails Not Found");
		}

	}

	@Override
	public void deleteAccountDetails(int accountId) {

		adr.deleteById(accountId);
	}
}
