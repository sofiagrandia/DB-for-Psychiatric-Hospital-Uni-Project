package psychiatrichospital.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import Project.Nurse;
import Project.Patient;
import Project.Doctor;
import Project.Contract;

public class DBManager {

	private Connection c;
     
	public void connection() {
		try {
			c = DriverManager.getConnection("jdbc:sqlite:./db/tables.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void closeConnection () throws SQLException {
		c.close();
	}

	//NURSE:
	public void insertNurse(Nurse nurse) {
		// Inserts into the data base the nurse that is passed as a parameter
		try {
			String s = "INSERT INTO nurse (name,gender,dob,hours)" + "VALUES (?, ?, ?, ?)";
			PreparedStatement p = c.prepareStatement(s);
			p.setString(1, nurse.getName());
			p.setString(2, nurse.getGender());
			p.setDate(3, nurse.getDob());
			p.setInt(4, nurse.getHours());
			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public List<Nurse> getNurse(String name) throws SQLException {
		List<Nurse> n = new ArrayList<Nurse>();
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM nurse WHERE name LIKE '%"+name+"%' ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name2 = rs.getString("name");
			String gender = rs.getString("gender");
			Date date = rs.getDate("dob");
			int hours=rs.getInt("hours");
			Nurse nurse= new Nurse(id, name2, gender, date, hours);
			n.add(nurse);
		}		
		rs.close();
		stmt.close();
		return n;
	}
	public List<Nurse> selectNurse() {
		List<Nurse> n = new ArrayList<Nurse>();
		try {
			String sql = "SELECT * FROM nurse";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			Nurse nurse1;
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date dob = rs.getDate("dob");
				int hours = rs.getInt("hours");

				nurse1 = new Nurse(id, name, gender, dob, hours);
				n.add(nurse1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n;

	}

	public Nurse getNurseId(Integer id) throws SQLException {
		Nurse nurse=null;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM nurse WHERE id ='"+id+"' ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id1 = rs.getInt("id");
			String name = rs.getString("name");
			String gender = rs.getString("gender");
			Date date = rs.getDate("dob");
			int hours=rs.getInt("hours");
			nurse= new Nurse(id1, name, gender, date, hours);
		}		
		rs.close();
		stmt.close();
		return nurse;
	}
	
	public void updateNurse(Nurse nurse) {
		try {
		String sql = "UPDATE nurse SET name=?,gender=?, dob=?, hours=? WHERE id=?" ;
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, nurse.getName());
		prep.setString(2, nurse.getGender());
		prep.setDate(3,nurse.getDob());
		prep.setInt(4, nurse.getHours());
		prep.setInt(5, nurse.getId());
		prep.executeUpdate();
		}catch(Exception e) {
				System.out.println(e.getMessage());
		}
	}

	
	
	
	//DOCTOR:
	public List<Doctor> selectDoctor() {
		List<Doctor> n = new ArrayList<Doctor>();
		try {
			String sql = "SELECT * FROM doctor";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			Doctor doctor1;
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date dob = rs.getDate("dob");
				int hours = rs.getInt("hours");

				doctor1 = new Doctor(id, name, gender, dob, hours);
				n.add(doctor1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n;

	}
	public void updateDoctor(Doctor doctor) {
		try {
		String sql = "UPDATE doctor SET name=?,gender=?, dob=?, hours=? WHERE id=?" ;
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, doctor.getName());
		prep.setString(2, doctor.getGender());
		prep.setDate(3,doctor.getDob());
		prep.setInt(4, doctor.getHours());
		prep.setInt(5, doctor.getId());
		prep.executeUpdate();
		}catch(Exception e) {
				System.out.println(e.getMessage());
		}
	}
	public void insertDoctor(Doctor doctor) {
		// Inserts into the data base the nurse that is passed as a parameter
		try {
			String s = "INSERT INTO doctor (name,gender,dob,hours)" + "VALUES (?, ?, ?, ?)";
			PreparedStatement p = c.prepareStatement(s);
			p.setString(1, doctor.getName());
			p.setString(2, doctor.getGender());
			p.setDate(3, doctor.getDob());
			p.setInt(4, doctor.getHours());
			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Doctor getDoctorId(Integer id) throws SQLException {
		Doctor doctor=null;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM doctor WHERE id ='"+id+"' ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id1 = rs.getInt("id");
			String name = rs.getString("name");
			String gender = rs.getString("gender");
			Date date = rs.getDate("dob");
			int hours=rs.getInt("hours");
			doctor= new Doctor(id1, name, gender, date, hours);
		}		
		rs.close();
		stmt.close();
		return doctor;
	}
	
	
	
	
	//PATIENT:
	
	public void insertPatient(Patient patient) {
		// Inserts into the data base the patient that is passed as a parameter
		try {
			String s = "INSERT INTO patient (name,gender,dob,room_id,phoyo)" + "VALUES (?, ?, ?, ?,?,?)";
			PreparedStatement p = c.prepareStatement(s);
			p.setString(1, patient.getName());
			p.setString(2, patient.getGender());
			p.setDate(3, patient.getDob());
			p.setInt(4, patient.getRoom_id());
			p.setBytes(5, patient.getPhoto());
			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Patient> getPatient(String name) throws SQLException {
		List<Patient> p = new ArrayList<Patient>();
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM patient WHERE name LIKE '%"+name+"%' ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name2 = rs.getString("name");
			String gender = rs.getString("gender");
			Date date = rs.getDate("dob");
			int room_id = rs.getInt("room_id");
			byte[] blobArray = rs.getBytes("photo");
			Patient patient = new Patient (id, name2, gender, date, room_id, blobArray);
			p.add(patient);
		}		
		rs.close();
		stmt.close();
		return p;
	}

	public Patient getPatientId(Integer id) throws SQLException {
		Patient patient=null;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM patient WHERE id ='"+id+"' ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id1 = rs.getInt("id");
			String name = rs.getString("name");
			String gender = rs.getString("gender");
			Date date = rs.getDate("dob");
			int room_id = rs.getInt("room_id");
			byte[] blobArray = rs.getBytes("photo");
			patient = new Patient (id1, name, gender, date, room_id, blobArray);
		}		
		rs.close();
		stmt.close();
		return patient;
	}
	
	public List<Patient> selectPatient() {
		List<Patient> p = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM patient";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Patient patient1;
			while (rs.next()) {
				int id = rs.getInt("id");
				String name2 = rs.getString("name");
				String gender = rs.getString("gender");
				Date date = rs.getDate("dob");
				int room_id = rs.getInt("room_id");
				byte[] blobArray = rs.getBytes("photo");
				patient1 = new Patient (id, name2, gender, date, room_id, blobArray);
				p.add(patient1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return p;

	}
	
	public class createRoom(Room room) {
		
		
	}
	
	public void updatePatient(Patient patient) {
		try {
		String sql = "UPDATE patient SET name=?,gender=?, dob=?, room_id=?, photo=? WHERE id=?" ;
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setString(1, patient.getName());
		prep.setString(2, patient.getGender());
		prep.setDate(3,patient.getDob());
		prep.setInt(4, patient.getRoom_id());
		prep.setBytes(5,  patient.getPhoto());
		prep.executeUpdate();
		}catch(Exception e) {
				System.out.println(e.getMessage());
		}
	}
	
	// CREATE TABLES
	public void createTables() {
		try {
		
					// Open database connection
					// CHOOSES THE DATA BASE THAT WE ARE USING
					Class.forName("org.sqlite.JDBC");
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
							   + " FOREIGN KEY (room_id) REFERENCES room (id) )";
					stmt2.executeUpdate(sql2);
					stmt2.close();
					Statement stmt3 = c.createStatement();
					String sql3 = "CREATE TABLE contract "
							   + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
							   + " money     REAL     NOT NULL, "
							   + " duration  DATETIME, "
							   + " holidays		INTEGER NOT NULL)";
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
							   +" FOREIGN KEY (contract_id) REFERENCES contract (id) )";
					
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
							+"FOREIGN KEY (contract_id) REFERENCES contract (id))";
					stmt5.executeUpdate(sql5);
					stmt5.close();
					
					Statement stmt6 = c.createStatement();
					String sql6 = "CREATE TABLE treatment"
							+ "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
							+" type TEXT NOT NULL,"
							+"number INTEGER NOT NULL,"
							+"doctor_id INTEGER,"
							+"FOREIGN KEY (doctor_id) REFERENCES doctor (id))";
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
					stmtSeq.close();		} catch (SQLException e) {
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
	
	// CONTRACT
	public void insertContract(Contract contract) {
			try {
				String s = "INSERT INTO contract (money, holidays, d1, d2)" + "VALUES (?, ?, ?, ?)";
				PreparedStatement p = c.prepareStatement(s);
				p.setFloat(1, contract.getMoney());
				p.setInt(2,contract.getHolidays());
				p.setDate(3, contract.getD1());
				p.setDate(4, contract.getD2());
				p.executeUpdate();
				p.close();
				
			}catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public List<Contract> getContractId(float money) throws SQLException {
			List<Contract> cn = new ArrayList<Contract>();
			Statement stmt = c.createStatement();
			String sqlCon = "SELECT * FROM contract WHERE money LIKE '%"+money+"%' ";
			ResultSet rs = stmt.executeQuery(sqlCon);
			while (rs.next()) {
				int id4 = rs.getInt("id");
				float money = rs.getFloat("money");
				int holidays = rs.getInt("holidays");
				Date d1 = rs.getDate("d1");
				Date d2 =rs.getDate("d2");
				Contract contract= new Contract(id4, money, holidays, d1, d2);
				cn.add(contract);
			}		
			rs.close();
			stmt.close();
			return cn;
		}

	public Contract getContractId(Integer id) throws SQLException{
			Contract contract= null;
			Statement stmt=c.createStatement();
			String sql = "SELECT * FROM contract WHERE id ='"+id+"' ";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int idCon = rs.getInt("id");
				float money = rs.getFloat("money");
				int holidays = rs.getInt("holidays");
				Date d1 = rs.getDate("d1");
				Date d2 = rs.getDate("d2");
				contract = new Contract(idCon, money, holidays, d1, d2);
			}
			rs.close();
			stmt.close();
			return contract;
		}

		public List<Contract> selectContract() {
			List<Contract> n = new ArrayList<Contract>();
			try {
				String sql = "SELECT * FROM contract";
				PreparedStatement p = c.prepareStatement(sql);
				ResultSet rs = p.executeQuery();
				Contract contract1;
				while (rs.next()) {
					int id = rs.getInt("id");
					float money = rs.getFloat("money");
					int holidays = rs.getInt("holidays");
					Date d1 = rs.getDate("d1");
					Date d2 = rs.getDate("d2");

					contract1 = new Contract(id, money, holidays, d1, d2);
					n.add(contract1);

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return n;

		}


		public void updateContract(Contract contract) {
			try{
				String sql = "UPDATE contract SET money=?, holidays=?, d1=?, d2=? WHERE id=?";
				PreparedStatement prep = c.prepareStatement(sql);
				prep.setFloat(1, contract.getMoney());
				prep.setInt(2, contract.getHolidays());
				prep.setDate(3, contract.getD1());
				prep.setDate(4, contract.getD2());
			}catch(Exception e) {
				System.out.println(e.getMessage());
		}}
	
	
	
	
	
	
	}





