package com.viratrigger.api.service;

import java.io.IOException;
import org.springframework.stereotype.Service;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Service
public class ApiCallService {


	public boolean makeApiCall(String mobileNumber, String labourId) throws Exception {

		OkHttpClient client = new OkHttpClient();

		FormBody formBody = new FormBody.Builder()
				.add("From", mobileNumber)
				.add("CallerId", "08047094548")
				.add("Url", "http://my.exotel.com/godrejproperties3/exoml/start_voice/843258")
				.add("CustomField", labourId)
				.build();

		// Build the request
		Request request = new Request.Builder()
				.url("https://api.exotel.com/v1/Accounts/godrejproperties3/Calls/connect.json")
				.header("Authorization", "Basic M2VhZGQyNjc4MTAxNTBmN2U1NWRlNWZlMDgxZmU2MzVjODEyZmZhYTdlYWJlNzBkOmI3NGUyM2FhN2JhNTcyMTg2NDk3NWYxZTJmNzVmNzI2MWI3YzY2Mzk4ZWVkYmY4ZA==")
				.header("accept", "application/json")
				.post(formBody)
				.build();

		// Execute the request and handle the response
		try (Response response = client.newCall(request).execute()) {
			if (!response.isSuccessful()) {
				System.err.println("API call failed for Labour ID: " + labourId + ", Mobile Number: " + mobileNumber + ". Status code: " + response.code());
				return false;
			}
			System.out.println("API call successful for Labour ID: " + labourId + ", Mobile Number: " + mobileNumber);

			return true;

		} catch (IOException e) {
			System.err.println("Error making API call for Labour ID: " + labourId + ", Mobile Number: " + mobileNumber + ". Error: " + e.getMessage());
			return false;
		}
	}
}