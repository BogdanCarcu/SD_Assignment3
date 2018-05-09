package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.DefaultHttpClient;

public class SubmissionMarker {
	
	private static final String BASE = "http://localhost:8080/grade";	

	 public boolean gradeSubmission(Long studentId, Long assignmentId, Float grade) {

		 try {

				HttpClient httpClient = new DefaultHttpClient();
				String request = BASE + String.format("?studentId=%d&assignmentId=%d&grade=%f", studentId, assignmentId, grade);
				
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
		         

				httpClient.getConnectionManager().shutdown();
				
				return builder.toString().contains("true");

			  } catch (MalformedURLException e) {

				e.printStackTrace();
			
			  } catch (IOException e) {

				e.printStackTrace();

			  }
		 
		 return false;
	 		 
			 
	 } 
	 
	
}
