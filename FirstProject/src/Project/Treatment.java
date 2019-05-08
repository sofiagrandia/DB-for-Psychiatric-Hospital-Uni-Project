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
@XmlType(propOrder = { "type", "number", "doctor" })

public class Treatment implements Serializable {
	@Id
	@GeneratedValue(generator="treatment")
	@TableGenerator(name="treatment", table="sqlite_sequence",
	    pkColumnName="type", valueColumnName="number", pkColumnValue="treatment")
	@XmlAttribute
	private Integer id;
	@XmlAttribute
	private String type;
	@XmlAttribute
	private Integer number;
	@OneToMany(mappedBy="treatment")
	@XmlElement(name = "Doctor")
    @XmlElementWrapper(name = "Doctors")
	private Doctor doctor;
	
	
	
	
	private static final long serialVersionUID = -5847813978949146445L;
	private int doctor_id;
	
	public Treatment() {
		super();
	}

	public Treatment(int id, String type, int number, int doctor_id) {
		super();
		this.id = id;
		this.type = type;
		this.number = number;
		this.doctor_id = doctor_id;
	}
	public Treatment(int id, String type, int number) {
		super();
		this.id = id;
		this.type = type;
		this.number = number;
	
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + doctor_id;
		result = prime * result + id;
		result = prime * result + number;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (doctor_id != other.doctor_id)
			return false;
		if (id != other.id)
			return false;
		if (number != other.number)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(int doctor_id) {
		this.doctor_id = doctor_id;
	}

	@Override
	public String toString() {
		return "Treatment [id=" + id + ", type=" + type + ", number=" + number + ", doctor_id=" + doctor_id + "]";
	}
	
}
