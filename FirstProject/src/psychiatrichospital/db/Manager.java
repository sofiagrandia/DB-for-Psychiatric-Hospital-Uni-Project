package psychiatrichospital.db;

import java.sql.SQLException;
import java.util.List;

import Project.Contract;
import Project.Doctor;
import Project.Nurse;
import Project.Patient;
import Project.Room;
import Project.Treatment;

public interface Manager {
	public void connection() ;
	
	public void closeConnection () throws SQLException ;
	//NURSE: esta hecho
	public void insertNurse(Nurse nurse);
	public List<Nurse> getNurse(String name) throws SQLException ;
	public List<Nurse> selectNurse();
	public Nurse getNurseId(Integer id)throws SQLException ;
	public void updateNurse(Nurse nurse);
	public void deleteNurse(int id) throws SQLException ;
	//DOCTOR:
	public List<Doctor> selectDoctor();
	public void updateDoctor(Doctor doctor);
	public void insertDoctor(Doctor doctor);
	public Doctor getDoctorId(Integer id)throws SQLException ;
	//PATIENT:JPA CAMBIAR
	public void insertPatient(Patient patient, Room room, Nurse nurse, Doctor doctor, Treatment treatment);
	public List<Patient> selectPatientByName(String name);
	public Patient selectPatientByid(Integer id) ;
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
	//TREATMENT dtd
	//ROOM jpa
	public void insertRoom(Room room, Patient patient);
	public Room selectRoomById(Integer id);
	public void updateRoom(Room room);
	public List<Room> selectRoom();
	public void deleteRoom(int id);
	}







