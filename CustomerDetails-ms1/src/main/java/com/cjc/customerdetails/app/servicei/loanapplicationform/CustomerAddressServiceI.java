package com.cjc.customerdetails.app.servicei.loanapplicationform;

import com.cjc.customerdetails.app.model.loanapplicationform.CustomerAddress;

public interface CustomerAddressServiceI {

	public CustomerAddress saveCustomerAddress(int loanid);

	public CustomerAddress getSingleCustomerAddress(int customerAddressId);

	public CustomerAddress updateCustomerAddress(int customerAddressId, CustomerAddress ca);

	public void deleteCustomerAddress(int customerAddressId);

}
