package com.wangzhixuan.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.Role;
import com.wangzhixuan.service.RoleService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.Tree;

@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(RoleController.class);
    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/role";
    }

    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(Role role, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = Maps.newHashMap();

/*        if(StringUtils.isNoneBlank(username)){
            condition.put("username", username);
        }
        if(StringUtils.isNoneBlank(nickname)){
            condition.put("nickname", nickname);
        }*/
        pageInfo.setCondition(condition);

        roleService.findDataGrid(pageInfo);
        return pageInfo;
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
            logger.error("添加角色失败：{}", e.getMessage());
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            roleService.deleteRole(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
        } catch (Exception e) {
            logger.error("删除角色失败：{}", e.getMessage());
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        Role role = roleService.findRoleById(id);
        request.setAttribute("role", role);
        return "/admin/roleEdit";
    }
    
  @RequestMapping("/edit")
  @ResponseBody
  public Result edit(Role role) {
      Result result = new Result();
      try {
          roleService.updateRole(role);
          result.setSuccess(true);
          result.setMsg("编辑成功！");
      } catch (Exception e) {
          logger.error("编辑角色失败：{}", e.getMessage());
          result.setMsg(e.getMessage());
      }
      return result;
  }

    @RequestMapping("/grantPage")
    public String grantPage(HttpServletRequest request, Long id) {
        Role r = roleService.findResourceByRoleId(id);
        request.setAttribute("role", r);
        return "/admin/roleGrant";
    }

//
//    @RequestMapping("/grant")
//    @ResponseBody
//    public Result grant(Role role) {
//        Result result = new Result();
//        try {
//            roleService.grant(role);
//            result.setMsg("授权成功！");
//            result.setSuccess(true);
//        } catch (Exception e) {
//            result.setMsg(e.getMessage());
//        }
//        return result;
//    }

}
