package com.first.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "f_history_day")
public class FundHistoryDay {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    String fundcode;
    String name;
    String jzrq;
    String dwjz;
    String gsz;
    String gszzl;
    String gztime;
    long timestamp;

    public FundHistoryDay(Long id, String fundcode, String name, String jzrq, String dwjz, String gsz, String gszzl, String gztime, long timestamp) {
        this.id = id;
        this.fundcode = fundcode;
        this.name = name;
        this.jzrq = jzrq;
        this.dwjz = dwjz;
        this.gsz = gsz;
        this.gszzl = gszzl;
        this.gztime = gztime;
        this.timestamp = timestamp;
    }

    public FundHistoryDay() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFundcode() {
        return fundcode;
    }

    public void setFundcode(String fundcode) {
        this.fundcode = fundcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJzrq() {
        return jzrq;
    }

    public void setJzrq(String jzrq) {
        this.jzrq = jzrq;
    }

    public String getDwjz() {
        return dwjz;
    }

    public void setDwjz(String dwjz) {
        this.dwjz = dwjz;
    }

    public String getGsz() {
        return gsz;
    }

    public void setGsz(String gsz) {
        this.gsz = gsz;
    }

    public String getGszzl() {
        return gszzl;
    }

    public void setGszzl(String gszzl) {
        this.gszzl = gszzl;
    }

    public String getGztime() {
        return gztime;
    }

    public void setGztime(String gztime) {
        this.gztime = gztime;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
