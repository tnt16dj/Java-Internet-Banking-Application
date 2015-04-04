/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.lang.*; //including Java packages used by this program

class RetrieveBalanceControlWeb
{
	private int CustomerID;
	private Account Acct;
	private CheckingAccount ChkAcct;
	private SavingsAccount SavAcct;
	private int[] AccountList = new int[2];
	private float[] AccountBalances = new float[2];

	public RetrieveBalanceControlWeb(int CustID)
	{
		CustomerID = CustID;

		Acct = new Account(CustID);
		AccountList = Acct.retrieveAccounts(CustID);

		ChkAcct = new CheckingAccount(AccountList[0]);
		AccountBalances[0] = ChkAcct.getBalance(AccountList[0]);

		SavAcct = new SavingsAccount(AccountList[1]);
		AccountBalances[1] = SavAcct.getBalance(AccountList[1]);

	}

	public float[] setBalances(int CustID){
		if(CustomerID == CustID){
			return AccountBalances;
		}
		else{
			AccountBalances[0] = 0.00f;
			AccountBalances[1] = 0.00f;
			return AccountBalances;
		}
	}

}