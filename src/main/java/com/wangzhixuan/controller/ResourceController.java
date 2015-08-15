package com.wangzhixuan.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.wangzhixuan.model.User;
import com.wangzhixuan.service.ResourceService;
import com.wangzhixuan.vo.ResourceVo;
import com.wangzhixuan.vo.Tree;

@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {
    
    private static Logger logger = LoggerFactory.getLogger(ResourceController.class);
    
    @Autowired
    private ResourceService resourceService;

    /**
     * @Description：菜单树
     * @return
     * @author：Wangzhixuan
     */
    @RequestMapping(value = "/tree", method = RequestMethod.POST)
    @ResponseBody
    public String tree() {
        User currentUser = getCurrentUser();
        List<Tree> tree = resourceService.tree(currentUser);
        return JSON.toJSONString(tree);
    }

    /**
     * @Description：资源管理
     * @return
     * @author：Wangzhixuan
     */
    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "admin/resource";
    }

    @RequestMapping(value = "/treeGrid", method = RequestMethod.POST)
    @ResponseBody
    public String treeGrid() {
        List<ResourceVo> treeGrid = resourceService.treeGrid();
        return JSON.toJSONString(treeGrid);
    }

/*    @RequestMapping("/allTree")
    @ResponseBody
    public List<Tree> allTree(boolean flag) {// true获取全部资源,false只获取菜单资源
        return resourceService.listAllTree(flag);
    }

    @RequestMapping("/treeGrid")
    @ResponseBody
    public List<Resource> treeGrid() {
        List<Resource> treeGrid = resourceService.treeGrid();
        String jsonString = JSON.toJSONString(treeGrid);
        System.out.println("------2:"+ jsonString);
        return resourceService.treeGrid();
    }

    @RequestMapping("/get")
    @ResponseBody
    public Resource get(Long id) {
        return resourceService.get(id);
    }

    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        Resource r = resourceService.get(id);
        request.setAttribute("resource", r);
        return "/admin/resourceEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Json edit(Resource resource) throws InterruptedException {
        Json j = new Json();
        try {
            resourceService.edit(resource);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }

    @RequestMapping("/delete")
    @ResponseBody
    public Json delete(Long id) {
        Json j = new Json();
        try {
            resourceService.delete(id);
            j.setMsg("删除成功！");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }

    @RequestMapping("/addPage")
    public String addPage() {
        return "/admin/resourceAdd";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Json add(Resource resource) {
        Json j = new Json();
        try {
            resourceService.add(resource);
            j.setSuccess(true);
            j.setMsg("添加成功！");
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }*/

}
