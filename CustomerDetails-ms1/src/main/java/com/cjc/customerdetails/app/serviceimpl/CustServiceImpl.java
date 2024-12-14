package com.cjc.customerdetails.app.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cjc.customerdetails.app.exception.AgeException;
import com.cjc.customerdetails.app.exception.EmailNotValidException;
import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.exception.InvalidNameException;
import com.cjc.customerdetails.app.exception.MobNoException;
import com.cjc.customerdetails.app.exception.PanCardException;
import com.cjc.customerdetails.app.model.Enquiry;
import com.cjc.customerdetails.app.repoi.CustRepoI;
import com.cjc.customerdetails.app.servicei.CustServiceI;
@Service
public class CustServiceImpl implements CustServiceI{

	@Autowired
	CustRepoI cri;
	
	private static final Logger log = LoggerFactory.getLogger(CustServiceImpl.class);
	
	@Override
	public Enquiry saveCustomer(Enquiry s) {
		
		Enquiry e = new Enquiry();
		
		long l = s.getMobileno();
		int count = 0;
		for (long no = l; no > 0; no = no / 10) 
		{
			count++;
		}
		if (count > 10 || count<10) 
		{
			log.error("please Enter valid Mobile No");
			throw new MobNoException("Mobno invalid ..Enter only 10 numbers");
		}else 
		{
			e.setMobileno(s.getMobileno());
		}
		
		
		if(s.getEmail().endsWith("@gmail.com") || s.getEmail().endsWith("@yahoo.com")) {
			e.setEmail(s.getEmail());
		}else {
			log.error("please Enter valid Email Id");
			throw new EmailNotValidException(" Email should end with @gmail.com or @yahoo.com ");
		}
		
		int f = s.getAge();
		if(f >18) {	
			e.setAge(f);
		}else
		{
			log.error("Required Age is 18");
			throw new AgeException("Enter age above 18");
		}
		String g = s.getUsername();
		char[]d=g.toCharArray();
		for(int i=0;i<d.length;i++)
		{
		if (d[i]>='a'&&d[i]<='z'||d[i]>='A'&&d[i]<='Z'||d[i]==32)
		{
		    e.setUsername(g);
		}
		else
		{
			log.error("Your Fname does not contain any special character");
			throw new InvalidNameException("Please Enter Valid Name");
		}
		
		}
		
		String r = s.getPassword();
		char[]w=r.toCharArray();
		for(int i=0;i<d.length;i++)
		{
		if (d[i]>='a'&&d[i]<='z'||d[i]>='A'&&d[i]<='Z'||d[i]==32)
		{
		    e.setPassword(r);	
		}
		else
		{
			log.error("Your Lname does not contain any special character");
			throw new InvalidNameException("Please Enter Valid Name");
		}
		
		}
		
		
		
		String pancard = s.getPancard();
		boolean isValid = true;

		for (int i = 0; i < pancard.length(); i++) {
			
		    char ch = pancard.charAt(i);
		    
		    if (i < 5) {
		    	
		        if (ch < 'A' || ch > 'Z') {
		            isValid = false;
		            break;
		        }
		    } else if (i < 9) {
		        
		        if (ch < '0' || ch > '9') {
		            isValid = false;
		            break;
		        }
		    } else {
		    	
		        if (ch < 'A' || ch > 'Z') {
		            isValid = false;
		            break;
		        }
		    }
		}

		if (isValid) {
		    e.setPancard(pancard);
		} else {
			log.error("Your Pan no contain Capital Letters And digit Only");
		    throw new PanCardException("Invalid PAN card number. Please enter a valid PAN card.");
		}

		
		e.setEnquiryStatus("Pending");		
		
		cri.save(e);
		return e;
	}


	@Override
	public void editCustomer(int customerid, Enquiry c)
	{		
		Optional<Enquiry> o = cri.findById(customerid);
		if(o.isPresent()) {
			Enquiry cd = o.get();
			
			cd.setUsername(c.getUsername());
			cd.setPassword(c.getPassword());
			cd.setEmail(c.getEmail());
			cd.setAge(c.getAge());
			cd.setMobileno(c.getMobileno());
			cd.setPancard(c.getPancard());
			
			cri.save(cd);			
		}	
	}

		@Override
		public Enquiry getData(int customerid) 
		{		
			Optional<Enquiry> o = cri.findById(customerid);
			if(o.isPresent()) {
				Enquiry cd = o.get();
				return cd;
			}
			else
			{
				throw new IdNotFountException("Id not found");
			}
		}


		@Override
		public List<Enquiry> getAllData() 
		{
			List<Enquiry> cd = cri.findAll();
			return cd;			
		}


		@Override
		public void deleteData(int customerid) 
		{			
			cri.deleteById(customerid);
		}


		@Override
		public void deleteAllCustomer() 
		{
			cri.deleteAll();
		}


		@Override
		public List<Enquiry> getAllApprovedData() 
		{
			
			List<Enquiry> en = cri.findAll();
			
			List<Enquiry> l= new ArrayList<Enquiry>();
			
			for (Enquiry e : en) 
			{
				 String status = e.getEnquiryStatus();
			    
			     if ("Approved".equals(status.trim())) 
			     {
			            l.add(e);
			            System.out.println("Approved enquiry: " + e);
			     }
			}
			
			System.out.println("List"+l);
			return l;
		}


		@Override
		public List<Enquiry> getAllRejectedData() 
		{
			List<Enquiry> en = cri.findAll();
			
			List<Enquiry> l= new ArrayList<Enquiry>();
			
			for (Enquiry e : en) 
			{
				 String status = e.getEnquiryStatus();
				 
			     if ("Rejected".equals(status.trim())) 
			     {
			            l.add(e);
			            System.out.println("Approved enquiry: " + e);
			     }
			}
			
			System.out.println("List"+l);
			return l;

		}

}	