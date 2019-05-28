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

import javax.xml.bind.JAXBException;

import Project.Nurse;
import Project.Patient;
import Project.Room;
import Project.Treatment;
import Project.TreatmentList;
import Project.Doctor;
import Project.Contract;

public class DBManager implements Manager {

	//CONNECTION
	private Connection c;
	public Connection getConnection() {
		return c;
	}
	public void connection() {
		try {
			c = DriverManager.getConnection("jdbc:sqlite:./db/tables.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void closeConnection() throws SQLException {
		c.close();
	}

	
	// NURSE
	public void insertNurse(Nurse nurse) {
		// Inserts into the data base the nurse that is passed as a parameter
		try {
			String s = "INSERT INTO nurse (name,gender,dob,hours)" + " VALUES (?, ?, ?, ?)";
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
		String sql = "SELECT * FROM nurse WHERE name LIKE '%" + name + "%' ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name2 = rs.getString("name");
			String gender = rs.getString("gender");
			Date date = rs.getDate("dob");
			int hours = rs.getInt("hours");
			Nurse nurse = new Nurse(id, name2, gender, date, hours);
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
			Nurse nurse;
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date dob = rs.getDate("dob");
				int hours = rs.getInt("hours");
			
				nurse = new Nurse(id, name, gender, dob, hours);
			
				n.add(nurse);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n;

	}

	public Nurse getNurseId(Integer id) throws SQLException {
		Nurse nurse = null;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM nurse WHERE id ='" + id + "' ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id1 = rs.getInt("id");
			String name = rs.getString("name");
			System.out.println(name);
			String gender = rs.getString("gender");
			Date date = rs.getDate("dob");
			int hours = rs.getInt("hours");
			nurse = new Nurse(id1, name, gender, date, hours);
		}
		rs.close();
		stmt.close();
		return nurse;
	}

	public void updateNurse(Nurse nurse) {
		try {
			String sql = "UPDATE nurse SET name=?,gender=?, dob=?, hours=? WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, nurse.getName());
			prep.setString(2, nurse.getGender());
			prep.setDate(3, nurse.getDob());
			prep.setInt(4, nurse.getHours());
			prep.setInt(5, nurse.getId());
			prep.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteNurse(int id) throws SQLException {
		String sql = "DELETE FROM nurse WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}

	
	// DOCTOR
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
			String sql = "UPDATE doctor SET name=?,gender=?, dob=?, hours=? WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, doctor.getName());
			prep.setString(2, doctor.getGender());
			prep.setDate(3, doctor.getDob());
			prep.setInt(4, doctor.getHours());
			prep.setInt(5, doctor.getId());
			prep.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertDoctor(Doctor doctor) {
		// Inserts into the data base the nurse that is passed as a parameter
		try {
			String s = "INSERT INTO doctor (name,gender,dob,hours)" + " VALUES (?, ?, ?, ?)";
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
		Doctor doctor = null;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM doctor WHERE id ='" + id + "' ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id1 = rs.getInt("id");
			String name = rs.getString("name");
			String gender = rs.getString("gender");
			Date date = rs.getDate("dob");
			int hours = rs.getInt("hours");
			doctor = new Doctor(id1, name, gender, date, hours);
		}
		rs.close();
		stmt.close();
		return doctor;
	}

	public void deleteDoctor(int id) throws SQLException {
		String sql = "DELETE FROM doctor WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}

	
	
	//TREATMENT
	
	public void insertTreatment(Treatment t) {
		try {
			String s = "INSERT INTO treatment (type, number)" + "VALUES (?, ?)";
			PreparedStatement p = c.prepareStatement(s);
			p.setString(1, t.getType());
			p.setInt(2, t.getNumber());
			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

	public List<Treatment> getTreatmentId(String type) throws SQLException {
		List<Treatment> t = new ArrayList<Treatment>();
		Statement stmt = c.createStatement();
		String sqlCon = "SELECT * FROM treatment WHERE type LIKE '%" + type + "%' ";
		ResultSet rs = stmt.executeQuery(sqlCon);
		while (rs.next()) {
			int id4 = rs.getInt("id");
			String type1 = rs.getString("type");
			int number = rs.getInt("number");
			Treatment treat = new Treatment(id4, type1, number);
			t.add(treat);
		}
		rs.close();
		stmt.close();
		return t;
	}
	
	public Treatment getTreatmentId(Integer id) throws SQLException {
		Treatment t = null;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM treatment WHERE id ='" + id + "' ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int id4 = rs.getInt("id");
			String type1 = rs.getString("type");
			int number = rs.getInt("number");
			t = new Treatment(id4, type1, number);
		}
		rs.close();
		stmt.close();
		return t;
	}
	public List<Treatment> selectTreatment() {
		List<Treatment> t2 = new ArrayList<Treatment>();
		try {
			String sql = "SELECT * FROM treatment";
			PreparedStatement p = c.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			Treatment t1;
			while (rs.next()) {
				int id4 = rs.getInt("id");
				String type1 = rs.getString("type");
				int number = rs.getInt("number");
				t1 = new Treatment(id4, type1, number);
				t2.add(t1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return t2;

	}
	
	public void updateTreatment(Treatment t) {
		try {
			String sql = "UPDATE treatment SET type=?, number=? WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setString(1, t.getType());
			prep.setInt(2, t.getNumber());
			prep.setInt(3, t.getId());
			prep.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	

	public void deleteTreatment(int id) throws SQLException {
		String sql = "DELETE FROM treatment WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}
	
	
	// PATIENT
	@Override
	public void insertPatient(Patient patient, Room room, Nurse nurse, Doctor doctor, Treatment treatment) {
			// TODO Auto-generated method stub

		}

	@Override
	public List<Patient> selectPatientByName(String name) {
			// TODO Auto-generated method stub
			return null;
		}

	@Override
	public Patient selectPatientByid(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}

	@Override
	public List<Patient> selectPatient() {
			// TODO Auto-generated method stub
			return null;
		}

	@Override
	public void updatePatient(Patient patient) {
			// TODO Auto-generated method stub

		}

	@Override
	public void deletePatient(int id) {
			// TODO Auto-generated method stub
		}
	
	
	// CONTRACT
	public void insertContract(Contract contract) {
		try {
			String s = "INSERT INTO contract (money, holidays, duration )" + " VALUES (?, ?, ? )";
			PreparedStatement p = c.prepareStatement(s);
			p.setFloat(1, contract.getMoney());
			p.setInt(2, contract.getHolidays());
			p.setDate(3, contract.getDob());
		
			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Contract> getContractId(float money) throws SQLException {
		List<Contract> cn = new ArrayList<Contract>();
		Statement stmt = c.createStatement();
		String sqlCon = "SELECT * FROM contract WHERE money LIKE '%" + money + "%' ";
		ResultSet rs = stmt.executeQuery(sqlCon);
		while (rs.next()) {
			int id4 = rs.getInt("id");
			int money1 = rs.getInt("money");
			int holidays = rs.getInt("holidays");
			Date d1 = rs.getDate("duration");

			Contract contract = new Contract(id4, money1, holidays, d1);
			cn.add(contract);
		}
		rs.close();
		stmt.close();
		return cn;
	}

	public Contract getContractbyId(Integer id) throws SQLException {
		Contract contract = null;
		Statement stmt = c.createStatement();
		String sql = "SELECT * FROM contract WHERE id ='" + id + "' ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			int idCon = rs.getInt("id");
			int money = rs.getInt("money");
			int holidays = rs.getInt("holidays");
			Date d1 = rs.getDate("duration");
			contract = new Contract(idCon, money, holidays, d1);
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
				int money = rs.getInt("money");
				int holidays = rs.getInt("holidays");
				Date d1 = rs.getDate("duration");
				

				contract1 = new Contract(id, money, holidays, d1);
				n.add(contract1);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return n;

	}



	public void updateContract(Contract contract) {
		try {
			String sql = "UPDATE contract SET money=?, duration=?, holidays=? WHERE id=?";
			PreparedStatement prep = c.prepareStatement(sql);
			prep.setInt(1, contract.getMoney());
			prep.setInt(3, contract.getHolidays());
			prep.setDate(2, contract.getDob());
			prep.setInt(4, contract.getId() );
			prep.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void deleteContract(int id) throws SQLException {
		String sql = "DELETE FROM contract WHERE id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, id);
		prep.executeUpdate();
	}

	
	
	
	// ROOM
	@Override
	public void insertRoom(Room room) {
		// TODO Auto-generated method stub

	}

	@Override
	public Room selectRoomById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRoom(Room room) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Room> selectRoom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRoom(int id) {
		// TODO Auto-generated method stub
	}

	// NURSE-PATIENT
	public void createRelationshipNP(int nid, int pid) {
		try {
			String s = "INSERT INTO nurse_patient (nurse_id, patient_id)" + " VALUES (?, ?)";
			PreparedStatement p = c.prepareStatement(s);
			p.setInt(1, nid);
			p.setInt(2, pid);
			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteRelationshipNP(int pid, int nid) throws SQLException {
		String sql = "DELETE FROM doctor_patient WHERE patient_id=? AND nurse_id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, pid);
		prep.setInt(2,nid);
		prep.executeUpdate();
	}
	// TREATMENT-PATIENT
	
	
	
	public void createRelationshipPT(int pid, int tid) {
		try {
			String s = "INSERT INTO patient_treatment (patient_id, treatment_id)" + " VALUES (?, ?)";
			PreparedStatement p = c.prepareStatement(s);
			p.setInt(1, pid);
			p.setInt(2, tid);
			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deleteRelationshipPT(int tid, int pid) throws SQLException {
		String sql = "DELETE FROM patient_treatment WHERE patient_id=? AND doctor_id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1,pid);
		prep.setInt(2,tid);
		prep.executeUpdate();
	}
	// TREATMENT-DOCTOR
	public void createRelationshipDT(int tid, int did) {
		try {
			String s = "UPDATE treatment SET doctor_id=? WHERE id=?";
			//String s = "INSERT INTO treatment  (doctor_id) " + "VALUES (?)";
			System.out.println(did);
			System.out.println(tid);
			PreparedStatement p = c.prepareStatement(s);
			p.setInt(1, did);
			p.setInt(2,  tid);
			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//PATIENT-DOCTOR
	public void createRelationshipPD(int did, int pid) {
		try {
			String s = "INSERT INTO doctor_patient (doctor_id, patient_id)" + " VALUES (?, ?)";
			PreparedStatement p = c.prepareStatement(s);
			p.setInt(1, did);
			p.setInt(2, pid);
			p.executeUpdate();
			p.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void deleteRelationshipPD(int pid, int did)
			throws SQLException {
		String sql = "DELETE FROM doctor_patient WHERE patient_id=? AND doctor_id=?";
		PreparedStatement prep = c.prepareStatement(sql);
		prep.setInt(1, pid);
		prep.setInt(2,did);
		prep.executeUpdate();
	}
	
	// CREATE TABLES
	public void createTables() {
			try {

				// Open database connection
				// CHOOSES THE DATA BASE THAT WE ARE USING
				Class.forName("org.sqlite.JDBC");
				// ACTIVATES
				c.createStatement().execute("PRAGMA foreign_keys=ON");
				System.out.println("Opened correctly.");
				// Create tables: begin
				// OJO CÓMO SE ESCRIBE. LOS ESPACIOS, LAS COMAS, LAS COMILLLAS....
				Statement stmt1 = c.createStatement();
				String sql1 = "CREATE TABLE room " + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT, "
						+ " floor  INTEGER NOT NULL)";
				stmt1.executeUpdate(sql1);
				stmt1.close();

				Statement stmt2 = c.createStatement();
				String sql2 = "CREATE TABLE patient " + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
						+ " name     TEXT     NOT NULL, " + " gender  TEXT 	NOT NULL, " + " dob      DATE	 NOT NULL, "
						+ " room_id   INTEGER, " 
						+ " FOREIGN KEY (room_id) REFERENCES room (id) )";
				stmt2.executeUpdate(sql2);
				stmt2.close();
				Statement stmt3 = c.createStatement();
				String sql3 = "CREATE TABLE contract " + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
						+ " money     REAL     NOT NULL, " + " duration  DATETIME, " + " holidays		INTEGER NOT NULL)";
				stmt3.executeUpdate(sql3);
				stmt3.close();

				Statement stmt4 = c.createStatement();
				String sql4 = "CREATE TABLE doctor " + "(id       INTEGER  PRIMARY KEY AUTOINCREMENT,"
						+ " name     TEXT     NOT NULL," + " gender  TEXT 	NOT NULL," + " dob      DATE	 NOT NULL,"
						+ " hours   INTEGER NOT NULL," + "contract_id INTEGER,"
						+ " FOREIGN KEY (contract_id) REFERENCES contract (id) )";

				stmt4.executeUpdate(sql4);
				stmt4.close();

				Statement stmt5 = c.createStatement();
				String sql5 = "CREATE TABLE nurse" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + "name TEXT NOT NULL,"
						+ "gender TEXT NOT NULL," + "dob DATE NOT NULL," + "hours INTEGER NOT NULL," + "contract_id INTEGER," + "FOREIGN KEY (contract_id) REFERENCES contract (id))";
				stmt5.executeUpdate(sql5);
				stmt5.close();

				Statement stmt6 = c.createStatement();
				String sql6 = "CREATE TABLE treatment" + "(id INTEGER PRIMARY KEY AUTOINCREMENT," + " type TEXT,"
						+ "number INTEGER NOT NULL," + "doctor_id INTEGER,"
						+ "FOREIGN KEY (doctor_id) REFERENCES doctor (id))";
				stmt6.executeUpdate(sql6);
				stmt6.close();

				Statement stmt7 = c.createStatement();
				String sql7 = "CREATE TABLE patient_treatment" + "(patient_id INTEGER," + "treatment_id INTEGER,"
						+ "FOREIGN KEY (patient_id) REFERENCES patient (id),"
						+ "FOREIGN KEY (treatment_id) REFERENCES treatment (id),"
						+ "PRIMARY KEY (patient_id, treatment_id))";
				stmt7.executeUpdate(sql7);
				stmt7.close();

				Statement stmt8 = c.createStatement();
				String sql8 = "CREATE TABLE nurse_patient" + "(patient_id INTEGER," + "nurse_id INTEGER,"
						+ "FOREIGN KEY (patient_id) REFERENCES patient (id),"
						+ "FOREIGN KEY (nurse_id) REFERENCES nurse (id)," + "PRIMARY KEY (patient_id, nurse_id))";
				stmt8.executeUpdate(sql8);
				stmt8.close();

				Statement stmt9 = c.createStatement();
				String sql9 = "CREATE TABLE doctor_patient" + "(patient_id INTEGER," + "doctor_id INTEGER,"
						+ "FOREIGN KEY (patient_id) REFERENCES patient (id),"
						+ "FOREIGN KEY (doctor_id) REFERENCES doctor (id)," + "PRIMARY KEY (patient_id, doctor_id))";
				stmt9.executeUpdate(sql9);
				stmt9.close();

				System.out.println("Tables created.");
				
				Statement stmtSeq = c.createStatement();
				String sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('patient', 1)";
				stmtSeq.executeUpdate(sqlSeq);
				sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('room', 1)";
				stmtSeq.executeUpdate(sqlSeq);
				sqlSeq = "INSERT INTO sqlite_sequence (name, seq) VALUES ('treatment', 1)";
				stmtSeq.executeUpdate(sqlSeq);
				stmtSeq.close();
			} catch (SQLException e) {
				e.getMessage().contains("already exists");
				if (e.getMessage().contains("already exists") == true) {
					System.out.println(" ");
				} else {
					e.printStackTrace();
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	@Override
	public void marshaller(TreatmentList tl, String direccion) {
		// TODO Auto-generated method stub
		
	}

}
