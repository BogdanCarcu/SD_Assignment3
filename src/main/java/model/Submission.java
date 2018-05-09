package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Submission {
	
	private Long studentId;
	
	private Long assignmentId;
	
	private float grade;
	
	private String gitLink;
	
	private String remark;
	
	
	public Submission() {
		
	}
	
	public Submission(float grade, String gitLink, String remark) {
		
		this.grade = grade;
		this.gitLink = gitLink;
		this.remark = remark;
		
	}

	public float getGrade() {
		return grade;
	}

	public void setGrade(float grade) {
		this.grade = grade;
	}

	public String getGitLink() {
		return gitLink;
	}

	public void setGitLink(String gitLink) {
		this.gitLink = gitLink;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getStudentId() {
		return studentId;
	}

	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}

	public Long getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Long assignmentId) {
		this.assignmentId = assignmentId;
	}

}
