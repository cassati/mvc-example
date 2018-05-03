package com.example.roma.sys.controller;

import com.example.framework.core.db.page.Page;
import com.example.roma.sys.entity.User;
import com.example.roma.sys.service.IUserService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
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
@RequestMapping(value="index/*")
public class IndexController {

    private static Logger log = Logger.getLogger(IndexController.class);

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "main.do")
    public String main(HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("in the main");
        return "/main";
    }

    @ResponseBody
    @RequestMapping(value = "data.do")
    public Map<String, Object> data(HttpServletRequest request, HttpServletResponse response, Model model) {
        log.debug("in the data");
        Map<String, Object> retMap = new HashMap<>();

        Map<String, Object> map = null;
        List<Map<String, Object>> datas = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            map = new HashMap<>();
            map.put("id", i + 1);
            map.put("name", "name" + (i + 1));
            datas.add(map);
        }

        retMap.put("datas", datas);
        retMap.put("size", 100);
        return retMap;
    }

    @ResponseBody
    @RequestMapping(value = "users.do")
    public Map<String, Object> users(HttpServletRequest request, HttpServletResponse response, Model model,
                                     Integer pageNo) {
        log.debug("in the users");
        Map<String, Object> retMap = new HashMap<>();

        Page page = new Page();
        page.setPageSize(4);
        if (pageNo != null && pageNo > 0) {
            page.setPageNo(pageNo);
        }
        page = userService.queryAll(page);

        retMap.put("datas", page.getResult());
        retMap.put("size", CollectionUtils.isEmpty(page.getResult()) ? 0 : page.getResult().size());
        return retMap;
    }

}
