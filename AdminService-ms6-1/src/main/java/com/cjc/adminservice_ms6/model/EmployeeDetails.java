package com.cjc.adminservice_ms6.model;

import com.cjc.adminservice_ms6.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Data;

@Entity
@Data
public class EmployeeDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empID;
	private String empFirstName;
	private String empMiddleName;
	private String empLastName;
	private String empEmail;
	private float empSalary;
	private int empAge;
	@Enumerated(EnumType.STRING)
	@Column(length = 999999999)
	private UserType userType;
	private String username;
	private String password;
	@Lob
	@Column(length = 999999999)
	private byte[] empImage;
	@Lob
	@Column(length = 999999999)
	private byte[] empPancard;

}
