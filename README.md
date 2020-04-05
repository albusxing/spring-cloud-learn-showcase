# spring-cloud-learn-showcase


### 1、使用 RestTemplate 实现消费者对提供者的调用。
消费者通过直连调用提供者，必须要在消费者服务中指明提供者的服务URL，形成了强耦合。
![image](./doc/res/01.png)


### 2、添加 Eureka 注册中心，通过 Eureka 实现消费者对提供者的调用。
服务消费者和提供者启动后，将服务注册到 Eureka 中，消费者通过在 Eureka 中查询服务来调用提供者。

#### 单机 Eureka
![image](doc/res/020.png)

#### 集群 Eureka
![image](./doc/res/021.png)

### 3、使用 Feign 实现实现消费者对提供者的调用(Eureka 注册中心)

#### 单一提供者
![image](./doc/res/03.png)

#### 集群提供者
![image](./doc/res/031.png)

### 4、在服务消费者端添加 Hystrix 服务容错处理
![image](./doc/res/04.png)

### 5、添加 Zuul 网关
添加了 Zuul 网关后，消费者接口(http://localhost:8080/consumer/depart/list)
可使用(http://zuulgateway.com:9000/04-consumer-feign-hystrix-8080/consumer/depart/list)进行访问。
![image](./doc/res/05.png)
