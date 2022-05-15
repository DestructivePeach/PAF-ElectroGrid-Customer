package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {

	//1. Create a Connection method 
		public Connection connect() 
		{ 
		     Connection con = null; 
		 
		     try { 
		             Class.forName("com.mysql.jdbc.Driver"); 
		            
		              //Provide the correct details: DBServer/DBName, username, password 
		             con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/powergridsystem","root", ""); 
		               
		             //For testing
		             System.out.print("Successfully connected"); 
		            	   
		     } 
		     catch(Exception e) { 
		             e.printStackTrace(); 
		     } 
		 
		     return con; 
		}
		
		
		
		//2. Create the method for get all the Customer details
		public String readCustomer() {
			
			   String output = ""; 
			   
			   try { 
			          Connection con = connect(); 
			          
			          if (con == null)  { 
			             return "Error while connecting to the database for reading."; 
			          } 
			 
			          // Prepare the html table to be displayed
			          output = "<table border='1'" 
			                   +"<tr><th>Account Number</th><th>First Name</th>"
			                   +"<th>Last Name</th><th>Address</th><th>Email</th>"
			                   +"<th>Contact Number</th>"
			                   + "<th>Update</th><th>Remove</th></tr>";              
			          
			          String query = "select * from customer"; 
			          Statement stmt = con.createStatement(); 
			          ResultSet rs = stmt.executeQuery(query); 
			
			          // iterate through the rows in the result set
			          while (rs.next()) { 
			        	  
			        	  	 String accountNumber = Integer.toString(rs.getInt("accountNumber")); 
				             String firstName = rs.getString("firstName");  
				             String lastName = rs.getString("lastName"); 
				             String address = rs.getString("address"); 
				             String emailId = rs.getString("emailId"); 
				             String contactNumber = rs.getString("contactNumber"); 
				              
				             
			 
				             // Add a row into the html table
				              
				             output += "<td>" + accountNumber + "</td>"; 
				             output += "<td>" + firstName + "</td>"; 
				             output += "<td>" + lastName + "</td>";
				             output += "<td>" + address + "</td>";
				             output += "<td>" + emailId + "</td>";
				             output += "<td>" + contactNumber + "</td>";
				             
			 
				          // buttons
				     		output+= "<td><input name='btnUpdate' type='button' value='Update' "
				     		+ "class='btnUpdate btn btn-secondary' data-accountnumber='" + accountNumber + "'></td>"
				     		+ "<td><input name='btnRemove' type='button' value='Remove' "
				     		+ "class='btnRemove btn btn-danger' data-accountnumber='" + accountNumber + "'></td></tr>"; 
			         } 
			 
			          con.close(); 
			         
			          // Complete the html table
			          output += "</table>"; 
			 
			    } 
			    catch (Exception e)  { 
			             output = "Error while reading data."; 
			             System.err.println(e.getMessage()); 
			    } 
			       
			          return output; 
			}
		//add Customer details 
		public String insertCustomer(String accountNumber,String firstName,String lastName, String address, String emailId, String contactNumber) {
			 
			 
		       String output = "";
				
			   try{
					  
				   Connection con = connect();
			
				   if (con == null) {
						   return "Error while connecting to the database";
				   }
				
				   
				
					  
					  
				   // create a prepared statement
				   String query = "insert into customer(accountNumber,firstName,lastName,address,emailId,contactNumber)" + " values (?,?,?,?,?,?)";
			
	              
				   PreparedStatement preparedStmt = con.prepareStatement(query);
			
				   // binding values
				   
				   preparedStmt.setInt(0, Integer.parseInt(accountNumber));
				   preparedStmt.setString(1, firstName);
				   preparedStmt.setString(2, lastName);
				   preparedStmt.setString(3, address);
				   preparedStmt.setString(4, emailId);
				   preparedStmt.setString(5, contactNumber);
				   
				   
				   //execute the statement
				   preparedStmt.execute();
				   con.close();
			
				   
				   String newCustomer = readCustomer(); 
				   output = "{\"status\":\"success\", \"data\": \"" +  newCustomer + "\"}"; 
				
				 } 
				 
				 catch (Exception e) 
				 { 
				    output = "{\"status\":\"error\", \"data\": \"Error while generating the Customer.\"}"; 
				 
				    System.err.println(e.getMessage()); 
				 } 
				 
				      return output; 
		} 
		//update Customer details
		public String updateCustomer(String accountNumber,String firstName,String lastName, String address, String emailId, String contactNumber, String string) {
			  String output = ""; 
			
			  try
			  { 
			      Connection con = connect(); 
			     
			      if (con == null) 
			      {
			    	  return "Error while connecting to the database for updating."; 
			      } 
			
			      
			      // create a prepared statement
			      String query = "UPDATE customer SET firstName=?firstName=?,lastName=?,address=?,emailId=?,contactNumber=?  WHERE accountNumber=?"; 
			
			      PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			      
			      // binding values
			       preparedStmt.setInt(0, Integer.parseInt(accountNumber));
				   preparedStmt.setString(1, firstName);
				   preparedStmt.setString(2, lastName);
				   preparedStmt.setString(3, address);
				   preparedStmt.setString(4, emailId);
				   preparedStmt.setString(5, contactNumber);
			       
			
			        // execute the statement
			        preparedStmt.execute(); 
			        con.close(); 
			        String newCustomer = readCustomer(); 
				    output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}"; 
			        
			      
			 } 
			  
			 catch (Exception e) 
			 { 
				 output = "{\"status\":\"error\", \"data\": \"Error while updating the Customer Details.\"}";  
			        System.err.println(e.getMessage()); 
			 } 
			 
			  return output;
		}
		
		//delete Customer details
		public String deleteCustomer(String accountNumber)
		{ 
		        String output = ""; 
		        
		        try{ 
		        
		        	    Connection con = connect(); 
		               
		        	    if (con == null) { 
		                    return "Error while connecting to the database for deleting."; 
		                } 
		        	    
		        	      // create a prepared statement
		        	      String query = "delete from customer where accountNumber=?"; 
		        	      
		        	      PreparedStatement preparedStmt = con.prepareStatement(query); 
		        	    
		        	      // binding values
		        	      preparedStmt.setInt(1, Integer.parseInt(accountNumber)); 
		        	     
		        	      // execute the statement
		        	      preparedStmt.execute(); 
		        	    
		        	      con.close(); 
		        	    
		        	      String newCustomer = readCustomer(); 
		   		       output = "{\"status\":\"success\", \"data\": \"" +  newCustomer + "\"}";
		        	    
		        } 
		        catch (Exception e) { 
		              
		        	output = "{\"status\":\"error\", \"data\": \"Error while deleting the Customer Details.\"}";  
		                 System.err.println(e.getMessage()); 
		        }
		        
		        return output; 
		}
}
		
		