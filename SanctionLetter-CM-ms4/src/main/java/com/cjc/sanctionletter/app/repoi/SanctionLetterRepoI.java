package com.cjc.sanctionletter.app.repoi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cjc.sanctionletter.app.model.SanctionLetter;

@Repository
public interface SanctionLetterRepoI extends JpaRepository<SanctionLetter,Integer>
{

}
