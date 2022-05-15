package com;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustomerAPI")
public class CustomerAPI extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	Customer customerObj = new Customer();
    
    public CustomerAPI() {
        
    	super();    
    } 
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	
		String output = customerObj.insertCustomer(
				                           request.getParameter("accountNumber"), 
				                           request.getParameter("firstName"), 
				                           request.getParameter("lastName"),
				                           request.getParameter("address"),
				                           request.getParameter("emailId"), 
				                           request.getParameter("contactNumber"));
        response.getWriter().write(output);
	}


    private static Map getParasMap(HttpServletRequest request) 
 	{ 
 	     Map<String, String> map = new HashMap<String, String>(); 
 	     try
 	     { 
 	         Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
 	         String queryString = scanner.hasNext() ?  scanner.useDelimiter("\\A").next() : ""; 
 	         scanner.close(); 
 	        
 	         String[] params = queryString.split("&"); 
 	         for (String param : params) 
 	         { 
 	              String[] p = param.split("="); 
 	              map.put(p[0], p[1]); 
 	         } 
 	     } 
 	     catch (Exception e) 
 	    { 
 	    } 
 	   return map; 
 	}
    
    protected void doPut(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException 
	{ 
			 Map paras = getParasMap(request); 
			 
			 String output = customerObj.updateCustomer(paras.get("hidCustomerIDSave").toString(), 
			                 paras.get("accountNumber").toString(), 
			                 paras.get("firstName").toString(), 
			                 paras.get("lastName").toString(),
			                 paras.get("address").toString(),
			                 paras.get("emailId").toString(), 
			                 paras.get("contactNumber").toString()); 
			     response.getWriter().write(output); 
	} 
    
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
			 Map paras = getParasMap(request); 
			 
			 String output = customerObj.deleteCustomer(paras.get("accountNumber").toString()); 
			
			 response.getWriter().write(output); 
	}
    
}