package com.sssm.eurekaemployeeconsumer.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EmployeeConsumerClient {

	public void getEmployee() {
		String baseURL = "http://localhost:8080/employee";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = null;
		try {
			response = restTemplate.exchange(baseURL, HttpMethod.GET, getHeaders(), String.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(response.getBody());
	}

	private HttpEntity getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON.toString());
		return new HttpEntity<>(headers);
	}

}
