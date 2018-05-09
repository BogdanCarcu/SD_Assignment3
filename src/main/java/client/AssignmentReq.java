package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import model.Assignment;

public class AssignmentReq {
	
	private static final String BASE = "http://localhost:8080/assignment";	
	
	public List<Assignment> getAssignments(Long labId) {
		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			String request;
			
			if(labId != null)
				request = String.format("http://localhost:8080/assignment?labId=%d", labId);
			else
				request = "http://localhost:8080/assignment";
			
			HttpGet getRequest = new HttpGet(request);
			
			HttpResponse response = httpClient.execute(getRequest);

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
	        
	        List<Assignment> assignments;
	        assignments = mapper.readValue(jsonInString, new TypeReference<List<Assignment>>(){});
	        
			httpClient.getConnectionManager().shutdown();
			
			return assignments;
			
		  }  catch (ClientProtocolException e) {
			
			e.printStackTrace();

		  } catch (IOException e) {
		
			e.printStackTrace();
		  }
		
		return null;
		
	}

	public Assignment getAssignmentById(Long assignmentId) {
	   
		try {
			
			HttpClient httpClient = new DefaultHttpClient();
			
			String request = BASE + String.format("/%d", assignmentId);
			
			HttpGet getRequest = new HttpGet(request);
			
			HttpResponse response = httpClient.execute(getRequest);

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
	        
	        Assignment assignment;
	        assignment = mapper.readValue(jsonInString, Assignment.class);
	        
			httpClient.getConnectionManager().shutdown();
			
			return assignment;
			
		  }  catch (ClientProtocolException e) {
			
			e.printStackTrace();

		  } catch (IOException e) {
		
			e.printStackTrace();
		  }
		
		return null;
		
	}

	public Assignment saveAssignment(Assignment assignment) {
	  
		 try {

 			HttpClient httpClient = new DefaultHttpClient();
 			String request = BASE;
 			HttpPost postRequest = new HttpPost(request);

 			ObjectMapper mapper = new ObjectMapper();
 			String jsonInString = mapper.writeValueAsString(assignment);
 		
 			StringEntity input = new StringEntity(jsonInString);
 			input.setContentType("application/json");
 			
 			postRequest.setEntity(input);

 			HttpResponse response = httpClient.execute(postRequest);

 			if (response.getStatusLine().getStatusCode() != 200) {
 				throw new RuntimeException("Failed : HTTP error code : "
 					+ response.getStatusLine().getStatusCode());
 			}


 			httpClient.getConnectionManager().shutdown();
 			
 			return assignment;

 		  } catch (MalformedURLException e) {

 			e.printStackTrace();
 		
 		  } catch (IOException e) {

 			e.printStackTrace();

 		  }
 	 
 	 return null;
		
	}
	
	public Assignment updateAssignment(Assignment assignment) {
	   

    	try {

			HttpClient httpClient = new DefaultHttpClient();
			String request = BASE;
			HttpPut putRequest = new HttpPut(request);

			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(assignment);
		
			StringEntity input = new StringEntity(jsonInString);
			input.setContentType("application/json");
			
			putRequest.setEntity(input);

			HttpResponse response = httpClient.execute(putRequest);

			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatusLine().getStatusCode());
			}


			httpClient.getConnectionManager().shutdown();
			
			return assignment;

		  } catch (MalformedURLException e) {

			e.printStackTrace();
		
		  } catch (IOException e) {

			e.printStackTrace();

		  }
	 
	 return null;
    		
	}
	

	public String deleteAssignmentById(Long assignmentId) {
	   
		try {

			HttpClient httpClient = new DefaultHttpClient();
			String request = BASE + String.format("/%d", assignmentId);
			HttpDelete delRequest = new HttpDelete(request);

			HttpResponse response = httpClient.execute(delRequest);

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
			return builder.toString();

		  } catch (MalformedURLException e) {

			e.printStackTrace();
		
		  } catch (IOException e) {

			e.printStackTrace();

		  }
	 
	 return null;
		
	}


}
