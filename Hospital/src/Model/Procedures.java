package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROCEDURES")
public class Procedures {
	
	
	 @Id @GeneratedValue
	 @Column(name = "ID")
	long id;
	 @Column(name = "PROCEDURESCODE")
	String proceduresCode;
	 @Column(name = "PROCEDURESDESCRIPTION")
	String proceduresDescription;
	 @Column(name = "PROCEDURESTYPE")
	String proceduresType;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProceduresCode() {
		return proceduresCode;
	}
	public void setProceduresCode(String proceduresCode) {
		this.proceduresCode = proceduresCode;
	}
	public String getProceduresDescription() {
		return proceduresDescription;
	}
	public void setProceduresDescription(String proceduresDescription) {
		this.proceduresDescription = proceduresDescription;
	}
	public String getProceduresType() {
		return proceduresType;
	}
	public void setProceduresType(String proceduresType) {
		this.proceduresType = proceduresType;
	}
	
	 
	 
	

}
