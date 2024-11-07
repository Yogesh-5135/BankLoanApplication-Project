package com.cjc.sanctionletter.app.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.cjc.sanctionletter.app.model.LoanApplication;
import com.cjc.sanctionletter.app.model.SanctionLetter;
import com.cjc.sanctionletter.app.repoi.LoanApplyRepoI;
import com.cjc.sanctionletter.app.repoi.SanctionLetterRepoI;
import com.cjc.sanctionletter.app.servicei.SanctionLetterI;
import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.mail.internet.MimeMessage;

@Service
public class SanctionLetterServiceImpl implements SanctionLetterI {
	
	@Autowired
	SanctionLetterRepoI slr;
	
	@Value("${spring.mail.username}")
	String from;
	@Autowired
	JavaMailSender sender;
	@Autowired
	LoanApplyRepoI lri; 
	
	
	@Override
	public void generateLimit(int loanid, List<LoanApplication> l) 
	{
		SanctionLetter sl = new SanctionLetter();
		
		 LoanApplication la = new LoanApplication();
		  
			Optional<LoanApplication> ol = lri.findById(loanid);
			if(ol.isPresent())
			{
				la = ol.get();
			
			   if(la.getCibil()>=750)
			   {
				   sl.setLoanAmtSanctioned(la.getCustomerTotalLoanRequired());
			   }
			   else if(la.getCibil()>=700)
			   {
				   double d = la.getCustomerTotalLoanRequired()-50000;
				   sl.setLoanAmtSanctioned(d);
			   }
			   else if(la.getCibil()>=650)
			   {
				   double s = la.getCustomerTotalLoanRequired()-100000;
				   sl.setLoanAmtSanctioned(s);
			   }
			   
			}
			else
			{
				throw new RuntimeException("Data Not Found");
			}
			
			sl.setSanctionDate(new Date());
			sl.setApplicantName(la.getCustomerName());
			sl.setContactDetails(la.getCustomerMobileNumber());
			sl.setInterestType("Simple");
						
			sl.setLoanTenureInMonth(la.getRequiredTenure());
			sl.setModeOfPayment("In Cash");
			sl.setRemarks("Ok");
			sl.setTermsCondition("The loan must be repaid in full by [repayment date].");
			sl.setStatus("Offered");
			
			slr.save(sl);
			la.setSanctionLetter(sl);
			lri.save(la);
		  }		
	

	@Override
	public void generateIntRate( int sanctionId) 
	{		
		Optional<SanctionLetter> o = slr.findById(sanctionId);
		
		SanctionLetter sl = new SanctionLetter();
		if(o.isPresent()) {
			sl = o.get();
		}
			
		if (sl.getLoanAmtSanctioned() >= 2500000)
		  {    
			    if (sl.getLoanTenureInMonth() <= 36) 
			    {
			        sl.setRateOfInterest(7.2f);  
			    } 
			    else 
			    {
			        sl.setRateOfInterest(8.2f);
			    }
		  }
		  else if (sl.getLoanAmtSanctioned() >= 1000000) 
		  {
			    if (sl.getLoanTenureInMonth() <= 36)
			    {
			        sl.setRateOfInterest(9.2f);  
			    } 
			    else 
			    {
			        sl.setRateOfInterest(10.2f);  
			    }
		  } 
		  else 
		  {
			    
			    if (sl.getLoanTenureInMonth() <= 36) 
			    {
			        sl.setRateOfInterest(11.2f); 
			    }
			    else 
			    {
			        sl.setRateOfInterest(12.2f);  
			    }
		  }
		 slr.save(sl);
	}

	@Override
	public void getMonthlyEmi(int sanctionId) {
	    Optional<SanctionLetter> o = slr.findById(sanctionId);

	    if (!o.isPresent()) {
	        System.out.println("Sanction Letter not found.");
	        return;
	    }

	    SanctionLetter sl = o.get();

	    double loanAmount = sl.getLoanAmtSanctioned();
	    float rateOfInterest = sl.getRateOfInterest();
	    int loanTenureInMonths = sl.getLoanTenureInMonth();

	    
	    float monthlyInterestRate = rateOfInterest / 12 / 100;

	    // Calculate the standard EMI using the formula
	    double emi = (loanAmount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, loanTenureInMonths)) /
	                 (Math.pow(1 + monthlyInterestRate, loanTenureInMonths) - 1);

	    sl.setMonthlyEmiAmount(emi);

	    double remainingBalance = loanAmount;  // Set initial remaining balance as the loan amount

	    System.out.println("Loan Amount: ₹" + loanAmount);
	    System.out.println("Annual Interest Rate: " + rateOfInterest + "%");
	    System.out.println("Loan Tenure: " + loanTenureInMonths + " months");
	    System.out.println("EMI: ₹" + emi);

	    // Loop through each month and calculate the EMI breakdown
	    for (int month = 1; month <= loanTenureInMonths; month++) {
	        // Calculate the interest payment for this month
	        double interestPayment = remainingBalance * monthlyInterestRate;

	        // Calculate the principal repayment (EMI - interest payment)
	        double principalRepayment = emi - interestPayment;

	        // Reduce the remaining balance by the principal repayment
	        remainingBalance -= principalRepayment;

	        // Round to two decimal places to avoid precision errors
	        interestPayment = Math.round(interestPayment * 100.0) / 100.0;
	        principalRepayment = Math.round(principalRepayment * 100.0) / 100.0;
	        remainingBalance = Math.round(remainingBalance * 100.0) / 100.0;

	        // If this is the final month, we need to adjust the EMI to match the remaining balance
	        if (month == loanTenureInMonths && remainingBalance > 0) {
	            emi = remainingBalance + interestPayment;  // Adjust EMI to the exact remaining balance
	            principalRepayment = remainingBalance;     // All of the remaining balance is principal repayment
	            remainingBalance = 0;                      // No remaining balance after the final payment
	        }

	        // If remainingBalance is negative due to rounding errors, reset it to 0
	        if (remainingBalance < 0) {
	            remainingBalance = 0;
	        }

	        // Optionally, you could log the EMI breakdown for each month
	        System.out.println("Month: " + month);
	        System.out.println("Interest Payment: ₹" + interestPayment);
	        System.out.println("Principal Repayment: ₹" + principalRepayment);
	        System.out.println("Remaining Balance: ₹" + remainingBalance);
	    }

	    // Save the updated SanctionLetter with the new EMI and other details
	    slr.save(sl);
	}

	
	@Override
	public void generateSanctionLetter( List<LoanApplication> l, int sanctionId ,int loanid) 
	{
	    Optional<SanctionLetter> ol = slr.findById(sanctionId);
	    SanctionLetter sanctionLetter = new SanctionLetter();
	    
	    if (ol.isPresent()) {
	        sanctionLetter = ol.get();
	    } else {
	        System.out.println("Sanction letter not found for sanctionId: " + sanctionId);
	        return;
	    }
	        	
	            String title = "Axis Bank Ltd.";

	            Document document = new Document(PageSize.A4);

	            String content1 = "\n\n Dear " + sanctionLetter.getApplicantName() +
	                    ",\nAxis Bank Ltd. is happy to inform you that your loan application has been approved.";
	            String content2 = "\n\nWe hope that you find the terms and conditions of this loan satisfactory " +
	                    "and that it will help you meet your financial needs.\n\n" +
	                    "If you have any questions or need any assistance regarding your loan, please do not hesitate to contact us.\n\n" +
	                    "We wish you all the best and thank you for choosing us.\n\n" +
	                    "Sincerely,\n\n" + "Vijay Chaudhari (Credit Manager)";

	            ByteArrayOutputStream opt = new ByteArrayOutputStream();
	            try {
	                PdfWriter.getInstance(document, opt);
	                document.open();

	                try {
	                    Image img = Image.getInstance("C:\\Users\\Dell\\Desktop\\istockphoto-1492732089-612x612.jpg");
	                    img.scalePercent(50, 50);
	                    img.setAlignment(Element.ALIGN_RIGHT);
	                    document.add(img);
	                } catch (BadElementException | IOException e) {
	                    e.printStackTrace();
	                }

	                Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
	                Paragraph titlePara = new Paragraph(title, titleFont);
	                titlePara.setAlignment(Element.ALIGN_CENTER);
	                document.add(titlePara);

	                Font contentFont1 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
	                Paragraph contentPara1 = new Paragraph(content1, contentFont1);
	                document.add(contentPara1);

	                PdfPTable table = new PdfPTable(2);
	                table.setWidthPercentage(100f);
	                table.setWidths(new int[]{2, 2});
	                table.setSpacingBefore(10);

	                Font headerFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
	                Font valueFont = FontFactory.getFont(FontFactory.HELVETICA);

	                PdfPCell cell = new PdfPCell();
	                cell.setBackgroundColor(CMYKColor.WHITE);
	                cell.setPadding(5);
	                cell.setPhrase(new Phrase("Loan amount Sanctioned", headerFont));
	                table.addCell(cell);
	                cell.setPhrase(new Phrase("₹ " + sanctionLetter.getLoanAmtSanctioned(), valueFont));
	                table.addCell(cell);

	                cell.setPhrase(new Phrase("Loan Tenure", headerFont));
	                table.addCell(cell);
	                cell.setPhrase(new Phrase(String.valueOf(sanctionLetter.getLoanTenureInMonth()), valueFont));
	                table.addCell(cell);

	                cell.setPhrase(new Phrase("Interest Rate", headerFont));
	                table.addCell(cell);
	                cell.setPhrase(new Phrase(sanctionLetter.getRateOfInterest() + " %", valueFont));
	                table.addCell(cell);

	                cell.setPhrase(new Phrase("Sanction Date", headerFont));
	                table.addCell(cell);
	                cell.setPhrase(new Phrase(String.valueOf(sanctionLetter.getSanctionDate()), valueFont));
	                table.addCell(cell);

	                cell.setPhrase(new Phrase("Monthly EMI", headerFont));
	                table.addCell(cell);
	                cell.setPhrase(new Phrase(String.valueOf(sanctionLetter.getMonthlyEmiAmount()), valueFont));
	                table.addCell(cell);

	                document.add(table);

	                Font contentFont2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
	                Paragraph contentPara2 = new Paragraph(content2, contentFont2);
	                document.add(contentPara2);

	                document.close();

	                ByteArrayInputStream byt = new ByteArrayInputStream(opt.toByteArray());
	                byte[] bytes = byt.readAllBytes();
	              
	                sanctionLetter.setSanctionLetter(bytes);

	                slr.save(sanctionLetter);	  
	                    
	                System.out.println("Sanction Letter generated and saved.");
	                
	                Optional<LoanApplication> o = lri.findById(loanid);
	                LoanApplication la = new LoanApplication();
	        	    
	        	    if (o.isPresent()) {
	        	    	la = o.get();
	        	    } else {
	        	        System.out.println("Loan Application not found for loanid: " + loanid);
	        	        return;
	        	    }
	        	    la.setSanctionLetter(sanctionLetter);
	        	    la.setLoanStatus("Sanctioned");
	        	    lri.save(la);
	        	        
	                
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	               
	        
	                MimeMessage mimemessage = sender.createMimeMessage();
	                byte[] sanctionLetter1 = sanctionLetter.getSanctionLetter();
	        
	        		try {
	        			MimeMessageHelper mimemessageHelper = new MimeMessageHelper(mimemessage, true);
	        			mimemessageHelper.setFrom(from);
	        			mimemessageHelper.setTo("9373502538os@gmail.com");
	        			mimemessageHelper.setSubject("Axis Bank Sanction Letter");
	        			String text = "Dear " + sanctionLetter.getApplicantName()
	        					+ ",\n" + "\n"
	        					+ "This letter is to inform you that we have reviewed your request for a loan . We are pleased to offer you a credit loan of "
	        					+ sanctionLetter.getLoanAmtSanctioned() + " for "
	        					+ sanctionLetter.getLoanTenureInMonth()+ ".\n" + "\n"
	        					+ "We are confident that you will manage your loan responsibly, and we look forward to your continued business.\n"
	        					+ "\n"
	        					+ "Should you have any questions about yourloan, please do not hesitate to contact us.\n"
	        					+ "\n" + "Thank you for your interest in our services.";

	        			mimemessageHelper.setText(text);

	        			mimemessageHelper.addAttachment("loanSanctionLetter.pdf", new ByteArrayResource(sanctionLetter1));
	        			sender.send(mimemessage);
                        
	        		} catch (Exception e) {
	        			System.out.println("Email Failed to Send!!!!!!");
	        			e.printStackTrace();
	        		}
	        	
	    }

	@Override
	public void loanStatusChange(String loanStatus, int loanid)
	{
		Optional<LoanApplication> ol = lri.findById(loanid);
		if(ol.isPresent())
		{
			LoanApplication l = ol.get();
			l.setLoanStatus(loanStatus);
			l.getSanctionLetter().setStatus(loanStatus);;
			lri.save(l);
		}
		else
		{
			throw new RuntimeException("Data Not Found");
		}
		
	}

	

	
	         
}	
	
	