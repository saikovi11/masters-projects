package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CHARGES")
public class Charges {
	
	
	 @Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="course_seq")
	 @SequenceGenerator(name="course_seq",sequenceName="CHARGESSEQ")
	 @Column(name = "ID")
	long id;
	
	 
	 @Column(name = "DISCHARGE") 
	long discharge;
	
	 @Column(name = "MEDICALITEM")
	long medicalItem;
	
	 @Column(name = "CHARGE")
	long charge;
	
	 @Column(name = "QUANTITY")
	long quantity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getDischarge() {
		return discharge;
	}

	public void setDischarge(long discharge) {
		this.discharge = discharge;
	}

	public long getMedicalItem() {
		return medicalItem;
	}

	public void setMedicalItem(long medicalItem) {
		this.medicalItem = medicalItem;
	}

	public long getCharge() {
		return charge;
	}

	public void setCharge(long charge) {
		this.charge = charge;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}
	 
	 

}
