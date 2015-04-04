<!--
/******************************************************************************
* Program Author: Todd Tkach
*******************************************************************************/
-->

<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.lang.*" %>
<%@ page import="java.lang.Float.*"%>
<%@ page import="java.lang.String.*"%>
<%@ page import="webfiles.Java.*;" %>

<% 	
	float confirmTransferBal = -2.00f; 
	Integer CustID;
	String FromAccountSelection = new String("");
	String ToAccountSelection = new String("");
	String TransactionStr = new String("");
	float TransAmount = 0.00f;
	
	CustID = Integer.parseInt(request.getParameter("CustID"));
	FromAccountSelection = request.getParameter("TransferFrom");
	ToAccountSelection = request.getParameter("TransferTo");
	TransactionStr = request.getParameter("AmountField");
	
	if(TransactionStr != null)
	{
		TransAmount = new Float(TransactionStr);
	}

	if(CustID != 0 && FromAccountSelection != "" && ToAccountSelection != "" && TransAmount > 0.00f)
	{
		if(FromAccountSelection.contentEquals("Checking") && ToAccountSelection.contentEquals("Savings"))
		{
			TransferControlWeb trcw = new TransferControlWeb(CustID, "Checking", "Savings", TransAmount);
			confirmTransferBal = trcw.doTransfer();
		}
		else if(FromAccountSelection.contentEquals("Savings") && ToAccountSelection.contentEquals("Checking"))
		{
			TransferControlWeb trcw = new TransferControlWeb(CustID, "Savings", "Checking", TransAmount);
			confirmTransferBal = trcw.doTransfer();
		}
	}
%>

<HTML>

<HEAD>
<style type="text/css">
	
	body {
		align: center;
	}
	
	select {
		width: 125px;
	}
	
	input[type=button] {
		width: 125px;
		background-color: lightblue;
		border-radius: 10px;
	}
	
	input[type=button]:hover {
		background-color: #F4FA58;
	}

</style>
</HEAD>


<BODY bgcolor='#F1F1FD' align="center">

	<h2>Please fill out the form to make a Transfer between Accounts</h2>

    <FORM NAME="TransferForm" ACTION="./Transfer.jsp" METHOD ="POST">
	<INPUT TYPE="Hidden" NAME='CustID' VALUE='<%=request.getParameter("CustID")%>'>
        <TABLE cellPadding=3 ALIGN='center'>

            <TR>
                <TD>Transfer From:</TD>
                <TD>
                    <select size="1" name="TransferFrom">
					<option selected value="Checking">Checking</option>
					<option value="Savings">Savings</option>    
					</select>
                </TD>
            </TR>
			
			<TR>
                <TD>Transfer To:</TD>
                <TD>
                    <select size="1" name="TransferTo">
					<option selected value="Checking">Checking</option>
					<option value="Savings">Savings</option>    
					</select>
                </TD>
            </TR>

            <TR bgcolor='#F1F1FD'>
                <TD>Amount to transfer:</TD>
                <TD>
                   <INPUT TYPE='text' NAME='AmountField' Value='' SIZE='15'>
                </TD>
            </TR>
          </TABLE><BR>

<DIV>
<INPUT TYPE="button" NAME='homeButton' VALUE='Main Menu' SIZE='15' ONCLICK="returnHome()">
<INPUT TYPE="Button" NAME='submitBNTN' VALUE='Complete Transfer' onclick="makeTransfer()">
</DIV>

</FORM>

</BODY>
</HTML>

<SCRIPT type="text/javascript">
	
	var cnfTransfer = <%=confirmTransferBal%>;
	
	if(cnfTransfer > 0)
	{
		alert("Your transfer was successful! Your balance is "+cnfTransfer);
		window.location = "./Welcome.jsp?CustID=<%=CustID%>";
	}
	else if(cnfTransfer == -1)
	{
		alert("There was a problem making a transfer between your accounts!  Please contact customer service!");
	}

	function makeTransfer(){
		var FromSelected = document.TransferForm.TransferFrom.selectedIndex;
		var FromSelectedOption = document.TransferForm.TransferFrom.options[FromSelected].value;
		var ToSelected = document.TransferForm.TransferTo.selectedIndex;
		var ToSelectedOption = document.TransferForm.TransferTo.options[ToSelected].value;
		var Amount = document.TransferForm.AmountField.value;
		var CustID = document.TransferForm.CustID.value;
		
		if(CustID != 0 && Amount > 0 && Amount && FromSelectedOption && ToSelectedOption && (FromSelectedOption != ToSelectedOption)) {
			document.TransferForm.submit();
		}
		else if (CustID == 0) {
			alert("There was a problem accessing your account.  Please contact customer service.");
		}
		else if (!Amount) {
			alert("You must provide an amount to transfer! Please input an amount and try again!");
		}
		else if (Amount <= 0) {
			alert("You must provide an amount greater than zero! Please try again!");
		}
		else if (!FromSelectedOption) {
			alert("You must select the account you wish to transfer from before proceeding.  Please try again!");
		}
		else if (!ToSelectedOption) {
			alert("You must select the account you wish to transfer to before proceeding.  Please try again!");
		}
		else if (FromSelectedOption == ToSelectedOption) {
			alert("Transfer From and Transfer To account selections must be different!  Please try again!");
		}
	}
	
	function returnHome() {
		window.location = "./Welcome.jsp?CustID=<%=CustID%>"
	}

</SCRIPT>