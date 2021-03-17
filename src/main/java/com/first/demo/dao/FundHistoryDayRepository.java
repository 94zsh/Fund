package com.first.demo.dao;

import com.first.demo.entity.FundHistoryDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FundHistoryDayRepository extends JpaRepository<FundHistoryDay, String>, JpaSpecificationExecutor<FundHistoryDay> {

    FundHistoryDay findByFundcode(String fundcode);
    FundHistoryDay findByJzrq(String jzrq);
    FundHistoryDay findByFundcodeAndJzrq(String fundcode,String jzrq);
}
