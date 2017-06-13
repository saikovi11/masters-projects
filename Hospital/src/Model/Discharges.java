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
@Table(name = "DISCHARGES")
public class Discharges {
	
	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="course_seq")
	 @SequenceGenerator(name="course_seq",sequenceName="DISCHARGESSEQ")
	 @Column(name = "ID")
	long id;
	
	 @Column(name = "ENCOUNTERNUMBER")
	long encounterNumber;
	
	 
	 @Column(name = "MEDICALRECORDNUMBER")
	long medicalRecordNumber;
	
	 
	 @Column(name = "DISCHARGEDATE")
	Date dischargeDate;
	
	 @Column(name = "ADMISSIONDATE")
	Date admissionDate;
	
	 
	 @Column(name = "GENDER")
	String gender;
	
	 @Column(name = "PATIENNAME")
	String patientName;
	
	 @Column(name = "PATIENTPHONENUMBER")
	String patientPhoneNumber;
	
	 @Column(name = "PATIENTADDRESS")
	String patientAddress;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getEncounterNumber() {
		return encounterNumber;
	}

	public void setEncounterNumber(long encounterNumber) {
		this.encounterNumber = encounterNumber;
	}

	public long getMedicalRecordNumber() {
		return medicalRecordNumber;
	}

	public void setMedicalRecordNumber(long medicalRecordNumber) {
		this.medicalRecordNumber = medicalRecordNumber;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getPatientPhoneNumber() {
		return patientPhoneNumber;
	}

	public void setPatientPhoneNumber(String patientPhoneNumber) {
		this.patientPhoneNumber = patientPhoneNumber;
	}

	public String getPatientAddress() {
		return patientAddress;
	}

	public void setPatientAddress(String patientAddress) {
		this.patientAddress = patientAddress;
	}
	
	

}
