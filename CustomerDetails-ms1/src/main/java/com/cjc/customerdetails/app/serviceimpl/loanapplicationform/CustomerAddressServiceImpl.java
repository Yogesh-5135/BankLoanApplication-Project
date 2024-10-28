package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.model.loanapplicationform.CustomerAddress;
import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;
import com.cjc.customerdetails.app.model.loanapplicationform.LocalAddress;
import com.cjc.customerdetails.app.model.loanapplicationform.PermanentAddress;
import com.cjc.customerdetails.app.repoi.loanapplicationform.CustomerAddressRepoI;
import com.cjc.customerdetails.app.repoi.loanapplicationform.LoanApplyRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.CustomerAddressServiceI;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressServiceI
{
 @Autowired
 CustomerAddressRepoI car;

 @Autowired
 LoanApplyRepoI lri;
 
 private static final Logger log = LoggerFactory.getLogger(CustomerAddressServiceImpl.class);
 
@Override
public CustomerAddress saveCustomerAddress(int loanid) 
{
	LoanApplication l = null;
	CustomerAddress ca = new CustomerAddress();
	
	Optional<LoanApplication> ol = lri.findById(loanid);
	if(ol.isPresent())
	{
		 l = ol.get();
	}
	else
	{
		log.error("Please Enter Valid Id");
		throw new IdNotFountException("Id Not Found");
	}
	
	car.save(ca);
	l.setCustomerAddress(ca);
	lri.save(l);
	
	return ca;
}

@Override
public CustomerAddress getSingleCustomerAddress(int customerAddressId) 
{
	Optional<CustomerAddress> oc = car.findById(customerAddressId);
	if(oc.isPresent())
	{
		CustomerAddress ca = oc.get();
		
		return ca;
	}
	else
	{
		log.error("Please Enter Valid Id");
		throw new IdNotFountException("Customer Address Not Found");
	}
	
}

@Override
public CustomerAddress updateCustomerAddress(int customerAddressId, CustomerAddress ca) 
{
	Optional<CustomerAddress> oc = car.findById(customerAddressId);
	if(oc.isPresent())
	{
		CustomerAddress c = oc.get();
		c.setLocalAddress(ca.getLocalAddress());
		c.setPermanentAddress(ca.getPermanentAddress());
		car.save(c);
		
		return c;
	}
	else
	{
		log.error("Please Enter Valid Id");
		throw new IdNotFountException("Customer Address Not Found");
	}
	
}

@Override
public void deleteCustomerAddress(int customerAddressId) 
{
	car.deleteById(customerAddressId);
	
}
}
