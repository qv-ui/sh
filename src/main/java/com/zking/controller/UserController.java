package com.zking.controller;

import com.zking.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    @RequestMapping("/")
    public String toLog(){

        return "login";
    }
    @RequestMapping("/dologin")
    public  String doLog(Model model, User user){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken(
                user.getUsername(),
                user.getPassword()
        );
        String msg="";
        boolean f=true;

        try{
            subject.login(token);
        }catch(IncorrectCredentialsException e){
            msg="密码错误";
            f=false;
        }catch(RuntimeException e){
            msg="账号不存在";
            f=false;
        }
       model.addAttribute("msg",msg);
        if (f){
            return "suc";
        }
         else{

            return "login";
        }
    }
}
