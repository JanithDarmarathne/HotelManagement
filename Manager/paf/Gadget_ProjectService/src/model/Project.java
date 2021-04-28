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
					"jdbc:mysql://localhost:3306/gadgetproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertProject(String loginId, String loginUser, String loginPass, String loginEmail) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into projects1(`loginId`, `loginUser`, `loginPass`, `loginEmail`, )" + " values ( ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, loginId);
			preparedStmt.setString(2, loginUser);
			preparedStmt.setString(3, loginPass);
			preparedStmt.setString(4, loginEmail);

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
				String loginId = Integer.toString(rs.getInt("loginId"));
				String loginUser = rs.getString("loginUser");
				String loginPass = rs.getString("loginPass");
				String loginEmail = rs.getString("loginEmail");

				output += "<tr><td>" + loginId + "</td>";
				output += "<td>" + loginUser + "</td>";
				output += "<td>" + loginPass + "</td>";
				output += "<td>" + loginEmail + "</td>";

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

	
	public String updateProject(String loginId, String loginUser, String loginPass, String loginEmail) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE projects1 SET loginId=?,loginUser=?,loginPass=?,loginEmail=? WHERE loginId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values

			preparedStmt.setInt(1, loginId);
			preparedStmt.setString(2, loginUser);
			preparedStmt.setString(3, loginPass);
			preparedStmt.setString(4, loginEmail);
			preparedStmt.setInt(5, Integer.parseInt(loginId));

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

	
	public String deleteProject(String loginId) {

		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from projects1 where loginId=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(loginId));

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
