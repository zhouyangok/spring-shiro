package com.wangzhixuan.controller;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.wangzhixuan.code.Result;
import com.wangzhixuan.model.User;
import com.wangzhixuan.service.UserService;
import com.wangzhixuan.utils.PageInfo;
import com.wangzhixuan.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/manager", method = RequestMethod.GET)
    public String manager() {
        return "/admin/user";
    }

    @RequestMapping(value = "/dataGrid", method = RequestMethod.POST)
    @ResponseBody
    public PageInfo dataGrid(UserVo userVo, Date createdatetimeStart, Date createdatetimeEnd, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows);
        Map<String, Object> condition = Maps.newHashMap();

        if(StringUtils.isNoneBlank(userVo.getName())){
            condition.put("name", userVo.getName());
        }
        if(userVo.getCreatedateStart() != null){
            condition.put("startTime", userVo.getCreatedateStart());
        }
        if(userVo.getCreatedateEnd() != null){
            condition.put("endTime", userVo.getCreatedateEnd());
        }
        pageInfo.setCondition(condition);
        userService.findDataGrid(pageInfo);
        return pageInfo;
    }

    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String addPage() {
        return "/admin/userAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Result add(User user) {
        Result result = new Result();
        User u = userService.findUserByLoginName(user.getLoginname());
        if (u != null) {
            result.setMsg("用户名已存在!");
        } else {
            try {
                userService.addUser(user);
                result.setSuccess(true);
                result.setMsg("添加成功！");
            } catch (Exception e) {
                result.setMsg(e.getMessage());
            }

        }
        return result;
    }

    @RequestMapping(value = "/editPwdPage", method = RequestMethod.GET)
    public String editPwdPage() {
        return "/admin/userEditPwd";
    }

    @RequestMapping("/editUserPwd")
    @ResponseBody
    public Result editUserPwd(HttpServletRequest request, String oldPwd, String pwd) {
        Result result = new Result();
        if(getCurrentUser().getPassword().equals(DigestUtils.md5Hex(oldPwd))){
            try {
                userService.updateUserPwdById(getUserId(), DigestUtils.md5Hex(pwd));
                result.setSuccess(true);
                result.setMsg("密码修改成功！");
            } catch (Exception e) {
                result.setMsg(e.getMessage());
            }
        }else{
            result.setMsg("老密码不正确!");
        }
        return result;
    }

/*    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(Long id) {
        Result j = new Result();
        try {
            userService.delete(id);
            j.setMsg("删除成功！");
            j.setSuccess(true);
        } catch (Exception e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }

    @RequestMapping("/editPage")
    public String editPage(HttpServletRequest request, Long id) {
        User u = userService.get(id);
        request.setAttribute("user", u);
        return "/admin/userEdit";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(User user) {
        Result j = new Result();
        try {
            userService.edit(user);
            j.setSuccess(true);
            j.setMsg("编辑成功！");
        } catch (ServiceException e) {
            j.setMsg(e.getMessage());
        }
        return j;
    }*/

}
