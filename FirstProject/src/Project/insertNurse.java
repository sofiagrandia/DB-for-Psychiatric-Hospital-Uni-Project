package Project;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class insertNurse {
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	

	public static void main(String args[]) {
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/tables.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			System.out.println("Please, input the nurse info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Id: ");
			String stringLeido = reader.readLine();
			int id = Integer.parseInt (stringLeido);
			System.out.print("Name: ");
			String name = reader.readLine();
			System.out.print("Gender: ");
			String gender = reader.readLine();
			System.out.print("Hours: ");
			stringLeido = reader.readLine();
			int hours= Integer.parseInt (stringLeido);
			System.out.print("Date of Birth (yyyy-MM-dd): ");
			String dob = reader.readLine();
			LocalDate dobDate = LocalDate.parse(dob, formatter);
			
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO nurse (id,name,gender,dob,hours)"
			+ "VALUES ('" + id + "', '" + name	+ "' , '" + gender + "' ,'"+ dob +"','" + hours + "');";
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Nurse info processed");
			System.out.println("Records inserted.");
		
		c.close();
		System.out.println("Database connection closed.");
	} catch (Exception e) {
		e.printStackTrace();
	}

}}