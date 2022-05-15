package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.util.HashMap; 
import java.util.Map; 
import java.util.Scanner;

/**
 * Servlet implementation class customerAPI
 */
@WebServlet("/customerAPI")
public class customerAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	customer customerObj = new customer(); 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 String output = customerObj.insertCustomer(request.getParameter("customerCode"), 
			 request.getParameter("customerName"), 
			request.getParameter("customerPhone"), 
			request.getParameter("customerDesc")); 
			response.getWriter().write(output); 
			}
	
	// Convert request parameters to a Map
	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
	 scanner.useDelimiter("\\A").next() : ""; 
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
	
	protected void doPut(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = customerObj.updateCustomer(paras.get("hidCustomerIDSave").toString(), 
			 paras.get("customerCode").toString(), 
			paras.get("customerName").toString(), 
			paras.get("customerPhone").toString(), 
			paras.get("customerDesc").toString()); 
			response.getWriter().write(output); 
			} 
			protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
			 throws ServletException, IOException 
			{ 
			 Map paras = getParasMap(request); 
			 String output = customerObj.deleteCustomer(paras.get("customerID").toString()); 
			response.getWriter().write(output); 
			}
	
}

