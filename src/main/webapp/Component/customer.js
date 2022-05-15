$(document).ready(function() 
{ 
if ($("#alertSuccess").text().trim() == "") 
 { 
 $("#alertSuccess").hide(); 
 } 
 $("#alertError").hide(); 
}); 

//save
$(document).on("click", "#btnSave", function(event) 
{ 
// Clear alerts---------------------
 $("#alertSuccess").text(""); 
 $("#alertSuccess").hide(); 
 $("#alertError").text(""); 
 $("#alertError").hide(); 
// Form validation-------------------
var status = validateCustomerForm(); 
if (status != true) 
 { 
 $("#alertError").text(status); 
 $("#alertError").show(); 
 return; 
 } 
// If valid------------------------
var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT"; 
 $.ajax( 
 { 
 url : "CustomerAPI", 
 type : type, 
 data : $("#formCustomer").serialize(), 
 dataType : "text", 
 complete : function(response, status) 
 { 
 onCustomerSaveComplete(response.responseText, status); 
 } 
 }); 
});

function onCustomerSaveComplete(response, status) 
{ 
     if (status == "success") 
     { 
        var resultSet = JSON.parse(response); 
 
        if (resultSet.status.trim() == "success") 
       { 
          $("#alertSuccess").text("Successfully saved."); 
          $("#alertSuccess").show(); 
          $("#divCustomerGrid").html(resultSet.data); 
       
       } else if (resultSet.status.trim() == "error") 
       { 
           $("#alertError").text(resultSet.data); 
           $("#alertError").show(); 
           
       } 
       } else if (status == "error") 
       { 
         $("#alertError").text("Error while saving."); 
         $("#alertError").show(); 
         
       } else
       { 
          $("#alertError").text("Unknown error while saving.."); 
          $("#alertError").show(); 
      } 
 
          $("#hidCustomerIDSave").val(""); 
          $("#formCustomer")[0].reset(); 
}



// UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event) 
{ 
$("#hidCustomerIDSave").val($(this).data("accountNumber"));
 $("#accountNumber").val($(this).closest("tr").find('td:eq(0)').text());   
 $("#firstName").val($(this).closest("tr").find('td:eq(1)').text()); 
 $("#lastName").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#address").val($(this).closest("tr").find('td:eq(3)').text()); 
 $("#emailId").val($(this).closest("tr").find('td:eq(4)').text()); 
 $("#contactNumber").val($(this).closest("tr").find('td:eq(5)').text()); 
});


 
 //delete
 $(document).on("click", ".btnRemove", function(event) 
{ 
 $.ajax( 
 { 
 url : "CustomerAPI", 
 type : "DELETE", 
 data : "accountNumber=" + $(this).data("accountnumber"),
 dataType : "text", 
 complete : function(response, status) 
 { 
 onCustomerDeleteComplete(response.responseText, status); 
 } 
 }); 
});
 
 
 
function onCustomerDeleteComplete(response, status) 
{ 
     if (status == "success") 
     { 
        var resultSet = JSON.parse(response); 
 
        if (resultSet.status.trim() == "success") 
       { 
          $("#alertSuccess").text("Successfully deleted."); 
          $("#alertSuccess").show(); 
          $("#divCustomerGrid").html(resultSet.data); 
       
       } else if (resultSet.status.trim() == "error") 
       { 
           $("#alertError").text(resultSet.data); 
           $("#alertError").show(); 
           
       } 
       } else if (status == "error") 
       { 
         $("#alertError").text("Error while deleting."); 
         $("#alertError").show(); 
         
       } else
       { 
          $("#alertError").text("Unknown error while deleting.."); 
          $("#alertError").show(); 
      } 
 
          $("#hidCustomerIDSave").val(""); 
          $("#formCustomer")[0].reset(); 
}

 
// CLIENT-MODEL================================================================
function validateCustomerForm() 
{ 
	
	// Account Number
	if ($("#accountNumber").val().trim() == "") 
	 { 
	 return "Insert Account Number."; 
	 }
	// First Name-------------------------------
	if ($("#firstName").val().trim() == "") 
	 { 
	 return "Insert First Name."; 
	 } 
	// Last Name
	if ($("#lastName").val().trim() == "") 
	 { 
	 return "Insert Last Name."; 
	 } 
	// Address------------------------
	if ($("#address").val().trim() == "") 
	 { 
	 return "Insert Address."; 
	 } 
	 
	var tmpNum = $("#contactNumber").val().trim(); 
	if (!$.isNumeric(tmpNum)) 
	 { 
	 return "Insert a numerical value for Contact Number."; 
	 }
	 
	 
return true; 
}