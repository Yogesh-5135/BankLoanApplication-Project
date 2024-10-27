package com.cjc.customerdetails.app.repoi.loanapplicationform;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.customerdetails.app.model.loanapplicationform.AccountDetails;

@Repository
public interface AccountDetailsRepoI extends JpaRepository<AccountDetails, Integer>
{

}
