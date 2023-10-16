package org.wxl.cqiemutualselection.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Component;
import org.wxl.cqiemutualselection.contant.UserConstant;
import org.wxl.cqiemutualselection.domain.entity.User;
import org.wxl.cqiemutualselection.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限加载接口实现类
 * @Component 保证此类被 SpringBoot 扫描，完成 Sa-Token 的自定义权限验证扩展
 * @author 16956
 */
@Component
public class StpInterfaceImpl implements StpInterface {
@Resource
private UserMapper userMapper;

    /**
     * 返回一个账号所拥有的权限码集合 
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询权限
        List<String> list = new ArrayList<String>();
//        list.add("101");
//        list.add("user.add");
//        list.add("user.update");
//        list.add("user.get");
//        // list.add("user.delete");
//        list.add("art.*");
        return list;
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 本 list 仅做模拟，实际项目中要根据具体业务逻辑来查询角色
        List<String> list = new ArrayList<String>();
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getType)
                .eq(User::getId,(Long)loginId);
        User user = userMapper.selectOne(queryWrapper);
        if (UserConstant.ROLE_STUDENT_SQL == user.getType()){
            list.add("student");
        }

        if (UserConstant.ROLE_TEACHER_SQL == user.getType()){
            list.add("teacher");
        }

        if (UserConstant.ROLE_COMPANIES_SQL == user.getType()){
            list.add("companies");
        }
        if (UserConstant.ROLE_ADMIN_SQL == user.getType()){
            list.add("admin");
        }
        return list;
    }

}
