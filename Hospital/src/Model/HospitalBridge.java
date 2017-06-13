package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "HOSPITALBRIDGE")
public class HospitalBridge {
	
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="course_seq")
	 @SequenceGenerator(name="course_seq",sequenceName="HOSPITALBRIDGESEQ")
	 @Column(name = "ID")
	long id;
	
	 @Column(name = "HOSPITAL")
	long hospital;
	
	 @Column(name = "PHYSICIAN")
	long physician;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getHospital() {
		return hospital;
	}

	public void setHospital(long hospital) {
		this.hospital = hospital;
	}

	public long getPhysician() {
		return physician;
	}

	public void setPhysician(long physician) {
		this.physician = physician;
	}
	 
	 
	 

}
