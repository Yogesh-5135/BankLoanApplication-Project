package com.cjc.adminservice_ms6.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class AdminService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int adminID;
	private String username;
	private String password;
	private String designation;
	private int age;
	private String email;
	private long mobileno;

}
