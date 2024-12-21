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
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AdminServiceImpl implements AdminServiceI {

    @Autowired
    AdminServiceRepoI ari;

    @Override
    public void saveAdmin(String json, MultipartFile empImage, MultipartFile empPancard) {
       
        ObjectMapper om = new ObjectMapper();
        EmployeeDetails emplyeedetails = null;

        try {
            
            emplyeedetails = om.readValue(json, EmployeeDetails.class);

           
            emplyeedetails.setUsername(CredentialGeneratorUtility.generateUsername(emplyeedetails.getEmpFirstName()));
            emplyeedetails.setPassword(CredentialGeneratorUtility.generatePassword(emplyeedetails.getEmpFirstName()));

            
            if (empImage != null && !empImage.isEmpty()) {
                emplyeedetails.setEmpImage(empImage.getBytes());
            } else {
                throw new RuntimeException("Employee image is required.");
            }

            if (empPancard != null && !empPancard.isEmpty()) {
                emplyeedetails.setEmpPancard(empPancard.getBytes());
            } else {
                throw new RuntimeException("Employee pancard is required.");
            }

        } catch (JsonProcessingException e) {
           
            e.printStackTrace();
            throw new RuntimeException("Error parsing the input JSON: " + e.getMessage(), e);
        } catch (IOException e) {
            
            e.printStackTrace();
            throw new RuntimeException("Error reading file data: " + e.getMessage(), e);
        }

        
        if (emplyeedetails != null) {
            try {
                
                ari.save(emplyeedetails);
            } catch (Exception e) {
                
                e.printStackTrace();
                throw new RuntimeException("Error saving employee details to database: " + e.getMessage(), e);
            }
        } 
        
    
    }
    @Override
    public EmployeeDetails getSingleAdmin(int id) {
        Optional<EmployeeDetails> o = ari.findById(id);
        EmployeeDetails a = new EmployeeDetails();
        if (o.isPresent()) {
            a = o.get();
            return a;
        } else {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }

    @Override
    public List<EmployeeDetails> getAllAdmin() {
        return ari.findAll();
    }

    @Override
    public void deleteAdmin(int id) {
        Optional<EmployeeDetails> o = ari.findById(id);
        if (o.isPresent()) {
            ari.deleteById(id);
        } else {
            throw new RuntimeException("Employee not found with ID: " + id);
        }
    }

    @Override
    public void editAdmin(int empId, String json, MultipartFile empImage, MultipartFile empPancard) {
        Optional<EmployeeDetails> o = ari.findById(empId);
        EmployeeDetails as = new EmployeeDetails();

        if (o.isPresent()) {
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

                
                if (empImage != null && !empImage.isEmpty()) {
                    as.setEmpImage(empImage.getBytes());
                }

                if (empPancard != null && !empPancard.isEmpty()) {
                    as.setEmpPancard(empPancard.getBytes());
                }

                
                ari.save(as);

            } catch (JsonProcessingException e) {
            
                e.printStackTrace();
                throw new RuntimeException("Error parsing the input JSON for update: " + e.getMessage(), e);
            } catch (IOException e) {
            	
                e.printStackTrace();
                throw new RuntimeException("Error reading file data for update: " + e.getMessage(), e);
            }
        } else {
            throw new RuntimeException("Employee not found with ID: " + empId);
        }
    }
}
