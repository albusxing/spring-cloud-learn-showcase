package com.dobby.provider.web;

import com.dobby.provider.domain.Depart;
import com.dobby.provider.service.DepartService;
import lombok.experimental.PackagePrivate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/provider/depart")
public class DepartController {

    @Autowired
    private DepartService departService;

    @Autowired
    private DiscoveryClient discoveryClient;


    @PostMapping("/save")
    public boolean save(@RequestBody Depart depart) {
        depart.setDbase("test");
        return departService.saveDepart(depart);
    }

    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable("id") Integer id) {
        return departService.deleteDepartById(id);
    }

    @PutMapping("/update")
    public boolean update(@RequestBody Depart depart) {
        depart.setDbase("test");
        return departService.updateDepart(depart);
    }

    @GetMapping("/get/{id}")
    public Depart get(@PathVariable("id") Integer id) {
        return departService.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> list() {
        return departService.listAllDeparts();
    }


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
