package com.example.roma.sys.controller;

import com.example.roma.sys.entity.User;
import com.example.roma.sys.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="user/*")
public class UserController {

    private static Logger log = Logger.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @RequiresPermissions("sys:user:add")
    @RequestMapping(value = "addUser.do")
    public String addUser(HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("in the addUser");
        return "/user/addUser";
    }

    @RequiresPermissions("sys:user:add")
    @ResponseBody
    @RequestMapping(value = "saveUser.do")
    public String saveUser(HttpServletRequest request, HttpServletResponse response, Model model, User user) {
        log.debug("in the saveUser");
        String msg = "用户新增成功";
        if (user == null) {
            return "请填写用户信息";
        }
        if (StringUtils.isBlank(user.getName())
                || StringUtils.isBlank(user.getUsername())
                || StringUtils.isBlank(user.getPassword())) {
            return "用户信息不完整";
        }
        try {
            userService.createUser(user);
        } catch (Exception e) {
            log.debug(e.getMessage());
            msg = "保存用户失败";
        }
        return msg;
    }

}
