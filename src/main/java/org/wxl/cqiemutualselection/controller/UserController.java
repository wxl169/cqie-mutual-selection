package org.wxl.cqiemutualselection.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.wxl.cqiemutualselection.common.BaseResponse;
import org.wxl.cqiemutualselection.common.ErrorCode;
import org.wxl.cqiemutualselection.common.ResultUtils;
import org.wxl.cqiemutualselection.domain.dto.LoginDTO;
import org.wxl.cqiemutualselection.exception.BusinessException;
import org.wxl.cqiemutualselection.service.IUserService;

import javax.annotation.Resource;

/**
 * @author 16956
 */
@Api(tags = "用户模块")
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    /**
     * 用户登录
     * @param loginDTO 账号密码
     * @return 是否登录成功
     */
    @PostMapping("/login")
    @ApiOperation("用户或企业登录")
    public BaseResponse doLogin(@RequestBody LoginDTO loginDTO){
        if (loginDTO == null){
            throw new BusinessException(ErrorCode.NULL_ERROR,"请输入账号和密码");
        }
        boolean loginJudge = userService.userLogin(loginDTO);
        if (!loginJudge){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"登录失败");
        }
        return ResultUtils.success(true);
    }







    @GetMapping("/isLogin")
    @ApiOperation("测试登录用户信息")
    public String test(){
        return "当前会话是否登录：" + StpUtil.isLogin() + "当前登录用户id," + StpUtil.getLoginId();
    }
}
