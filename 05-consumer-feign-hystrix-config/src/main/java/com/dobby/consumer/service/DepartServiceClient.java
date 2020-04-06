package com.dobby.consumer.service;

import com.dobby.consumer.bean.Depart;
import com.dobby.consumer.config.AppConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @FeignClient : 标记当前接口是一个 Feign 客户端，
 *              使用 value 指定要调用的服务的名称
 *              使用 configuration 指定配置信息
 *              使用 fallbackFactory 指定容错处理类
 * @RequestMapping : 使用 RequestMapping 指定需要调用的提供者的接口url
 */
@FeignClient(value = "04-provider-8081", configuration = AppConfig.class, fallbackFactory = DepartFallbackFactory.class)
@RequestMapping("/provider/depart")
public interface DepartServiceClient {


    @PostMapping("/save")
    boolean saveDepart(Depart depart);

    @DeleteMapping("/delete/{id}")
    boolean deleteDepartById(@PathVariable("id") Integer id);

    @PutMapping("/update")
    boolean updateDepart(Depart depart);

    @GetMapping("/get/{id}")
    Depart getDepartById(@PathVariable("id") Integer id);

    @GetMapping("/list")
    List<Depart> listAllDeparts();

}
