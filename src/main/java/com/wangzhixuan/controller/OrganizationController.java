package com.wangzhixuan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.Organization;
import com.wangzhixuan.service.OrganizationService;
import com.wangzhixuan.vo.Tree;

@Controller
@RequestMapping("/organization")
public class OrganizationController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganizationService organizationService;

    @RequestMapping("/manager")
    public String manager() {
        return "/admin/organization";
    }

    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree() {
        List<Tree> trees = organizationService.findTree();
        return trees;
    }
    
    @RequestMapping("/treeGrid")
    @ResponseBody
    public List<Organization> treeGrid() {
        List<Organization> treeGrid =organizationService.findTreeGrid();
        return treeGrid;
    }


    @RequestMapping("/addPage")
    public String addPage() {
        return "/admin/organizationAdd";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Organization organization) {
        Result result = new Result();
        try {
            organizationService.addOrganization(organization);
            result.setSuccess(true);
            result.setMsg("添加成功！");
        } catch (Exception e) {
            logger.info("添加部门失败：{}", e.getMessage());
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        Organization organization = organizationService.findOrganizationById(id);
        request.setAttribute("organization", organization);
        return "/admin/organizationEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(Organization organization) {
        Result result = new Result();
        try {
            organizationService.updateOrganization(organization);
            result.setSuccess(true);
            result.setMsg("编辑成功！");
        } catch (Exception e) {
            logger.info("编辑部门失败：{}", e.getMessage());
            result.setMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            organizationService.deleteOrganizationById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
        } catch (Exception e) {
            result.setMsg(e.getMessage());
            logger.info("删除部门失败：{}", e.getMessage());
        }
        return result;
    }
}
