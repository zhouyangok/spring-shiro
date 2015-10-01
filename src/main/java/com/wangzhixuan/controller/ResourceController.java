package com.wangzhixuan.controller;

import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.Resource;
import com.wangzhixuan.model.User;
import com.wangzhixuan.service.ResourceService;
import com.wangzhixuan.vo.Tree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description：资源管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);

    @Autowired
    private ResourceService resourceService;

    /**
     * @return
     * @Description：菜单树
     * @author：Wangzhixuan
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> tree() {
        User currentUser = getCurrentUser();
        List<Tree> tree = resourceService.findTree(currentUser);
        return tree;
    }

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "admin/resource";
    }

    @RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
    @ResponseBody
    public List<Resource> treeGrid() {
        List<Resource> treeGrid = resourceService.findResourceAll();
        return treeGrid;
    }

    @RequestMapping("/addPage")
    public String addPage() {
        return "/admin/resourceAdd";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Resource resource) {
        Result result = new Result();
        try {
            resourceService.addResource(resource);
            result.setSuccess(true);
            result.setMsg("添加成功！");
            return result;
        } catch (RuntimeException e) {
            logger.error("添加资源失败：{}", e.getMessage());
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/allTree")
    @ResponseBody
    public List<Tree> allTree() {
        return resourceService.findAllTree();
    }

    /**
     * @return
     * @Description：资源树
     * @author：Wangzhixuan
     */
    @RequestMapping(value = "/allTrees", method = RequestMethod.POST)
    @ResponseBody
    public List<Tree> allTrees() {
        return resourceService.findAllTrees();
    }

    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        Resource resource = resourceService.findResourceById(id);
        request.setAttribute("resource", resource);
        return "/admin/resourceEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(Resource resource) {
        Result result = new Result();
        try {
            resourceService.updateResource(resource);
            result.setSuccess(true);
            result.setMsg("编辑成功！");
            return result;
        } catch (RuntimeException e) {
            logger.error("编辑资源失败：{}", e.getMessage());
            result.setMsg(e.getMessage());
            return result;
        }
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result result = new Result();
        try {
            resourceService.deleteResourceById(id);
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return result;
        } catch (RuntimeException e) {
            logger.error("删除资源失败：{}", e.getMessage());
            result.setMsg(e.getMessage());
            return result;
        }
    }

}
