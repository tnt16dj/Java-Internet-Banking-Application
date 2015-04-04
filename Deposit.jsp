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

<% 	float confirmDepositBal = -2.00f; 
	Integer CustID;
	String AccountSelection;
	String TransactionStr = new String("");
	float TransAmount = 0.00f;
	
	CustID = Integer.parseInt(request.getParameter("CustID"));
	AccountSelection = request.getParameter("CheckingOrSavings");
	TransactionStr = request.getParameter("AmountField");
	
	if(TransactionStr != null)
	{
		TransAmount = new Float(TransactionStr);
	}

	if(CustID != 0 && AccountSelection != "" && TransAmount > 0.00f)
	{
		if(AccountSelection.contentEquals("Checking"))
		{
			DepositControlWeb dpcw = new DepositControlWeb(CustID, "Checking", TransAmount);
			confirmDepositBal = dpcw.doDeposit();
		}
		else if(AccountSelection.contentEquals("Savings"))
		{
			DepositControlWeb dpcw = new DepositControlWeb(CustID, "Savings", TransAmount);
			confirmDepositBal = dpcw.doDeposit();
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

	<h2>Please fill out the form to make a Deposit</h2>

    <FORM NAME="DepositForm" ACTION="./Deposit.jsp" METHOD ="POST">
	<INPUT TYPE="Hidden" NAME='CustID' VALUE='<%=request.getParameter("CustID")%>'>
        <TABLE cellPadding=3 ALIGN='center'>

            <TR>
                <TD>Deposit To:</TD>
                <TD>
                    <select size="1" name="CheckingOrSavings">
					<option selected value="Checking">Checking</option>
					<option value="Savings">Savings</option>    
					</select>
                </TD>
            </TR>

            <TR>
                <TD>Amount to Deposit:</TD>
                <TD>
                   <INPUT TYPE='text' NAME='AmountField' Value='' SIZE='15'>
                </TD>
            </TR>
          </TABLE><BR>

<div>
<INPUT TYPE="button" NAME='homeButton' VALUE='Main Menu' SIZE='15' ONCLICK="returnHome()">
<INPUT TYPE="Button" NAME='submitBNTN' VALUE='Complete Deposit' onclick="makeDeposit()">
</div>

</FORM>

</BODY>
</HTML>
<SCRIPT type="text/javascript">
	
	var cnfDeposit = <%=confirmDepositBal%>;
	
	if(cnfDeposit > 0)
	{
		alert("Your deposit was successful! Your balance is "+cnfDeposit);
		window.location = "./Welcome.jsp?CustID=<%=CustID%>";
	}
	else if(cnfDeposit == -1)
	{
		alert("There was a problem making a deposit to your account!  Please contact customer service!");
	}

	function makeDeposit(){
		var Selected = document.DepositForm.CheckingOrSavings.selectedIndex;
		var SelectedOption = document.DepositForm.CheckingOrSavings.options[Selected].value;
		var Amount = document.DepositForm.AmountField.value;
		var CustID = document.DepositForm.CustID.value;
		
		if(CustID != 0 && Amount > 0 && Amount && SelectedOption) {
			document.DepositForm.submit();
		}
		else if (CustID == 0) {
			alert("There was a problem accessing your account.  Please contact customer service.");
		}
		else if (!Amount) {
			alert("You must provide an amount to deposit! Please input an amount and try again!");
		}
		else if (Amount <= 0) {
			alert("You must provide an amount greater than zero! Please try again!");
		}
		else if (!SelectedOption) {
			alert("You must select an account before making a deposit.  Please try again!");
		}

	}
	
	function returnHome() {
		window.location = "./Welcome.jsp?CustID=<%=CustID%>"
	}

</SCRIPT>