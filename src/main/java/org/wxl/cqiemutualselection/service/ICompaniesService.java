package org.wxl.cqiemutualselection.service;

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
     * 企业登录账号
     *
     * @param companiesAccount 企业账号
     * @param password 密码
     * @return 是否登录成功
     */
    boolean companiesLogin(String companiesAccount, String password);
}
