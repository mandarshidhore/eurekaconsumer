package com.sssm.eurekaemployeeconsumer.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@Component
public class EmployeeConsumerClient {

	@Autowired
	private EurekaClient client;

	@Autowired
	private RestTemplateBuilder restTemplateBuilder;

	// note that it takes some time for producer register correctly with the server
	// if this method is invoked immediately after producer starts, it may report issues w/ DiscoveryClient
	public void getEmployee() {
		System.out.println("==> Invoking employee-producer to get Employee information ====");
		RestTemplate restTemplate = restTemplateBuilder.build();
		InstanceInfo instanceInfo = client.getNextServerFromEureka("employee-producer", false);
		String serviceBaseUrl = instanceInfo.getHomePageUrl();

		ResponseEntity<String> response = null;
		try {
			response = restTemplate.getForEntity(serviceBaseUrl + "/employee", String.class);
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
