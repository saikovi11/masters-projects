package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SPECIALTIES")
public class Specialties {
	
	

	 @Id @GeneratedValue
	 @Column(name = "ID")
	long id;
	
	 @Column(name = "SPECIALTYNAME")
	String specialtyName;
	
	 @Column(name = "SPECIALTYCODE")
	String specialtyCode;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSpecialtyName() {
		return specialtyName;
	}

	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}

	public String getSpecialtyCode() {
		return specialtyCode;
	}

	public void setSpecialtyCode(String specialtyCode) {
		this.specialtyCode = specialtyCode;
	}
	 
	 
	
	

}
