package Project;
import Project.Patient;
import Project.Treatment;
import xmlUtils.SQLDateAdapter;
import Project.Contract;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "doctor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Doctor")
@XmlType(propOrder = { "name", "gender", "dob" ,"hours"})

public class Doctor implements Serializable {
	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator="doctor")
	@TableGenerator(name="doctor", table="sqlite_sequence",
	   pkColumnName="name", valueColumnName="seq", pkColumnValue="doctor")
	

	private static final long serialVersionUID = 4922702477075776313L;
	@XmlTransient
	int id;
	@XmlElement
	private String name;
	@XmlElement
	private String  gender;
	@XmlElement
	@XmlJavaTypeAdapter(SQLDateAdapter.class)
	private Date dob;
	@XmlElement
	private int hours;
	@XmlTransient
	private Contract contract;
	@XmlTransient
	private List <Patient> patients;
	@XmlTransient
	private List <Treatment> treatments;

	public Doctor(int id, String name, String gender, Date dob, int hours, Contract contract, List<Patient> patients,
			List<Treatment> treatments) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.hours = hours;
		this.contract = contract;
		this.patients = patients;
		this.treatments = treatments;
	}
	public Doctor(String name, String gender, Date dob, int hours, Contract contract, List<Patient> patients,
			List<Treatment> treatments) {
		super();
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.hours = hours;
		this.contract = contract;
		this.patients = patients;
		this.treatments = treatments;
	}
	public Doctor(List<Patient> patients, List<Treatment> treatments) {
		super();
		this.patients = patients;
		this.treatments = treatments;
	}
	public Doctor(String nameDoc, String genderDoc, Date dDoc, int hoursDoc) {
		super();
		this.name=nameDoc;
		this.gender=genderDoc;
		this.dob=dDoc;
		this.hours=hoursDoc;
	}
	public Doctor(int id1, String name2, String gender2, Date date, int hours2) {
		this.id=id1;
		this.name=name2;
		this.gender=gender2;
		this.dob=date;
		this.hours=hours2;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
	}
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	public List<Treatment> getTreatments() {
		return treatments;
	}
	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contract == null) ? 0 : contract.hashCode());
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((gender == null) ? 0 : gender.hashCode());
		result = prime * result + hours;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((patients == null) ? 0 : patients.hashCode());
		result = prime * result + ((treatments == null) ? 0 : treatments.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Doctor other = (Doctor) obj;
		if (contract == null) {
			if (other.contract != null)
				return false;
		} else if (!contract.equals(other.contract))
			return false;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (gender == null) {
			if (other.gender != null)
				return false;
		} else if (!gender.equals(other.gender))
			return false;
		if (hours != other.hours)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (patients == null) {
			if (other.patients != null)
				return false;
		} else if (!patients.equals(other.patients))
			return false;
		if (treatments == null) {
			if (other.treatments != null)
				return false;
		} else if (!treatments.equals(other.treatments))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", hours=" + hours
				+ ", contract=" + contract + ", patients=" + patients + ", treatments=" + treatments + "]";
	}
	public Doctor() {
		super();
		// TODO INITIALIZE LISTS
	}
	
	
	
	
}