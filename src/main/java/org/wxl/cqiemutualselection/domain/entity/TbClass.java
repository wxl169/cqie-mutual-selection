package org.wxl.cqiemutualselection.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
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
 *   班级
 * </p>
 *
 * @author 16956
 * @since 2023-10-13
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_class")
@ApiModel(value="TbClass对象", description="")
public class TbClass implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "班级主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "班级名")
    @TableField("class_name")
    private String className;

    @ApiModelProperty(value = "班级人数")
    @TableField("class_number")
    private Integer classNumber;

    @ApiModelProperty(value = "辅导员id")
    @TableField("user_id")
    private Long userId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "逻辑删除")
    @TableField("is_delete")
    private Integer isDelete;


}
