package com.first.demo.service.impl;

import com.first.demo.dao.FundFocusRepository;
import com.first.demo.dao.FundHistoryDayRepository;
import com.first.demo.dao.FundInvalidRepository;
import com.first.demo.dao.FundRepository;
import com.first.demo.entity.Fund;
import com.first.demo.entity.FundFocus;
import com.first.demo.entity.FundHistoryDay;
import com.first.demo.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AppServiceImpl implements AppService {
    private static final Logger logger = Logger.getLogger(String.valueOf(AppServiceImpl.class));
    @Autowired
    private FundRepository fundRepository;
    @Autowired
    private FundHistoryDayRepository fundHistoryDayRepository;
    @Autowired
    private FundFocusRepository fundFocusRepository;
    @Autowired
    private FundInvalidRepository fundInvalidRepository;

    private final RestTemplate restTemplate;
    private final HttpEntity<String> entity;
    public AppServiceImpl() {
        restTemplate = new RestTemplate(
                new HttpComponentsClientHttpRequestFactory()); // 使用HttpClient，支持GZIP

        restTemplate.getMessageConverters().set(1,
                new StringHttpMessageConverter(StandardCharsets.UTF_8)); // 支持中文编码
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        entity = new HttpEntity<>(headers);
    }
    @Override
    public String getData() {
        RestTemplate restTemplate = new RestTemplate(
                new HttpComponentsClientHttpRequestFactory()); // 使用HttpClient，支持GZIP
        restTemplate.getMessageConverters().set(1,
                new StringHttpMessageConverter(StandardCharsets.UTF_8)); // 支持中文编码

//        天气接口  测试
        String uri="http://wthrcdn.etouch.cn/weather_mini?city="+"深圳";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
        logger.info("getData uri:" + uri);
        logger.info("getData:" + strbody);

        return strbody;
    }

    @Override
    public List<Fund> getFundList() {

        long timeStart = System.currentTimeMillis();
        List<Fund> fundList = new ArrayList<>();
        try {
            fundList = fundRepository.getAllBy();
            logger.info("获取列表数量：" + fundList.size());
            logger.info("time getFundList 耗时:" + (System.currentTimeMillis() - timeStart) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fundList;
    }

    @Override
    public List<FundFocus> getFocusList(String account) {
        long timeStart = System.currentTimeMillis();
        List<FundFocus> fundList = new ArrayList<>();
        try {
            fundList = fundFocusRepository.findByAccount(account);
            logger.info("获取列表数量：" + fundList.size());
            logger.info("time getFocusList 耗时:" + (System.currentTimeMillis() - timeStart) + " ms");
            return fundList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fundList;
    }

    @Override
    public List<FundHistoryDay> getHistoryList(String account,long timestamp) {
        long timeStart = System.currentTimeMillis();
        List<FundHistoryDay> fundHistoryDayList = new ArrayList<>();
        try {
            SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd");
            String queryDay = formatter.format(new Date(timestamp * 1000));
            logger.info("获取列表日期：" + queryDay);
            fundHistoryDayList = fundHistoryDayRepository.findByGztimeLike("%" + queryDay + "%");
            logger.info("获取列表数量：" + fundHistoryDayList.size());
            logger.info("time getHistoryList 耗时:" + (System.currentTimeMillis() - timeStart) + " ms");
            return fundHistoryDayList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fundHistoryDayList;
    }

    @Override
    public String addFocus(String account, String code) {
        FundFocus fundFocus = new FundFocus();
        fundFocus.setAccount(account);
        fundFocus.setCode(code);
        FundFocus result = fundFocusRepository.save(fundFocus);
        return result.toString();
    }

    @Override
    @Transactional
    public String deleteFocus(String account, String code) {
        fundFocusRepository.deleteByAccountAndCode(account,code);
        return "success delete";
    }

    public FundHistoryDay findSearch(String fundCode,String jzrq) {

        Optional<FundHistoryDay> fundHistoryDay= fundHistoryDayRepository.findOne(new Specification<FundHistoryDay>() {
            @Override
            public Predicate toPredicate(Root<FundHistoryDay> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();

                if (fundCode != null) {
                    list.add(cb.equal(root.get("fundcode").as(String.class), fundCode));
                }
                if (jzrq != null) {
                    list.add(cb.equal(root.get("jzrq").as(String.class), jzrq));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }

        });

        return fundHistoryDay.orElse(null);
    }
}
