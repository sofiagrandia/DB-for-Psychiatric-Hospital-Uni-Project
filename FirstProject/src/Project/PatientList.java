package Project;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "PatientList")

public class PatientList {
	@XmlElement(name="Patient")
	private List<Patient> listPatient;

	public PatientList(List<Patient> listPatient) {
		super();
		this.listPatient = listPatient;
	}

	public List<Patient> getListPatient() {
		return listPatient;
	}

	public void setListPatient(List<Patient> listPatient) {
		this.listPatient = listPatient;
	}

	@Override
	public String toString() {
		return "PatientList []";
	}

	public PatientList() {
		super();
	}
	
	
}
