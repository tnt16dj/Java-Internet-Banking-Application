import java.lang.*; //including Java packages used by this program
import java.sql.*;
import java.util.*;

class Transaction
{
	private int CustomerID;
	private float TransactionAmount;
	private String TransactionType;
	private String AccountType;
	private String trDate;

	public Transaction(int CustID, String AcctType, String TransType, float TransAmt) {
		CustomerID = CustID;
		AccountType = AcctType;
		TransactionType = TransType;
		TransactionAmount = TransAmt;
	}

	public Transaction(int CustID) {
		CustomerID = CustID;
	}

	public boolean RecordTransaction()
	{
		if(AccountType == "Checking")
				{
				boolean tranStatus = false;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "INSERT INTO Transactions (AcctNo,TransType,TransAmt,TransDate)"
					                     +"VALUES ((SELECT AcctNo FROM CheckingAccount WHERE AcctNo ="
					                     +"(SELECT CheckingAcct FROM Account WHERE CustID = "+CustomerID+")),"
					                     +"'"+TransactionType+"',"+TransactionAmount+", CURRENT_TIMESTAMP);";
					Stmt.executeUpdate(SQL_Command);

					tranStatus = true;
					Stmt.close();
					ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {   tranStatus = false;
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
			    {     tranStatus = false;
					  System.out.println("Exception: " + e);
					  e.printStackTrace ();
				}
			    return tranStatus;
			}
			else if(AccountType == "Savings")
				{
				boolean tranStatus = false;
				try {
					DBConnection ToDB = new DBConnection(); //Have a connection to the DB
					Connection DBConn = ToDB.openConn();
					Statement Stmt = DBConn.createStatement();
					String SQL_Command = "INSERT INTO Transactions (AcctNo,TransType,TransAmt,TransDate)"
					                     +"VALUES ((SELECT AcctNo FROM SavingsAccount WHERE AcctNo ="
					                     +"(SELECT SavingsAcct FROM Account WHERE CustID = "+CustomerID+")),"
					                     +"'"+TransactionType+"',"+TransactionAmount+", CURRENT_TIMESTAMP);";
					Stmt.executeUpdate(SQL_Command);

					tranStatus = true;
					Stmt.close();
					ToDB.closeConn();

			    }
				catch(java.sql.SQLException e)
		        {   tranStatus = false;
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
			    {     tranStatus = false;
					  System.out.println("Exception: " + e);
					  e.printStackTrace ();
				}
			    return tranStatus;
			}
			else
			return false;
		}

	public Vector searchTransactions(int CustID, String transDate) throws SQLException //Vector
		{
			Vector transRecords = new Vector();
			Vector overallVector = new Vector();

			CustomerID = CustID;
			trDate = transDate;

			try
			{
				DBConnection ToDB = new DBConnection(); //Have a connection to the DB
				Connection DBConn = ToDB.openConn();
				Statement Stmt = DBConn.createStatement();
				String SQL_Command = "SELECT AcctNo, TransType, TransAmt FROM Transactions WHERE (AcctNo = "
				                     +"(SELECT CheckingAcct FROM Account WHERE CustID = "+CustomerID+") "
				                     +"OR AcctNo = (SELECT SavingsAcct FROM Account WHERE CustID = "+CustomerID+")) "
				                     +"AND Convert(nvarchar(20), TransDate, 101) = '"+trDate+"';"; //SQL query command
				ResultSet Rslt = Stmt.executeQuery(SQL_Command);
				ResultSetMetaData rsmd = Rslt.getMetaData();

				while(Rslt.next()){
					transRecords = ToDB.getNextRow(Rslt, rsmd);
					overallVector.add(transRecords);
				}
				Stmt.close();
				ToDB.closeConn();

			}
			catch(java.sql.SQLException e)
			{
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
				System.out.println("Exception: " + e);
				e.printStackTrace();
			}

			return overallVector;
		}

		//Need to develop transaction search method...
		//May need to use a vector... convert all data to byte array...
		//Or may need to use Object Input string or Object Output String... these can read Vectors directly...
}