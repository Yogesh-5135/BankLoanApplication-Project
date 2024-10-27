package com.cjc.customerdetails.app.repoi.loanapplicationform;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.customerdetails.app.model.loanapplicationform.GuarantorDetails;

@Repository
public interface GuarantorDetailsRepoI extends JpaRepository<GuarantorDetails, Integer>
{

}
