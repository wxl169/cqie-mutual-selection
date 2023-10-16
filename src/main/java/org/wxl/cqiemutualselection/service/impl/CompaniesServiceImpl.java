package org.wxl.cqiemutualselection.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.wxl.cqiemutualselection.common.ErrorCode;
import org.wxl.cqiemutualselection.contant.CompaniesConstant;
import org.wxl.cqiemutualselection.domain.dto.CompaniesInfoDTO;
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
    public Companies saveCompaniesRegisterInfo(CompaniesInfoDTO companiesInfoDTO) {

        return null;
    }
}
