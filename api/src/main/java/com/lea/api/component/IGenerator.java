package com.lea.api.component;

import java.util.Collection;
import java.util.List;

public interface IGenerator {

    /**
     * @Description: 单个对象的深度复制及类型转换，vo/domain , po
     * @param s 数据对象
     * @param clz 复制目标类型
     * @return
     * @author banjuer@outlook.com
     * @Time 2018年5月9日 下午3:53:24
     */
    <T, S> T convert(S s, Class<T> clz);


    <T, S> List<T> convert(Collection<S> sCollection, Class<T> clz);


}
