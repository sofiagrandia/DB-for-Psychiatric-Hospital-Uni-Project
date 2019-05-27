package psychiatrichospital.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.JAXBException;

import Project.Contract;
import Project.Doctor;
import Project.Nurse;
import Project.Patient;
import Project.Room;
import Project.Treatment;
import Project.TreatmentList;

public interface Manager {
	public void connection();

	public void closeConnection() throws SQLException;

	// NURSE: esta hecho
	public void insertNurse(Nurse nurse);

	public List<Nurse> getNurse(String name) throws SQLException;

	public List<Nurse> selectNurse();

	public Nurse getNurseId(Integer id) throws SQLException;

	public void updateNurse(Nurse nurse);

	public void deleteNurse(int id) throws SQLException;

	
	// DOCTOR:
	public List<Doctor> selectDoctor();

	public void updateDoctor(Doctor doctor);

	public void insertDoctor(Doctor doctor);

	public Doctor getDoctorId(Integer id) throws SQLException;

	public void deleteDoctor(int id) throws SQLException;

	
	// PATIENT:JPA CAMBIAR
	public void insertPatient(Patient patient, Room room, Nurse nurse, Doctor doctor, Treatment treatment);

	public List<Patient> selectPatientByName(String name);

	public Patient selectPatientByid(Integer id);

	public List<Patient> selectPatient();

	public void updatePatient(Patient patient);

	public void deletePatient(int id);


	// CONTRACT
	public void insertContract(Contract contract);

	public List<Contract> getContractId(float money) throws SQLException;

	public Contract getContractbyId(Integer id) throws SQLException;

	public List<Contract> selectContract();

	public void updateContract(Contract contract);

	public void deleteContract(int id) throws SQLException;

	
	// ROOM jpa
	public void insertRoom(Room room);

	public Room selectRoomById(Integer id);

	public void updateRoom(Room room);

	public List<Room> selectRoom();

	public void deleteRoom(int id);
	
	// TREATMENT dtd
	public void insertTreatment(Treatment t);
	
	public List<Treatment> getTreatmentId(String type) throws SQLException;
	
	public Treatment getTreatmentId(Integer id) throws SQLException ;
	
	public List<Treatment> selectTreatment();
	
	public void updateTreatment(Treatment t) ;
	
	public void deleteTreatment(int id) throws SQLException;
	
	
	// CREATE TABLES
		public void createTables();
		
		
	//REALATIONSHIPS
		public void createRelationshipNP(int nid, int pid);
		
		public void deleteRelationshipNP(int pid, int nid) throws SQLException;
		
		public void createRelationshipPT(int tid, int pid) ;
		
		public void deleteRelationshipPT(int tid, int pid) throws SQLException;
		
		public void createRelationshipPD(int did, int pid);
		
		public void deleteRelationshipPD(int pid, int did) throws SQLException;
		
		
		//MARSHALL
		
		public void marshaller (TreatmentList tl, String direccion) throws JAXBException, SQLException;
}
