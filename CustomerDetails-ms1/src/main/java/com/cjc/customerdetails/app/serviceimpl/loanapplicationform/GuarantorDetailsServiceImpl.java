package com.cjc.customerdetails.app.serviceimpl.loanapplicationform;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.customerdetails.app.exception.IdNotFountException;
import com.cjc.customerdetails.app.exception.InvalidDataException;
import com.cjc.customerdetails.app.model.loanapplicationform.DependentInforamtion;
import com.cjc.customerdetails.app.model.loanapplicationform.GuarantorDetails;
import com.cjc.customerdetails.app.model.loanapplicationform.LoanApplication;
import com.cjc.customerdetails.app.repoi.loanapplicationform.GuarantorDetailsRepoI;
import com.cjc.customerdetails.app.repoi.loanapplicationform.LoanApplyRepoI;
import com.cjc.customerdetails.app.servicei.loanapplicationform.GuarantorDetailsServiceI;

@Service
public class GuarantorDetailsServiceImpl implements GuarantorDetailsServiceI
{
  @Autowired
  GuarantorDetailsRepoI gdr;
  @Autowired
  LoanApplyRepoI lri;
  
  private static final Logger log = LoggerFactory.getLogger(GuarantorDetailsServiceImpl.class);
  
	@Override
	public GuarantorDetails saveData(GuarantorDetails s ,int loanid) 
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
		
		GuarantorDetails di = new GuarantorDetails();
		
		
	
		String guarantorName = s.getGuarantorName();
		boolean validGuarantorName = true;
		for (char c : guarantorName.toCharArray()) {
		    if (!(Character.isLetter(c) || Character.isWhitespace(c))) {
		        validGuarantorName = false;
		        break;
		    }
		}
		if (validGuarantorName) {
		    di.setGuarantorName(guarantorName);
		} else {
		    log.error("Invalid guarantor name");
		    throw new InvalidDataException("Guarantor name should only contain letters and spaces");
		}

		
		String guarantorDateOfBirth = s.getGuarantorDateOfBirth();
		if (guarantorDateOfBirth != null && !guarantorDateOfBirth.isEmpty() && guarantorDateOfBirth.length() == 10) {
		    // Check if the date follows "YYYY-MM-DD" format
		    if (Character.isDigit(guarantorDateOfBirth.charAt(0)) &&
		        Character.isDigit(guarantorDateOfBirth.charAt(1)) &&
		        Character.isDigit(guarantorDateOfBirth.charAt(2)) &&
		        Character.isDigit(guarantorDateOfBirth.charAt(3)) &&
		        guarantorDateOfBirth.charAt(4) == '-' &&
		        Character.isDigit(guarantorDateOfBirth.charAt(5)) &&
		        Character.isDigit(guarantorDateOfBirth.charAt(6)) &&
		        guarantorDateOfBirth.charAt(7) == '-' &&
		        Character.isDigit(guarantorDateOfBirth.charAt(8)) &&
		        Character.isDigit(guarantorDateOfBirth.charAt(9))) {
		        
		        di.setGuarantorDateOfBirth(guarantorDateOfBirth);
		    } else {
		        log.error("Invalid date of birth format");
		        throw new InvalidDataException("Guarantor date of birth must be in the format YYYY-MM-DD");
		    }
		} else {
		    log.error("Date of birth cannot be null or empty");
		    throw new InvalidDataException("Guarantor date of birth cannot be null or empty");
		}

	
		String guarantorRelationship = s.getGuarantorRelationshipwithCustomer();
		boolean validRelationship = true;
		for (char c : guarantorRelationship.toCharArray()) {
		    if (!(Character.isLetter(c) || Character.isWhitespace(c))) {
		        validRelationship = false;
		        break;
		    }
		}
		if (validRelationship) {
		    di.setGuarantorRelationshipwithCustomer(guarantorRelationship);
		} else {
		    log.error("Invalid relationship with customer");
		    throw new InvalidDataException("Guarantor relationship with customer should only contain letters and spaces");
		}

		
		long guarantorMobileNumber = s.getGuarantorMobileNumber();
		if (String.valueOf(guarantorMobileNumber).length() == 10) {
		    di.setGuarantorMobileNumber(guarantorMobileNumber);
		} else {
		    log.error("Invalid mobile number");
		    throw new InvalidDataException("Guarantor mobile number must be a 10-digit number");
		}

		
		long guarantorAadharCardNo = s.getGuarantorAdharCardNo();
		if (String.valueOf(guarantorAadharCardNo).length() == 12) {
		    di.setGuarantorAdharCardNo(guarantorAadharCardNo);
		} else {
		    log.error("Invalid Aadhar card number");
		    throw new InvalidDataException("Guarantor Aadhar card number must be a 12-digit number");
		}

	
		String guarantorMortgageDetails = s.getGuarantorMortgageDetails();
		boolean validMortgageDetails = true;
		for (char c : guarantorMortgageDetails.toCharArray()) {
		    if (!(Character.isLetter(c) || Character.isDigit(c) || Character.isWhitespace(c) || c == ',' || c == '.' || c == '-')) {
		        validMortgageDetails = false;
		        break;
		    }
		}
		if (validMortgageDetails) {
		    di.setGuarantorMortgageDetails(guarantorMortgageDetails);
		} else {
		    log.error("Invalid mortgage details");
		    throw new InvalidDataException("Guarantor mortgage details contain invalid characters");
		}

		
		String guarantorJobDetails = s.getGuarantorJobDetails();
		boolean validJobDetails = true;
		for (char c : guarantorJobDetails.toCharArray()) {
		    if (!(Character.isLetter(c) || Character.isDigit(c) || Character.isWhitespace(c))) {
		        validJobDetails = false;
		        break;
		    }
		}
		if (validJobDetails) {
		    di.setGuarantorJobDetails(guarantorJobDetails);
		} else {
		    log.error("Invalid job details");
		    throw new InvalidDataException("Guarantor job details should only contain letters, numbers, and spaces");
		}

		
		String guarantorLocalAddress = s.getGuarantorLoaclAddress();
		boolean validLocalAddress = true;
		for (char c : guarantorLocalAddress.toCharArray()) {
		    if (!(Character.isLetter(c) || Character.isDigit(c) || Character.isWhitespace(c) || c == ',' || c == '.' || c == '-')) {
		        validLocalAddress = false;
		        break;
		    }
		}
		if (validLocalAddress) {
		    di.setGuarantorLoaclAddress(guarantorLocalAddress);
		} else {
		    log.error("Invalid local address");
		    throw new InvalidDataException("Guarantor local address contains invalid characters");
		}

		
		String guarantorPermanentAddress = s.getGuarantorPermanentAddress();
		boolean validPermanentAddress = true;
		for (char c : guarantorPermanentAddress.toCharArray()) {
		    if (!(Character.isLetter(c) || Character.isDigit(c) || Character.isWhitespace(c) || c == ',' || c == '.' || c == '-')) {
		        validPermanentAddress = false;
		        break;
		    }
		}
		if (validPermanentAddress) {
		    di.setGuarantorPermanentAddress(guarantorPermanentAddress);
		} else {
		    log.error("Invalid permanent address");
		    throw new InvalidDataException("Guarantor permanent address contains invalid characters");
		}

		
		
		gdr.save(di);
		l.setGurantordetails(di);
		lri.save(l);
		
		
		return di;
	}
	
	@Override
	public void editData(int GuarantorId, GuarantorDetails c) 		
	{
		Optional<GuarantorDetails> o = gdr.findById(GuarantorId);
		if(o.isPresent()) {
			GuarantorDetails cd = o.get();
			
			cd.setGuarantorAdharCardNo(c.getGuarantorAdharCardNo());
			cd.setGuarantorDateOfBirth(c.getGuarantorDateOfBirth());
			cd.setGuarantorJobDetails(c.getGuarantorJobDetails());
			cd.setGuarantorLoaclAddress(c.getGuarantorLoaclAddress());
			cd.setGuarantorMobileNumber(c.getGuarantorMobileNumber());
			cd.setGuarantorMortgageDetails(c.getGuarantorMortgageDetails());
			cd.setGuarantorName(c.getGuarantorName());
			cd.setGuarantorPermanentAddress(c.getGuarantorPermanentAddress());
			cd.setGuarantorRelationshipwithCustomer(c.getGuarantorRelationshipwithCustomer());
			
			gdr.save(cd);			
		}	else
		{
			throw new RuntimeException("Id not found");
		}
		
	}
	
	@Override
	public GuarantorDetails getData(int GuarantorId)
	{
		GuarantorDetails cd = new GuarantorDetails();
		Optional<GuarantorDetails> o = gdr.findById(GuarantorId);
		if(o.isPresent()) {
			cd = o.get();
			return cd;
		}
		else
		{
			throw new RuntimeException("Id not found");
		}
		
	}
	
	@Override
	public void deleteData(int GuarantorId) 
	{
		gdr.deleteById(GuarantorId);
		
	}
	 
  
}
