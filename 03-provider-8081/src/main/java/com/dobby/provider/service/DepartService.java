package com.dobby.provider.service;

import com.dobby.provider.domain.Depart;

import java.util.List;

public interface DepartService {

    boolean saveDepart(Depart depart);
    boolean deleteDepartById(Integer id);
    boolean updateDepart(Depart depart);
    Depart getDepartById(Integer id);
    List<Depart> listAllDeparts();
}
