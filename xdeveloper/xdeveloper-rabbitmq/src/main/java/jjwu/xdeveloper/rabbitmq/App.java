package jjwu.xdeveloper.rabbitmq;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.junit.Test;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Throwable, IOException
    {
    	
    	DefaultHttpClient httpClient = new DefaultHttpClient();
    	httpClient.getCredentialsProvider().setCredentials(new AuthScope("127.0.0.1",15672), 
    			new UsernamePasswordCredentials("guest","guest"));
    	HttpGet httpGet = new HttpGet("http://127.0.0.1:15672/api/connections");
    	HttpResponse response = httpClient.execute(httpGet);
    	HttpEntity entity = response.getEntity();
    	if(entity != null){
    		InputStreamReader reader = new InputStreamReader(entity.getContent());
    		BufferedReader bufferedReader = new BufferedReader(reader);
    		String str = "";
    		while (null !=(str = bufferedReader.readLine())) {
    			System.out.println(str);
    		}
//    		JSONObject result = JSONObject.fromObject(str);
//    		System.out.println(result);
    	}
    }
    
    
    @Test
    public void test() throws Throwable{
    	
    	File file = new File("/Users/jjwu/Desktop/fibo.py");
    	FileReader reader = new FileReader(file);
    	BufferedReader bufferedReader = new BufferedReader(reader);
    	
    	
    	StringBuilder builder = new StringBuilder();
    	
    	String str = "";
    	while ((str = bufferedReader.readLine()) != null) {
    		builder.append(str).append("\n");
		}
    	
    	System.out.println(builder.toString());
    }
}
