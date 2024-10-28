package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.exception.InvalidDataException;
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
 
 private static final Logger log = LoggerFactory.getLogger(PermanentAddressServiceImpl.class);

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
	
	PermanentAddress pa = new PermanentAddress();
	
	String a1 = a.getAreaname();
	char[]b = a1.toCharArray();
	for(int i=0;i<b.length;i++)
	{
	if (b[i]>='a'&& b[i]<='z'|| b[i]>='A'&& b[i]<='Z'|| b[i]==32)
	{
		pa.setAreaname(a1); 	
	}
	else
	{
		throw new InvalidDataException("Areaname does not contain any special character or Number");
	}
	}
	
	String c = a.getCityname();
	char[]d = c.toCharArray();
	for(int i=0;i<d.length;i++)
	{
	if (d[i]>='a'&& d[i]<='z'|| d[i]>='A'&& d[i]<='Z'|| d[i]==32)
	{
		pa.setCityname(c); 	
	}
	else
	{
		throw new InvalidDataException("Cityname does not contain any special character or Number");
	}
	}
    
	String e = a.getDistrict();
	char[]f = e.toCharArray();
	for(int i=0;i<f.length;i++)
	{
	if (f[i]>='a'&& f[i]<='z'|| f[i]>='A'&& f[i]<='Z'|| f[i]==32)
	{
		pa.setDistrict(e); 	
	}
	else
	{
		throw new InvalidDataException("District Name does not contain any special character or Number");
	}
	}
    
	String g = a.getState();
	char[]h = g.toCharArray();
	for(int i=0;i<h.length;i++)
	{
	if (h[i]>='a'&& h[i]<='z'|| h[i]>='A'&& h[i]<='Z'|| h[i]==32)
	{
		pa.setState(g); 	
	}
	else
	{
		throw new InvalidDataException("State Name does not contain any special character or Number");
	}
	}
	
	long i = a.getPincode();
	int count = 0;
	for (long no = i; no > 0; no = no / 10) 
	{
		count++;
	}
	if (count > 6 || count<6) 
	{
		throw new InvalidDataException("Pincode Is Invalid ..Enter only 6 numbers");
	}
	else 
	{
		pa.setPincode(i);
	}
	
	int j = a.getHouseNumber();
	if (j <= 1 || j >= 99) 
	{ 
        throw new InvalidDataException("House number must be between 1 and 99.");
    }
	else
	{
		pa.setHouseNumber(j);
	}
	
	String k = a.getStreetName();
	char[]m = k.toCharArray();
	for(int i1=0;i1<m.length;i++)
	{
	if (m[i1]>='a'&& m[i1]<='z'|| m[i1]>='A'&& m[i1]<='Z'|| m[i1]==32)
	{
		pa.setStreetName(k); 	
	}
	else
	{
		throw new InvalidDataException("StreetName does not contain any special character or Number");
	}
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
