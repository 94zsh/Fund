package com.first.demo.service;

public interface FundService {
    public String getData();
    public String getFundList();
    public String getFundDetail();
    public String updateFocusInfo(String account);
    public String updateFocusInfo(String account,String code);
}
