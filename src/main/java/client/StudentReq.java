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

import model.Student;

public class StudentReq {

	private static final String BASE = "http://localhost:8080/student";
	
	    public List<Student> getAllStudents() {
	       
			try {
				HttpClient httpClient = new DefaultHttpClient();
				
				HttpGet getRequest = new HttpGet(BASE);
				
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
		        
		        List<Student> students;
		        students = mapper.readValue(jsonInString, new TypeReference<List<Student>>(){});
		        
				httpClient.getConnectionManager().shutdown();
				
				return students;
				
			  }  catch (ClientProtocolException e) {
				
				e.printStackTrace();

			  } catch (IOException e) {
			
				e.printStackTrace();
			  }
			
			return null;

	    	
	    }
	    
	    public Student getStudentById(Long studentId) {
	       
	    	try {
				HttpClient httpClient = new DefaultHttpClient();
				
				String request = BASE + String.format("/%d", studentId);
				
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
		        
		        Student student;
		        student = mapper.readValue(jsonInString, Student.class);
		        
				httpClient.getConnectionManager().shutdown();
				
				return student;
				
			  }  catch (ClientProtocolException e) {
				
				e.printStackTrace();

			  } catch (IOException e) {
			
				e.printStackTrace();
			  }
			
			return null;
	    	
	    }
	    
	
	    public Student saveStudent(Student student) {
	      
	    	 try {

	    			HttpClient httpClient = new DefaultHttpClient();
	    			String request = BASE;
	    			HttpPost postRequest = new HttpPost(request);

	    			ObjectMapper mapper = new ObjectMapper();
	    			String jsonInString = mapper.writeValueAsString(student);
	    		
	    			StringEntity input = new StringEntity(jsonInString);
	    			input.setContentType("application/json");
	    			
	    			postRequest.setEntity(input);

	    			HttpResponse response = httpClient.execute(postRequest);

	    			if (response.getStatusLine().getStatusCode() != 200) {
	    				throw new RuntimeException("Failed : HTTP error code : "
	    					+ response.getStatusLine().getStatusCode());
	    			}


	    			httpClient.getConnectionManager().shutdown();
	    			
	    			return student;

	    		  } catch (MalformedURLException e) {

	    			e.printStackTrace();
	    		
	    		  } catch (IOException e) {

	    			e.printStackTrace();

	    		  }
	    	 
	    	 return null;

	    	}
	    

	  
	    public Student updateStudent(Student student) {
	        
	    	try {

    			HttpClient httpClient = new DefaultHttpClient();
    			String request = BASE;
    			HttpPut putRequest = new HttpPut(request);

    			ObjectMapper mapper = new ObjectMapper();
    			String jsonInString = mapper.writeValueAsString(student);
    		
    			StringEntity input = new StringEntity(jsonInString);
    			input.setContentType("application/json");
    			
    			putRequest.setEntity(input);

    			HttpResponse response = httpClient.execute(putRequest);

    			if (response.getStatusLine().getStatusCode() != 200) {
    				throw new RuntimeException("Failed : HTTP error code : "
    					+ response.getStatusLine().getStatusCode());
    			}


    			httpClient.getConnectionManager().shutdown();
    			
    			return student;

    		  } catch (MalformedURLException e) {

    			e.printStackTrace();
    		
    		  } catch (IOException e) {

    			e.printStackTrace();

    		  }
    	 
    	 return null;
	    	
	    	
	    }

	  
	    public String deleteStudentById(Long studentId) {
	    	
	    	try {

    			HttpClient httpClient = new DefaultHttpClient();
    			String request = BASE + String.format("/%d", studentId);
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
