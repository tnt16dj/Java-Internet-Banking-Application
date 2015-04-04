/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.lang.*; //including Java packages used by this program
import java.sql.*;

class CheckingAccount {

	private float checkingAcctBal = 0.00f;
	private int CheckingAcctNo;
	private int CustomerID;
	private float TransactionAmount;

	public CheckingAccount(int AccountNo){
		CheckingAcctNo = AccountNo;
	}

	public CheckingAccount(int CustID, Float TransAmt){
		CustomerID = CustID;
		TransactionAmount = TransAmt;
	}


	public float getBalance(int AccountNo){
				checkingAcctBal = 0.00f;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "SELECT AcctBalance FROM CheckingAccount WHERE AcctNo ='"+CheckingAcctNo+"';"; //SQL query command
					ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username and password exist/are correct.

						while(Rslt.next()){
							checkingAcctBal = Rslt.getFloat("AcctBalance");
							//System.out.println("Chk Bal: " + checkingAcctBal);
						}
						Stmt.close();
						ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {   checkingAcctBal = 0.00f;
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
			    {     checkingAcctBal = 0.00f;
					  System.out.println("Exception: " + e);
					  e.printStackTrace ();
				}
			    return checkingAcctBal;
	}


	public int Deposit(){
				CheckingAcctNo = 0;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "UPDATE CheckingAccount SET AcctBalance = AcctBalance + "+TransactionAmount+" WHERE AcctNo = (SELECT CheckingAcct FROM Account WHERE CustID ="+CustomerID+");";
					                     //SQL query command

					if(Stmt.executeUpdate(SQL_Command) > 0){

					SQL_Command = "SELECT AcctNo FROM CheckingAccount WHERE AcctNo = (SELECT CheckingAcct FROM Account WHERE CustID ="+CustomerID+");";
					ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username and password exist/are correct.

						while(Rslt.next()){
							CheckingAcctNo = Rslt.getInt("AcctNo");
							//System.out.println("Chk Bal: " + checkingAcctBal);
						}
					}

					Stmt.close();
					ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {
					CheckingAcctNo = 0;
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
					CheckingAcctNo = 0;
					System.out.println("Exception: " + e);
					e.printStackTrace ();
				}
			    return CheckingAcctNo;
	}


	public int Withdraw(){
				CheckingAcctNo = 0;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "UPDATE CheckingAccount SET AcctBalance = AcctBalance - "+TransactionAmount+" WHERE AcctNo = (SELECT CheckingAcct FROM Account WHERE CustID ="+CustomerID+");";
					                     //SQL query command

					if(Stmt.executeUpdate(SQL_Command) > 0){

					SQL_Command = "SELECT AcctNo FROM CheckingAccount WHERE AcctNo = (SELECT CheckingAcct FROM Account WHERE CustID ="+CustomerID+");";
					ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username and password exist/are correct.

						while(Rslt.next()){
							CheckingAcctNo = Rslt.getInt("AcctNo");
							//System.out.println("Chk Bal: " + checkingAcctBal);
						}
					}

					Stmt.close();
					ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {
					CheckingAcctNo = 0;
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
					CheckingAcctNo = 0;
					System.out.println("Exception: " + e);
					e.printStackTrace ();
				}
			    return CheckingAcctNo;
	}


}