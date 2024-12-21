package com.cjc.adminservice_ms6.serviceimpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.adminservice_ms6.model.EmployeeDetails;
import com.cjc.adminservice_ms6.repoi.AdminServiceRepoI;
import com.cjc.adminservice_ms6.servicei.AdminServiceI;
import com.cjc.adminservice_ms6.utility.CredentialGeneratorUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdminServiceImpl implements AdminServiceI{
	
	@Autowired
	AdminServiceRepoI ari;

	@Override
	public void saveAdmin(String json, MultipartFile empImage, MultipartFile empPancard) 
	{
        ObjectMapper om = new ObjectMapper();
		
		EmployeeDetails emplyeedetails = null;
		
		try {
			emplyeedetails = om.readValue(json,EmployeeDetails.class);
					emplyeedetails.setUsername(CredentialGeneratorUtility.generateUsername(emplyeedetails.getEmpFirstName()));
					emplyeedetails.setPassword(CredentialGeneratorUtility.generatePassword(emplyeedetails.getEmpFirstName()));
		    } 
		    catch (JsonMappingException e) 
		    {
			e.printStackTrace();
		    }
		    catch (JsonProcessingException e) 
		    {
			e.printStackTrace();
		    }
		if(emplyeedetails!=null)
		    {
			try {
				emplyeedetails.setEmpImage(empImage.getBytes());
				emplyeedetails.setEmpPancard(empPancard.getBytes());
			    }
			    catch (IOException e) 
			    {
				e.printStackTrace();
			    }
		    }
		ari.save(emplyeedetails);
		
	}


	@Override
	public EmployeeDetails getSingleAdmin(int id) 
	{
		
		Optional<EmployeeDetails> o = ari.findById(id);
		EmployeeDetails a = new EmployeeDetails();
		if(o.isPresent()) {
			a = o.get();
			return a;
		}else
		{ 
			throw new RuntimeException("Id not found");
		}
	}

	@Override
	public List<EmployeeDetails> getAllAdmin() 
	{
		List<EmployeeDetails> l = ari.findAll();
		return l;
	}

	@Override
	public void deleteAdmin(int id) 
	{
		ari.deleteById(id);
	}

	@Override
	public void editAdmin(int empId,String json, MultipartFile empImage, MultipartFile empPancard) 
	{
		Optional<EmployeeDetails> o = ari.findById(empId);
		EmployeeDetails as = new EmployeeDetails();
		if(o.isPresent()) {
			
			            as = o.get();

	            ObjectMapper objectMapper = new ObjectMapper();
	            try {
	                EmployeeDetails updatedData = objectMapper.readValue(json, EmployeeDetails.class);

	                as.setEmpFirstName(updatedData.getEmpFirstName());
	                as.setEmpMiddleName(updatedData.getEmpMiddleName());
	                as.setEmpLastName(updatedData.getEmpLastName());
	                as.setEmpEmail(updatedData.getEmpEmail());
	                as.setEmpSalary(updatedData.getEmpSalary());
	                as.setEmpAge(updatedData.getEmpAge());
	                as.setUserType(updatedData.getUserType());
	                
	                as.setEmpImage(empImage.getBytes());
	                as.setEmpPancard(empPancard.getBytes());
	                

	                ari.save(as);

	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
		
		
	}

	
}
