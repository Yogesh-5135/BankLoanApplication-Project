package com.cjc.customerdetails.app.servicei.loanapplicationform;

import com.cjc.customerdetails.app.model.loanapplicationform.LocalAddress;

public interface LocalAddressServiceI {

	public void saveLocalAddress(LocalAddress a, int customerAddressId);

	public LocalAddress getSingleLocalAddress(int localAddressId);

	public LocalAddress updateLocalAddress(int localAddressId, LocalAddress a);

	public void deleteLocalAddress(int localAddressId);

}
