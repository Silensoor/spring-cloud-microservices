package com.javastart.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayAutoConfiguration;
import org.springframework.context.annotation.Import;


@SpringBootApplication
@EnableDiscoveryClient
@Import(GatewayAutoConfiguration.class)
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
