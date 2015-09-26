package com.wangzhixuan.controller;

import com.wangzhixuan.service.SlaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/slave")
public class SlaveController extends BaseController {

    protected static Logger logger = LoggerFactory.getLogger(SlaveController.class);

    @Autowired
    private SlaveService slaveService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test(Model model) {
        Integer count = slaveService.count();
        model.addAttribute("count", count);
        return "admin/test";
    }

}
