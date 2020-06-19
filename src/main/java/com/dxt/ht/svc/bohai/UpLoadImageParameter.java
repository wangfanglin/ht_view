package com.dxt.ht.svc.bohai;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 *  影像上传入参
 * @author tangchao
 */
@Data
public class UpLoadImageParameter {
    private String base64;                      //BASE64字符串
    private String clmNo;                       //渤海案件号
    private final String imgType = "0";         //固定为0
    private String openId;                      //与案件号相同

    public UpLoadImageParameter(String base64, String clmNo, String openId) {
        this.base64 = base64;
        this.clmNo = clmNo;
        this.openId = openId;
    }

}
