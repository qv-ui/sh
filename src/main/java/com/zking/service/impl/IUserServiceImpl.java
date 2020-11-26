package com.zking.service.impl;

import com.zking.mapper.UserMapper;
import com.zking.model.User;
import com.zking.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int deleteByPrimaryKey(Integer userid) {
        return userMapper.deleteByPrimaryKey(userid);
    }

    @Override
    public int insert(User record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertSelective(User record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public User selectByPrimaryKey(Integer userid) {
        return userMapper.selectByPrimaryKey(userid);
    }

    @Override
    public int updateByPrimaryKeySelective(User record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(User record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public int register(User user) {
        return userMapper.register(user);
    }

    @Override
    public User login(User user) {
        return userMapper.login(user);
    }

    @Override
    public Set<String> find(String usernaem) {
        return userMapper.find(usernaem);
    }

    @Override
    public Set<String> findePer(String username) {
        return userMapper.findePer(username);
    }
}
