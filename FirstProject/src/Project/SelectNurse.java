package Project;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.format.DateTimeFormatter;

import Project.Nurse;

public class SelectNurse {
	
	public static void main(String args[]) {
		try {
			// Open database connection
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/tables.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			// Retrieve data: begin
			Statement stmt = c.createStatement();
			String sql = "SELECT * FROM nurse";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date dob = rs.getDate("dob");
				int hours = rs.getInt("hours");
				
				Nurse nurse1 = new Nurse (id, name, gender, dob, hours);
				
				System.out.println(nurse1);
			}
			rs.close();
			stmt.close();
			System.out.println("Search finished.");
			// Retrieve data: end
			
			// Close database connection
			c.close();
			System.out.println("Database connection closed.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}