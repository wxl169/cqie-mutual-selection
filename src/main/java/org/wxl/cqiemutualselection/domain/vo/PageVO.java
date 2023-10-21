package org.wxl.cqiemutualselection.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 16956
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO {
    private Object rows;
    private Long total;
}
