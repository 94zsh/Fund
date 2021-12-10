package com.first.demo.dao;

import com.first.demo.entity.FundHistoryDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface FundHistoryDayRepository extends JpaRepository<FundHistoryDay, String>, JpaSpecificationExecutor<FundHistoryDay> {

    FundHistoryDay findByFundcode(String fundcode);
    FundHistoryDay findByJzrq(String jzrq);
    FundHistoryDay findByFundcodeAndJzrq(String fundcode,String jzrq);
    List<FundHistoryDay> findByGztimeLike(String gztime);
//    List<FundHistoryDay> getFundHistoryDayByTimestampBetween(String gztime);
}
