package org.wxl.cqiemutualselection.contant;


/**
 * 用户模块常量
 * @author 16956
 */
public interface UserConstant {
    /**
     * 登录类型为用户
     */
    int LOGIN_TYPE_USER = 0;

    /**
     * 登录类型为企业
     */
    int LOGIN_TYPE_COMPANIES = 1;

    /**
     * 用户账号加密盐值
     */
    String USER_PASSWORD_SALT = "WXL_USER";

    /**
     * 性别为男
     */
    String GENDER_BOY = "男";
    int GENDER_BOY_SQL = 0;

    /**
     * 性别为女
     */
    String GENDER_GIRL= "女";
    int GENDER_GIRL_SQL = 1;

    /**
     * 身份：学生
     */
    String ROLE_STUDENT = "学生";
    int ROLE_STUDENT_SQL = 0;

    /**
     * 身份：老师
     */
    String ROLE_TEACHER = "老师";
    int ROLE_TEACHER_SQL = 1;
    /**
     * 身份：企业
     */
    String ROLE_COMPANIES = "企业";
    int ROLE_COMPANIES_SQL = 3;

    /**
     * 身份：管理员
     */
    String ROLE_ADMIN = "管理员";
    int ROLE_ADMIN_SQL = 2;

    /**
     * 用户状态：未删除
     */
    int NOT_IS_DELETE = 0;

}
