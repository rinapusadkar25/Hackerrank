<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>Registration Page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/foundation/6.3.1/css/foundation.css">
<style>
	.error {
		color: red;
	}
	
body{
 Margin: 0 auto;
 Width: 60%;
 background-color: #f7f7f7;
 position: relative;
 }
 table{
 position: absolute;
 }
 
 h2{
 margin-top: 100px;
 color: white;
 background-color: #3b5998;
  border-radius:10px;
 }
 
td{
width:500px;
}
  td:hover{
 background-color: 	#dfe3ee;
font-weight: bold;
Border-radius:10px;

 }
 button {
 	margin-top: 240px;
    -webkit-transition-duration: 0.4s; /* Safari */
    transition-duration: 0.4s;
    width:90px;
    height:50px;
    border: 2px solid #3b5998;
     }

button:hover {
    background-color: #3b5998; /* Green */
    color: white;
}
 </style>

</head>
<body class="body">
<center><h2 class="h2">Perform Transaction Page</h2></center>

<form:form method="POST" commandName="/BaseCodeSlice_BankManagementSystem/mvc/performtransaction" modelAttribute="Transaction">
<table>
    <tr>
        <td >Transaction Description:</td>
        <td><form:input path="transactiondescription"  placeholder="Transaction Description" maxlength="30"/></td>
        <td><form:errors path="transactiondescription" cssClass="error" /></td>
    </tr>
    <tr>
        <td>Transaction Type :</td>
        <td><form:select  path="transactiontype" maxlength="10" class="form-control"
						id="transaction type" placeholder="Transaction Type" >
						<option value="" disabled selected>Select account type</option>
						<option value="deposit">Deposit</option>
						<option value="withdrawl">Withdrawl</option>
						</form:select></td>
        <td><form:errors path="transactiontype" cssClass="error" /></td>
    </tr>
    <tr>
        <td>Transaction Amount :</td>
        <td><form:input  type="number"  path="transactionAmount"  placeholder="Transaction Amount" maxlength="10"  /></td>
        <td><form:errors path="transactionAmount" cssClass="error" /></td>
    </tr>
    <!-- <tr>
    <td></td>
       <td colspan="2"><input type="submit" value="Add"></td>
    </tr> -->
</table>
		<center><button  type="submit" value="Add">Add</button></center>
</form:form>

</body>
</html>