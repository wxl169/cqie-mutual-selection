package org.wxl.cqiemutualselection.controller;


import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 16956
 */
@Api(tags = "页面跳转模块")
@Controller
@RequestMapping("/system")
public class SystemController {


    /**
     * 跳转到登录页面
     *  此接口加上了 @SaIgnore 可以游客访问
     */
    @SaIgnore
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user/Login";
    }

    /**
     * 跳转到注册页面
     *  此接口加上了 @SaIgnore 可以游客访问
     */
    @SaIgnore
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "user/Register";
    }

    /**
     * 跳转到excel页面
     * @return excel页面
     */
    @SaCheckRole("admin")
    @RequestMapping("/toExcel")
    public String toExcel(){
        return "user/excel";
    }


}
