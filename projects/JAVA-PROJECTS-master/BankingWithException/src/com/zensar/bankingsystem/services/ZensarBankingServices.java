package com.zensar.bankingsystem.services;

import com.zensar.bankingsystem.beans.Account;
import com.zensar.bankingsystem.beans.Customer;
import com.zensar.bankingsystem.beans.Transaction;

public interface ZensarBankingServices {
	int acceptCustomerDetails(String custName, String HomeAddressCity,
			String HomeAddressState, int HomeAddressPincode,
			String LocalAddressCity, String LocalAddressState,
			int LocalAddressPincode);
	
	int openAccount(int custId, int balance, String accType);

	int getAccountBalance(int custId, int accNo);

	int withdraw(int custId, int accNo, int amt);

	boolean fundTransfer(int custIdFrom, int accNoFrom, int custIdTo,
			int accNoTo, int amt);

	int deposit(int custId, int accNo, int amt);

	Customer getCustomerDetails(int custId);

	Account getAccountDetails(int custId, int accNo);

	Account[] getAllAccountsDetails(int custId);

	Transaction[] getAllTransactionDetails(int custId, int accNo);

}
