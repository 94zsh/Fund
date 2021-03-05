package com.first.demo.service;

import com.first.demo.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getUserInfo();
    public User Login(String account,String psw);
}
