package com.first.demo.dao;

import com.first.demo.entity.Fund;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundRepository extends JpaRepository<Fund, Integer> {

    Fund findByCode(Integer code);
}
