import java.lang.*; //including Java packages used by this program
import java.awt.*;
import javax.swing.*;

class RecordTransactionControl {

	private int CustomerID;
	private String AccountType;
	private String TransactionType;
	private float TransactionAmount;
	private Transaction trans;

	public RecordTransactionControl(int CustID, String AcctType, String TransType, float TransAmt) {

		CustomerID = CustID;
		AccountType = AcctType;
		TransactionType = TransType;
		TransactionAmount = TransAmt;
	}

	public void RecordTransaction(){
		trans = new Transaction(CustomerID, AccountType, TransactionType, TransactionAmount);

		trans.RecordTransaction();
	}
}