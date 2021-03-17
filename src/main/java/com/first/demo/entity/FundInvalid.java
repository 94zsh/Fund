package com.first.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "f_invalid")
public class FundInvalid {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    String code;
    String name;
    String updateTime;

    public FundInvalid(Long id, String code, String name, String updateTime) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.updateTime = updateTime;
    }

    public FundInvalid(String code, String name, String updateTime) {
        this.code = code;
        this.name = name;
        this.updateTime = updateTime;
    }

    public FundInvalid() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
