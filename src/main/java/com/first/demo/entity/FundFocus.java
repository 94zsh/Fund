package com.first.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "f_focus")
public class FundFocus {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    String account;
    String code;
    String name;
    String jzrq;
    String dwjz;
    String gsz;
    String gszzl;
    String gztime;
    long timestamp;

    public FundFocus() {
    }

    public FundFocus(Long id, String account, String code, String name, String jzrq, String dwjz, String gsz, String gszzl, String gztime, long timestamp) {
        this.id = id;
        this.account = account;
        this.code = code;
        this.name = name;
        this.jzrq = jzrq;
        this.dwjz = dwjz;
        this.gsz = gsz;
        this.gszzl = gszzl;
        this.gztime = gztime;
        this.timestamp = timestamp;
    }
    public FundFocus(String account, String code, String name, String jzrq, String dwjz, String gsz, String gszzl, String gztime, long timestamp) {
        this.account = account;
        this.code = code;
        this.name = name;
        this.jzrq = jzrq;
        this.dwjz = dwjz;
        this.gsz = gsz;
        this.gszzl = gszzl;
        this.gztime = gztime;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    @Override
    public String toString() {
        return "FundFocus{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", jzrq='" + jzrq + '\'' +
                ", dwjz='" + dwjz + '\'' +
                ", gsz='" + gsz + '\'' +
                ", gszzl='" + gszzl + '\'' +
                ", gztime='" + gztime + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
