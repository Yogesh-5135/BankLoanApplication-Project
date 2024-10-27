package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.model.loanapplicationform.CustomerAddress;
import com.cjc.customerdetails.app.model.loanapplicationform.PermanentAddress;
import com.cjc.customerdetails.app.repoi.loanapplicationform.CustomerAddressRepoI;
import com.cjc.customerdetails.app.repoi.loanapplicationform.PermanentAddressRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.PermanentAddressServiceI;

@Service
public class PermanentAddressServiceImpl implements PermanentAddressServiceI
{
 @Autowired
 PermanentAddressRepoI par;
 
 @Autowired
 CustomerAddressRepoI car;

@Override
public PermanentAddress savePermanentAddress(PermanentAddress a,int customerAddressId) 
{
	CustomerAddress l = null;
	
	Optional<CustomerAddress> ol = car.findById(customerAddressId);
	if(ol.isPresent())
	{
		 l = ol.get();
	}
	else
	{
		throw new IdNotFountException("Id Not Found");
	}
	
	PermanentAddress pa = null;
	
	try {
		pa.setAreaname(a.getAreaname());
		pa.setCityname(a.getCityname());
		pa.setDistrict(a.getDistrict());
		pa.setState(a.getState());
		pa.setPincode(a.getPincode());
		pa.setHouseNumber(a.getHouseNumber());
		pa.setStreetName(a.getStreetName());
		
	    } 
	 catch (Exception e) 
	    {
		  e.printStackTrace();
	    }
	par.save(pa);
	l.setPermanentAddress(pa);
	car.save(l);
	
	return pa;
}

@Override
public PermanentAddress getSinglePermanentAddress(int permanentAddressId) 
{
	Optional<PermanentAddress> op = par.findById(permanentAddressId);
	if(op.isPresent())
	{
		PermanentAddress a = op.get();
		
		return a;
	}
	else
	{
		throw new IdNotFountException("Permanent Address Not Found");
	}
}

@Override
public PermanentAddress updatePermanentAddress(int permanentAddressId, PermanentAddress a) 
{
	Optional<PermanentAddress> op = par.findById(permanentAddressId);
	if(op.isPresent())
	{
		PermanentAddress a1 = op.get();
		a1.setAreaname(a.getAreaname());
		a1.setCityname(a.getCityname());
		a1.setDistrict(a.getDistrict());
		a1.setState(a.getState());
		a1.setPincode(a.getPincode());
		a1.setHouseNumber(a.getHouseNumber());
		a1.setStreetName(a.getStreetName());
		
		par.save(a1);
	
		return a1;
	}
	else
	{
		throw new IdNotFountException("Permanent Address Not Found");
	}
}

@Override
public void deletePermanentAddress(int permanentAddressId) 
{
	par.deleteById(permanentAddressId);
}
}
