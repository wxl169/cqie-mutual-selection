package org.wxl.cqiemutualselection.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.wxl.cqiemutualselection.common.BaseResponse;
import org.wxl.cqiemutualselection.common.ErrorCode;
import org.wxl.cqiemutualselection.common.ResultUtils;
import org.wxl.cqiemutualselection.domain.dto.CompaniesInfoDTO;
import org.wxl.cqiemutualselection.domain.dto.LoginDTO;
import org.wxl.cqiemutualselection.domain.vo.CurrentUserVO;
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
     * @return 登录用户信息
     */
    @PostMapping("/login")
    @SaIgnore
    @ApiOperation("用户或企业登录")
    public BaseResponse<CurrentUserVO> doLogin(@RequestBody LoginDTO loginDTO){
        if (loginDTO == null){
            throw new BusinessException(ErrorCode.NULL_ERROR,"请输入账号和密码");
        }
        return ResultUtils.success(userService.userLogin(loginDTO));
    }


    /**
     * 企业注册
     * @param companiesInfoDTO 企业账号信息
     * @return 是否登录成功
     */
    @SaIgnore
    @PostMapping("/register")
    @ApiOperation("用户或企业登录")
    public BaseResponse doRegister(@RequestBody CompaniesInfoDTO companiesInfoDTO){
        if (companiesInfoDTO == null){
            throw new BusinessException(ErrorCode.NULL_ERROR,"请输入企业账号信息");
        }
        boolean judge = userService.companiesRegister(companiesInfoDTO);
        if (!judge){
            throw  new BusinessException(ErrorCode.SYSTEM_ERROR,"企业注册失败");
        }
        return ResultUtils.success(true);
    }

    /**
     * 获取当前登录的用户信息
     *
     * @return 当前登录的用户信息
     */
    @SaIgnore
    @GetMapping("/currentUser")
    @ApiOperation("获取当前登录的用户信息")
    public BaseResponse<CurrentUserVO> getCurrentUser(){
        Long loginId = StpUtil.getLoginIdAsLong();
        return ResultUtils.success(userService.getCurrentUserInfo(loginId));
    }

    @SaIgnore
    @PostMapping("/logout")
    @ApiOperation("退出登录")
    public BaseResponse<String> userLogout(){
        Long loginId = StpUtil.getLoginIdAsLong();
        StpUtil.logout(loginId);
        return ResultUtils.success("退出成功");
    }

}
