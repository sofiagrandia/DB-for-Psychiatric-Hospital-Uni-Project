package psychiatrichospital.db;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Project.Contract;
import Project.Doctor;
import Project.Nurse;
import Project.Patient;
import Project.Room;
import Project.Treatment;
import Project.TreatmentList;


public class XMLManager {
	
	
	public static void marshaller (TreatmentList tl, String direccion) throws JAXBException, SQLException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(TreatmentList.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		
		File file = new File("./xmls/" + direccion);
		marshaller.marshal(tl, file);
		//return marshaller.marshal(treatment);
	}
	
	public static Treatment  unmarshallerTreatment (File file) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(Treatment.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		Treatment treatment = (Treatment) unmarshaller.unmarshal(file);
		return treatment;
		
		/*public static void main(String[] args) throws JAXBException {

			
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Treatment.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

			File file = new File("./xmls/External-Treatment.xml");
			Treatment t = (Treatment) unmarshaller.unmarshal(file);

			System.out.println("Treatment:");
			System.out.println("Type: " + t.getType());
			System.out.println("Number: " + t.getNumber());
			System.out.println("Doctor id: " + t.getDoctor());
			System.out.println("List of patients:" + t.getP());
			

			factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();

			EntityTransaction tx1 = em.getTransaction();

			tx1.begin();

			
			em.persist(t);
			
			tx1.commit();*/
	}

	


}
