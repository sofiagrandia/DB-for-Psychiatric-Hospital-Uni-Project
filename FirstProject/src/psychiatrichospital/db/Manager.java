import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public interface Manager {
	public void connection() ;
	
	public void closeConnection () ;
	//NURSE:
	public void insertNurse(Nurse nurse);
	public List<Nurse> getNurse(String name) ;
	public List<Nurse> selectNurse();
	public Nurse getNurseId(Integer id)throws SQLException ;
	public void updateNurse(Nurse nurse);
	//DOCTOR:
	public List<Doctor> selectDoctor();
	public void updateDoctor(Doctor doctor);
	public void insertDoctor(Doctor doctor);
	public Doctor getDoctorId(Integer id)throws SQLException ;
	//PATIENT:
	public void insertPatient(Patient patient);
	public List<Patient> getPatient(String name);
	public Patient getPatientId(Integer id) throws SQLException ;
	public List<Patient> selectPatient();
	public void updatePatient(Patient patient);
	// CREATE TABLES
	public void createTables();
	// CONTRACT
	public void insertContract(Contract contract);
	public List<Contract> getContractId(float money) throws SQLException;
	public Contract getContractId(Integer id) throws SQLException;
	public List<Contract> selectContract();
	public void updateContract(Contract contract);
	
	
	
	}






}
