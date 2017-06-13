package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MEDICALITEMS")
public class MedicalItems {
	
	
	 @Id @GeneratedValue
	 @Column(name = "ID")
	long id;
	
	 @Column(name = "MEDICALITEMCODE")
	String medicalItemCode;
	
	 @Column(name = "MEDICALITEMDESCRIPTION")
	String medicalItemDescription;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMedicalItemCode() {
		return medicalItemCode;
	}

	public void setMedicalItemCode(String medicalItemCode) {
		this.medicalItemCode = medicalItemCode;
	}

	public String getMedicalItemDescription() {
		return medicalItemDescription;
	}

	public void setMedicalItemDescription(String medicalItemDescription) {
		this.medicalItemDescription = medicalItemDescription;
	}
	 
	 
	
	

}
