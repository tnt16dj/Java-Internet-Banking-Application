import java.lang.*; //including Java packages used by this program
import java.awt.*;
import javax.swing.*;

class WithdrawControlWeb {

	private CheckingAccount ChkAcct;
	private SavingsAccount SavAcct;
	private int CustomerID;
	private String AccountType;
	private Float TransactionAmount;
	private Float AccountBalance;
	private int AccountNumber;

	public WithdrawControlWeb (int CustID, String AcctType, float TransAmount) {
		CustomerID = CustID;
		AccountType = AcctType;
		TransactionAmount = TransAmount;
	}

	public float doWithdraw() {
		AccountBalance = -1.00f;
		if(AccountType == "Checking")
		{
			//create new instance of CheckingAccount Class...
			ChkAcct = new CheckingAccount(CustomerID, TransactionAmount);

			//Invoke the Deposit method of CheckingAccount
			AccountNumber = ChkAcct.Withdraw();

			if(AccountNumber > 0)  //AccountNumber will only be > 0 if Deposit Successful on Database...
			{
				//Invoke getBalance method to get Account Balance after the Deposit...
				AccountBalance = ChkAcct.getBalance(AccountNumber);

				//Record the Transaction...
				RecordTransactionControl trns = new RecordTransactionControl(CustomerID, AccountType, "Withdraw", TransactionAmount);
				trns.RecordTransaction();
			}
			else  //fall into the 'else' if there was a DB problem and AccountNumber of 0 is returned (0 cannot be valid AccountNo)...
			{
				//set the AccountBalance to -1 as in indicator to TransactionConfirmationBO that a problem occurred...
				AccountBalance = -1.00f;

				//Create an instance of TransactionConfirmationBO to display user results...
				return AccountBalance;
			}
		}
		else if(AccountType == "Savings")
		{
			//create new instance of SavingsAccount Class...
			SavAcct = new SavingsAccount(CustomerID, TransactionAmount);

			//Invoke the Deposit method of SavingsAccount
			AccountNumber = SavAcct.Withdraw();

			if(AccountNumber > 0) //AccountNumber will only be > 0 if Deposit Successful on Database...
			{
				//Invoke getBalance method to get Account Balance after the Deposit...
				AccountBalance = SavAcct.getBalance(AccountNumber);

				//Record the Transaction...
				RecordTransactionControl trns = new RecordTransactionControl(CustomerID, AccountType, "Withdraw", TransactionAmount);
				trns.RecordTransaction();
			}
			else  //fall into the 'else' if there was a DB problem and AccountNumber of 0 is returned (0 cannot be valid AccountNo)...
			{
				//set the AccountBalance to -1 as in indicator to TransactionConfirmationBO that a problem occurred...
				AccountBalance = -1.00f;

				//Create an instance of TransactionConfirmationBO to display user results...
				return AccountBalance;
			}
		}
		return AccountBalance;
	}
}