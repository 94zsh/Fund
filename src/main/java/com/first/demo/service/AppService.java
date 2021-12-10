package com.first.demo.service;

import com.first.demo.entity.Fund;
import com.first.demo.entity.FundFocus;
import com.first.demo.entity.FundHistoryDay;

import javax.transaction.Transactional;
import java.util.List;

public interface AppService {
    public String getData();
    public List<Fund> getFundList();
    public List<FundFocus> getFocusList(String account);
    public List<FundHistoryDay> getHistoryList(String account,long timestamp);
    public String addFocus(String account,String code);
    @Transactional
    public String deleteFocus(String account,String code);
}
