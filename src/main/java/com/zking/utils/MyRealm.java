package com.zking.utils;

import com.zking.model.User;
import com.zking.service.IUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private IUserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String username = principalCollection.getPrimaryPrincipal().toString();
        //得到用户角色
        Set<String> roles = userService.find(username);
        //得到用户的权限
        Set<String> per = userService.findePer(username);


        //设置主题中的用户角色以及权限
        SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(per);

        return authorizationInfo;

    }

    //身份验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//      得到前端用户输入的账号
        String username = authenticationToken.getPrincipal().toString();
//      得到前端用户输入的密码
        String password = authenticationToken.getCredentials().toString();
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        //根据账号获取数据库中用户的个人信息
        User u = userService.login(user);
        if(null==u){
            //根据账号找不到用户
            throw new RuntimeException("账号不存在");
        }

        SimpleAuthenticationInfo authenticationInfo=new SimpleAuthenticationInfo(
                u.getUsername(),
                u.getPassword(),
                ByteSource.Util.bytes(u.getSalt()),
                this.getName()
        );



        return authenticationInfo;
    }
}
