package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Project {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/https://demo.phpmyadmin.net/master-config/index.php?route=/database/structure&&server=2&db=hotel1&table=",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertProject(String roomID, String roomName, String roomType, String bookingDate) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into projects1(`roomID`, `roomName`, `roomType`, `bookingDate`, )" + " values ( ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, roomID);
			preparedStmt.setString(2, roomName);
			preparedStmt.setString(3, roomType);
			preparedStmt.setString(4, bookingDate);

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
				String roomID = Integer.toString(rs.getInt("roomID"));
				String roomName = rs.getString("roomName");
				String roomType = rs.getString("roomType");
				String bookingDate = rs.getString("bookingDate");

				output += "<tr><td>" + roomID + "</td>";
				output += "<td>" + roomName + "</td>";
				output += "<td>" + roomType + "</td>";
				output += "<td>" + bookingDate + "</td>";

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

	
	public String updateProject(String roomID, String roomName, String roomType, String bookingDate) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE projects1 SET roomID=?,roomName=?,roomType=?,bookingDate=? WHERE roomID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setInt(1, roomID);
			preparedStmt.setString(2, roomName);
			preparedStmt.setString(3, roomType);
			preparedStmt.setString(4, bookingDate);
			preparedStmt.setInt(5, Integer.parseInt(roomID));

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

	
	public String deleteProject(String roomID) {

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
			preparedStmt.setInt(1, Integer.parseInt(roomID));

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
