package com.dobby.consumer.web;

import com.dobby.consumer.bean.Depart;
import com.dobby.consumer.service.DepartServiceClient;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/consumer/depart")
public class DepartController {

    /**
     * 不使用 RestTemplate 调用提供者服务；
     * 改用 Feign 调用，可以像调用本地方法一样，调用提供者的接口
     */
    @Resource
    private DepartServiceClient departServiceClient;


    @PostMapping("/save")
    public boolean saveHandle(Depart depart) {
        boolean result = departServiceClient.saveDepart(depart);
        return result;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteHandle(@PathVariable("id") int id) {
        departServiceClient.deleteDepartById(id);
    }

    @PutMapping("/update")
    public void updateHandle(Depart depart) {
        departServiceClient.updateDepart(depart);
    }

    @GetMapping("/get/{id}")
    public Depart getHandle(@PathVariable("id") int id) {
        return departServiceClient.getDepartById(id);
    }

    @GetMapping("/list")
    public List<Depart> listHandle() {
        return departServiceClient.listAllDeparts();
    }



}
