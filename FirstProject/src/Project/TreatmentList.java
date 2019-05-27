package Project;

import java.util.List;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "TreatmentList")

public class TreatmentList {
	@XmlElement(name="Treatment")
	private List<Treatment> listTreatment;

	public TreatmentList(List<Treatment> listTreatment) {
		super();
		this.listTreatment = listTreatment;
	}

	public List<Treatment> getListTreatment() {
		return listTreatment;
	}

	public void setListTreatment(List<Treatment> listTreatment) {
		this.listTreatment = listTreatment;
	}

	@Override
	public String toString() {
		return "TreatmentList []";
	}

	public TreatmentList() {
		super();
	}
	
	
}
