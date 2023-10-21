package org.wxl.cqiemutualselection.domain.vo.companies;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 16956
 */
@Data
public class CompaniesInfoVO {
    @ApiModelProperty(value = "企业主键")
    private Long id;

    @ApiModelProperty(value = "企业全称")
    private String companiesFullName;

    @ApiModelProperty(value = "企业地址（存入json格式数据，存多个地址）")
    private String address;

    @ApiModelProperty(value = "行业")
    private String industry;

    @ApiModelProperty(value = "成立日期")
    private LocalDateTime foundingTime;

    @ApiModelProperty(value = "企业类型")
    private String companiesType;

    @ApiModelProperty(value = "企业人数")
    private Integer peopleNumber;

    @ApiModelProperty(value = "审核通过")
    private Integer process;
}
