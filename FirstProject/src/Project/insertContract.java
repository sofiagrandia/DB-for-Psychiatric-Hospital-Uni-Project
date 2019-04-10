package Project;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class insertContract {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


	public static void main(String args[]) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/company.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Database connection opened.");
			
			System.out.println("Please, input the contract info:");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.print("Id: ");
			String stringLeido = reader.readLine();
			int id = Integer.parseInt (stringLeido);
			System.out.print("Money: ");
			stringLeido = reader.readLine();
			float money= Float.parseFloat(stringLeido);
			System.out.print("Holidays: ");
			stringLeido = reader.readLine();
			int holidays = Integer.parseInt (stringLeido);
			System.out.print("Duration (yyyy-MM-dd): ");
			String duration = reader.readLine();
			LocalDate durationDate = LocalDate.parse(duration, formatter);
			
			Statement stmt = c.createStatement();
			String sql = "INSERT INTO contract (id,money,duration,holidays)"
			+ "VALUES ('" + id + "', '" + money	+ "' , '" + duration + "' ,'"+ holidays +"');";
			
			stmt.executeUpdate(sql);
			stmt.close();
			System.out.println("Contract info processed");
			System.out.println("Records inserted.");
		
		c.close();
		System.out.println("Database connection closed.");
	} catch (Exception e) {
		e.printStackTrace();
	}

			
			
			
		}

}
