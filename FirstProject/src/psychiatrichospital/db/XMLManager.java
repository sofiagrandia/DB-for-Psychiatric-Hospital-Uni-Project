package psychiatrichospital.db;

import java.io.File;
import java.sql.*;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

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
	
	public static TreatmentList  unmarshallerTreatment (String name) throws JAXBException {
		
		JAXBContext jaxbContext = JAXBContext.newInstance(TreatmentList.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		File file = new File("./xmls/" +name);
		TreatmentList tl = (TreatmentList) unmarshaller.unmarshal(file);
		return tl;
	}
	public static void simpleTransform (String sourcePath, String xsltPath, String resultDir) {
		TransformerFactory tf = TransformerFactory.newInstance();
		try {
			Transformer t = tf.newTransformer(new StreamSource(new File(xsltPath)));
			System.out.println("Source path where the XML is:" + sourcePath);
			System.out.println("XSLT path" + xsltPath);
			System.out.println("Directory where you want to put it" + resultDir);
			t.transform(new StreamSource(new File (sourcePath)), new StreamResult(new File (resultDir)));
			
		}catch(Exception e) {
			e.printStackTrace();
		}}
	
	public static void HTMLgenTreatments(String name) {
		
	}

	
}


