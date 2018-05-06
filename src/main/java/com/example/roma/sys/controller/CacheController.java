package com.example.roma.sys.controller;

import com.example.framework.cache.EhCacheUtils;
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
@RequestMapping(value="cache/*")
public class CacheController {

    private static Logger log = Logger.getLogger(CacheController.class);

    @Autowired
    private IUserService userService;

    @ResponseBody
    @RequestMapping(value = "hit.do")
    public Map<String, Object> hit(HttpServletRequest request, HttpServletResponse response, Model model,
                                     Integer pageNo) {
        log.debug("in the cache");
        String all_users_key = "all_users";
        Object cached = EhCacheUtils.get(all_users_key);
        if (cached != null) {
            log.debug("hit the cache all_users");
            return (Map<String, Object>)cached;
        }

        Map<String, Object> retMap = new HashMap<>();

        Page page = new Page();
        page.setPageSize(4);
        if (pageNo != null && pageNo > 0) {
            page.setPageNo(pageNo);
        }
        page = userService.queryAll(page);

        retMap.put("datas", page.getResult());
        retMap.put("size", CollectionUtils.isEmpty(page.getResult()) ? 0 : page.getResult().size());

        EhCacheUtils.put(all_users_key, retMap);
        return retMap;
    }

}
