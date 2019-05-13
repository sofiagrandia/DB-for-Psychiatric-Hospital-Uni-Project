package Project;


import java.io.*;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.*;

import psychiatrichospital.db.DBManager;
import psychiatrichospital.db.JPAManager;

public class Marshall {
	
		private static DBManager dm;
		private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
		
		
		public static void main(String[] args) throws Exception {
			
			
			
			
			JAXBContext jaxbContext = JAXBContext.newInstance(Treatment.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
			
//System.out.println(dm.selectTreatment());
System.out.print("Choose a treatment to turn into an XML file:");
//			int t_id = Integer.parseInt(reader.readLine());
			//Treatment treatment = dm.getTreatmentId(t_id);
			Treatment treatment = new Treatment(1, "test", 2);
			
			File file = new File("./xmls/sample-treatment.xml");
			marshaller.marshal(treatment, file);
			marshaller.marshal(treatment, System.out);

		}

}
