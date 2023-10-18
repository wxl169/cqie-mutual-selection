package org.wxl.cqiemutualselection.domain.vo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author 16956
 */
@Data
public class CurrentUserVO {
    @ApiModelProperty(value = "用户主键")
    private Long id;

    @ApiModelProperty(value = "0:学生，1：辅导员，2：管理员,3:企业")
    private Integer type;

    @ApiModelProperty(value = "学号")
    private String userAccount;

    @ApiModelProperty(value = "用户名")
    private String userName;


    @ApiModelProperty(value = "头像")
    private String avatar;

}
