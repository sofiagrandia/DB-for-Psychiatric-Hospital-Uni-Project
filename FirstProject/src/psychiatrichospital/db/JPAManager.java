

import javax.persistence.Persistence;

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
	
	public void insertNurse(Nurse nurse) {
		
	}
	
	public void insertDoctor(Doctor doctor) {
	}
	
	
	
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
}