package com.dxt.ht.svc.form.entity.vo;

import com.dxt.ht.svc.form.entity.HtClaimSettlementForm;
import lombok.Data;

import java.util.List;

/***
 * 在线理赔VO   viceParts、masterUnit两个字段是通过","来分隔数据的，所以需要用List来操作数据
 *
 * @author wangfanglin
 * @date 2020/04/10
 */
@Data
public class HtClaimSettlementFormVO extends HtClaimSettlementForm {

    /**副部件*/
    private List<String> vicePartsList;

    /**主部件*/
    private List<String> masterUnitList;

    /**副部件名称*/
    private List<String> vicePartsNameList;

    /**主部件名称*/
    private List<String> masterUnitNameList;
}
