package com.dxt.ht.svc.apply.entity;


import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 故障信息子节点
 *
 * @author wangfanglin
 * @since 2020/04/22
 */
public class AssessList implements Parameter{
    /**故障大类ID*/
    private String deviceFault;
    /**故障小类ID*/
    private String deviceSubfault;
    /**维修方案ID*/
    private String repairPlan;
    /**建议维修价格*/
    private String planAmt;
    /**实际金额 默认0*/
    private String realAmt;


    public AssessList(String deviceFault, String deviceSubfault, String repairPlan, String planAmt){
        this.deviceFault = deviceFault;
        this.deviceSubfault = deviceSubfault;
        this.repairPlan = repairPlan;
        this.planAmt = planAmt;
    }


    public String getDeviceFault() {
        return deviceFault;
    }

    public String getDeviceSubfault() {
        return deviceSubfault;
    }

    public String getRepairPlan() {
        return repairPlan;
    }

    public String getPlanAmt() {
        return planAmt;
    }

    public String getRealAmt() {
        return realAmt;
    }

    public void setRealAmt(String realAmt) {
        this.realAmt = realAmt;
    }

    @Override
    public boolean checkMandatory() {
        return !StringUtils.isEmpty(deviceFault) && !StringUtils.isEmpty(deviceSubfault)
                && StringUtils.isEmpty(repairPlan) && StringUtils.isEmpty(planAmt);
    }
}
