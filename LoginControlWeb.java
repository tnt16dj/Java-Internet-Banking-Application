/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.lang.*; //including Java packages used by this program

class LoginControlWeb
{
	private String UName, PWord;
	private Account Acct;
	private int CustID;

    public LoginControlWeb(String Username, String Password)
    {
		UName = Username;
		PWord = Password;
	}

	public int retrieveCustID()
	{
		Acct = new Account(UName, PWord);
		CustID = Acct.signIn();

		if (CustID != 0)
		{
			//JOptionPane.showMessageDialog(null, "Login Successful!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			/*Bal_BO = new BalancePanel(CustID);
			Dep_BO = new DepositPanel(CustID);
			Wth_BO = new WithdrawPanel(CustID);
			Trn_BO = new TransferPanel(CustID);
			TrHs_BO = new TransHistPanel(CustID);  //Newly added for transaction history lookup...
			Tab_BO = new ConfirmTabPanel(Bal_BO, Dep_BO, Wth_BO, Trn_BO, TrHs_BO);
			Cnf_BO = new ConfirmationBO(Tab_BO);*/

			return CustID;
		}
		else
		{
			//JOptionPane.showMessageDialog(null, "Login Failed.  Please check Username and Password and try again!", "Confirmation", JOptionPane.INFORMATION_MESSAGE);
			return 0;
		}
	}
}
