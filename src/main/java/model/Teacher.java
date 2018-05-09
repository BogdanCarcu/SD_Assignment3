package model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Teacher implements IUser{
	
	
	private String email;
	
	private String password;

	public Teacher() {
		
	}
	
	public Teacher(String email, String password) {
		
		this.email = email;
		this.password = password;
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
