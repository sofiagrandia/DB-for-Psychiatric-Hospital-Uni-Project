package Project;



import Project.Nurse;
import Project.Doctor;
import Project.Treatment;
import Project.Room;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "patients")

public class Patient implements Serializable {

	private static final long serialVersionUID = -4386205613920392395L;
	@Id
	@GeneratedValue(generator="patients")
	@TableGenerator(name="patients", table="sqlite_sequence",
	    pkColumnName="name", valueColumnName="seq", pkColumnValue="patients")
	private int id;
	private String name;
	private String gender;
	private Date dob;
    @OnetoOne(mappedBy= "room")
	private int room_id;
	private byte [] photo;
	private Room room;
	private List <Treatment> treatments;
	private List <Doctor> doctors;
	private List <Nurse> nurses;
	public Patient(int id, String name, String gender, Date dob, int room_id, byte[] photo, Room room,
			List<Treatment> treatments, List<Doctor> doctors, List<Nurse> nurses) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.room_id = room_id;
		this.photo = photo;
		this.room = room;
		this.treatments = treatments;
		this.doctors = doctors;
		this.nurses = nurses;
	}
	public Patient(String name, String gender, Date dob, int room_id, byte[] photo, Room room,
			List<Treatment> treatments, List<Doctor> doctors, List<Nurse> nurses) {
		super();
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.room_id = room_id;
		this.photo = photo;
		this.room = room;
		this.treatments = treatments;
		this.doctors = doctors;
		this.nurses = nurses;
	}
	public Patient(List<Treatment> treatments, List<Doctor> doctors, List<Nurse> nurses) {
		super();
		this.treatments = treatments;
		this.doctors = doctors;
		this.nurses = nurses;
	}
	public Patient(int id2, String name2, String gender2, Date date, int room_id2, byte[] blobArray) {
		// TODO Auto-generated constructor stub
	}
	public Patient(String nameP, String genderP, Date dP, int room_idP, byte[] bytesBlob) {
		// TODO Auto-generated constructor stub
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
	public int getRoom_id() {
		return room_id;
	}
	public void setRoom_id(int room_id) {
		this.room_id = room_id;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room room) {
		this.room = room;
	}
	public List<Treatment> getTreatments() {
		return treatments;
	}
	public void setTreatments(List<Treatment> treatments) {
		this.treatments = treatments;
	}
	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	public List<Nurse> getNurses() {
		return nurses;
	}
	public void setNurses(List<Nurse> nurses) {
		this.nurses = nurses;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void addNurse(Nurse n) {
		if (!nurses.contains(n)) {
			this.nurses.add(n);
		}
	}
	public void removeNurse(Nurse n) {
		if (nurses.contains(n)) {
			this.nurses.remove(n);
		}
	}
	public void addDoctor(Doctor d) {
		if (!doctors.contains(d)) {
			this.doctors.add(d);
		}
	}
	public void removeDoctor(Doctor d) {
		if (doctors.contains(d)) {
			this.doctors.remove(d);
		}
	}
	public void addTreatment(Treatment t) {
		if (treatments.contains(t)) {
			this.treatments.add(t);
		}
	}
	public void removeTreatment(Treatment t) {
		if (treatments.contains(t)) {
			this.treatments.remove(t);
		}
	}
	
	public void removeRoom() {
		this.room=null;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", room_id=" + room_id
				+ ", photo=" + Arrays.toString(photo) + ", room=" + room + ", treatments=" + treatments + ", doctors="
				+ doctors + ", nurses=" + nurses + "]";
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
		Patient other = (Patient) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}