package model;

import java.sql.Timestamp;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class LaboratoryClass {
	
	private Long labId;
	
	private int number;
	
	private Date date;
	
	private String title;
	
	private String curricula;
	
	private String labText;
	
	public LaboratoryClass() {
		
	}
	
	public LaboratoryClass(int number, Timestamp date, String title, 
			String curricula, String labText) { 
		
		this.number = number;
		this.date = date;
		this.title = title;
		this.curricula = curricula;
		this.labText = labText;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		
		if(number < 0)
			this.number = 1;
		else if(number > 14)
			this.number = 14;
		else
			this.number = number;
		
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date2) {
		this.date = date2;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCurricula() {
		return curricula;
	}

	public void setCurricula(String curricula) {
		this.curricula = curricula;
	}

	public String getLabText() {
		return labText;
	}

	public void setLabText(String labText) {
		this.labText = labText;
	}

	public Long getLabId() {
		return labId;
	}

	public void setLabId(Long labId) {
		this.labId = labId;
	}

		

}
