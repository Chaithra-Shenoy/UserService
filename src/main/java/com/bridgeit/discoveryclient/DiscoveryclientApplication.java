package com.bridgeit.discoveryclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name="UserService")
public class DiscoveryclientApplication {
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryclientApplication.class, args);
	}
}
