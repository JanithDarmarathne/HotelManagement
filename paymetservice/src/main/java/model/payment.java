package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class payment {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//http://localhost/phpmyadmin/index.php?route=/sql&db=hotel&table=payment
			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://http://localhost/phpmyadmin/index.php?route=/sql&db=hotel&table=payment","root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertProject(String payID, String payDate, String payAmt, String payDesc) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payment(`payID`, ` payDate`, `payAmt`, `payDesc`, )" + " values ( ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setString(1, payID);
			preparedStmt.setString(2, payDate);
			preparedStmt.setString(3, payAmt);
			preparedStmt.setString(4, payDesc);

			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the Project.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String readProject() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>Project ID</th><th>Project Name</th><th>Date</th><th>Category</th><th>Details</th></tr>";
			String query = "select * from projects1";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String payID = Integer.toString(rs.getInt("payID"));
				String payDate = rs.getString("payDate");
				String payAmt = rs.getString("payAmt");
				String payDesc = rs.getString("payDesc");

				output += "<tr><td>" + payID + "</td>";
				output += "<td>" + payDate + "</td>";
				output += "<td>" + payAmt + "</td>";
				output += "<td>" + payDesc + "</td>";

			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the Project.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	
	public String updateProject(String payID, String payDate, String payAmt, String payDesc) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE projects1 SET payID=?,payDate=?,payAmt=?,payDesc=? WHERE payID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setString(1, payID);
			preparedStmt.setString(2, payDate);
			preparedStmt.setString(3, payAmt);
			preparedStmt.setString(4, payDesc);
			preparedStmt.setString(5, payID);

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the Project.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	
	public String deleteProject(String payID) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from projects1 where roomID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(payID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the Project.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
