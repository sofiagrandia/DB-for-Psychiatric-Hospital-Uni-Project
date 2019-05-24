package Project;
import java.io.Serializable;


import javax.persistence.*;
import javax.persistence.OneToMany;
import java.util.*;

import Project.Patient;


@Entity
@Table(name = "room")
public class Room  implements Serializable{

	private static final long serialVersionUID = -50179526691591647L;

	@Id
	@GeneratedValue(generator="room")
	@TableGenerator(name="room", table="sqlite_sequence",
		    pkColumnName="floor", valueColumnName="seq", pkColumnValue="room")
	private Integer id;
	private int floor;
    @OneToMany(mappedBy= "room")
	private List <Patient> patients;
    
    
	public Room() {
		super();
		this.patients = new ArrayList<Patient>();
	}
	
	public Room(int id, int floor, List <Patient> patients) {
		super();
		this.id = id;
		this.floor = floor;
		this.patients = patients;
	}
	public Room(int floor, List <Patient> patients) {
		super();
		this.floor = floor;
		this.patients = patients;
	}
	public Room(int floor) {
		super();
		this.floor = floor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getFloor() {
		return floor;
	}
	public void setFloor(int floor) {
		this.floor = floor;
	}
	public List <Patient> getPatient() {
		return patients;
	}
	public void setPatient(List <Patient> patients) {
		this.patients = patients;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public void addPatient(Patient p) {
		if(this.patients==null) {
			this.patients= new ArrayList<>();
			this.patients.add(p);
		}else
		if (!patients.contains(p)) {
			this.patients.add(p);
		}
	}
	public void removePatient(Patient p) {
		if (patients.contains(p)) {
			this.patients.remove(p);
		}
	}
	
	@Override
	public String toString() {
		return "Room [id=" + id + ", floor=" + floor + ", patient=" + patients + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Room other = (Room) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
	
	}
	
	
	
	