import java.lang.*; //including Java packages used by this program
import java.awt.*;
import javax.swing.*;

class TransferControlWeb {

	private CheckingAccount ChkAcct;
	private SavingsAccount SavAcct;
	private int CustomerID;
	private String FromAccountType;
	private String ToAccountType;
	private Float TransactionAmount;
	private Float AccountBalance;
	private int ChkAccountNumber;
	private int SavAccountNumber;

	public TransferControlWeb (int CustID, String FromAcct, String ToAcct, float TransAmt)
	{
		CustomerID = CustID;
		FromAccountType = FromAcct;
		ToAccountType = ToAcct;
		TransactionAmount = TransAmt;
	}

	public float doTransfer()
	{
		AccountBalance = -1.00f;
		if(FromAccountType == "Checking")  //Transfer from Checking to Savings
		{
			//Withdraw from the Checking Account and then Deposit to the Savings Account...
			ChkAcct = new CheckingAccount(CustomerID, TransactionAmount);
			ChkAccountNumber = ChkAcct.Withdraw();

			if(ChkAccountNumber > 0)  //if ChkAccountNumber > 0 then the Withdraw was successful...
			{
				RecordTransactionControl trns = new RecordTransactionControl(CustomerID, FromAccountType, "Withdraw", TransactionAmount);
				trns.RecordTransaction();

				SavAcct = new SavingsAccount(CustomerID, TransactionAmount);
				SavAccountNumber = SavAcct.Deposit();

				if(SavAccountNumber > 0) //if SavAccountNumber > 0 then the Deposit was successful...
				{
					AccountBalance = SavAcct.getBalance(SavAccountNumber);

					trns = new RecordTransactionControl(CustomerID, ToAccountType, "Deposit", TransactionAmount);
					trns.RecordTransaction();

					return AccountBalance;
				}
			}
		}
		else if(FromAccountType == "Savings")
		{
			SavAcct = new SavingsAccount(CustomerID, TransactionAmount);
			SavAccountNumber = SavAcct.Withdraw();

			if(SavAccountNumber > 0)
			{
				RecordTransactionControl trns = new RecordTransactionControl(CustomerID, FromAccountType, "Withdraw", TransactionAmount);
				trns.RecordTransaction();

				ChkAcct = new CheckingAccount(CustomerID, TransactionAmount);
				ChkAccountNumber = ChkAcct.Deposit();

				if(ChkAccountNumber > 0)
				{
					AccountBalance = ChkAcct.getBalance(ChkAccountNumber);

					trns = new RecordTransactionControl(CustomerID, ToAccountType, "Deposit", TransactionAmount);
					trns.RecordTransaction();

					return AccountBalance;
				}
			}
		}
		return AccountBalance;
	}
}