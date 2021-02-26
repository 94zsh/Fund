package com.first.demo.service.impl;

import com.first.demo.dao.UserRepository;
import com.first.demo.entity.User;
import com.first.demo.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

@Service
public class UserServiceImpl implements UserService {
//    @Autowired
//    private JdbcTemplate jdbcTemplate;//Spring的JdbcTemplate是自动配置的，可直接使用

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> getUserInfo() {

        Log log = LogFactory.getLog(UserServiceImpl.class);
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName("test");
        userList.add(user);
//        String sql = "SELECT * FROM user";
//        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
//        for (Map<String, Object> map : list) {
//            Set<Map.Entry<String, Object>> entries = map.entrySet();
//            if (entries != null) {
//                Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
//                while (iterator.hasNext()) {
//                    Map.Entry<String, Object> entry = (Map.Entry<String, Object>) iterator.next();
//                    Object key = entry.getKey();
//                    Object value = entry.getValue();
//                    System.out.println(key + ":" + value);
//                    user.setId(666L);
//                    user.setName(value.toString());
//                }
//            }
//        }
//        return user;

        List<User> list = userRepository.findAll();
        if(list.size() > 0){
            return list;
        }else{
            return userList;
        }
    }

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getAllUser")
    @ResponseBody
    public List<User> findAll() {
        List<User> list = new ArrayList<User>();
        list = userRepository.findAll();
        return list;
    }

    @RequestMapping("/getByUserName")
    @ResponseBody
    public User getByUserName(String userName) {
        User user = userRepository.findByName(userName);
        return user;
    }
}

