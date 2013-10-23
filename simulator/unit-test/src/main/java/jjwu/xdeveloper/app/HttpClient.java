package jjwu.xdeveloper.app;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class HttpClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WebResource client = Client.create().resource("");
			WebResource wr = client.path("http://127.0.0.1:15672/api/connections");
			ClientResponse reponse = wr.header("Content-Type", "application/json;UTF-8").entity("").post(ClientResponse.class);
			
			String rs = reponse.getEntity(String.class);

			System.out.println(rs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
