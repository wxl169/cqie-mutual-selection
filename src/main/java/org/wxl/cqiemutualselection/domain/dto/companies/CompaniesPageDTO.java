package org.wxl.cqiemutualselection.domain.dto.companies;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author 16956
 */
@Data
public class CompaniesPageDTO {
    @ApiModelProperty(value = "企业全称")
    private String companiesFullName;

    @ApiModelProperty(value = "行业")
    private String industry;

    @ApiModelProperty(value = "企业类型")
    private String companiesType;

    @ApiModelProperty(value = "审核通过")
    private Integer process;

}
