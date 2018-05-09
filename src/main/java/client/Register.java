package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;

import model.Student;

public class Register {

	private static final String BASE = "http://localhost:8080/register";	
	
	public Student studentRegister(String token, String email, String password) {
		
		 try {

				HttpClient httpClient = new DefaultHttpClient();
				String request = BASE + String.format("?token=%s&email=%s&password=%s", token, email, PasswordEncryptor.oneWayEncryption(password));
			
				HttpPut putRequest = new HttpPut(request);
				HttpResponse response = httpClient.execute(putRequest);

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
		        
		        Student student;
		        student = mapper.readValue(jsonInString, Student.class);


				httpClient.getConnectionManager().shutdown();
				
				return student;

			  } catch (MalformedURLException e) {

				e.printStackTrace();
			
			  } catch (IOException e) {

				e.printStackTrace();

			  }
		 
		 return null;
	 		 
	}
			
	
}
