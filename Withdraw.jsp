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

<% 	float confirmWithdrawBal = -2.00f; 
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
			WithdrawControlWeb wthw = new WithdrawControlWeb(CustID, "Checking", TransAmount);
			confirmWithdrawBal = wthw.doWithdraw();
		}
		else if(AccountSelection.contentEquals("Savings"))
		{
			WithdrawControlWeb wthw = new WithdrawControlWeb(CustID, "Savings", TransAmount);
			confirmWithdrawBal = wthw.doWithdraw();
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

	<h2>Please fill out the form to make a Withdrawal</h2>

    <FORM NAME="WithdrawForm" ACTION="./Withdraw.jsp" METHOD ="POST">
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
                <TD>Amount to Withdraw:</TD>
                <TD>
                   <INPUT TYPE='text' NAME='AmountField' Value='' SIZE='15'>
                </TD>
            </TR>
          </TABLE><BR>

<DIV>		  
<INPUT TYPE="button" NAME='homeButton' VALUE='Main Menu' SIZE='15' ONCLICK="returnHome()">
<INPUT TYPE="Button" NAME='submitBNTN' VALUE='Complete Withdraw' onclick="makeWithdraw()">
</DIV>

</FORM>

</BODY>
</HTML>
<SCRIPT type="text/javascript">
	
	var cnfWithdraw = <%=confirmWithdrawBal%>;
	
	if(cnfWithdraw > 0)
	{
		alert("Your withdrawal was successful! Your balance is "+cnfWithdraw);
		window.location = "./Welcome.jsp?CustID=<%=CustID%>";
	}
	else if(cnfWithdraw == -1)
	{
		alert("There was a problem making a withdrawal from your account!  Please contact customer service!");
	}

	function makeWithdraw(){
		var Selected = document.WithdrawForm.CheckingOrSavings.selectedIndex;
		var SelectedOption = document.WithdrawForm.CheckingOrSavings.options[Selected].value;
		var Amount = document.WithdrawForm.AmountField.value;
		var CustID = document.WithdrawForm.CustID.value;
		
		if(CustID != 0 && Amount > 0 && Amount && SelectedOption) {
			document.WithdrawForm.submit();
		}
		else if (CustID == 0) {
			alert("There was a problem accessing your account.  Please contact customer service.");
		}
		else if (!Amount) {
			alert("You must provide an amount to withdrawal! Please input an amount and try again!");
		}
		else if (Amount <= 0) {
			alert("You must provide an amount greater than zero! Please try again!");
		}
		else if (!SelectedOption) {
			alert("You must select an account before making a withdrawal.  Please try again!");
		}

	}
	
	function returnHome() {
		window.location = "./Welcome.jsp?CustID=<%=CustID%>"
	}

</SCRIPT>