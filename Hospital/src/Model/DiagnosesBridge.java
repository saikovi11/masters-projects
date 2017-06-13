package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DIAGNOSESBRIDGE")
public class DiagnosesBridge {
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="course_seq")
	 @SequenceGenerator(name="course_seq",sequenceName="DIAGNOSESBRIDGESEQ")
	@Column(name = "ID")
	long id;
	
	 @Column(name = "DISCHARGE")
	long discharge;
	
	 @Column(name = "DIAGNOSED_PHYSICIAN")
	long diagnosedPhysician;
	
	 @Column(name = "DIAGNOSES")
	long diagnoses;
	
	 @Column(name = "PRESENTONADMISSION")
	char presentOnAdmission;
	
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

	public long getDiagnosedPhysician() {
		return diagnosedPhysician;
	}

	public void setDiagnosedPhysician(long diagnosedPhysician) {
		this.diagnosedPhysician = diagnosedPhysician;
	}

	public long getDiagnoses() {
		return diagnoses;
	}

	public void setDiagnoses(long diagnoses) {
		this.diagnoses = diagnoses;
	}

	public char getPresentOnAdmission() {
		return presentOnAdmission;
	}

	public void setPresentOnAdmission(char presentOnAdmission) {
		this.presentOnAdmission = presentOnAdmission;
	}

	public long getSequence() {
		return sequence;
	}

	public void setSequence(long sequence) {
		this.sequence = sequence;
	}
	
	

}
