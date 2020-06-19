package com.dxt.ht.svc.config;

import java.lang.annotation.*;

/**
 * @ProjectName: airline
 * @Package: com.ticket.airline.config
 * @Description: java类作用描述
 * @Author: Z.G.C
 * @CreateDate: 2019/9/20 5:33 PM
 * @UpdateUser: Z.G.C
 * @UpdateDate: 2019/9/20 5:33 PM
 * @UpdateRemark: The modified content
 * @Version: 1.0
 * <p>Copyright: Copyright (c) 2019</p>
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Encrypt {
}
