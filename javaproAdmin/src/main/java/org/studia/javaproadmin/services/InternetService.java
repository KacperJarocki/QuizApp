package org.studia.javaproadmin.services;

import com.google.gson.Gson;
import org.studia.javaproadmin.entities.Client;
import org.studia.javaproadmin.entities.Roles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class InternetService {
	URL apiUrl = new URL("http://localhost:8080");
	public InternetService() throws MalformedURLException {
	}
	public Roles sendLoginRequest(Client client) throws IOException {
		Gson gson = new Gson();
		URL url = new URL(apiUrl + "/login");
		String jsonInputString = gson.toJson(client);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("PUT");
		conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);

		try(OutputStream os = conn.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		int code = conn.getResponseCode(); // To ensure the request was successful
		System.out.println("Response Code : " + code);
		if (code == HttpURLConnection.HTTP_UNAUTHORIZED) {
			System.out.println("Authentication is required.");
			return Roles.NONE;
		}
		Roles role = null;
		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println("Response Body : " + response.toString());

			role = gson.fromJson(response.toString(), Roles.class);
		}

		return role;
	}
}
