package com.cjc.sanctionletter.app.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjc.sanctionletter.app.repoi.SanctionLetterRepoI;
import com.cjc.sanctionletter.app.servicei.SanctionLetterI;

@Service
public class SanctionLetterServiceImpl implements SanctionLetterI
{
 @Autowired 
 SanctionLetterRepoI slr;


}
