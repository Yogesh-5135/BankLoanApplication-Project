package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.exception.InvalidDataException;
import com.cjc.customerdetails.app.model.loanapplicationform.CustomerAddress;
import com.cjc.customerdetails.app.model.loanapplicationform.LocalAddress;
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
 public void savePermanentAddress(PermanentAddress a, int customerAddressId) {
     
     
     CustomerAddress ca = new CustomerAddress();
     Optional<CustomerAddress> ol = car.findById(customerAddressId);
     if (ol.isPresent()) {
         ca = ol.get();
        
     } else {
         log.error("CustomerAddress not found with id: {}", customerAddressId);
         throw new IdNotFountException("Id Not Found");
     }

     PermanentAddress pa = new PermanentAddress();
     
     
     log.info("Validating area name: {}", a.getAreaname());
     String a1 = a.getAreaname();
     char[] b = a1.toCharArray();
     for (int i = 0; i < b.length; i++) {
         if (Character.isLetter(b[i]) || b[i] == 32) {
             pa.setAreaname(a1);
         } else {
             log.error("Invalid character in area name: {}", a1);
             throw new InvalidDataException("Areaname does not contain any special character or Number");
         }
     }

     
     String c = a.getCityname();
     char[] d = c.toCharArray();
     for (int i = 0; i < d.length; i++) {
         if (Character.isLetter(d[i]) || d[i] == 32) {
             pa.setCityname(c);
         } else {
             log.error("Invalid character in city name: {}", c);
             throw new InvalidDataException("Cityname does not contain any special character or Number");
         }
     }

     
     String e = a.getDistrict();
     char[] f = e.toCharArray();
     for (int i = 0; i < f.length; i++) {
         if (Character.isLetter(f[i]) || f[i] == 32) {
             pa.setDistrict(e);
         } else {
             log.error("Invalid character in district name: {}", e);
             throw new InvalidDataException("District Name does not contain any special character or Number");
         }
     }

     
     String g = a.getState();
     char[] h = g.toCharArray();
     for (int i = 0; i < h.length; i++) {
         if (Character.isLetter(h[i]) || h[i] == 32) {
             pa.setState(g);
         } else {
             log.error("Invalid character in state name: {}", g);
             throw new InvalidDataException("State Name does not contain any special character or Number");
         }
     }

     
     long i = a.getPincode();
     int count = 0;
     for (long no = i; no > 0; no = no / 10) {
         count++;
     }
     if (count != 6) {
         log.error("Invalid pincode: {}", i);
         throw new InvalidDataException("Pincode is invalid. It must be 6 digits.");
     } else {
         pa.setPincode(i);
     }

    
     int j = a.getHouseNumber();
     if (j <= 1 || j >= 99) {
         log.error("Invalid house number: {}", j);
         throw new InvalidDataException("House number must be between 1 and 99.");
     } else {
         pa.setHouseNumber(j);
     }

    
     String k = a.getStreetName();
     char[] m = k.toCharArray();
     for (int i1 = 0; i1 < m.length; i1++) {
         if (Character.isLetter(m[i1]) || m[i1] == 32) {
             pa.setStreetName(k);
         } else {
             log.error("Invalid character in street name: {}", k);
             throw new InvalidDataException("StreetName does not contain any special character or Number");
         }
     }

   
     par.save(pa);
     ca.setPermanentAddress(pa);
     car.save(ca);

    
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
		log.error("Please Enter Valid Id");
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
		log.error("Please Enter Valid Id");
		throw new IdNotFountException("Permanent Address Not Found");
	}
}

@Override
public void deletePermanentAddress(int permanentAddressId) 
{
	par.deleteById(permanentAddressId);
}
}
