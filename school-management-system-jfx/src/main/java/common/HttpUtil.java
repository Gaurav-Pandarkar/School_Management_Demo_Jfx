package common;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
	public static HttpURLConnection postJsonRequest(String strUrl,String jsonRequest) throws IOException {
		URL url = new URL(strUrl);
	HttpURLConnection postConnection = (HttpURLConnection) url.openConnection();
	postConnection.setRequestMethod("POST");

	postConnection.setRequestProperty("Content-Type", "application/json");
	postConnection.setDoOutput(true);

	OutputStream outputStreamobj = postConnection.getOutputStream();
	outputStreamobj.write(jsonRequest.getBytes());

	outputStreamobj.flush();
	outputStreamobj.close();
	
	return postConnection;
	}

	
	 public static HttpURLConnection getRequest(String strUrl) throws IOException {
	        URL url = new URL(strUrl);
	        HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
	        getConnection.setRequestMethod("GET");

	        getConnection.setRequestProperty("Accept", "application/json");

	        return getConnection;
	    }

}
