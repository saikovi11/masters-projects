package Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISCHARGEPHYSICIANBRIDGE")
public class DischargePhysicianBridge {

	@Id
	@GeneratedValue
	@Column(name = "ID")
	long id;

	@Column(name = "DISCHARGE")
	long discharge;

	@Column(name = "PHYSICIAN")
	long physician;

	@Column(name = "PHYSICIANTYPE")
	String physicianType;

	@Column(name = "INTERACTIONDATE")
	Date interactionDate;

	@Column(name = "SEQUENCE")
	long sequence;

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

	public long getPhysician() {
		return physician;
	}

	public void setPhysician(long physician) {
		this.physician = physician;
	}

	public String getPhysicianType() {
		return physicianType;
	}

	public void setPhysicianType(String physicianType) {
		this.physicianType = physicianType;
	}

	public Date getInteractionDate() {
		return interactionDate;
	}

	public void setInteractionDate(Date interactionDate) {
		this.interactionDate = interactionDate;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}

}
