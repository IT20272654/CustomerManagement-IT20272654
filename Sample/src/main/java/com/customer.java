package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class customer {

		private Connection connect() 
		{ 
			Connection con = null; 
			try
			{ 
				Class.forName("com.mysql.jdbc.Driver"); 
				con = 
						DriverManager.getConnection( 
								"jdbc:mysql://127.0.0.1:3306/electrogrid", "root", "9909"); 
			} 
			catch (Exception e) 
			{ 
				e.printStackTrace(); 
			} 
			return con; 
		} 
		public String readCustomer() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{ 
					return "Error while connecting to the database for reading."; 
				} 
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Customer Code</th> <th>Customer Name</th><th>Customer Phone</th>"+ "<th>Customer Description</th> <th>Update</th><th>Remove</th></tr>"; 
				String query = "select * from customer"; 
				Statement stmt = con.createStatement(); 
				ResultSet rs = stmt.executeQuery(query); 
				// iterate through the rows in the result set
				while (rs.next()) 
				{ 
					String customerID = Integer.toString(rs.getInt("customerID")); 
					String customerCode = rs.getString("customerCode"); 
	 
					String customerName = rs.getString("customerName"); 
					String customerPhone = rs.getString("customerPhone"); 
					String customerDesc = rs.getString("customerDesc"); 
					// Add into the html table
					output += "<tr><td><input id='hidCustomerIDUpdate' name='hidCustomerIDUpdate' type='hidden' value='" + customerID+ "'>" + customerCode + "</td>"; output += "<td>" + customerName + "</td>"; output += "<td>" + customerPhone + "</td>"; output += "<td>" + customerDesc + "</td>"; 
					// buttons
					output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>" + "<td><input name='btnRemove' type='button' value='Remove'  class='btnRemove btn btn-danger'  data-customerid='" + customerID + "'>" + "</td></tr>"; 
				} 
				con.close(); 
				// Complete the html table
				output += "</table>"; 
			} 
			catch (Exception e) 
			{ 
				output = "Error while reading the customer."; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
		public String insertCustomer(String code, String name, String phone, String desc) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{ 
					return "Error while connecting to the database for inserting."; 
				} 
				// create a prepared statement
				String query = " insert into customer (`customerID`,`customerCode`,`customerName`,`customerPhone`,`customerDesc`)" + " values (?, ?, ?, ?, ?)"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, 0); 
				preparedStmt.setString(2, code); 
				preparedStmt.setString(3, name); 
				preparedStmt.setString(4, phone); 
				preparedStmt.setString(5, desc); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newCustomer = readCustomer(); 
				output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}"; 
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while inserting the customer.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
		public String updateCustomer(String ID, String code, String name, String phone, String desc) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{ 
					return "Error while connecting to the database for updating."; 
				} 
				// create a prepared statement
				String query = "UPDATE customer SET customerCode=?,customerName=?,customerPhone=?,customerDesc=? WHERE customerID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setString(1, code); 
				preparedStmt.setString(2, name); 
				preparedStmt.setString(3, phone); 
				preparedStmt.setString(4, desc); 
				preparedStmt.setInt(5, Integer.parseInt(ID)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newCustomer = readCustomer(); 
				output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}"; 
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while updating the customer.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
		public String deleteCustomer(String customerID) 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
				if (con == null) 
				{ 
					return "Error while connecting to the database for deleting."; 
				} 
				// create a prepared statement
				String query = "delete from customer where customerID=?"; 
				PreparedStatement preparedStmt = con.prepareStatement(query); 
				// binding values
				preparedStmt.setInt(1, Integer.parseInt(customerID)); 
				// execute the statement
				preparedStmt.execute(); 
				con.close(); 
				String newCustomer = readCustomer(); 
				output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}"; 
			} 
			catch (Exception e) 
			{ 
				output = "{\"status\":\"error\", \"data\": \"Error while deleting the customer.\"}"; 
				System.err.println(e.getMessage()); 
			} 
			return output; 
		} 
	}










	
	
	



