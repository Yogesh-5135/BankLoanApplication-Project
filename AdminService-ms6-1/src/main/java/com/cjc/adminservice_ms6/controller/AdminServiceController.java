package com.cjc.adminservice_ms6.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cjc.adminservice_ms6.model.EmployeeDetails;
import com.cjc.adminservice_ms6.servicei.AdminServiceI;

@RestController
@RequestMapping("/api/v5")
public class AdminServiceController {
	
	
	@Autowired
	AdminServiceI asi;

	@PostMapping("/saveAdmin")
	public ResponseEntity<String> addAdmin(@RequestPart("info")String json,
			   @RequestPart("empImage")MultipartFile empImage,
	           @RequestPart("empPancard")MultipartFile empPancard
	           ) 
	{
		asi.saveAdmin(json,empImage,empPancard);
		return new ResponseEntity<String>("Data added", HttpStatus.CREATED);
	}
	
	@GetMapping("/getAdmin/{empID}")
	public ResponseEntity<EmployeeDetails> getAdmin(@PathVariable ("empID") int id) 
	{
		EmployeeDetails a = asi.getSingleAdmin(id);
		return new ResponseEntity<EmployeeDetails>(a , HttpStatus.OK);
	}
	
	@GetMapping("/getAllAdmin")
	public ResponseEntity<List<EmployeeDetails>> getAdmins() 
	{
		List<EmployeeDetails> l = asi.getAllAdmin();
		return new ResponseEntity<List<EmployeeDetails>>(l , HttpStatus.OK);
	}
	
	@GetMapping("/getEmployee/{username}/{password}")
	public ResponseEntity<EmployeeDetails> getEmployee(@PathVariable ("username") String username,@PathVariable ("password") String password )
	{
		EmployeeDetails ed = asi.getEmployee(username,password);
		
		return new ResponseEntity<EmployeeDetails>(ed,HttpStatus.OK);
	}
	@DeleteMapping("/delete/{empID}")
	public ResponseEntity<String> getAdmins(@PathVariable ("empID") int id) 
	{
		asi.deleteAdmin(id);
		return new ResponseEntity<String>("Data deleted", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/editAdmin/{empID}")
	public ResponseEntity<String> updateAdmin(@PathVariable ("empID") int empID,@RequestPart("info")String json,
			   @RequestPart("empImage")MultipartFile empImage,
	           @RequestPart("panCard")MultipartFile empPancard

	           )
	{
		asi.editAdmin(empID,json,empImage,empPancard);
		return new ResponseEntity<String>("Data Updated" , HttpStatus.OK);
	}
	
}
