package org.wxl.cqiemutualselection.domain.dto;

import cn.hutool.core.date.DatePattern;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author 16956
 */
@Data
public class UserExcelDTO {

    @ExcelProperty(value = "学号",index = 0)
    private String userAccount;
    @ExcelProperty(value = "姓名",index = 1)
    private String realName;
    @ExcelProperty(value = "性别",index = 2)
    private String gender;
    @ExcelProperty(value = "身份证号",index = 3)
    private String identityNumber;
    @ExcelProperty(value = "手机号",index = 4)
    private String phone;
    @ExcelProperty(value = "班级",index = 5)
    private String className;
    @ExcelProperty(value = "出生日期",index = 6)
    @DateTimeFormat(value = DatePattern.NORM_DATE_PATTERN)
    private Date birthday;
    @ExcelProperty(value = "身份",index = 7)
    private String role;

}
