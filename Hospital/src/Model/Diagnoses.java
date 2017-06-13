package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DIAGNOSES")
public class Diagnoses {
	
	
	 @Id @GeneratedValue
	 @Column(name = "ID")
	long id;
	 
	 @Column(name = "DIAGNOSESCODE")
	String diagnosesCode;
	 
	 @Column(name = "DIAGNOSESDESCRIPTION")
	String diagnosesDescription;
	 
	 @Column(name = "DIAGNOSESTYPE")
	String diagnosesType;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDiagnosesCode() {
		return diagnosesCode;
	}
	public void setDiagnosesCode(String diagnosesCode) {
		this.diagnosesCode = diagnosesCode;
	}
	public String getDiagnosesDescription() {
		return diagnosesDescription;
	}
	public void setDiagnosesDescription(String diagnosesDescription) {
		this.diagnosesDescription = diagnosesDescription;
	}
	public String getDiagnosesType() {
		return diagnosesType;
	}
	public void setDiagnosesType(String diagnosesType) {
		this.diagnosesType = diagnosesType;
	}
	
	

}
