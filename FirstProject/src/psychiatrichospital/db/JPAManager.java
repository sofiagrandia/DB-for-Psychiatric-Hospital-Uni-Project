package psychiatrichospital.db;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import Project.Contract;
import Project.Doctor;
import Project.Nurse;
import Project.Patient;
import Project.Room;
import Project.Treatment;

public class JPAManager implements Manager {
	private EntityManager em;

	public void connection() {

		em = Persistence.createEntityManagerFactory("psychiatric-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub

	}

	// NURSE
	@Override
	public Nurse getNurseId(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateNurse(Nurse nurse) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertNurse(Nurse nurse) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Nurse> getNurse(String name) {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Nurse> selectNurse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteNurse(int id) throws SQLException {
		// TODO Auto-generated method stub
	}

	
	// DOCTOR
	@Override
	public void updateDoctor(Doctor doctor) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Doctor> selectDoctor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertDoctor(Doctor doctor) {
		// TODO Auto-generated method stub

	}

	@Override
	public Doctor getDoctorId(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override

	public void deleteDoctor(int id) throws SQLException {
		// TODO Auto-generated method stub
	}
	
	// PATIENT añadir lo de abajo
	public void insertPatient(Patient patient, Room room, Nurse nurse, Doctor doctor, Treatment treatment) {
		this.insertPatient(patient);
		this.assignPatientRoom(patient, room);
//		em.getTransaction().begin();
//		em.persist(patient);
//		patient.setRoom(room);
//		room.addPatient(patient);
//		patient.addNurse(nurse);
//		patient.addDoctor(doctor);
//		patient.addTreatment(treatment);
//		em.getTransaction().commit();

	}
	
	public void insertPatient(Patient p) {
		em.getTransaction().begin();
		em.persist(p);
		em.getTransaction().commit();
	}
	
	public void assignPatientRoom(Patient p, Room r) {
		em.getTransaction().begin();
		p.setRoom(r);
		r.addPatient(p);
		em.getTransaction().commit();
	}

	public List<Patient> selectPatientByName(String name) {
		Query q1 = em.createNativeQuery("SELECT * FROM patient WHERE name LIKE ?", Patient.class);
		q1.setParameter(1, "%" + name + "%");
		List<Patient> patients = (List<Patient>) q1.getResultList();
		// Se imprime en el UI
		return patients;
	}

	public Patient selectPatientByid(Integer id) {
		Query q1 = em.createNativeQuery("SELECT * FROM patient WHERE id LIKE ?", Patient.class);
		q1.setParameter(1, id);
		Patient p = (Patient) q1.getSingleResult();
		return p;
	}

	public void updatePatient(Patient patient) {
		// Query q = em.createNativeQuery("SELECT * FROM patient WHERE id = ?",
		// Patient.class);
		// q.setParameter(1, patient_id);
		// Patient p = (Patient) q.getSingleResult();
		// String newName = reader.readLine();
		// String newGender = reader.readLine();
		// Date newDate = reader.readLine();
		em.flush();
		// Integer newRoom = reader.readLine();
		// Blob newPhoto = reader.readLine();
		// OBJETOS
		// Begin transaction
		em.getTransaction().begin();
		// Make changes
		// patient.setFloor(newF);
		// End transaction
		em.getTransaction().commit();
	}

	@Override
	public List<Patient> selectPatient() {
		List<Patient> p = new ArrayList<Patient>();
		Query q1 = em.createNativeQuery("SELECT * FROM patient ", Patient.class);
		p = q1.getResultList();
		return p;
	}

	public void deletePatient(int id) {

		Query q2 = em.createNativeQuery("SELECT * FROM patient WHERE id = ?", Patient.class);
		q2.setParameter(1, id);
		Patient p = (Patient) q2.getSingleResult();

		// Begin transaction
		em.getTransaction().begin();
		// Store the object
		em.remove(p);
		// End transaction
		em.getTransaction().commit();
	}
	

	// CONTRACT
	@Override
	public void insertContract(Contract contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Contract> getContractId(float money) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contract getContractId(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Contract> selectContract() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateContract(Contract contract) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteContract(int id) throws SQLException {
		// TODO Auto-generated method stub
	}
	
	
	// ROOM
	// Nos falta un metodo que muestre todas las rooms
	public void insertRoom(Room room, Patient patient) {

		em.getTransaction().begin();
		em.persist(room);
		room.addPatient(patient);
		patient.setRoom(room);
		em.getTransaction().commit();

	}

	public Room selectRoomById(Integer id) {
		Query q1 = em.createNativeQuery("SELECT * FROM room WHERE id LIKE ?", Room.class);
		q1.setParameter(1, id);
		Room r = (Room) q1.getSingleResult();
		return r;
	}

	public void updateRoom(Room room) {
		// Query q = em.createNativeQuery("SELECT * FROM room WHERE id = ?",
		// Room.class);
		// q.setParameter(1, room_id);
		// Room r = (Room) q.getSingleResult();
		// Integer newF = reader.readLine();
		// Patient patient=
		// Begin transaction
		em.getTransaction().begin();
		// Make changes
		em.flush();
		// r.setFloor(newF);
		// End transaction
		em.getTransaction().commit();

	}

	public List<Room> selectRoom() {
		List<Room> r = new ArrayList<Room>();
		Query q1 = em.createNativeQuery("SELECT * FROM room ", Room.class);
		r = q1.getResultList();
		return r;
	}

	public void deleteRoom(int id) {
		Query q2 = em.createNativeQuery("SELECT * FROM room WHERE id = ?", Room.class);
		q2.setParameter(1, id);
		Room r = (Room) q2.getSingleResult();

		// Begin transaction
		em.getTransaction().begin();
		// Store the object
		em.remove(r);
		// End transaction
		em.getTransaction().commit();
	}

	// TABLAS
		@Override
		public void createTables() {
			// TODO Auto-generated method stub

		}
//TREATMENT
		@Override
		public void insertTreatment(Treatment t) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public List<Treatment> getTreatmentId(String type) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public Treatment getTreatmentId(Integer id) throws SQLException {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public List<Treatment> selectTreatment() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void updateTreatment(Treatment t) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteTreatment(int id) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void createRelationshipNP(int nid, int pid) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteRelationshipNP(int pid, int nid) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void createRelationshipPT(int tid, int pid) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteRelationshipPT(int tid, int pid) throws SQLException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void createRelationshipPD(int did, int pid) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void deleteRelationshipPD(int pid, int did) throws SQLException {
			// TODO Auto-generated method stub
			
		}
}