import java.net.*;
import java.io.*;
import java.util.*;

class RetrieveTransactionControlWeb{

	private Socket sock;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private int CustomerID;
	private String transDate;

	public RetrieveTransactionControlWeb(int CustID, String userDate){
		CustomerID = CustID;
		transDate = userDate;
	}

	public Vector RetrieveTransactions()
	{
		Vector transHist = new Vector();

		//Making the Socket connection from the Client
		try
		{
			InetAddress addr = InetAddress.getByName("localhost");
			Integer port = 2020;
			sock = new Socket(addr,port);

			oos = new ObjectOutputStream(sock.getOutputStream());
			oos.flush();
			ois = new ObjectInputStream(sock.getInputStream());

			oos.writeUTF(transDate);
			oos.flush();

			oos.writeInt(CustomerID);
			oos.flush();

			Object str;
			str = ois.readObject();
			transHist = (Vector) str;

			sock.close();
		}
		catch(Exception e)
		{
			System.err.println(e);
		}

		return transHist;
	}
}

