package com.example.roma.sys.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="/*")
public class LoginController {

    private static Logger log = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "login.do")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model, String error) {
        log.debug("in the login");
        if (StringUtils.isNoneBlank(error)) {
            model.addAttribute("error", error);
        }
        return "login";
    }

    @RequestMapping(value = "reallogin.do")
    public String reallogin(HttpServletRequest request, HttpServletResponse response, Model model,
                            String username, String password) {
        log.debug("in the reallogin");
        String error = null;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            error = "用户名/密码错误";
        } catch (IncorrectCredentialsException e) {
            error = "用户名/密码错误";
        } catch (AuthenticationException e) {
            error = "其他错误：" + e.getMessage();
        }

        if (error != null) {
            model.addAttribute("error", error);
            return "redirect:/login.do";
        }
        return "redirect:/authenticated.do";
    }

    @RequestMapping(value = "logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("in the logout");
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.logout();
        } catch (Exception e) {
            log.debug(e.getMessage());
        }
        return "redirect:/login.do";
    }

    @RequestMapping(value = "unauthorized.do")
    public String unauthorized(HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("in the unauthorized");
        return "unauthorized";
    }

    @RequestMapping(value = "authenticated.do")
    public String authenticated(HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("in the authenticated");
        return "authenticated";
    }

}
