package com.zking.service;

import com.zking.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
public interface IUserService {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int register(User user);
    
    User login(User user);

    /**
     * 获取用户角色
     * @param usernaem
     * @return
     */
    Set<String> find(String usernaem);

    /**
     * 获取用户权限
     * @param username
     * @return
     */
    Set<String> findePer(String username);


}