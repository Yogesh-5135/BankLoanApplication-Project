package com.cjc.ledger_loan_ms5.repoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.ledger_loan_ms5.model.Ledger;

@Repository
public interface LedgerRepoI extends JpaRepository<Ledger, Integer>
{

}
