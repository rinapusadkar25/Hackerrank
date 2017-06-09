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
 Width: 90%;
 background-color: #f7f7f7;
 }
 
 div{
 	
 	 background-color: #dfe3ee;
 	 border-radius:10px;
 	 height: 410px;
 }
 h2{
	Margin-top:100px;
 color: white;
 background-color: #3b5998;
  border-radius:10px;
 }
th{
background-color:#8b9dc3;
border-radius: 10px;
}
 td{
  border-radius:10px;
	background-color: 	#f7f7f7;
	
 }
 tr{
 height:10px;
 }
 td:hover{
 background-color: 	#dfe3ee;
font-weight: bold;
 }
 input:hover{
 background-color: 	#dfe3ee;
 
 }
  button {
 	margin-top: 20px;
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
<body>

<form:form method="POST" commandName="/BaseCodeSlice_BankManagementSystem/mvc/addEducationLoan" modelAttribute="loan">

<center><h2>Loan Aplication</h2></center>
<table>
<tr>
	<td>
<table>
	
    <tr>
        <td>Loan Amount :</td>
        <td><form:input type="number" path="eduLoanAmount" placeholder="Loan Amount" maxlength="15"/></td>
        <td><form:errors path="eduLoanAmount" cssClass="error" /></td>
    </tr>
    <tr>
      
          <%java.text.DateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy");%>
          <td class="C">Loan Apply Date  :</td>
          <td><form:input path="eduLoanApplyDate" type="hidden" value="<%= df.format(new java.util.Date())%>"  disabled="disabled"/><%= df.format(new java.util.Date())%></td>
       
          <td><form:errors path="eduLoanApplyDate" cssClass="error" /></td>
      </tr>
      <tr>
        <td>Loan Duration:</td>
        <td><form:select  path="eduLoanDuration" maxlength="10" class="form-control"
						id="education loan"  >
						<option value="" disabled selected>Select Loan Duration</option>
						<option value="5">5</option>
						<option value="10">10</option>
						</form:select></td>
        <td><form:errors path="eduLoanDuration" cssClass="error" /></td>
    </tr>
     <tr>
        <td>Father's Annual Income :</td>
        <td><form:input type="number"  path="fatherAnnualIncome" placeholder="Annual Income" maxlength="15"/></td>
        <td><form:errors path="fatherAnnualIncome" cssClass="error" /></td>
    </tr>
   
    </table>
    </td>
    <td>
    <table>
   
     <tr>
        <td>Course Fee :</td>
        <td><form:input type="number"  path="courseFee" placeholder="Course Fee" maxlength="15"/></td>
        <td><form:errors path="courseFee" cssClass="error" /></td>
    </tr>
     <tr>
        <td>Course Name :</td>
        <td><form:input path="courseName" placeholder="Course Name" maxlength="40" /></td>
        <td><form:errors path="courseName" cssClass="error" /></td>
    </tr>
     <tr>
        <td>Father's Name :</td>
        <td><form:input path="fatherName"  placeholder="Father's Name" maxlength="40"/></td>
        <td><form:errors path="fatherName" cssClass="error" /></td>
    </tr>
     <tr>
        <td>Id Card Number :</td>
        <td><form:input path="idCardNumber"  placeholder="ID Number" maxlength="16"/></td>
        <td><form:errors path="idCardNumber" cssClass="error" /></td>
    </tr>
    
   </table>
   <td>
    </tr>
   <!--  <tr>
        <td colspan="2"><input type="submit" value="Add"></td>
    </tr> -->
</table>
<center><button type="submit" value="Add">Add</button></center>

</form:form>

</body>
</html>