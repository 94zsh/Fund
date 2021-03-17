package com.first.demo.controller;

import com.first.demo.service.FundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class FundController {
    @Autowired
    FundService fundService;
    @RequestMapping(value = "/getData",method = RequestMethod.GET)
    public String getData(){
        String data = fundService.getData();
        return data;
    }
    //获取所有列表到数据库
    @RequestMapping(value = "/getFundList",method = RequestMethod.GET)
    public String getFundList(){
        String data = fundService.getFundList();
        return data;
    }
    //获取所有今日详情到数据库
    @RequestMapping(value = "/getFundDetail",method = RequestMethod.GET)
    public String getFundDetail(@RequestParam(value="code",required=false,defaultValue="000001") String code){
        String data = fundService.getFundDetail();
        return data;
    }

    //更新帐号关注最新详情
    @RequestMapping(value = "/updateFocusInfo",method = RequestMethod.GET)
    public String UpdateFocusInfo(@RequestParam(value="account",required=false,defaultValue="admin") String account){
        String data = fundService.updateFocusInfo(account);
        return data;
    }
}
