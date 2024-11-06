package com.cjc.sanctionletter.app.serviceimpl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.sanctionletter.app.model.LoanApplication;
import com.cjc.sanctionletter.app.model.SanctionLetter;
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

@Service
public class SanctionLetterServiceImpl implements SanctionLetterI {
	@Autowired
	SanctionLetterRepoI slr;
	
	@Override
	public void generateLimit(int loanid, List<LoanApplication> l) 
	{
		SanctionLetter sl = new SanctionLetter();
		
		 for(LoanApplication la:l)
		  {
			if( la.getLoanid() == loanid)
			{
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
		  }		
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
	public void getMonthlyEmi( int sanctionId) 
	{
		Optional<SanctionLetter> o = slr.findById(sanctionId);
				
		SanctionLetter sl = new SanctionLetter();
		if(o.isPresent()) {
			sl = o.get();
		}
				
		float monthlyInterestRate = sl.getRateOfInterest() / 12 / 100;
				 
		double emi = (sl.getLoanAmtSanctioned() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, sl.getLoanTenureInMonth())) /
			                (Math.pow(1 + monthlyInterestRate, sl.getLoanTenureInMonth()) - 1);
			        
		sl.setMonthlyEmiAmount(emi);
		slr.save(sl);
		}

	@Override
	public void generateSanctionLetter(int loanid,List<LoanApplication> l,int sanctionId) 
	{
		Optional<SanctionLetter> ol = slr.findById(sanctionId);
		SanctionLetter s  = new SanctionLetter();
		if(ol.isPresent())
		{
			 s = ol.get();
		}
		for(LoanApplication la:l)
		  {
			if( la.getLoanid() == loanid)
			{
				String title = "Axis Bank Ltd.";

				Document document = new Document(PageSize.A4);

				String content1 = "\n\n Dear " +la.getCustomerName()
						+ ","
						+ "\nAxis Bank Ltd. is Happy to informed you that your loan application has been approved. ";

				String content2 = "\n\nWe hope that you find the terms and conditions of this loan satisfactory "
						+ "and that it will help you meet your financial needs.\n\nIf you have any questions or need any assistance regarding your loan, "
						+ "please do not hesitate to contact us.\n\nWe wish you all the best and thank you for choosing us."
						+ "\n\nSincerely,\n\n" + "Vijay Chaudhari (Credit Manager)";

				ByteArrayOutputStream opt = new ByteArrayOutputStream();
				
				PdfWriter.getInstance(document, opt);
				document.open();
               
				Image img = null;
				
				try {
					img = Image.getInstance("C:\\Users\\Akshay\\Downloads\\images\\house-building-or-home-flat-style-2H3BC0F.jpg");
					img.scalePercent(50, 50);
					img.setAlignment(Element.ALIGN_RIGHT);
					document.add(img);

				} catch (BadElementException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Font titlefont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 25);
				Paragraph titlepara = new Paragraph(title, titlefont);
				titlepara.setAlignment(Element.ALIGN_CENTER);
				document.add(titlepara);

				Font titlefont2 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
				Paragraph paracontent1 = new Paragraph(content1, titlefont2);
				document.add(paracontent1);

				PdfPTable table = new PdfPTable(2);
				table.setWidthPercentage(100f);
				table.setWidths(new int[] { 2, 2 });
				table.setSpacingBefore(10);

				PdfPCell cell = new PdfPCell();
				cell.setBackgroundColor(CMYKColor.WHITE);
				cell.setPadding(5);

				Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
				font.setColor(5, 5, 161);

				Font font1 = FontFactory.getFont(FontFactory.HELVETICA);
				font.setColor(5, 5, 161);

				cell.setPhrase(new Phrase("Loan amount Sanctioned", font));
				table.addCell(cell);

				cell.setPhrase(new Phrase(String.valueOf("₹ " + s.getLoanAmtSanctioned()),font1));
				table.addCell(cell);

				cell.setPhrase(new Phrase("loan tenure", font));
				table.addCell(cell);

				cell.setPhrase(new Phrase(String.valueOf(s.getLoanTenureInMonth()), font1));
				table.addCell(cell);

				cell.setPhrase(new Phrase("interest rate", font));
				table.addCell(cell);

				cell.setPhrase(new Phrase(String.valueOf(s.getRateOfInterest()) + " %", font1));
				table.addCell(cell);

				cell.setPhrase(new Phrase("Sanction Date", font));
				table.addCell(cell);
				
				cell.setPhrase(new Phrase(String.valueOf(s.getSanctionDate()), font));
				table.addCell(cell);
				
				cell.setPhrase(new Phrase("Monthly EMI", font));
			    table.addCell(cell);
				 
				cell.setPhrase(new Phrase(String.valueOf(s.getMonthlyEmiAmount()), font1));
				table.addCell(cell);
				
				document.add(table);

				Font titlefont3 = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10);
				Paragraph paracontent2 = new Paragraph(content2, titlefont3);
				document.add(paracontent2);
				document.close();
				
				ByteArrayInputStream byt = new ByteArrayInputStream(opt.toByteArray());
				byte[] bytes = byt.readAllBytes();
				la.getSanctionLetter().setSanctionLetter(bytes);
		
				 slr.save(s);
			}
				
			}
		  }
	}
		  
	
