package com.example.roma.sys.controller;

import com.example.framework.core.db.page.Page;
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
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value="dao/*")
public class DaoController {

    private static Logger log = Logger.getLogger(DaoController.class);

    @Autowired
    private IUserService userService;

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

    @ResponseBody
    @RequestMapping(value = "userRoles.do")
    public Map<String, Object> userRoles(HttpServletRequest request, HttpServletResponse response, Model model,
                                     Integer pageNo) {
        log.debug("in the userRoles");
        Map<String, Object> retMap = new HashMap<>();

        Page page = new Page();
        page.setPageSize(4);
        if (pageNo != null && pageNo > 0) {
            page.setPageNo(pageNo);
        }
        page = userService.queryUserRole(page);

        retMap.put("datas", page.getResult());
        retMap.put("size", CollectionUtils.isEmpty(page.getResult()) ? 0 : page.getResult().size());
        return retMap;
    }

}
