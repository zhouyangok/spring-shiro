package com.wangzhixuan.controller;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.utils.PageInfo;
import com.wangzhixuan.model.Role;
import com.wangzhixuan.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：权限管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    /**
     * 权限管理页
     *
     * @return
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/role";
    }

    /**
     * 权限列表
     *
     * @param role
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public Object dataGrid(Role role, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        pageInfo.setCondition(condition);

        roleService.findDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 权限树
     *
     * @return
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public Object tree() {
        return roleService.findTree();
    }

    /**
     * 添加权限页
     *
     * @return
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "/admin/roleAdd";
    }

    /**
     * 添加权限
     *
     * @param role
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(Role role) {
        try {
            roleService.addRole(role);
            return renderSuccess("添加成功！");
        } catch (RuntimeException e) {
            logger.error("添加角色失败：{}", e);
            return renderError(e.getMessage());
        }
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        try {
            roleService.deleteRoleById(id);
            return renderSuccess("删除成功！");
        } catch (RuntimeException e) {
            logger.error("删除角色失败：{}", e);
            return renderError(e.getMessage());
        }
    }

    /**
     * 编辑权限页
     *
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        Role role = roleService.findRoleById(id);
        request.setAttribute("role", role);
        return "/admin/roleEdit";
    }

    /**
     * 删除权限
     *
     * @param role
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(Role role) {
        try {
            roleService.updateRole(role);
            return renderSuccess("编辑成功！");
        } catch (RuntimeException e) {
            logger.error("编辑角色失败：{}", e);
            return renderError(e.getMessage());
        }
    }

    /**
     * 授权页面
     *
     * @param request
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/grantPage")
    public String grantPage(HttpServletRequest request, Long id, Model model) {
        model.addAttribute("id", id);
        return "/admin/roleGrant";
    }

    /**
     * 授权页面页面根据角色查询资源
     *
     * @param request
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/findResourceIdListByRoleId")
    @ResponseBody
    public Object findResourceByRoleId(HttpServletRequest request, Long id, Model model) {
        try {
            List<Long> resources = roleService.findResourceIdListByRoleId(id);
            return renderSuccess(resources);
        } catch (RuntimeException e) {
            logger.error("角色查询资源失败：{}", e);
            return renderError(e.getMessage());
        }
    }

    /**
     * 授权
     *
     * @param id
     * @param resourceIds
     * @return
     */
    @RequestMapping("/grant")
    @ResponseBody
    public Object grant(Long id, String resourceIds) {
        try {
            roleService.updateRoleResource(id, resourceIds);
            return renderSuccess("授权成功！");
        } catch (RuntimeException e) {
            logger.error("授权成功失败：{}", e);
            return renderError(e.getMessage());
        }
    }

}
