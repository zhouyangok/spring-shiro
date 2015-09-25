package com.wangzhixuan.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.code.Result;

/**
 * @Description：
 * @author：Wangzhixuan
 * @date：2015年6月30日 下午4:14:41
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/")
    public String index(){
        return "redirect:/index";
    }

    @RequestMapping(value = "/index")
    public String index(Model model) {
        return "/index";
    }

    @RequestMapping(value = "/login",method=RequestMethod.GET)
    public String login(Model model,HttpServletRequest request) {
        logger.info("GET请求登录");
        if(SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "/login";
    }

    /**
     * @description：登录页面
     * @author：Wangzhixuan
     * @param：
     */
    @RequestMapping(value = "/login",method=RequestMethod.POST)
    @ResponseBody
    public Result loginPost(String username, String password, HttpServletRequest request, Model model) {
        logger.info("POST请求登录");
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, DigestUtils.md5Hex(password).toCharArray());
        token.setRememberMe(true);
        Result result = new Result();
        try {
            //调用 com.wangzhixuan.shiro.ShiroDbRealm
            user.login(token);
        } catch (UnknownAccountException e) {
            logger.error("账号不存在：{}", e.getMessage());
            result.setMsg("账号不存在");
            return result;
        }  catch (DisabledAccountException e) {
            logger.error("账号未启用：{}", e.getMessage());
            result.setMsg("账号未启用");
            return result;
        }catch (IncorrectCredentialsException e) {
            logger.error("密码错误：{}", e.getMessage());
            result.setMsg("密码错误");
            return result;
        } catch (RuntimeException e) {
            logger.error("未知错误,请联系管理员：{}", e.getMessage());
            result.setMsg("未知错误,请联系管理员");
            return result;
        }
        result.setSuccess(true);
        return result;
    }

    /**
     * @description：未授权
     * @author：Wangzhixuan
     * @param：
     */
    @RequestMapping(value = "/unauth")
    public String unauth(Model model) {
        if(SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/login";
        }
        return "/unauth";
    }

    /**
     * @description：
     * @author：Wangzhixuan
     * @param：
     */
    @RequestMapping(value="/logout")
    public void logout(HttpServletRequest request) {
        logger.info("登出");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }
}
