package Project;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import Project.Contract;
import Project.Patient;

public class Nurse implements Serializable  {

	private static final long serialVersionUID = -328423001663369252L;
	int id;
	private String name;
	private String  gender;
	private Date dob;
	private int hours;
	
	private List<Patient> patients;
	private Contract contract;
	
	
	public Nurse() {
		super();
		this.patients=new ArrayList<Patient>();
		
	}
	public Nurse(int id, String name, String gender, Date dob, int hours, List<Patient> patients, Contract contract) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.hours = hours;
		this.patients = patients;
		this.contract = contract;
	}
	public Nurse(String name, String gender, Date dob, int hours, List<Patient> patients, Contract contract) {
		super();
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.hours = hours;
		this.patients = patients;
		this.contract = contract;
	}
	public Nurse(List<Patient> patients) {
		super();
		this.patients = patients;
	}
	public Nurse(int id2, String name2, String gender2, Date dob2, int hours2) {
		this.id=id2;
		this.name=name2;
		this.gender=gender2;
		this.dob=dob2;
		this.hours=hours2;
	}
	
	
	public Nurse(String name, String gender, Date dob, int hours) {
		super();
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.hours = hours;
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
	public List<Patient> getPatients() {
		return patients;
	}
	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}
	public Contract getContract() {
		return contract;
	}
	public void setContract(Contract contract) {
		this.contract = contract;
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
		Nurse other = (Nurse) obj;
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
		return true;
	}
	@Override
	public String toString() {
		return "Nurse [id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", hours=" + hours
				+ ", patients=" + patients + ", contract=" + contract + "]";
	}

	
}