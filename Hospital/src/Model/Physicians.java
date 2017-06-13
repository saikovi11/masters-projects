package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PHYSICIANS")
public class Physicians {

	
	@Id @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="course_seq")
	 @SequenceGenerator(name="course_seq",sequenceName="PHYSICIANSSEQ")
	 @Column(name = "ID")
	long id;
	
	 @Column(name = "PHYSICIANID")
	String physicianId;
	
	 @Column(name = "NATIONALPROVIDERID")
	String nationalProviderId;
	
	 @Column(name = "PHYSICIANNAME")
	String physicianName;
	
	 @Column(name = "EMAIL")
	String email;
	
	 @Column(name = "GENDER")
	String gender;
	
	 @Column(name = "PRIMARYSTATE")
	String primaryState;
	
	 @Column(name = "PRIMARYCITY")
	String primaryCity;
	
	 @Column(name = "PRIMARYCOUNTRY")
	String primaryCountry;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPhysicianId() {
		return physicianId;
	}

	public void setPhysicianId(String physicianId) {
		this.physicianId = physicianId;
	}

	public String getNationalProviderId() {
		return nationalProviderId;
	}

	public void setNationalProviderId(String nationalProviderId) {
		this.nationalProviderId = nationalProviderId;
	}

	public String getPhysicianName() {
		return physicianName;
	}

	public void setPhysicianName(String physicianName) {
		this.physicianName = physicianName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPrimaryState() {
		return primaryState;
	}

	public void setPrimaryState(String primaryState) {
		this.primaryState = primaryState;
	}

	public String getPrimaryCity() {
		return primaryCity;
	}

	public void setPrimaryCity(String primaryCity) {
		this.primaryCity = primaryCity;
	}

	public String getPrimaryCountry() {
		return primaryCountry;
	}

	public void setPrimaryCountry(String primaryCountry) {
		this.primaryCountry = primaryCountry;
	}
	 
	 
}
