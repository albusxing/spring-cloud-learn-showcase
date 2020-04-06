package com.dobby.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @EnableEurekaClient ： 启用 eureka 客户端
 * @EnableFeignClients : 开启 openfeign 客户端，并指定 service 接口所在的包
 * @EnableCircuitBreaker : 开启服务容错处理
 */
@EnableCircuitBreaker
@EnableFeignClients(basePackages = "com.dobby.consumer.service")
@EnableEurekaClient
@SpringBootApplication
public class ConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerApplication.class, args);
	}

}
