package com.dobby.provider.web;

import com.dobby.provider.domain.Depart;
import com.dobby.provider.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author liguoqing
 */
@RestController
@RequestMapping("/provider/depart")
public class DepartController {

    @Autowired
    private DepartService departService;


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
        if (depart.getId() == null) {
            return false;
        }
        return departService.updateDepart(depart);
    }

    @GetMapping("/get/{id}")
    public Depart get(@PathVariable("id") Integer id) {
        Depart depart = departService.getDepartById(id);
        return depart;
    }

    @GetMapping("/list")
    public List<Depart> list() {
        return departService.listAllDeparts();
    }


}
