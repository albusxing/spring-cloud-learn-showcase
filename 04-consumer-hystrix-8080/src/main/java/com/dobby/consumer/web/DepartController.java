package com.dobby.consumer.web;

import com.dobby.consumer.bean.Depart;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/consumer/depart")
public class DepartController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String SERVICE_PROVIDER = "http://04-PROVIDER-8081";

    @Autowired
    private DiscoveryClient discoveryClient;


    @PostMapping("/save")
    public boolean saveHandle(Depart depart) {
        String url = SERVICE_PROVIDER + "/provider/depart/save";
        return restTemplate.postForObject(url, depart, Boolean.class);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHandle(@PathVariable("id") int id) {
        String url = SERVICE_PROVIDER + "/provider/depart/delete/" + id;
        restTemplate.delete(url);
    }

    @PutMapping("/update")
    public void updateHandle(Depart depart) {
        String url = SERVICE_PROVIDER + "/provider/depart/update";
        restTemplate.put(url, depart);
    }


    /**
     * @HystrixCommand : 添加服务容错处理，指定失败调用的方法
     */
    @HystrixCommand(fallbackMethod = "getDepartHystrix")
    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        String url = SERVICE_PROVIDER + "/provider/depart/get/" + id;
        return restTemplate.getForObject(url, Depart.class);
    }


    public Depart getDepartHystrix(@PathVariable("id") int id) {
        Depart depart = new Depart();
        depart.setId(id);
        depart.setName("no this depart");
        depart.setDbase("no this info");
        return depart;
    }




    @GetMapping("/list")
    public List<Depart> listHandle() {
        String url = SERVICE_PROVIDER + "/provider/depart/list";
        return restTemplate.getForObject(url, List.class);
    }


    /**
     * 服务发现
     */
    @GetMapping("/discovery")
    public Map<String, Object> discoveryHandle() {
        Map<String, Object> result = new HashMap<>();
        // 获取服务注册列表中的所有微服务名称，即是 spring.application.name 指定的名称
        List<String> services = discoveryClient.getServices();
        result.put("services", services);

        List<String> instanceList = new ArrayList<>();
        for (String service : services) {
            // 获取指定名称的微服务的 所有服务提供者
            List<ServiceInstance> instances = discoveryClient.getInstances(service);
            for (ServiceInstance instance : instances) {
                // 获取服务id，即 eureka.instance.instance-id 指定的值
                String serviceId = instance.getServiceId();
                URI uri = instance.getUri();
                instanceList.add(serviceId + "," + uri);
            }
        }
        result.put("instances", instanceList);
        return result;
    }
}
