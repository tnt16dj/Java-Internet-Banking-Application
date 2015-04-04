import java.io.*;
import java.net.*;
import java.lang.*;
import java.sql.*;
import java.util.*;

public class SocketServer
{
   public static void main(String[] args) throws IOException {
      	Socket ServerSock;

	 	while(true)
	 	{
     		//accept the incoming SocketClient connection request.
     		try
     		{
	 			ServerSocket s = new ServerSocket(2020);
	 			ServerSock = s.accept();
	 			System.out.println("Connection from: " + ServerSock.getInetAddress());
	 			ServerSock.setKeepAlive(true);

     			ObjectOutputStream oos = new ObjectOutputStream(ServerSock.getOutputStream());
     			oos.flush();
     			ObjectInputStream ois = new ObjectInputStream(ServerSock.getInputStream());

				String dateCriteria;
				dateCriteria = ois.readUTF();

				Integer CustID;
				CustID = ois.readInt();

				Transaction trans = new Transaction(CustID);

				Vector vec = new Vector();
				vec = trans.searchTransactions(CustID, dateCriteria);

	 			oos.writeObject(vec);
	 			oos.flush();

	 			ServerSock.close();
	 			s.close();
	 		}
     		catch (Exception e)
     		{
	 			System.err.println(e);
	 			return;
     		}
	 	}
	}
}
