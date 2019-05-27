package Project;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Tabless {
	public static void main(String args[]) {
		try {
			// Open database connection
			// CHOOSES THE DATA BASE THAT WE ARE USING
			Class.forName("org.sqlite.JDBC");
			//CONNECTS TO THE DATA BASE
			Connection c = DriverManager.getConnection("jdbc:sqlite:./db/tables.db");
			//ACTIVATES
			c.createStatement().execute("PRAGMA foreign_keys=ON");
			System.out.println("Opened correctly.");
			
			// Create tables: begin
			// OJO CÓMO SE ESCRIBE. LOS ESPACIOS, LAS COMAS, LAS COMILLLAS....
			Statement stmt1 = c.createStatement();
			String sql1 = "CREATE TABLE room "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT, "
					   + " type TEXT NOT NULL, "
					   + " floor  INTEGER NOT NULL)";
			stmt1.executeUpdate(sql1);
			stmt1.close();
			
			Statement stmt2 = c.createStatement();
			String sql2 = "CREATE TABLE patient "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL, "
					   + " gender  TEXT 	NOT NULL, "
					   + " dob      DATE	 NOT NULL, "
					   + " room_id   INTEGER NOT NULL, "
					   + " photo	BLOB,"
					   + " FOREIGN KEY (room_id) REFERENCES room (id),)";
			stmt2.executeUpdate(sql2);
			stmt2.close();
			Statement stmt3 = c.createStatement();
			String sql3 = "CREATE TABLE contract "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " money     REAL     NOT NULL, "
					   + " duration  DATETIME, "
					   + " holidays		INTEGER NOT NULL),)";
			stmt3.executeUpdate(sql3);
			stmt3.close();
			
			Statement stmt4 = c.createStatement();
			String sql4 = "CREATE TABLE doctor "
					   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
					   + " name     TEXT     NOT NULL," 
					   + " gender  TEXT 	NOT NULL," 
					   + " dob      DATE	 NOT NULL," 
					   + " hours   INTEGER NOT NULL," 
					   +" photo	BLOB," 
					   + "contract_id INTEGER,"
					   +" FOREIGN KEY (contract_id) REFERENCES contract (id),)";
			
			stmt4.executeUpdate(sql4);
			stmt4.close();
			
			Statement stmt5 = c.createStatement();
			String sql5 = "CREATE TABLE nurse"
					+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,"
					+ "gender TEXT NOT NULL,"
					+ "dob DATE NOT NULL,"
					+ "hours INTEGER NOT NULL,"
					+"photo BLOB,"
					+"contract_id INTEGER,"
					+"FOREIGN KEY (contract_id) REFERENCES contract (id),)";
			stmt5.executeUpdate(sql5);
			stmt5.close();
			
			Statement stmt6 = c.createStatement();
			String sql6 = "CREATE TABLE treatment"
					+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+" type TEXT NOT NULL,"
					+"number INTEGER NOT NULL,"
					+"doctor_id INTEGER,"
					+"FOREIGN KEY (doctor_id) REFERENCES doctor (id),)";
			stmt6.executeUpdate(sql6);
			stmt6.close();
			
			Statement stmt7 = c.createStatement();
			String sql7 = "CREATE TABLE patient_treatment"
					+"(patient_id INTEGER,"
					+"treatment_id INTEGER,"
					+"FOREIGN KEY (patient_id) REFERENCES patient (id),"
					+"FOREIGN KEY (treatment_id) REFERENCES treatment (id),"
					+"PRIMARY KEY (patient_id, treatment_id),)";
			stmt7.executeUpdate(sql7);
			stmt7.close();
			
			Statement stmt8 = c.createStatement();
			String sql8 = "CREATE TABLE nurse_patient"
					+"(patient_id INTEGER,"
					+"nurse_id INTEGER,"
					+"FOREIGN KEY (patient_id) REFERENCES patient (id),"
					+"FOREIGN KEY (nurse_id) REFERENCES nurse (id),"
					+"PRIMARY KEY (patient_id, nurse_id),)";
			stmt8.executeUpdate(sql8);
			stmt8.close();
			
			Statement stmt9 = c.createStatement();
			String sql9 = "CREATE TABLE doctor_patient"
					+"(patient_id INTEGER,"
					+"doctor_id INTEGER,"
					+"FOREIGN KEY (patient_id) REFERENCES patient (id),"
					+"FOREIGN KEY (doctor_id) REFERENCES doctor (id),"
					+"PRIMARY KEY (patient_id, doctor_id),)";
			stmt9.executeUpdate(sql9);
			stmt9.close();
			
			System.out.println("Tables created.");
			// Create table: end
			
			// - Set initial values for the Primary Keys
			// - Don't try to understand this until JPA is explained
			// This is usually not needed, since the initial values
			// are set when the first row is inserted, but since we
			// are using JPA and JDBC in the same project, and JPA
			// needs an initial value, we do this.
			// TODAVIA NO ENTENDEMOS
			Statement stmtSeq = c.createStatement();
			String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('departments', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('employees', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('reports', 1)";
			stmtSeq.executeUpdate(sqlSeq);
			stmtSeq.close();
			
			// Close database connection!!!!! IMPORTANT
			System.out.println("Database connection closed.");
		} catch (SQLException e) {
			e.getMessage().contains("already exists");
			if (e.getMessage().contains("already exists")==true) {
				System.out.println(" ");
			}else {
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
}
