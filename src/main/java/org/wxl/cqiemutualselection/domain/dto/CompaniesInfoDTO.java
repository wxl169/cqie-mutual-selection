package org.wxl.cqiemutualselection.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 16956
 */
@Data
public class CompaniesInfoDTO {
    @ApiModelProperty(value = "企业名称")
    private String companiesName;

    @ApiModelProperty(value = "企业全称")
    private String companiesFullName;

    @ApiModelProperty(value = "企业地址（存入json格式数据，存多个地址）")
    private String address;

    @ApiModelProperty(value = "行业")
    private String industry;

    @ApiModelProperty(value = "成立日期")
    private LocalDateTime foundingTime;

    @ApiModelProperty(value = "注册资产")
    private Double registerAssets;

    @ApiModelProperty(value = "企业类型")
    private String companiesType;

    @ApiModelProperty(value = "企业介绍")
    private String companiesIntroduce;

    @ApiModelProperty(value = "企业人数")
    private Integer peopleNumber;
    @ApiModelProperty(value = "企业邮箱")
    private String companiesEmail;

    @ApiModelProperty(value = "密码")
    private String password;
}
