package com.wangzhixuan.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.scan.SpringUtils;
import com.wangzhixuan.commons.utils.JsonUtils;
import com.wangzhixuan.event.WebHooksEvent;
import com.wangzhixuan.event.WebHooksListener;

/**
 * WebHooks 自动更新部署
 * Created by L.cm on 2016/9/23.
 */
@Controller
@SuppressWarnings("unchecked")
public class WebHooksController extends BaseController {
    private static final Logger logger = LogManager.getLogger(WebHooksController.class);
    
    /**
     * git@osc WebHooks 设置
     * WIKI: http://git.oschina.net/oschina/git-osc/wikis/WebHook-%E4%BD%BF%E7%94%A8%E7%AE%80%E4%BB%8B
     * @param hook json字符串
     * @return jsonBean
     */
//    @RequestMapping(value = "webhooks", method = RequestMethod.POST)
    @RequestMapping(value = "webhooks")
    @ResponseBody
    public Object hooks(@RequestParam String hook) {
        logger.info("webhooks json: {}", hook);
        Map<String, Object> hookMap = JsonUtils.parse(hook, Map.class);
        // 发送事件 ThreadPoolTaskExecutor
        SpringUtils.publishEvent(new WebHooksEvent(hookMap));
        return renderSuccess("ok");
    }
}
