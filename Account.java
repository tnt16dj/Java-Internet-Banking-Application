/******************************************************************************
*	Program Author: Dr. Yongming Tang for CSCI 6810 Java and the Internet	  *
*	Date: September, 2012													  *
*******************************************************************************/

import java.lang.*; //including Java packages used by this program
import java.sql.*;

class Account
{
	private String Username, Password, Password1;
	private int CustomerID;

	public Account(String UN, String PassW, String PassW1) {
		Username = UN;
		Password = PassW;
		Password1 = PassW1;
	}

	public Account(String UN, String PassW) {
		Username = UN;
		Password = PassW;
	}

	public Account(int CustID){
		CustomerID = CustID;
	}

    public boolean signUp() {
		if (!Username.equals("") && !Password.equals("") && !Password1.equals("") &&
		    Password.equals(Password1) && !Username.equals("someone@yahoo.com"))
		     return true;
		else return false;
	}

    public int signIn() {
				int CustID = 0;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "SELECT CustID FROM Account WHERE Username ='"+Username+"'AND Password ='"+Password+"';"; //SQL query command
					ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username and password exist/are correct.

						while(Rslt.next()){
							CustID = Rslt.getInt("CustID");
						}
						Stmt.close();
						ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {   CustID = 0;
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
			    {     CustID = 0;
					  System.out.println("Exception: " + e);
					  e.printStackTrace ();
				}
			    return CustID;
	}


	public int[] retrieveAccounts(int CustID){
				int[] accounts = new int[2];
				accounts[0] = 0;
				accounts[1] = 0;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "SELECT CheckingAcct, SavingsAcct FROM Account WHERE CustID ='"+CustID+"';"; //SQL query command
					ResultSet Rslt = Stmt.executeQuery(SQL_Command); //Inquire if the username and password exist/are correct.

						while(Rslt.next()){
							accounts[0] = Rslt.getInt("CheckingAcct");
							accounts[1] = Rslt.getInt("SavingsAcct");
						}
						//System.out.println("Chk Account " + accounts[0]);
						//System.out.println("Sav Account " + accounts[1]);
						Stmt.close();
						ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {   accounts[0] = 0;
					accounts[1] = 0;
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
			    {     accounts[0] = 0;
					  accounts[1] = 0;
					  System.out.println("Exception: " + e);
					  e.printStackTrace ();
				}
			    return accounts;
			}


	/*public float transferAmount(String fromAcctType, String toAcctType, float transferAmt){
		if(transferAmt > 0 && fromAcctType != toAcctType)
		{
			withdrawAmount(fromAcctType, transferAmt);
			float newAcctBal = depositAmount(toAcctType, transferAmt);
			return newAcctBal;
		}
		else
		return 0.00f;
	}*/
}