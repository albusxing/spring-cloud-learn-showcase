package com.dobby.provider.service;

import com.dobby.provider.domain.Depart;
import com.dobby.provider.repository.DepartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartRepository departRepository;

    @Override
    public boolean saveDepart(Depart depart) {
        Depart result = departRepository.save(depart);
        if (null == result) {
            return false;
        }
        return true;
    }

    @Override
    public boolean deleteDepartById(Integer id) {
        // 对于deleteById()方法，若DB中不存在该id，而执行了其删除操作，将抛出异常
        if (departRepository.existsById(id)) {
            departRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateDepart(Depart depart) {
        Depart result = departRepository.save(depart);
        if (result != null) {
            return true;
        }
        return false;
    }

    @Override
    public Depart getDepartById(Integer id) {
        // getOne()方法：若其指定的id不存在，该方法将抛出异常
        if (departRepository.existsById(id)) {
            return departRepository.getOne(id);
        }
        return null;
    }

    @Override
    public List<Depart> listAllDeparts() {
        return departRepository.findAll();
    }
}
