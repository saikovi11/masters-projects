package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HOSPITALS")
public class Hospitals {
	
	 @Id @GeneratedValue
	 @Column(name = "ID")
	long id;
	 
	 @Column(name = "HOSPITALNAME")
	String hospitalName;
	 
	 
	 @Column(name = "HOSPITALID")
	long hospitalId;
	
	 @Column(name = "LOCATION")
	String location;
	
	 @Column(name = "ISTEACHING")
	char isTeaching;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public long getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(long hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public char getIsTeaching() {
		return isTeaching;
	}

	public void setIsTeaching(char isTeaching) {
		this.isTeaching = isTeaching;
	}
	 
	 
	 

}
