package com.first.demo.controller;

import com.first.demo.bean.Response;
import com.first.demo.constant.Constant;
import com.first.demo.entity.Fund;
import com.first.demo.entity.FundFocus;
import com.first.demo.entity.FundHistoryDay;
import com.first.demo.service.AppService;
import com.first.demo.service.FundService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
public class AppController {
    @Autowired
    AppService appService;
    @Autowired
    FundService fundService;
    @RequestMapping(value = "/app/getData")
    public String getData(){
        String data = appService.getData();
        return data;
    }
    //获取数据库列表
    @RequestMapping(value = "/app/getFundList",method = RequestMethod.GET)
    public Response getFundList(){
        List<Fund> fundList = appService.getFundList();
        Response response = new Response();
        if(fundList != null && fundList.size() > 0){
            response.setMsg("成功");
            response.setStateCode(Constant.RESPONSE_CODE_SUCCESS);
            response.setData(new Gson().toJson(fundList));
        }else{
            response.setStateCode(Constant.RESPONSE_CODE_FAILED);
            response.setMsg("数据为空");
        }
        return response;
    }
    //获取关注列表
    @RequestMapping(value = "/app/getFocusList",method = RequestMethod.POST,produces="application/json")
    @ResponseBody
    public Response getFocusList(@RequestParam("account") String account){
        List<FundFocus> focusList = appService.getFocusList(account);
        Response response = new Response();
        if(focusList != null && focusList.size() > 0){
            response.setMsg("成功");
            response.setStateCode(Constant.RESPONSE_CODE_SUCCESS);
            response.setData(new Gson().toJson(focusList));
        }else{
            response.setStateCode(Constant.RESPONSE_CODE_FAILED);
            response.setMsg("数据为空");
        }
        return response;
    }

    @RequestMapping(value = "/app/addFocus",method = RequestMethod.POST,produces="application/json")
    @ResponseBody
    public Response addFocus(@RequestParam("account") String account,@RequestParam("code") String code){
        String result = appService.addFocus(account,code);
        Response response = new Response();
        if(result != null && result.length() > 0){
            response.setMsg("新增成功");
            response.setStateCode(Constant.RESPONSE_CODE_SUCCESS);
            response.setData(result);
            fundService.updateFocusInfo(account,code);
        }else{
            response.setStateCode(Constant.RESPONSE_CODE_FAILED);
            response.setMsg("新增失败");
        }
        return response;
    }

    @RequestMapping(value = "/app/deleteFocus",method = RequestMethod.POST,produces="application/json")
    @ResponseBody
    @Transactional
    public Response deleteFocus(@RequestParam("account") String account,@RequestParam("code") String code){
        String result = appService.deleteFocus(account,code);
        Response response = new Response();
        if(result != null && result.length() > 0){
            response.setMsg("删除成功");
            response.setStateCode(Constant.RESPONSE_CODE_SUCCESS);
            response.setData(result);
        }else{
            response.setStateCode(Constant.RESPONSE_CODE_FAILED);
            response.setMsg("删除失败");
        }
        return response;
    }

    //获取某日历史列表
    @RequestMapping(value = "/app/getHistoryDayList",method = RequestMethod.POST,produces="application/json")
    @ResponseBody
    public Response getHistoryDayList(@RequestParam("account") String account,@RequestParam("gztime") long timestamp){
        List<FundHistoryDay> fundHistoryDayList = appService.getHistoryList(account,timestamp);
        Response response = new Response();
        if(fundHistoryDayList != null && fundHistoryDayList.size() > 0){
            response.setMsg("成功");
            response.setStateCode(Constant.RESPONSE_CODE_SUCCESS);
            response.setData(new Gson().toJson(fundHistoryDayList));
        }else{
            response.setStateCode(Constant.RESPONSE_CODE_FAILED);
            response.setMsg("数据为空");
        }
        return response;
    }


}
