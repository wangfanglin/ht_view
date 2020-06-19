package com.dxt.ht.svc.common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.beanutils.PropertyUtils;

public class comment {

    /**
     * 转成map对象
     *
     * @return
     */
    public Map map() {
        try {
            Map map = PropertyUtils.describe(this);
            map.remove("class");
            Iterator<Map.Entry> itera = map.entrySet().iterator();
            Map.Entry entry = null;
            while (itera.hasNext()) {
                entry = itera.next();
                if (Objects.isNull(entry.getValue()) || StringUtils.isEmpty(entry.getValue().toString())) {
                    itera.remove();
                }
            }
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
}
