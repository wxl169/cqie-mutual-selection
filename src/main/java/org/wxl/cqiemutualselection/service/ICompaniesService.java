package org.wxl.cqiemutualselection.service;

import org.wxl.cqiemutualselection.domain.dto.companies.CompaniesInfoDTO;
import org.wxl.cqiemutualselection.domain.dto.companies.CompaniesPageDTO;
import org.wxl.cqiemutualselection.domain.entity.Companies;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wxl.cqiemutualselection.domain.vo.PageVO;
import org.wxl.cqiemutualselection.domain.vo.companies.CompaniesInfoVO;

import java.util.List;

/**
 * <p>
 *  企业服务类
 * </p>
 *
 * @author 16956
 * @since 2023-10-12
 */
public interface ICompaniesService extends IService<Companies> {


    /**
     * 将企业信息保存到公司表
     *
     * @param companiesInfoDTO 公司信息
     * @return 是否保持成功
     */
    Companies saveCompaniesRegisterInfo(CompaniesInfoDTO companiesInfoDTO);

    /**
     * 获取所有公司信息
     *
     * @param pageNum 页码
     * @param pageSize 数据量
     * @param companiesPageDTO 筛选条件
     * @return 所有公司信息
     */
    PageVO getCompaniesInfoAll(Integer pageNum, Integer pageSize, CompaniesPageDTO companiesPageDTO);
}
