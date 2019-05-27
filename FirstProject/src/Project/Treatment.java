package Project;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.*;





@Entity
@Table(name = "treatment")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Treatment")
@XmlType(propOrder = { "type", "number", "doctor" ,"p"})

public class Treatment implements Serializable {
	
	
	public Treatment() {
		super();
		p = new ArrayList<Patient>();
	}

	public Treatment(String type, Integer number) {
		super();
		
		this.type = type;
		this.number = number;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Treatment(int id, String type, int number ) {
		super();
		this.id=id;
		this.type=type;
		this.number=number;
	}
	
	@Id
	@GeneratedValue(generator = "treatment")
	@TableGenerator(name = "treatment", table = "sqlite_sequence", 
		    pkColumnName="name", valueColumnName="seq", pkColumnValue="patient")
	@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String type;
	@XmlAttribute
	private Integer number;
@Transient
@XmlElement(name = "Doctor")
	// @XmlElementWrapper(name = "Doctors")
	private Doctor doctor;
	@ManyToMany
	@JoinTable(name = "patients", joinColumns = {
			@JoinColumn(name = "treatment_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "patient_id", referencedColumnName = "id") })
	@XmlElement(name = "Patient")
	@XmlElementWrapper(name = "Patients")
	private List<Patient> p;

	public Treatment(Integer id, String type, Integer number, Doctor doctor, List<Patient> p) {
		super();
		this.id = id;
		this.type = type;
		this.number = number;
		this.doctor = doctor;
		this.p = p;
	}
	
	
	public Treatment() {
		super();
	}



	public Treatment(String type, Integer number, Doctor doctor, List<Patient> p) {
		super();
		this.type = type;
		this.number = number;
		this.doctor = doctor;
		this.p = p;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Treatment other = (Treatment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public List<Patient> getP() {
		return p;
	}
	public void setP(List<Patient> p) {
		this.p = p;
	}
	@Override
	public String toString() {
		return "Treatment [id=" + id + ", type=" + type + ", number=" + number + ", doctor=" + doctor + ", p=" + p
				+ "]";
	}

}
