package com.cjc.customerdetails.app.servicei.loanapplicationform;

import com.cjc.customerdetails.app.model.loanapplicationform.AccountDetails;

public interface AccountDetailsServiceI {

	public AccountDetails saveAccountDetails(AccountDetails ad, int loanid);

	public AccountDetails getSingleAccountDetails(int accountId);

	public AccountDetails updateAccountDetails(int accountId, AccountDetails ad);

	public void deleteAccountDetails(int accountId);

}
