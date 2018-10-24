package jms;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DweetConnection {
	
	private String URL = "http://dweet.io/dweet/for";
	private JsonParser jsonParser = new JsonParser();
	private String userName = "auctionApplication";
	
	public DweetConnection() {
	}
	
	public boolean publish(JsonElement content) {
		try {
			URL url = new URL(URL + userName);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestProperty("Content-Type", "application/json; charset=utf-8");
			connection.setRequestMethod("POST");
			connection.setDoInput(true);
			connection.setDoOutput(true);
			
			PrintWriter out = new PrintWriter(connection.getOutputStream());
			out.println(content.toString());
			out.flush();
			out.close();
			
			JsonObject response = readResponse(connection.getInputStream());
			connection.disconnect();

			return response.get("this").getAsString().equals("succeeded");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private JsonObject readResponse(InputStream inputStream) {
		Scanner scan = new Scanner(inputStream);
		StringBuilder sn = new StringBuilder();
		while (scan.hasNext())
			sn.append(scan.nextLine()).append("\n");
		scan.close();
		return jsonParser.parse(sn.toString()).getAsJsonObject();
	}

}