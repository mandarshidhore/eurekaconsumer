package com.sssm.eurekaemployeeconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

import com.sssm.eurekaemployeeconsumer.client.EmployeeConsumerClient;

@SpringBootApplication
@EnableDiscoveryClient
public class EurekaEmployeeConsumerApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(EurekaEmployeeConsumerApplication.class, args);
		EmployeeConsumerClient client = ctx.getBean(EmployeeConsumerClient.class);
		client.getEmployee();
	}
}
