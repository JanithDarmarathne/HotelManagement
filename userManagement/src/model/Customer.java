package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//import java.sql.*; 
public class Customer
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.cj.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/user", "root", ""); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 
public String insertCustomer(String cusId,String identity, String name, String email, String address) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = " insert into customer (`customerID`,`customerIdentity`,`customerName`,`customerEmail`,`customerAddress`)"
 + " values (?, ?, ?, ?, ?)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1, cusId); 
 preparedStmt.setString(2, identity); 
 preparedStmt.setString(3, name); 
 preparedStmt.setString(4, email); 
 preparedStmt.setString(5, address); 
// execute the statement3
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String readCustomer() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Customer Identity</th><th>Customer Name</th>" +
 "<th>Customer Email</th>" + 
 "<th>Customer Address</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from customer"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String customerID = rs.getString("customerID"); 
 String customerIdentity = rs.getString("customerIdentity"); 
 String customerName = rs.getString("customerName"); 
 String customerEmail = rs.getString("customerEmail"); 
 String customerAddress = rs.getString("customerAddress"); 
 // Add into the html table
 output += "<tr><td>" + customerIdentity + "</td>"; 
 output += "<td>" +customerName + "</td>"; 
 output += "<td>" + customerEmail + "</td>"; 
 output += "<td>" + customerAddress + "</td>"; 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update'  class='btn btn-secondary'></td>"
 + "<td><form method='post' action='items.jsp'>"+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
 + "<input name='itemID' type='hidden' value='" + customerID 
 + "'>" + "</form></td></tr>"; 
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
public String updateCustomer(String customerID, String identity, String name, String email, String address)
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for updating."; } 
 // create a prepared statement
 String query = "UPDATE customer SET customerID =?,customerName=?,customerEmail=?,customerAddress=? WHERE customerIdentity=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values

 preparedStmt.setString(1, customerID); 
 preparedStmt.setString(2, name); 
 preparedStmt.setString(3, email); 
 preparedStmt.setString(4,address); 
 preparedStmt.setString(5,identity); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Updated successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while updating the customer."; 
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
 {return "Error while connecting to the database for deleting."; } 
 // create a prepared statement
 String query = "delete from customer where customerID=?"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 // binding values
 preparedStmt.setString(1,customerID); 
 // execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Deleted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while deleting the customer."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
}
