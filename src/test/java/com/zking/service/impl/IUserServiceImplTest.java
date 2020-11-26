package com.zking.service.impl;

import com.zking.model.User;
import com.zking.service.IUserService;
import com.zking.utils.PasswordHelper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class IUserServiceImplTest extends BaseTestCase{
    private User user;
    @Autowired
     private IUserService userService;
    @Before
    public void setUp() throws Exception {
        user=new User();
    }

    @Test
    public void register() {
        user.setUsername("cxk");
        String salt = PasswordHelper.createSalt();
        user.setSalt(salt);
        String credentials = PasswordHelper.createCredentials("123", salt);
        user.setPassword(credentials);
        userService.register(user);
    }

    @Test
    public void login() {
        user.setUsername("cxk");
        user.setPassword("123");
        User u = userService.login(user);
        String salt = u.getSalt();
        boolean b = PasswordHelper.checkCredentials("123", salt, u.getPassword());
        System.out.println(b);

    }
}