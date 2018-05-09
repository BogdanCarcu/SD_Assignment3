package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import model.IUser;
import model.Student;
import model.Teacher;

public class Login {
	
public IUser login(String email, String password) {
		
		try {
			
			HttpClient httpClient = new DefaultHttpClient();
			String request = String.format("http://localhost:8080/login?email=%s&password=%s", email, PasswordEncryptor.oneWayEncryption(password));
			HttpGet loginRequest = new HttpGet(request);
			
			HttpResponse response = httpClient.execute(loginRequest);
	
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
			
			BufferedReader br = new BufferedReader(
                    new InputStreamReader((response.getEntity().getContent())));
			
			
			 StringBuilder builder = new StringBuilder();
	         String line;

	            while ((line = br.readLine()) != null) {
	                builder.append(line);
	                builder.append(System.lineSeparator());
	            }

	        ObjectMapper mapper = new ObjectMapper();
	        String jsonInString = builder.toString();
	        
	        if(jsonInString.equals("")) {
	        	
	        	httpClient.getConnectionManager().shutdown();
	        	return null;
	        }
	        
	        IUser user;
	        if(!jsonInString.contains("group"))
	        	user = mapper.readValue(jsonInString, Teacher.class);
	        else
	        	user = mapper.readValue(jsonInString, Student.class);
	        
	        
			httpClient.getConnectionManager().shutdown();
			
			return user;
			
		  }  catch (ClientProtocolException e) {
			
			e.printStackTrace();

		  } catch (IOException e) {
		
			e.printStackTrace();
		  }
		
		return null;
	}

}
