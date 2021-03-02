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
    @RequestMapping(value = "/getFundList",method = RequestMethod.GET)
    public String getFundList(){
        String data = fundService.getFundList();
        return data;
    }
    @RequestMapping(value = "/getFundDetail",method = RequestMethod.GET)
    public String getFundDetail(@RequestParam(value="code",required=false,defaultValue="000001") String code){
        String data = fundService.getFundDetail();
        return data;
    }
}
