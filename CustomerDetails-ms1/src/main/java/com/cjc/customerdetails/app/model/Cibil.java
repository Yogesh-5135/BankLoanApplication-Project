package com.cjc.customerdetails.app.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Cibil {

	@Id
	private int cibid;
	private int cibilscore;
	private Date date;
	private String status;
	private String remark;
	
}