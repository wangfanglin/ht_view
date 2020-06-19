package com.dxt.ht.svc.filters.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * @ProjectName: airline
 * @Package: com.ticket.airline.utils
 * @Description: java类作用描述
 * @Author: Z.G.C
 * @CreateDate: 2019/11/19 2:51 PM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/11/19 2:51 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public JsonUtils() {
    }

    public static String objectToJson(Object o) {
        try {
            return mapper.writeValueAsString(o);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static Map jsonToMap(String json) {
        try {
            return (Map)mapper.readValue(json, Map.class);
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }
}
