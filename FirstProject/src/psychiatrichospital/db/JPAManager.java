

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Persistence;
import javax.persistence.Query;

import Project.Doctor;
import Project.Nurse;
import Project.Patient;
import Project.Treatment;
import sample.db.pojos.Department;

public class JPAManager {
	
	private EntityManager em;
	
	public void connection(){
	
			em = Persistence.createEntityManagerFactory("psychiatric-provider").createEntityManager();
			em.getTransaction().begin();ç
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
		

}
	
	
	public void insertRoom(Room room, Patient patient) {
		
		em.getTransaction().begin();
		em.persist(room);
		// 2.- Link room and patient
		room.addPatient(patient);
		patient.addRoom(room);
		em.getTransaction().commit();
	
	}
	
	public Room selectRoom(Integer id) {
		Query q1 = em.createNativeQuery("SELECT * FROM room WHERE id LIKE ?", Room.class);
		q1.setParameter(1, room_id);
		Room r = (Room) q1.getSingleResult();
		return r;
	}
	
	
	public void insertNurse(Nurse nurse) {
		
	}
	
	public void insertDoctor(Doctor doctor) {
	}
	
	
	//LISTA DE PATIENTS SIN PASAR NAME 
	public void insertPatient(Patient patient, Room room, Nurse nurse, Doctor doctor, Treatment treatment) {
		em.getTransaction().begin();
		em.persist(patient);
		patient.addRoom(room);
		room.addPatient(patient);
		patient.addNurse(nurse);
		patient.addDoctor(doctor);
		patient.addTreatment(treatment);
		em.getTransaction().commit();
		
	}
	
	public List<Patient> selectPatientByName(String name){
		Query q1 = em.createNativeQuery("SELECT * FROM patient WHERE name LIKE ?", Patient.class);
		q1.setParameter(1, "%" + name + "%");
		List<Patient> patients = (List<Patient>) q1.getResultList();
		// Se imprime en el UI 
		return patients;
	}
	
	public Patient selectPatientByid(Integer id) {
		Query q1 = em.createNativeQuery("SELECT * FROM patient WHERE id LIKE ?", Patient.class);
		q1.setParameter(1, patient_id);
		Patient p = (Patient) q1.getSingleResult();
		return p;
	}
	
	public void insertNurse(Nurse nurse) {
	
	}
	public List<Nurse> getNurse(String name) throws SQLException {
	
	}
	public List<Nurse> selectNurse() {
		
	}
	public Doctor getDoctorId(Integer id) throws SQLException {
		
	}
	public List<Doctor> selectDoctor() {
	}
	
	//CAMBIAMOS NEWLOCATION POR NEWFLOOR TENEMOS QUE UPDATE PATIENT
	public void updateRoom(Room room) {
		//Query q = em.createNativeQuery("SELECT * FROM room WHERE id = ?", Room.class);
		//q.setParameter(1, room_id);
		//Room r = (Room) q.getSingleResult();
		//Integer newF = reader.readLine();
		//Patient patient=
		// Begin transaction
		em.getTransaction().begin();
		// Make changes
		em.flush();
		//r.setFloor(newF);
		// End transaction
		em.getTransaction().commit();
		
	}
	public void updatePatient(Patient patient) {
		//Query q = em.createNativeQuery("SELECT * FROM patient WHERE id = ?", Patient.class);
		//q.setParameter(1, patient_id);
		//Patient p = (Patient) q.getSingleResult();
		//String newName = reader.readLine();
		//String newGender = reader.readLine();
		//Date newDate = reader.readLine();
		em.flush();
		//Integer newRoom = reader.readLine();
		//Blob newPhoto = reader.readLine();
		//OBJETOS
		// Begin transaction
		em.getTransaction().begin();
		// Make changes
		p.setFloor(newF);
		// End transaction
		em.getTransaction().commit();
	}
}