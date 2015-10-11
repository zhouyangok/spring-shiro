package com.wangzhixuan.controller;

import com.wangzhixuan.service.SlaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @description：主重库测试
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/slave")
public class SlaveController extends BaseController {

    private static Logger LOGGER = LoggerFactory.getLogger(SlaveController.class);

//    @Autowired
//    private SlaveService slaveService;
//
//    @RequestMapping(value = "/test", method = RequestMethod.GET)
//    public String test(Model model) {
//        Integer count = slaveService.count();
//        model.addAttribute("count", count);
//        return "admin/test";
//    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        return "admin/test";
    }

}
