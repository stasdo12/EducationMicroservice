package ua.donetc.discoverymicr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryMicrApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryMicrApplication.class, args);
	}

}
