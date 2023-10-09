package org.wxl.cqiemutualselection.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 实体类copy
 * @author 16956
 */
public class BeanCopyUtils {
    private BeanCopyUtils(){}

    public static <V> V copyBean(Object source,Class<V> clazz){
        //创建目标对象
        V  result = null;
        try{
            result = clazz.newInstance();
            //实现属性Copy
            BeanUtils.copyProperties(source,result);
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回结果
        return result;
    }

    public static <O,V> List<V> copyBeanList(List<O>list,Class<V> clazz){
        return list.stream().map(o->copyBean(o,clazz))
                .collect(Collectors.toList());
    }

}
