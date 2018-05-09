package model;

import java.sql.Timestamp;
import java.util.Date;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Assignment {
	
	private Long assignmentId;
	
	private String name;

	private Date deadline;
	
	private String description;
	
	private Long labId;
	
	public Assignment() {
		
	}
	
	public Assignment(String name, Timestamp deadline, String description, Long labId) {
		
		this.name = name;
		this.deadline = deadline;
		this.setDescription(description);
		this.labId = labId;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getLabId() {
		return labId;
	}

	public void setLabId(Long labId) {
		this.labId = labId;
	}

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

}
