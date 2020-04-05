package com.dobby.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @EnableEurekaClient ： 启用 eureka 客户端
 * @EnableCircuitBreaker : 开启 Hystrix 的服务容错
 */
@EnableCircuitBreaker
@EnableEurekaClient
@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
