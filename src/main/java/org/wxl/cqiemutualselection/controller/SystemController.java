package org.wxl.cqiemutualselection.controller;


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


    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user/Login";
    }
    @RequestMapping("/toExcel")
    public String toExcel(){
        return "user/excel";
    }


}
