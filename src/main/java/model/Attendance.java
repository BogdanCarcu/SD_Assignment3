package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Attendance {
	
	private Long studentId;
	
	private Long labId;
	
	private boolean isPresent;

	public Attendance() {
		
	}
	
	public Attendance(boolean isPresent, Long labId, Long studentId) {
	
		this.isPresent = isPresent;
		this.labId = labId;
		this.studentId = studentId;
		
	}
	

	public boolean isPresent() {
		return isPresent;
	}

	public void setPresent(boolean isPresent) {
		this.isPresent = isPresent;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getLabId() {
		return labId;
	}

	public void setLabId(Long labId) {
		this.labId = labId;
	}
	

}
