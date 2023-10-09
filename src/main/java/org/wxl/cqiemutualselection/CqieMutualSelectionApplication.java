package org.wxl.cqiemutualselection;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 16956
 */
@SpringBootApplication
@MapperScan("org.wxl.cqiemutualselection.mapper")
public class CqieMutualSelectionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CqieMutualSelectionApplication.class, args);
    }

}
