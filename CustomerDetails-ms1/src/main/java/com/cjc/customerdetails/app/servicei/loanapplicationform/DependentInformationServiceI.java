package com.cjc.customerdetails.app.servicei.loanapplicationform;

import com.cjc.customerdetails.app.model.loanapplicationform.DependentInforamtion;

public interface DependentInformationServiceI {

	DependentInforamtion saveData(DependentInforamtion s);

	void editData(int dependentInfoId, DependentInforamtion c);

	DependentInforamtion getData(int dependentInfoId);

	void deleteData(int dependentInfoId);

}
