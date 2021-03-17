package com.first.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "f_list")
public class Fund {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    String code;
    String simpleName;
    String cnName;
    String type;
    String fullName;

    public Fund(String code, String simpleName, String cnName, String type, String fullName) {
        this.code = code;
        this.simpleName = simpleName;
        this.cnName = cnName;
        this.type = type;
        this.fullName = fullName;
    }

    public Fund() {
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

    public String getSimpleName() {
        return simpleName;
    }

    public void setSimpleName(String simpleName) {
        this.simpleName = simpleName;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "code='" + code + '\'' +
                ", simpleName='" + simpleName + '\'' +
                ", cnName='" + cnName + '\'' +
                ", type='" + type + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
