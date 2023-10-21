package org.wxl.cqiemutualselection.controller;

import cn.dev33.satoken.annotation.SaCheckRole;
import com.alibaba.excel.EasyExcel;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.wxl.cqiemutualselection.common.BaseResponse;
import org.wxl.cqiemutualselection.common.ResultUtils;
import org.wxl.cqiemutualselection.domain.dto.user.UserExcelDTO;
import org.wxl.cqiemutualselection.listener.DemoDataListener;
import org.wxl.cqiemutualselection.service.IUserService;

import javax.annotation.Resource;
import java.io.BufferedInputStream;
import java.io.IOException;

/**
 * @author 16956
 */
@RestController
@ApiOperation("导入ExeclDemo")
@RequestMapping("/import")
public class ExcelController {
    @Resource
    private IUserService userService;


    /**
     * 从Excel中导入学生和辅导员的数据
     * @param file excel文件
     * @return 是否导入成功
     */
    @SaCheckRole("admin")
    @PostMapping("/userInfo")
    public BaseResponse importEmpData(@RequestParam("file")MultipartFile file){
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(file.getInputStream());
            EasyExcel.read(in, UserExcelDTO.class, new DemoDataListener(userService)).sheet().doRead();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return ResultUtils.success("导入成功！");
    }
}
