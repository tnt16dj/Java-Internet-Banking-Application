<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="webfiles.Java.*;" %>

<%
	Integer CustID, i;
	String UserDate = new String("");
	Vector TransHistory = new Vector();
	Integer vecSize = 0;

	CustID = Integer.parseInt(request.getParameter("CustID"));
	UserDate = request.getParameter("DateField");
	
	if(CustID != 0 && UserDate != null)
	{
		RetrieveTransactionControlWeb rtcw = new RetrieveTransactionControlWeb(CustID, UserDate);
		TransHistory = rtcw.RetrieveTransactions();

		vecSize = TransHistory.size();
		// out.println(vecSize);
	}
%>

<HTML>

<HEAD>
<style type="text/css">
	
	body {
		align: center;
	}
	
	table {
		background-color: black;
	}

	th {
		background-color: darkblue;
		color: white;
		padding-top: 5px;
		padding-bottom: 5px;
		padding-left: 50px;
		padding-right: 50px;
	}
	
	td {
		background-color: white;
		padding-top: 5px;
		padding-bottom: 5px;
		padding-left: 50px;
		padding-right: 50px;
		text-align: center;
	}

	input[type=button] {
		width: 120px;
		background-color: lightblue;
		border-radius: 10px;
	}
	
	input[type=button]:hover {
		background-color: #F4FA58;
	}

</style>
</HEAD>

<BODY bgcolor='#F1F1FD' align="center">

	<h2>Enter a date and click 'View Transactions' to view Transaction History</h2>
	
    <FORM NAME="TransactionForm" ACTION="./TransactionHistory.jsp" METHOD ="POST">
	<INPUT TYPE="Hidden" NAME='CustID' VALUE='<%=request.getParameter("CustID")%>'>
	
	<div>
	Date:&nbsp;
	<INPUT TYPE="textbox" NAME="DateField" VALUE='' SIZE='15'>
	</div>
	<div>
	<INPUT TYPE="button" NAME='homeButton' VALUE='Main Menu' SIZE='15' ONCLICK="returnHome()">
	<INPUT TYPE="Button" NAME='submitBNTN' VALUE='View Transactions' SIZE='15' ONCLICK="searchTrans()">
	</div>
	</FORM>
	
	<% if(vecSize != 0) { %>
	<div id="balanceTable">
	<table border="1" Align="center">
	
	<tr>
	<th>Transaction Date</th>
	<th>Account Number</th>
	<th>Transaction Type</th>
	<th>Transaction Amount</th>
	</tr>
	
	<% for(i=0;i<vecSize;i++) { %>
	<tr>
	<td><%=UserDate%></td>
	<td><%=((Vector)TransHistory.get(i)).get(0)%></td>
	<td><%=((Vector)TransHistory.get(i)).get(1)%></td>
	<td><%=((Vector)TransHistory.get(i)).get(2)%></td>
	</tr>
	<% } %>
	
	</table>
	</div>
<% } %>
	
<script src="./jquery-1.8.1.js"></script>
<script>
	var t = $('div[id="balanceTable"]');
	t.hide();
	t.fadeIn(2500);
</script>
	
</BODY>
</HTML>

<SCRIPT type="text/javascript">
	function searchTrans() {
		var uDate = document.TransactionForm.DateField.value;
		
		if (uDate != "") {
			document.TransactionForm.submit();
		}
		else {
			alert("You must supply a date before searching for Transactions! Please try again!");
		}
	}
	
	function returnHome() {
		window.location = "./Welcome.jsp?CustID=<%=CustID%>"
	}
</SCRIPT>