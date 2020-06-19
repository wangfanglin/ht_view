package com.dxt.ht.svc.sf.other;

/**
 *
 * 该类表示调用 《顺丰下单接口》时
 * 返回的结果
 *
 * 如果想要查询具体的参数说明
 * 请参考该网址：https://qiao.sf-express.com/pages/developDoc/index.html?level2=460077
 *
 * create by unreal-tc
 * 2019/11/27
 *
 */
public class PlaceOrderResult {
    private String orderid;
    private String mailno;
    private String return_tracking_no;
    private String origincode;
    private String destcode;
    private Integer filter_result;
    private String remark;
    private String mapping_mark;
    private String isUpstairs;
    private String isSpecialWarehouseService;
    private RlsInfo rls_info;


    public String getOrderid() {
        return orderid;
    }

    public String getMailno() {
        return mailno;
    }

    public String getReturn_tracking_no() {
        return return_tracking_no;
    }

    public String getOrigincode() {
        return origincode;
    }

    public String getDestcode() {
        return destcode;
    }

    public Integer getFilter_result() {
        return filter_result;
    }

    public String getRemark() {
        return remark;
    }

    public String getMapping_mark() {
        return mapping_mark;
    }

    public String getIsUpstairs() {
        return isUpstairs;
    }

    public String getIsSpecialWarehouseService() {
        return isSpecialWarehouseService;
    }

    public RlsInfo getRls_info() {
        return rls_info;
    }

    @Override
    public String toString() {
        return "PlaceOrderResult{" +
                "orderid='" + orderid + '\'' +
                ", mailno='" + mailno + '\'' +
                ", return_tracking_no='" + return_tracking_no + '\'' +
                ", origincode='" + origincode + '\'' +
                ", destcode='" + destcode + '\'' +
                ", filter_result=" + filter_result +
                ", remark='" + remark + '\'' +
                ", mapping_mark='" + mapping_mark + '\'' +
                ", isUpstairs='" + isUpstairs + '\'' +
                ", isSpecialWarehouseService='" + isSpecialWarehouseService + '\'' +
                ", rls_info=" + rls_info +
                '}';
    }

    class RlsInfo{
        private String invoke_result;
        private String rls_code;
        private String errorDesc;
        private RlsDetail rls_detail;

        public String getInvoke_result() {
            return invoke_result;
        }

        public String getRls_code() {
            return rls_code;
        }

        public String getErrorDesc() {
            return errorDesc;
        }

        public RlsDetail getRls_detail() {
            return rls_detail;
        }

        @Override
        public String toString() {
            return "RlsInfo{" +
                    "invoke_result='" + invoke_result + '\'' +
                    ", rls_code='" + rls_code + '\'' +
                    ", errorDesc='" + errorDesc + '\'' +
                    ", rls_detail=" + rls_detail +
                    '}';
        }
    }

    class RlsDetail{
        private String waybillNo;
        private String sourceTransferCode;
        private String sourceCityCode;
        private String sourceDeptCode;
        private String sourceTeamCode;
        private String destCityCode;
        private String destDeptCode;
        private String destDeptCodeMapping;
        private String destTeamCode;
        private String destTeamCodeMapping;
        private String destTransferCode;
        private String destRouteLabel;
        private String proName;
        private String cargoTypeCode;
        private String limitTypeCode;
        private String expressTypeCode;
        private String codingMapping;
        private String codingMappingOut;
        private String xbFlag;
        private String printFlag;
        private String twoDimensionCode;
        private String proCode;
        private String printIcon;
        private String abFlag;
        private String errMsg;
        private String destPortCode;
        private String destCountry;
        private String destPostCode;
        private String goodsValueTotal;
        private String currencySymbol;
        private String goodsNumber;
        private String checkCode;


        public String getWaybillNo() {
            return waybillNo;
        }

        public String getSourceTransferCode() {
            return sourceTransferCode;
        }

        public String getSourceCityCode() {
            return sourceCityCode;
        }

        public String getSourceDeptCode() {
            return sourceDeptCode;
        }

        public String getSourceTeamCode() {
            return sourceTeamCode;
        }

        public String getDestCityCode() {
            return destCityCode;
        }

        public String getDestDeptCode() {
            return destDeptCode;
        }

        public String getDestDeptCodeMapping() {
            return destDeptCodeMapping;
        }

        public String getDestTeamCode() {
            return destTeamCode;
        }

        public String getDestTeamCodeMapping() {
            return destTeamCodeMapping;
        }

        public String getDestTransferCode() {
            return destTransferCode;
        }

        public String getDestRouteLabel() {
            return destRouteLabel;
        }

        public String getProName() {
            return proName;
        }

        public String getCargoTypeCode() {
            return cargoTypeCode;
        }

        public String getLimitTypeCode() {
            return limitTypeCode;
        }

        public String getExpressTypeCode() {
            return expressTypeCode;
        }

        public String getCodingMapping() {
            return codingMapping;
        }

        public String getCodingMappingOut() {
            return codingMappingOut;
        }

        public String getXbFlag() {
            return xbFlag;
        }

        public String getPrintFlag() {
            return printFlag;
        }

        public String getTwoDimensionCode() {
            return twoDimensionCode;
        }

        public String getProCode() {
            return proCode;
        }

        public String getPrintIcon() {
            return printIcon;
        }

        public String getAbFlag() {
            return abFlag;
        }

        public String getErrMsg() {
            return errMsg;
        }

        public String getDestPortCode() {
            return destPortCode;
        }

        public String getDestCountry() {
            return destCountry;
        }

        public String getDestPostCode() {
            return destPostCode;
        }

        public String getGoodsValueTotal() {
            return goodsValueTotal;
        }

        public String getCurrencySymbol() {
            return currencySymbol;
        }

        public String getGoodsNumber() {
            return goodsNumber;
        }

        public String getCheckCode() {
            return checkCode;
        }

        @Override
        public String toString() {
            return "RlsDetail{" +
                    "waybillNo='" + waybillNo + '\'' +
                    ", sourceTransferCode='" + sourceTransferCode + '\'' +
                    ", sourceCityCode='" + sourceCityCode + '\'' +
                    ", sourceDeptCode='" + sourceDeptCode + '\'' +
                    ", sourceTeamCode='" + sourceTeamCode + '\'' +
                    ", destCityCode='" + destCityCode + '\'' +
                    ", destDeptCode='" + destDeptCode + '\'' +
                    ", destDeptCodeMapping='" + destDeptCodeMapping + '\'' +
                    ", destTeamCode='" + destTeamCode + '\'' +
                    ", destTeamCodeMapping='" + destTeamCodeMapping + '\'' +
                    ", destTransferCode='" + destTransferCode + '\'' +
                    ", destRouteLabel='" + destRouteLabel + '\'' +
                    ", proName='" + proName + '\'' +
                    ", cargoTypeCode='" + cargoTypeCode + '\'' +
                    ", limitTypeCode='" + limitTypeCode + '\'' +
                    ", expressTypeCode='" + expressTypeCode + '\'' +
                    ", codingMapping='" + codingMapping + '\'' +
                    ", codingMappingOut='" + codingMappingOut + '\'' +
                    ", xbFlag='" + xbFlag + '\'' +
                    ", printFlag='" + printFlag + '\'' +
                    ", twoDimensionCode='" + twoDimensionCode + '\'' +
                    ", proCode='" + proCode + '\'' +
                    ", printIcon='" + printIcon + '\'' +
                    ", abFlag='" + abFlag + '\'' +
                    ", errMsg='" + errMsg + '\'' +
                    ", destPortCode='" + destPortCode + '\'' +
                    ", destCountry='" + destCountry + '\'' +
                    ", destPostCode='" + destPostCode + '\'' +
                    ", goodsValueTotal='" + goodsValueTotal + '\'' +
                    ", currencySymbol='" + currencySymbol + '\'' +
                    ", goodsNumber='" + goodsNumber + '\'' +
                    ", checkCode='" + checkCode + '\'' +
                    '}';
        }
    }


}
