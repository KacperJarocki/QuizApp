package org.studia.javaproadmin.services;

import com.google.gson.Gson;
import org.studia.javaproadmin.Request.FinishedTestRequest;
import org.studia.javaproadmin.Request.TestRequest;
import org.studia.javaproadmin.entities.Client;
import org.studia.javaproadmin.entities.Question;
import org.studia.javaproadmin.entities.Roles;
import org.studia.javaproadmin.entities.Test;
import org.studia.javaproadmin.response.testStartResponse;

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
	public void sendQuestion(Question question) throws IOException {
		Gson gson = new Gson();
		URL url = new URL(apiUrl + "/question");
		String jsonInputString = gson.toJson(question);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
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
		}
	}

	public void sendAddUserRequest(Client client) throws IOException {
		Gson gson = new Gson();
		URL url = new URL(apiUrl + "/client");
		String jsonInputString = gson.toJson(client);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);

		try(OutputStream os = conn.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}

		int code = conn.getResponseCode();
		System.out.println("Response Code : " + code);
		if (code == HttpURLConnection.HTTP_UNAUTHORIZED) {
			System.out.println("Authentication is required.");
		}
	}
	public long startTestRequest(String clientAlbumNumber) throws IOException {
		TestRequest testRequest = new TestRequest();
		Client client = new Client();
		client.setAlbumNumber(clientAlbumNumber);
		testRequest.setTestUser(client);
		testRequest.setHowManyQuestions(3);
		long testID;
		Gson gson = new Gson();
		URL url = new URL(apiUrl + "/test");
		String jsonInputString = gson.toJson(testRequest);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);

		try(OutputStream os = conn.getOutputStream()) {
			byte[] input = jsonInputString.getBytes("utf-8");
			os.write(input, 0, input.length);
		}
		int code = conn.getResponseCode();
		System.out.println("Response Code : " + code);
		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println("Response Body : " + response.toString());
			testID = gson.fromJson(String.valueOf(response), testStartResponse.class).getTestId();
		}

		return testID;
	}
	public Test startTest(long testID) throws IOException {
		Test test = new Test();
		Gson gson = new Gson();
		URL url = new URL(apiUrl + "/startTest?id=" + testID);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Content-Type", "application/json; utf-8");
		conn.setRequestProperty("Accept", "application/json");
		conn.setDoOutput(true);

		int code = conn.getResponseCode();
		System.out.println("Response Code : " + code);
		try(BufferedReader br = new BufferedReader(
				new InputStreamReader(conn.getInputStream(), "utf-8"))) {
			StringBuilder response = new StringBuilder();
			String responseLine = null;
			while ((responseLine = br.readLine()) != null) {
				response.append(responseLine.trim());
			}
			System.out.println("Response Body : " + response.toString());
			test = gson.fromJson(String.valueOf(response), Test.class);
		}

		return test;
	}
//Todo: implement the finishTestRequest method to connect to api
	public int finishTest(FinishedTestRequest finishedTestRequest) {
		return 20;
	}
}
