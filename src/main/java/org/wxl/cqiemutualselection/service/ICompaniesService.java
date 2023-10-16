package org.wxl.cqiemutualselection.service;

import org.wxl.cqiemutualselection.domain.dto.CompaniesInfoDTO;
import org.wxl.cqiemutualselection.domain.entity.Companies;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
