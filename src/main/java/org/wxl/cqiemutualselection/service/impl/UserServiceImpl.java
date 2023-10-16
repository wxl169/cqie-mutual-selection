package org.wxl.cqiemutualselection.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.commons.lang3.StringUtils;
import org.wxl.cqiemutualselection.common.ErrorCode;
import org.wxl.cqiemutualselection.contant.UserConstant;
import org.wxl.cqiemutualselection.domain.dto.LoginDTO;
import org.wxl.cqiemutualselection.domain.dto.UserExcelDTO;
import org.wxl.cqiemutualselection.domain.entity.TbClass;
import org.wxl.cqiemutualselection.domain.entity.User;
import org.wxl.cqiemutualselection.exception.BusinessException;
import org.wxl.cqiemutualselection.mapper.TbClassMapper;
import org.wxl.cqiemutualselection.mapper.UserMapper;
import org.wxl.cqiemutualselection.service.ICompaniesService;
import org.wxl.cqiemutualselection.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.wxl.cqiemutualselection.utils.BeanCopyUtils;
import org.wxl.cqiemutualselection.utils.MD5Util;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 16956
 * @since 2023-10-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private ICompaniesService companiesService;
    @Resource
    private TbClassMapper classMapper;
    @Resource
    private UserMapper userMapper;


    @Override
    public boolean userLogin(LoginDTO loginDTO) {
        if (loginDTO.getType() == null){
            throw new BusinessException(ErrorCode.NULL_ERROR,"请选择登录账户类型");
        }
        // 1.选择的登录类型 ———— 用户
        if (loginDTO.getType() == UserConstant.LOGIN_TYPE_USER){
            // 1.1 判断账号密码是否为空
            judgeUserInfo(loginDTO.getUserAccount(),loginDTO.getPassword());
            // 1.2 对密码进行加密处理
            String password = UserConstant.USER_PASSWORD_SALT + MD5Util.string2MD5(loginDTO.getPassword());
            // 1.3 检查账号密码是否正确
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.select(User::getId,User::getUserAccount,User::getPassword)
                    .eq(User::getUserAccount,loginDTO.getUserAccount())
                    .eq(User::getPassword,password);
            User user = this.getOne(queryWrapper);
            // 1.4 判断是否登录成功
            // 1.4.1 如果user为空，则登录失败
            if (user == null){
                throw new BusinessException(ErrorCode.SYSTEM_ERROR,"请注册账号");
            }
            // 1.4.2 如果user不为空，则登录成功保存在Session中
            StpUtil.login(user.getId());
            return true;
        }

        // 2.选择的登录类型 ———— 企业
        if (loginDTO.getType() == UserConstant.LOGIN_TYPE_COMPANIES){
            // 1.1 判断账号密码是否为空
            judgeUserInfo(loginDTO.getUserAccount(),loginDTO.getPassword());
            // 1.2 企业登录
            return companiesService.companiesLogin(loginDTO.getCompaniesAccount(),loginDTO.getPassword());
        }
        return false;
    }

    @Override
    public void saveByExcel(List<UserExcelDTO> cachedDataList) {
        if (cachedDataList == null ||cachedDataList.size() == 0){
            throw new BusinessException(ErrorCode.NULL_ERROR,"请检查文件是否为空");
        }
        LambdaQueryWrapper<TbClass> queryWrapper = new LambdaQueryWrapper<>();
        //1.将Excel中的UserExcelDTO类型的数据转换为User类型的数据
        List<User> userList = cachedDataList.stream().map(userExcelDTO -> {
            //1.1 复制UserExcelDTO类型的数据为User类型
            User user = BeanCopyUtils.copyBean(userExcelDTO, User.class);
            //1.2 如果性别为空则默认为男
            if (!StringUtils.isBlank(userExcelDTO.getGender())){
                //1.3.1 如果性别为男，则存入0,反之则为1
                if (UserConstant.GENDER_BOY.equals(userExcelDTO.getGender())){
                    user.setGender(UserConstant.GENDER_BOY_SQL);
                }else {
                    user.setGender(UserConstant.GENDER_GIRL_SQL);
                }
            }else{
                user.setGender(UserConstant.GENDER_BOY_SQL);
            }
            //1.3 如果身份为空，则默认为学生
            if (!StringUtils.isBlank(userExcelDTO.getRole())){
                //1.3.1 如果身份为学生，则存入0,反之则为1
                if (UserConstant.ROLE_STUDENT.equals(userExcelDTO.getRole())){
                    user.setType(UserConstant.ROLE_STUDENT_SQL);
                }else {
                    user.setType(UserConstant.ROLE_TEACHER_SQL);
                }
            }else{
                user.setType(UserConstant.ROLE_STUDENT_SQL);
            }
            //1.4 如果身份是学生则需要查询班级id
            if (UserConstant.ROLE_STUDENT.equals(userExcelDTO.getRole())){
                queryWrapper.select(TbClass::getId)
                        .eq(TbClass::getClassName,userExcelDTO.getClassName());
                TbClass tbClass = classMapper.selectOne(queryWrapper);
                user.setClassId(tbClass.getId());
            }
            //1.5 设置默认密码为身份证后六位
            if (StringUtils.isNotBlank(userExcelDTO.getIdentityNumber())){
                String password = userExcelDTO.getIdentityNumber().substring(userExcelDTO.getIdentityNumber().length() - 6);
                user.setPassword(UserConstant.USER_PASSWORD_SALT + MD5Util.string2MD5(password));
            }
            user.setUserName(userExcelDTO.getRealName());
            user.setBirthday(LocalDateTime.ofInstant(userExcelDTO.getBirthday().toInstant(), ZoneId.systemDefault()));
            return user;
        }).collect(Collectors.toList());
        //2、 将数据批量存入数据库中
        boolean b = this.saveBatch(userList);
        if (!b){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"导入excel失败");
        }
    }

    @Override
    public List<UserExcelDTO> excludeUser(List<UserExcelDTO> cachedDataList) {
        if (cachedDataList == null ||cachedDataList.size() == 0){
            throw new BusinessException(ErrorCode.NULL_ERROR,"请检查文件是否为空");
        }
        //1. 将所有学号存入集合中
        List<String> userAccountList = new ArrayList<>(cachedDataList.size());
        //2. 筛选出学号为空，真实姓名为空的数据
        List<UserExcelDTO> collect = cachedDataList.stream().filter(userExcelDTO -> {
            if (StringUtils.isBlank(userExcelDTO.getUserAccount()) || StringUtils.isBlank(userExcelDTO.getRealName())) {
                return false;
            }
            userAccountList.add(userExcelDTO.getUserAccount());
            return true;
        }).collect(Collectors.toList());
        if (userAccountList.size() == 0){
            return null;
        }
        //3.根据Excel中的学号查询数据库中是否存在相同的数据
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getUserAccount)
                .in(User::getUserAccount,userAccountList);
        List<User> users = userMapper.selectList(queryWrapper);
        //如果数据库中存在相同的数据，则排除
        if (users.size() != 0){
            collect = collect.parallelStream().filter(userExcelDTO -> {
                for (User user : users) {
                    if (user.getUserAccount().equals(userExcelDTO.getUserAccount())) {
                        return false;
                    }
                }
                return true;
            }).collect(Collectors.toList());
        }
        return collect;
    }


    /**
     * 判断账号密码是否为空
     *
     * @param userAccount 账号
     * @param password 密码
     */
    private void judgeUserInfo(String userAccount,String password){
        if (StringUtils.isBlank(userAccount)){
            throw new BusinessException(ErrorCode.NULL_ERROR,"请输入账号");
        }
        if (StringUtils.isBlank(password)){
            throw new BusinessException(ErrorCode.NULL_ERROR,"请输入密码");
        }
    }
}
