package Project;


import java.io.*;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.*;




public class Marshall {
	
		private static EntityManager em;
		private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
		private static void printReports() {
			Query q1 = em.createNativeQuery("SELECT * FROM treatment", Treatment.class);
			List<Treatment> treats = (List<Treatment>) q1.getResultList();
			// Print the departments
			for (Treatment t : treats) {
				System.out.println(t);
			}
		}
		
		public static void main(String[] args) throws Exception {
			
			em = Persistence.createEntityManagerFactory("company-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
					
			// Create the JAXBContext
			JAXBContext jaxbContext = JAXBContext.newInstance(Treatment.class);
			// Get the marshaller
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			// Pretty formatting
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			
			// Choose the report to turn into an XML
			// Choose his new department
			printReports();
			System.out.print("Choose a treatment to turn into an XML file:");
			int t_id = Integer.parseInt(reader.readLine());
			Query q2 = em.createNativeQuery("SELECT * FROM reports WHERE id = ?", Treatment.class);
			q2.setParameter(1, t_id);
			Treatment treatment = (Treatment) q2.getSingleResult();
			
			// Use the Marshaller to marshal the Java object to a file
			File file = new File("./xmls/Sample-Report.xml");
			marshaller.marshal(treatment, file);
			// Printout
			marshaller.marshal(treatment, System.out);

		}

}
