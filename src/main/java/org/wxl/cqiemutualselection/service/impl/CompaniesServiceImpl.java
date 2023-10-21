package org.wxl.cqiemutualselection.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.apache.commons.lang3.StringUtils;
import org.wxl.cqiemutualselection.domain.dto.companies.CompaniesInfoDTO;
import org.wxl.cqiemutualselection.domain.dto.companies.CompaniesPageDTO;
import org.wxl.cqiemutualselection.domain.entity.Companies;
import org.wxl.cqiemutualselection.domain.vo.PageVO;
import org.wxl.cqiemutualselection.domain.vo.companies.CompaniesInfoVO;
import org.wxl.cqiemutualselection.mapper.CompaniesMapper;
import org.wxl.cqiemutualselection.service.ICompaniesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wxl.cqiemutualselection.utils.BeanCopyUtils;

import java.util.ArrayList;
import java.util.List;

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

    @Reference
    private CompaniesMapper companiesMapper;
    @Override
    public Companies saveCompaniesRegisterInfo(CompaniesInfoDTO companiesInfoDTO) {

        return null;
    }

    @Override
    public PageVO getCompaniesInfoAll(Integer pageNum, Integer pageSize, CompaniesPageDTO companiesPageDTO) {
        if (pageNum == null||pageNum == 0){
            pageNum = 1;
        }
        if (pageSize == null || pageSize == 0){
            pageSize = 10;
        }
        Page<Companies> page = new Page<>(pageNum,pageSize);
        LambdaQueryWrapper<Companies> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.select(Companies::getId,Companies::getCompaniesFullName,
                Companies::getAddress,Companies::getIndustry,Companies::getFoundingTime,
                Companies::getCompaniesType, Companies::getPeopleNumber,Companies::getProcess)
                        .like(StringUtils.isNotBlank(companiesPageDTO.getCompaniesFullName()),Companies::getCompaniesFullName,companiesPageDTO.getCompaniesFullName())
                                .eq(StringUtils.isNotBlank(companiesPageDTO.getIndustry()),Companies::getIndustry,companiesPageDTO.getIndustry())
                                        .eq(StringUtils.isNotBlank(companiesPageDTO.getCompaniesType()),Companies::getCompaniesType,companiesPageDTO.getCompaniesType())
                                                .eq(companiesPageDTO.getProcess() != null,Companies::getProcess,companiesPageDTO.getProcess());

        page(page,queryWrapper);
        List<CompaniesInfoVO> companiesInfoVOS = BeanCopyUtils.copyBeanList(page.getRecords(), CompaniesInfoVO.class);
        return new PageVO(companiesInfoVOS,page.getTotal());
    }

}
