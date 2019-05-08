package Project;

import java.io.File;
import Project.Treatment;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class Unmarshall {

	private static final String PERSISTENCE_PROVIDER = "tables-provider";
	private static EntityManagerFactory factory;

	public static void main(String[] args) throws JAXBException {

		JAXBContext jaxbContext = JAXBContext.newInstance(Treatment.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		File file = new File("./xmls/External-Treatment.xml");
		Treatment t = (Treatment) unmarshaller.unmarshal(file);

		System.out.println("Treatment:");
		System.out.println("Type: " + t.getType());
		System.out.println("Number: " + t.getNumber());
		System.out.println("Doctor id: " + t.getDoctor_id());

		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		EntityTransaction tx1 = em.getTransaction();

		tx1.begin();

		
		em.persist(t);
		
		tx1.commit();
	}
}

