/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.lang.*; //including Java packages used by this program
import java.sql.*;

class SavingsAccount {

	private float savingsAcctBal = 0.00f;
	private int SavingsAcctNo;
	private int CustomerID;
	private float TransactionAmount;

	public SavingsAccount(int AccountNo){
		SavingsAcctNo = AccountNo;
	}

	public SavingsAccount(int CustID, Float TransAmt){
		CustomerID = CustID;
		TransactionAmount = TransAmt;
	}

	public float getBalance(int AccountNo){
				savingsAcctBal = 0.00f;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "SELECT AcctBalance FROM SavingsAccount WHERE AcctNo ='"+SavingsAcctNo+"';"; //SQL query command
					ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username and password exist/are correct.

						while(Rslt.next()){
							savingsAcctBal = Rslt.getFloat("AcctBalance");
							//System.out.println("Sav Acct: " + savingsAcctBal);
						}
						Stmt.close();
						ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {   savingsAcctBal = 0.00f;
				    System.out.println("SQLException: " + e);
				    while (e != null)
					 {   System.out.println("SQLState: " + e.getSQLState());
						 System.out.println("Message: " + e.getMessage());
						 System.out.println("Vendor: " + e.getErrorCode());
						 e = e.getNextException();
						 System.out.println("");
					 }
				}
				catch (java.lang.Exception e)
			    {     savingsAcctBal = 0.00f;
					  System.out.println("Exception: " + e);
					  e.printStackTrace ();
				}
			    return savingsAcctBal;
	}


	public int Deposit(){
				SavingsAcctNo = 0;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "UPDATE SavingsAccount SET AcctBalance = AcctBalance + "+TransactionAmount+" WHERE AcctNo = (SELECT SavingsAcct FROM Account WHERE CustID ="+CustomerID+");";
					                     //SQL query command

					if(Stmt.executeUpdate(SQL_Command) > 0){

					SQL_Command = "SELECT AcctNo FROM SavingsAccount WHERE AcctNo = (SELECT SavingsAcct FROM Account WHERE CustID ="+CustomerID+");";
					ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username and password exist/are correct.

						while(Rslt.next()){
							SavingsAcctNo = Rslt.getInt("AcctNo");
							//System.out.println("Chk Bal: " + checkingAcctBal);
						}
					}

					Stmt.close();
					ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {
					SavingsAcctNo = 0;
				    System.out.println("SQLException: " + e);
				    while (e != null)
					 {   System.out.println("SQLState: " + e.getSQLState());
						 System.out.println("Message: " + e.getMessage());
						 System.out.println("Vendor: " + e.getErrorCode());
						 e = e.getNextException();
						 System.out.println("");
					 }
				}
				catch (java.lang.Exception e)
			    {
					SavingsAcctNo = 0;
					System.out.println("Exception: " + e);
					e.printStackTrace ();
				}
			    return SavingsAcctNo;
	}


	public int Withdraw(){
				SavingsAcctNo = 0;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "UPDATE SavingsAccount SET AcctBalance = AcctBalance - "+TransactionAmount+" WHERE AcctNo = (SELECT SavingsAcct FROM Account WHERE CustID ="+CustomerID+");";
					                     //SQL query command

					if(Stmt.executeUpdate(SQL_Command) > 0){

					SQL_Command = "SELECT AcctNo FROM SavingsAccount WHERE AcctNo = (SELECT SavingsAcct FROM Account WHERE CustID ="+CustomerID+");";
					ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username and password exist/are correct.

						while(Rslt.next()){
							SavingsAcctNo = Rslt.getInt("AcctNo");
							//System.out.println("Chk Bal: " + checkingAcctBal);
						}
					}

					Stmt.close();
					ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {
					SavingsAcctNo = 0;
				    System.out.println("SQLException: " + e);
				    while (e != null)
					 {   System.out.println("SQLState: " + e.getSQLState());
						 System.out.println("Message: " + e.getMessage());
						 System.out.println("Vendor: " + e.getErrorCode());
						 e = e.getNextException();
						 System.out.println("");
					 }
				}
				catch (java.lang.Exception e)
			    {
					SavingsAcctNo = 0;
					System.out.println("Exception: " + e);
					e.printStackTrace ();
				}
			    return SavingsAcctNo;
	}

}