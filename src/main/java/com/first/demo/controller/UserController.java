package com.first.demo.controller;

import com.first.demo.entity.User;
import com.first.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/getUserItem",method = RequestMethod.GET)
    public String getUserItem(){
        List<User> user = userService.getUserInfo();
        return user.toString();
    }
}
