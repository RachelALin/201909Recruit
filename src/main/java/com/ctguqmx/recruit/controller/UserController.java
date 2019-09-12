package com.ctguqmx.recruit.controller;

import com.ctguqmx.recruit.pojo.User;
import com.ctguqmx.recruit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("/loginout")
    public String loginout(Model model, HttpSession session){
        session.invalidate();
        model.addAttribute("msg","安全退出！");
        return "redirect:/user/login";
    }

    @RequestMapping("/login")
    public String loginhtml(){
        return "login";
    }

    @RequestMapping("/dologin")
    @ResponseBody
    public String login(@RequestBody User user, Model model, HttpSession session){
        String result;
        User user1 = userService.login(user.getUserName(),user.getPassWord());
        model.addAttribute("user",user1);
        session.setAttribute("user",user1);
        if (user1!=null){
            result="1";
        }else {
            result="0";
        }
        return result;
    }

    @RequestMapping("/register")
    public String register(){
        return "register";
    }

    @RequestMapping("/doregist")
    @ResponseBody
    public String regist(@RequestBody User user){
        String result;
        System.out.println(user);
        if (userService.register(user)){
            result = "1";
        }else {
            result = "0";
        }
        return result;
    }

    @RequestMapping("/404")
    public String page404(){
        return "404";
    }
}
