package com.wangzhixuan.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.wangzhixuan.model.Role;
import com.wangzhixuan.service.RoleService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.utils.Result;
import com.wangzhixuan.vo.Tree;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;
    
    @RequestMapping("/manager")
    public String manager() {
        return "/admin/role";
    }

    @RequestMapping("/dataGrid")
    @ResponseBody
    public String dataGrid(Role role, Integer page, Integer rows) {
        PageInfo pageInfo = new PageInfo(page, rows);

        Map<String, Object> condition = Maps.newHashMap();

/*        if(StringUtils.isNoneBlank(username)){
            condition.put("username", username);
        }
        if(StringUtils.isNoneBlank(nickname)){
            condition.put("nickname", nickname);
        }*/
        pageInfo.setCondition(condition);

        roleService.findDataGrid(pageInfo);
        return JSON.toJSONString(pageInfo);
    }

    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree() {
        return roleService.findTree();
    }

    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "/admin/roleAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(Role role) {
        Result result = new Result();
        try {
            roleService.addRole(role);
            result.setSuccess(true);
            result.setMsg("添加成功！");
        } catch (Exception e) {
            result.setMsg(e.getMessage());
        }
        return result;
    }
//
//    @RequestMapping("/delete")
//    @ResponseBody
//    public Json delete(Long id) {
//        Json j = new Json();
//        try {
//            roleService.delete(id);
//            j.setMsg("删除成功！");
//            j.setSuccess(true);
//        } catch (Exception e) {
//            j.setMsg(e.getMessage());
//        }
//        return j;
//    }
//
//    @RequestMapping("/get")
//    @ResponseBody
//    public Role get(Long id)  {
//        return roleService.get(id);
//    }
//    
//    @RequestMapping("/editPage")
//    public String editPage(HttpServletRequest request, Long id) {
//        Role r = roleService.get(id);
//        request.setAttribute("role", r);
//        return "/admin/roleEdit";
//    }
//
//    @RequestMapping("/edit")
//    @ResponseBody
//    public Json edit(Role role) {
//        Json j = new Json();
//        try {
//            roleService.edit(role);
//            j.setSuccess(true);
//            j.setMsg("编辑成功！");
//        } catch (Exception e) {
//            j.setMsg(e.getMessage());
//        }
//        return j;
//    }
//    
//    @RequestMapping("/grantPage")
//    public String grantPage(HttpServletRequest request, Long id) {
//        Role r = roleService.get(id);
//        request.setAttribute("role", r);
//        return "/admin/roleGrant";
//    }
//
//    @RequestMapping("/grant")
//    @ResponseBody
//    public Json grant(Role role) {
//        Json j = new Json();
//        try {
//            roleService.grant(role);
//            j.setMsg("授权成功！");
//            j.setSuccess(true);
//        } catch (Exception e) {
//            j.setMsg(e.getMessage());
//        }
//        return j;
//    }

}
