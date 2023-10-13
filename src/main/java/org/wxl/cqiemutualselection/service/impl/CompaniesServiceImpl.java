package org.wxl.cqiemutualselection.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.wxl.cqiemutualselection.common.ErrorCode;
import org.wxl.cqiemutualselection.contant.CompaniesConstant;
import org.wxl.cqiemutualselection.domain.entity.Companies;
import org.wxl.cqiemutualselection.exception.BusinessException;
import org.wxl.cqiemutualselection.mapper.CompaniesMapper;
import org.wxl.cqiemutualselection.service.ICompaniesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wxl.cqiemutualselection.utils.MD5Util;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 16956
 * @since 2023-10-12
 */
@Service
public class CompaniesServiceImpl extends ServiceImpl<CompaniesMapper, Companies> implements ICompaniesService {

    @Override
    public boolean companiesLogin(String companiesAccount, String companiesPassword) {
        // 1.判断账号密码是否为空
        judgeCompaniesInfo(companiesAccount,companiesPassword);
        // 2.对密码加密
        String password = CompaniesConstant.COMPANIES_PASSWORD_SALT + MD5Util.string2MD5(companiesPassword);
        // 3.判断账号是否注册
        LambdaQueryWrapper<Companies> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(Companies::getId,Companies::getCompaniesAccount,Companies::getPassword)
                .eq(Companies::getCompaniesAccount,companiesAccount)
                .eq(Companies::getPassword,password);
        Companies companies = this.getOne(queryWrapper);
        //3.1 如果companies为空，则未注册账号
        if (companies == null){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"请注册账号");
        }
        //3.2 如果不为空，则登录成功
        StpUtil.login(companies.getId());
        return true;
    }


    /**
     * 判断账号密码是否为空
     *
     * @param userAccount 账号
     * @param password 密码
     */
    private void judgeCompaniesInfo(String userAccount,String password){
        if (StringUtils.isBlank(userAccount)){
            throw new BusinessException(ErrorCode.NULL_ERROR,"请输入账号");
        }
        if (StringUtils.isBlank(password)){
            throw new BusinessException(ErrorCode.NULL_ERROR,"请输入密码");
        }
    }
}
