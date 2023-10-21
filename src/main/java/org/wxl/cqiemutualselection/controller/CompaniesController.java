package org.wxl.cqiemutualselection.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.wxl.cqiemutualselection.common.BaseResponse;
import org.wxl.cqiemutualselection.common.ErrorCode;
import org.wxl.cqiemutualselection.common.ResultUtils;
import org.wxl.cqiemutualselection.contant.UserConstant;
import org.wxl.cqiemutualselection.domain.dto.companies.CompaniesPageDTO;
import org.wxl.cqiemutualselection.domain.vo.PageVO;
import org.wxl.cqiemutualselection.domain.vo.companies.CompaniesInfoVO;
import org.wxl.cqiemutualselection.domain.vo.user.CurrentUserVO;
import org.wxl.cqiemutualselection.exception.BusinessException;
import org.wxl.cqiemutualselection.service.ICompaniesService;
import org.wxl.cqiemutualselection.service.IUserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 16956
 */
@Api(tags = "公司模块")
@RestController
@RequestMapping("/companies")
public class CompaniesController {
    @Resource
    private ICompaniesService companiesService;
    @Resource
    private IUserService userService;


    /**
     * 获取所有公司信息
     *
     * @return 登录用户信息
     */
    @SaCheckRole("admin")
    @GetMapping("/get/all")
    @ApiOperation("获取所有公司信息")
    public BaseResponse<PageVO> getCompaniesInfoAll(
            @RequestParam(value = "pageNum") Integer pageNum, @RequestParam("pageSize") Integer pageSize, CompaniesPageDTO companiesPageDTO){
         Long loginId = StpUtil.getLoginIdAsLong();
        CurrentUserVO currentUserInfo = userService.getCurrentUserInfo(loginId);
        if (UserConstant.ROLE_ADMIN_SQL != currentUserInfo.getType()){
            throw new BusinessException(ErrorCode.NULL_ERROR,"权限不足");
        }
        return ResultUtils.success(companiesService.getCompaniesInfoAll(pageNum,pageSize,companiesPageDTO));
    }


}
