<%@ page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

    <!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Customer Management</title>
<link rel="stylesheet" href="Views/bootstrap.css">
<script src="Component/jquery-3.6.0.min.js"></script>
<script src="Component/customer.js"></script>
</head>
<body> 

	<nav class="navbar navbar-light" style="background-color: #50C878;">
		<span class="navbar-brand mb-0 h1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ELECTRO GRID (EG) - POWER GRID MANAGEMENT SYSTEM</span>
	   </nav>
       <br>
    
       <div class="container"><div class="row"><div class="col-9">

				<h3 class="m-3" style="text-align:center">Customer Management</h3>
	<form id="formCustomer" name="formCustomer">
		 
		 Account Number: 
		 <input id="accountNumber" name="accountNumber" type="text" class="form-control form-control-sm">
		 <br> 
		 
		 First Name: 
		 <input id="firstName" name="firstName" type="text"  class="form-control form-control-sm">
		 <br> 
		 
		 Last Name: 
		 <input id="lastName" name="lastName" type="text" class="form-control form-control-sm">
		 <br>
		 
		 Address: 
		 <input id="address" name="address" type="text"  class="form-control form-control-sm">
		 <br> 
		 
		 Email: 
		 <input id="emailId" name="emailId" type="text"  class="form-control form-control-sm">
		 <br> 
		 
		 Contact Number: 
		 <input id=contactNumber name="contactNumber" type="text"  class="form-control form-control-sm">
		 <br> 
		 
		 <input id="btnSave" name="btnSave" type="button" style="background-color: #4CAF50; margin-left:325px; width:17%; font-size:20px" value="Save" class="btn btn-primary">
		 <input type="hidden" id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
		</form>
	
	<br>
	<div id="alertSuccess" class="alert alert-success"></div>
	<div id="alertError" class="alert alert-danger"></div>
	
	
	<div id="divCustomerGrid">
 
			 <%
			 Customer customerObj = new Customer(); 
			 out.print(customerObj.readCustomer()); 
			 %>
 
</div>
</div> </div> </div> 
</body>
</html>