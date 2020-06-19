package com.dxt.ht.svc.form.entity.vo;


import com.dxt.ht.svc.form.entity.HtClaimSettlementForm;
import com.dxt.ht.svc.form.entity.HtHistory;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 工单进度VO
 *
 * @author wangfanglin
 * @date 2020/04/10
 */
@Data
public class FormProgressVO {

    /**工单进度信息*/
    private List<HtHistory> hisList;

    /**副部件*/
    private List<String> viceParts;

    /**主部件*/
    private List<String> masterUnit;

    /**在线理赔对象*/
    private HtClaimSettlementForm htClaimSettlementForm;

    //顺丰邮寄状态
    private String sfStatus;
    //全损状态
    private String dudStatus;
    //支付状态
    private String payStatus;

    //全损工单的审核状态
    private String auditStatus;


    @ApiModelProperty(value = "自付款额度")
    private BigDecimal deductiblesLimit;

    private String formId;

    private String createDate;

}
