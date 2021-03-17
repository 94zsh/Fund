package com.first.demo.dao;

import com.first.demo.entity.FundInvalid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundInvalidRepository extends JpaRepository<FundInvalid, String> {
}
