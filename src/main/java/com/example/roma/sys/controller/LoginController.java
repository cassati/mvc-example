package com.example.roma.sys.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value="sys/*")
public class LoginController {

    private static Logger log = Logger.getLogger(LoginController.class);

    @RequestMapping(value = "login.do")
    public String login(HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("in the login");
        return "/login";
    }

    @RequestMapping(value = "logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("in the logout");
        return "/logout";
    }

    @RequestMapping(value = "unauthorized.do")
    public String unauthorized(HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("in the unauthorized");
        return "/unauthorized";
    }

    @RequestMapping(value = "authenticated.do")
    public String authenticated(HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("in the authenticated");
        return "/authenticated";
    }

}
