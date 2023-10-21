package org.wxl.cqiemutualselection.service;

import org.wxl.cqiemutualselection.domain.dto.companies.CompaniesInfoDTO;
import org.wxl.cqiemutualselection.domain.dto.user.LoginDTO;
import org.wxl.cqiemutualselection.domain.dto.user.UserExcelDTO;
import org.wxl.cqiemutualselection.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.wxl.cqiemutualselection.domain.vo.user.CurrentUserVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 16956
 * @since 2023-10-12
 */
public interface IUserService extends IService<User> {

    /**
     * 用户或企业登录
     *
     * @param loginDTO 账号密码
     * @return 登录用户信息
     */
    CurrentUserVO userLogin(LoginDTO loginDTO);

    /**
     * 保存从excel导入的数据
     * @param cachedDataList excel导入的数据
     */
    void saveByExcel(List<UserExcelDTO> cachedDataList);

    /**
     * 排除已存在的数据
     *
     * @param cachedDataList 要存入的数据
     * @return 需要存的数据
     */
    List<UserExcelDTO> excludeUser(List<UserExcelDTO> cachedDataList);

    /**
     * 企业注册
     *
     * @param companiesInfoDTO 企业注册信息
     * @return 是否注册成功
     */
    boolean companiesRegister(CompaniesInfoDTO companiesInfoDTO);
    /**
     * 获取当前登录的用户信息
     *
     * @return 当前登录的用户信息
     */
    CurrentUserVO getCurrentUserInfo(Long userId);
}
