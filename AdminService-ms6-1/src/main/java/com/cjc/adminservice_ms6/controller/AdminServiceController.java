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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjc.adminservice_ms6.model.AdminService;
import com.cjc.adminservice_ms6.servicei.AdminServiceI;

@RestController
@RequestMapping("/api/v1")
public class AdminServiceController {
	
	@Autowired
	AdminServiceI asi;

	@PostMapping("/saveAdmin")
	public ResponseEntity<String> addAdmin(@RequestBody AdminService a) 
	{
		asi.saveAdmin(a);
		return new ResponseEntity<String>("Data added", HttpStatus.CREATED);
	}
	
	@GetMapping("/getAdmin/{adminID}")
	public ResponseEntity<AdminService> getAdmin(@PathVariable ("adminID") int id) 
	{
		AdminService a = asi.getSingleAdmin(id);
		return new ResponseEntity<AdminService>(a , HttpStatus.OK);
	}
	
	@GetMapping("/getAllAdmin")
	public ResponseEntity<List<AdminService>> getAdmins() 
	{
		List<AdminService> l = asi.getAllAdmin();
		return new ResponseEntity<List<AdminService>>(l , HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{adminID}")
	public ResponseEntity<String> getAdmins(@PathVariable ("adminID") int id) 
	{
		asi.deleteAdmin(id);
		return new ResponseEntity<String>("Data deleted", HttpStatus.NO_CONTENT);
	}
	
	@PutMapping("/editAdmin/{adminID}")
	public ResponseEntity<String> updateAdmin(@PathVariable ("adminID") int id , @RequestBody AdminService a)
	{
		asi.editAdmin(id , a);
		return new ResponseEntity<String>("Data Updated" , HttpStatus.OK);
	}
	
}
