package org.wxl.cqiemutualselection.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 16956
 * @since 2023-10-12
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_companies")
@ApiModel(value="Companies对象", description="")
public class Companies implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "企业主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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

    @ApiModelProperty(value = "审核通过")
    private Integer process;
    @ApiModelProperty(value = "录入时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除")
    private Integer isDelete;


}
