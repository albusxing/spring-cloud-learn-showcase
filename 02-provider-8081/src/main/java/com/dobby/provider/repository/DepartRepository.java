package com.dobby.provider.repository;

import com.dobby.provider.domain.Depart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartRepository extends JpaRepository<Depart, Integer> {
}
