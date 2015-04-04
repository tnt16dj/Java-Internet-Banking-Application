<!--
/******************************************************************************
*	Program Author: Todd Tkach
*	Date: December, 2012
*******************************************************************************/
-->

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.lang.Integer.*" %>
<%@ page import="webfiles.Java.*;" %>

<%
	String Username = new String("");
	String Password = new String("");
	Integer CustID;
	float[] AccountBalances;
	
	Username = request.getParameter("UserName");
	Password = request.getParameter("PassWord");
	
	if(Username != null && Password != null)
	{         
		LoginControlWeb lcw = new LoginControlWeb(Username, Password);
		CustID = lcw.retrieveCustID();
	}
	else
	{
		CustID = Integer.parseInt(request.getParameter("CustID"));
	}
	
	RetrieveBalanceControlWeb rbcw = new RetrieveBalanceControlWeb(CustID);
	AccountBalances = rbcw.setBalances(CustID);
%>
<html>	

<head>
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
	}
	
	#balanceTable {
		position: relative;
		top:15px;
	}
	
	a:hover {
		color: blue;
	}
	
	a {
		color: #5882FA;
	}

</style>

</head>

<body bgcolor='#F1F1FD' align="Center">

<div>
	<h2>Please make a selection from the available options:</h2>
</div>

<div id="nav">
	<h3>
	<A HREF='./Deposit.jsp?CustID=<%=CustID%>'>Deposit</A>&nbsp;&nbsp;
	<A HREF='./Withdraw.jsp?CustID=<%=CustID%>'>Withdraw</A>&nbsp;&nbsp;
	<A HREF='./Transfer.jsp?CustID=<%=CustID%>'>Transfer</A>&nbsp;&nbsp;
	<A HREF='./TransactionHistory.jsp?CustID=<%=CustID%>'>Transaction History</A>
	</h3>
</div>

<div id="balanceTable">
	<table border="1" Align="center">
	
	<tr>
	<th>Account</th>
	<th>Balance</th>
	</tr>
	
	<tr>
	<td>Checking</td>
	<td><%=AccountBalances[0]%></td>
	</tr>
	
	<tr>
	<td>Savings</td>
	<td><%=AccountBalances[1]%></td>
	</tr>
</div>

<script src="./jquery-1.8.1.js"></script>
<script>
	var t = $('div[id="balanceTable"]');
	t.hide();
	t.fadeIn(2500);
	
	var n = $('div[id="nav"]');
	n.hide();
	n.fadeIn(1000);
</script>

</body>
</html>

<script type="text/javascript">
</script>