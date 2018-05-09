package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import model.Submission;

public class SubmissionReq {
	
	private static final String BASE = "http://localhost:8080/submission";	
	
	public Submission getSubmissionById(Long studentId, Long assignmentId) {
		
		try {
			HttpClient httpClient = new DefaultHttpClient();
			String request = String.format("http://localhost:8080/submission?studentId=%d&assignmentId=%d", studentId, assignmentId);
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
	        
	        if(jsonInString.equals("") || jsonInString.contains("no content")) {
	        	
	        	httpClient.getConnectionManager().shutdown();
	        	return null;
	        }
	        
	        Submission s = null;
	        List<Submission> ss;
	        ss = mapper.readValue(jsonInString, new TypeReference<List<Submission>>(){});
	        
	        if(ss.size() > 0)
	        	s = ss.get(0);
	   
			httpClient.getConnectionManager().shutdown();
			
			return s;
			
		  }  catch (ClientProtocolException e) {
			
			e.printStackTrace();

		  } catch (IOException e) {
		
			e.printStackTrace();
		  }
		
		return null;
		
		}	
	
	
    public Submission saveSubmission(Submission submission) {
    	
    	try {

 			HttpClient httpClient = new DefaultHttpClient();
 			String request = BASE;
 			HttpPost postRequest = new HttpPost(request);

 			ObjectMapper mapper = new ObjectMapper();
 			String jsonInString = mapper.writeValueAsString(submission);
 		
 			StringEntity input = new StringEntity(jsonInString);
 			input.setContentType("application/json");
 			
 			postRequest.setEntity(input);

 			HttpResponse response = httpClient.execute(postRequest);

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

 	        
 	        if(builder.toString().equals("") || builder.toString().equals("no content")) {
 	        	
 	        	httpClient.getConnectionManager().shutdown();
 	        	return null;
 	        }
 			

 			httpClient.getConnectionManager().shutdown();		
 			
 			
 			return submission;

 		  } catch (MalformedURLException e) {

 			e.printStackTrace();
 		
 		  } catch (IOException e) {

 			e.printStackTrace();

 		  }
 	 
 	 return null;
    }
    
 	
 	 public boolean updateSubmission(Long studentId, Long assignmentId, 
 			 			 String remark,
 			 			 String gitLink) {
 		
 	
 		try {

			HttpClient httpClient = new DefaultHttpClient();
			String request = BASE + String.format("?studentId=%d&assignmentId=%d", studentId, assignmentId);
			
			if(remark != null)
				request += String.format("&remark=%s", remark.replaceAll(" ", "+"));
			
			if(gitLink != null)
				request += String.format("&gitLink=%s", gitLink);
			
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

	        if(builder.toString().contains("false"))
	        	return false;

			httpClient.getConnectionManager().shutdown();
			
			return true;

		  } catch (MalformedURLException e) {

			e.printStackTrace();
		
		  } catch (IOException e) {

			e.printStackTrace();

		  }
	 
	 return false;
 		 
 		
 	 }
 	 

    public String deleteSubmission(Long studentId, Long assignmentId) {
    	try {

			HttpClient httpClient = new DefaultHttpClient();
			String request = BASE + String.format("?studentId=%d&assignmentId=%d", studentId, assignmentId);
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
 
   
    public Map<String, Float> getAllGradesForAssignment(Long id) {
    
    	
    	try {
			HttpClient httpClient = new DefaultHttpClient();
			
			String request = BASE + String.format("/%d", id);
			
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
	        
	        HashMap<String, Float> result = mapper.readValue(jsonInString, new TypeReference<Map<String, Float>>(){});
	        
	        
			httpClient.getConnectionManager().shutdown();
			
			return result;
			
		  }  catch (ClientProtocolException e) {
			
			e.printStackTrace();

		  } catch (IOException e) {
		
			e.printStackTrace();
		  }
		
		return null;
    	
    	
    }

    
}
