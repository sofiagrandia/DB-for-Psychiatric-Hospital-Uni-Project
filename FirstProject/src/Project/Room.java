package Project;
import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import Project.Patient;
import sample.db.pojos.Department;

@Entity
@Table(name = "room")
public class Room  implements Serializable{
	

	/**
	 * 
	 */
	@Id
	@GeneratedValue(generator="room")
	@TableGenerator(name="room", table="sqlite_sequence",
		    pkColumnName="floor", valueColumnName="seq", pkColumnValue="room")
	private static final long serialVersionUID = -50179526691591647L;
	private int id;
	private int floor;
    @OneToOne(mappedBy= "patient")
	private Patient patient;
	public Room(int id, int floor, Patient patient) {
		super();
		this.id = id;
		this.floor = floor;
		this.patient = patient;
	}
	public Room(int floor, Patient patient) {
		super();
		this.floor = floor;
		this.patient = patient;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public Patient getPatient() {
		return patient;
	}
	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public String toString() {
		return "Room [id=" + id + ", floor=" + floor + ", patient=" + patient + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	// Equals uses primary keys, since they are unique
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Room other = (Room) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	}
	
	
	
	