package Project;
import java.io.Serializable;
import java.sql.Date;
import Project.Doctor;
import Project.Nurse;

public class Contract implements Serializable{


	 /**
	 * 
	 */
	private static final long serialVersionUID = -7266906418797553220L;
	int id;
	 private float money;
	 private int holidays;
	 private Date dob;
	 private Doctor doctor;
	 private Nurse nurse;
	 
	 public Contract(int id, float money, int holidays, Date dob, Doctor doctor, Nurse nurse) {
			super();
			this.id = id;
			this.money = money;
			this.holidays = holidays;
			this.dob = dob;
			this.doctor = doctor;
			this.nurse = nurse;
		}
		public Contract(float money, int holidays, Date dob, Doctor doctor, Nurse nurse) {
			super();
			this.money = money;
			this.holidays = holidays;
			this.dob = dob;
			this.doctor = doctor;
			this.nurse = nurse;
		}
		 
	public Contract( float money2, int holidays2, Date d) {
			this.money=money2;
			this.holidays=holidays2;
			this.dob=d;}
	
	public Contract( int id,float money2, int holidays2, Date d) {
		this.id=id;
		this.money=money2;
		this.holidays=holidays2;
		this.dob=d;}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getMoney() {
		return money;
	}
	public void setMoney(float money) {
		this.money = money;
	}
	public int getHolidays() {
		return holidays;
	}
	public void setHolidays(int holidays) {
		this.holidays = holidays;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Doctor getDoctor() {
		return doctor;
	}
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	public Nurse getNurse() {
		return nurse;
	}
	public void setNurse(Nurse nurse) {
		this.nurse = nurse;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dob == null) ? 0 : dob.hashCode());
		result = prime * result + ((doctor == null) ? 0 : doctor.hashCode());
		result = prime * result + holidays;
		result = prime * result + id;
		result = prime * result + Float.floatToIntBits(money);
		result = prime * result + ((nurse == null) ? 0 : nurse.hashCode());
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
		Contract other = (Contract) obj;
		if (dob == null) {
			if (other.dob != null)
				return false;
		} else if (!dob.equals(other.dob))
			return false;
		if (doctor == null) {
			if (other.doctor != null)
				return false;
		} else if (!doctor.equals(other.doctor))
			return false;
		if (holidays != other.holidays)
			return false;
		if (id != other.id)
			return false;
		if (Float.floatToIntBits(money) != Float.floatToIntBits(other.money))
			return false;
		if (nurse == null) {
			if (other.nurse != null)
				return false;
		} else if (!nurse.equals(other.nurse))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Contract [id=" + id + ", money=" + money + ", holidays=" + holidays + ", dob=" + dob + ", doctor="
				+ doctor + ", nurse=" + nurse + "]";
	}
	
	
	 
	 
}
