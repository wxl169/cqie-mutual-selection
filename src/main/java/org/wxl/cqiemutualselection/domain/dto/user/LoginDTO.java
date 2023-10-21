package org.wxl.cqiemutualselection.domain.dto.user;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author 16956
 */
@Data
@ApiModel(value="用户或企业登录对象")
public class LoginDTO {

    @ApiModelProperty(value = "学号")
    private String userAccount;
    @ApiModelProperty(value = "企业账号")
    private String companiesAccount;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "企业或用户",notes = "0：用户，1：企业")
    private Integer type;


}
