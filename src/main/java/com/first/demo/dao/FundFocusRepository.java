package com.first.demo.dao;

import com.first.demo.entity.FundFocus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FundFocusRepository extends JpaRepository<FundFocus, String> {

    List<FundFocus>  findByAccount(String account);
    FundFocus  findFundFocusByAccountAndCode(String account,String code);
    void deleteByAccountAndCode(String account,String code);
}
