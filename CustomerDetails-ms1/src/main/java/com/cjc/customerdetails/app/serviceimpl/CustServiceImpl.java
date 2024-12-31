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
			e.setMobileno(l);
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
		String g = s.getName();
		char[]d=g.toCharArray();
		for(int i=0;i<d.length;i++)
		{
		if (d[i]>='a'&&d[i]<='z'||d[i]>='A'&&d[i]<='Z'||d[i]==32)
		{
		    e.setName(g);
		}
		else
		{
			log.error("Your Name does not contain any special character");
			throw new InvalidNameException("Please Enter Valid Name");
		}
		
		}
		String q = s.getUsername();
		char[] z = q.toCharArray();

		
		for (int i = 0; i < z.length; i++) {
		   
		    if ((z[i] >= 'a' && z[i] <= 'z') || (z[i] >= 'A' && z[i] <= 'Z') || (z[i] >= '0' && z[i] <= '9') || z[i] == 32) {
		      
		        e.setUsername(q);
		    } else {
		       
		        log.error("Your username contains invalid special characters");
		        throw new InvalidNameException("Please Enter a Valid Username");
		    }
		}

		String r = s.getPassword();
		char[] w = r.toCharArray();

		boolean hasAlpha = false;
		boolean hasDigit = false;
		boolean hasSpecialChar = false;

		for (int i = 0; i < w.length; i++) {
	
		    if (Character.isLetter(w[i])) {
		        hasAlpha = true;
		    }
		  
		    else if (Character.isDigit(w[i])) {
		        hasDigit = true;
		    }
	
		    else if (!Character.isLetterOrDigit(w[i])) {
		        hasSpecialChar = true;
		    }
		}
		if (hasAlpha && hasDigit && hasSpecialChar) {
		    e.setPassword(r);
		} else {
		    log.error("Password must contain at least one letter, one digit, and one special character.");
		    throw new InvalidNameException("Please Enter Valid Password. It must contain at least one letter, one digit, and one special character.");
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
			
			cd.setName(c.getName());
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
		public Enquiry getCustomer(String username, String password) {
			
			Enquiry cd = cri.findByUsernameAndPassword(username,password);
			
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
			    
			     if ("Approved for ApplyLoan".equals(status.trim())) 
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