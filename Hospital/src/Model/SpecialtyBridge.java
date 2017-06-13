package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "SPECIALTYBRIDGE")
public class SpecialtyBridge {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="course_seq")
	 @SequenceGenerator(name="course_seq",sequenceName="SPECIALTYBRIDGESEQ")
	@Column(name = "ID")
	long id;
	
	 @Column(name = "SPECIALTY")
	long specialty;
	
	 @Column(name = "PHYSICIAN")
	long physician;
	
	 @Column(name = "ISPRIMARYSPECIALTY")
	char isPrimarySpecialty;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getSpecialty() {
		return specialty;
	}

	public void setSpecialty(long specialty) {
		this.specialty = specialty;
	}

	public long getPhysician() {
		return physician;
	}

	public void setPhysician(long physician) {
		this.physician = physician;
	}

	public char getIsPrimarySpecialty() {
		return isPrimarySpecialty;
	}

	public void setIsPrimarySpecialty(char isPrimarySpecialty) {
		this.isPrimarySpecialty = isPrimarySpecialty;
	}
	 
	 
	
	
	
	

}
