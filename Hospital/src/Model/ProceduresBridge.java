package Model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PROCEDURESBRIDGE")
public class ProceduresBridge {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="course_seq")
	 @SequenceGenerator(name="course_seq",sequenceName="PROCEDURESBRIDGESEQ")
	 @Column(name = "ID")
	long id;
	
	 @Column(name = "DISCHARGE")
	long discharge;
	
	 @Column(name = "PROCEDURE")
	long procedure;
	
	 
	 @Column(name = "PROCEDURE_PHYSICIAN")
	long procedurePhysician;
	
	 @Column(name = "PROCEDUREDATE")
	Date procedureDate;
	
	 
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


	public long getProcedure() {
		return procedure;
	}


	public void setProcedure(long procedure) {
		this.procedure = procedure;
	}


	public long getProcedurePhysician() {
		return procedurePhysician;
	}


	public void setProcedurePhysician(long procedurePhysician) {
		this.procedurePhysician = procedurePhysician;
	}


	public Date getProcedureDate() {
		return procedureDate;
	}


	public void setProcedureDate(Date procedureDate) {
		this.procedureDate = procedureDate;
	}


	public long getSequence() {
		return sequence;
	}


	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	 
	 
	 
	
	

}
