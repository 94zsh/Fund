package com.first.demo.controller;

import com.first.demo.bean.Response;
import com.first.demo.constant.Constant;
import com.first.demo.entity.User;
import com.first.demo.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/getAllUser",method = RequestMethod.GET)
//    @CrossOrigin
    public Response getAllUser(){
        List<User> user = userService.getUserInfo();
        Response response = new Response();
        if(user != null && user.size() > 0){
            response.setMsg("成功");
            response.setStateCode(Constant.RESPONSE_CODE_SUCCESS);
            response.setData(new Gson().toJson(user));
        }else{
            response.setStateCode(Constant.RESPONSE_CODE_FAILED);
            response.setMsg("数据为空");
        }
        return response;
    }
    @RequestMapping(value = "/login",method = {RequestMethod.POST,RequestMethod.GET},produces="application/json"/*,params = { "account","psw" }*/)
    @ResponseBody
    public Response Login(@RequestParam("account") String account,@RequestParam("psw") String psw/*,@PathVariable String psw*/){
        User user = userService.Login(account,psw);
        Response response = new Response();
        if(user == null){
            response.setStateCode(Constant.RESPONSE_CODE_FAILED);
            response.setMsg("帐号或密码错误");
        }else{
            response.setMsg("登陆成功");
            response.setStateCode(Constant.RESPONSE_CODE_SUCCESS);
            response.setData(new Gson().toJson(user));
        }
        return response;
    }
}
