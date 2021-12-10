package com.first.demo.dao;

import com.first.demo.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundRepository extends JpaRepository<Fund, Integer> {

    Fund findByCode(String code);
    List<Fund> getAllBy();
}
