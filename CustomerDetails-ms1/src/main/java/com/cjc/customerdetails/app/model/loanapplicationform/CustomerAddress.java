package com.cjc.customerdetails.app.model.loanapplicationform;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class CustomerAddress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customerAddressId;
	
	@OneToOne(cascade=CascadeType.ALL)
	private PermanentAddress permanentAddress;
	
	@OneToOne(cascade=CascadeType.ALL)
	private LocalAddress localAddress;

}
