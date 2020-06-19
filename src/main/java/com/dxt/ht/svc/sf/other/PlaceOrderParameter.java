package com.dxt.ht.svc.sf.other;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * 该类表示调用 《顺丰下单接口》时
 * 所需传入的参数
 *
 * 如果想要查询具体的参数说明
 * 请参考该网址：https://qiao.sf-express.com/pages/developDoc/index.html?level2=460077
 *
 * create by unreal-tc
 * 2019/11/27
 *
 */
@XStreamAlias("Order")
public class PlaceOrderParameter {
    @XStreamAsAttribute
    private String orderid;
    @XStreamAsAttribute
    private String mailno;
    @XStreamAsAttribute
    private String j_company;
    @XStreamAsAttribute
    private String j_contact;
    @XStreamAsAttribute
    private String j_tel;
    @XStreamAsAttribute
    private String j_mobile;
    @XStreamAsAttribute
    private String j_province;
    @XStreamAsAttribute
    private String j_city;
    @XStreamAsAttribute
    private String j_county;
    @XStreamAsAttribute
    private String j_address;
    @XStreamAsAttribute
    private String d_company;
    @XStreamAsAttribute
    private String d_contact;
    @XStreamAsAttribute
    private String d_tel;
    @XStreamAsAttribute
    private String d_mobile;
    @XStreamAsAttribute
    private String d_province;
    @XStreamAsAttribute
    private String d_city;
    @XStreamAsAttribute
    private String d_county;
    @XStreamAsAttribute
    private String d_address;
    @XStreamAsAttribute
    private String custid;
    @XStreamAsAttribute
    private Integer pay_method;
    @XStreamAsAttribute
    private String express_type;
    @XStreamAsAttribute
    private Integer parcel_quantity;
    @XStreamAsAttribute
    private BigDecimal cargo_length;
    @XStreamAsAttribute
    private BigDecimal cargo_width;
    @XStreamAsAttribute
    private BigDecimal cargo_height;
    @XStreamAsAttribute
    private BigDecimal volume;
    @XStreamAsAttribute
    private BigDecimal cargo_total_weight;
    @XStreamAsAttribute
    private Date sendstarttime;
    @XStreamAsAttribute
    private Integer is_docall;
    @XStreamAsAttribute
    private String need_return_tracking_no;
    @XStreamAsAttribute
    private String return_tracking;
    @XStreamAsAttribute
    private Integer temp_range;
    @XStreamAsAttribute
    private String template;
    @XStreamAsAttribute
    private String remark;
    @XStreamAsAttribute
    private Integer oneself_pickup_flg;
    @XStreamAsAttribute
    private String special_delivery_type_code;
    @XStreamAsAttribute
    private String special_delivery_value;
    @XStreamAsAttribute
    private String realname_num;
    @XStreamAsAttribute
    private Integer routelabelForReturn;
    @XStreamAsAttribute
    private Integer routelabelService;
    @XStreamAsAttribute
    private Integer is_unified_waybill_no;
    private Cargo Cargo;
    private AddedService AddedService;
    /**
     *  该构造方法内所有参数是必传字段
     *
     * @param orderid       客户订单号,建议英文字母+YYMMDD(日期)+流水号,如:TB1207300000001
     * @param j_address     寄件方详细地址,包括省市区,示例:“广东省深圳市福田区新洲十一街万基商务大厦10楼” ,如果需要生成电子面单,则必填。
     * @param d_company     到件方公司名称
     * @param d_contact     到件方联系人
     * @param d_tel         到件方联系电话
     * @param d_address     到件方详细地址,如果不传输d_province/d_city字段,此详细地址需包含省市信息,以提高地址识别的成功率,示例:“广东省深圳市福田区新洲十一街万基商务大厦10楼”。
     */
    public PlaceOrderParameter(String orderid, String j_address, String d_company, String d_contact, String d_tel, String d_address,Cargo cargo) {
        this.orderid = orderid;
        this.j_address = j_address;
        this.d_company = d_company;
        this.d_contact = d_contact;
        this.d_tel = d_tel;
        this.d_address = d_address;
        this.Cargo = cargo;
    }


    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getMailno() {
        return mailno;
    }

    public void setMailno(String mailno) {
        this.mailno = mailno;
    }

    public String getJ_company() {
        return j_company;
    }

    public void setJ_company(String j_company) {
        this.j_company = j_company;
    }

    public String getJ_contact() {
        return j_contact;
    }

    public void setJ_contact(String j_contact) {
        this.j_contact = j_contact;
    }

    public String getJ_tel() {
        return j_tel;
    }

    public void setJ_tel(String j_tel) {
        this.j_tel = j_tel;
    }

    public String getJ_mobile() {
        return j_mobile;
    }

    public void setJ_mobile(String j_mobile) {
        this.j_mobile = j_mobile;
    }

    public String getJ_province() {
        return j_province;
    }

    public void setJ_province(String j_province) {
        this.j_province = j_province;
    }

    public String getJ_city() {
        return j_city;
    }

    public void setJ_city(String j_city) {
        this.j_city = j_city;
    }

    public String getJ_county() {
        return j_county;
    }

    public void setJ_county(String j_county) {
        this.j_county = j_county;
    }

    public String getJ_address() {
        return j_address;
    }

    public void setJ_address(String j_address) {
        this.j_address = j_address;
    }

    public String getD_company() {
        return d_company;
    }

    public void setD_company(String d_company) {
        this.d_company = d_company;
    }

    public String getD_contact() {
        return d_contact;
    }

    public void setD_contact(String d_contact) {
        this.d_contact = d_contact;
    }

    public String getD_tel() {
        return d_tel;
    }

    public void setD_tel(String d_tel) {
        this.d_tel = d_tel;
    }

    public String getD_mobile() {
        return d_mobile;
    }

    public void setD_mobile(String d_mobile) {
        this.d_mobile = d_mobile;
    }

    public String getD_province() {
        return d_province;
    }

    public void setD_province(String d_province) {
        this.d_province = d_province;
    }

    public String getD_city() {
        return d_city;
    }

    public void setD_city(String d_city) {
        this.d_city = d_city;
    }

    public String getD_county() {
        return d_county;
    }

    public void setD_county(String d_county) {
        this.d_county = d_county;
    }

    public String getD_address() {
        return d_address;
    }

    public void setD_address(String d_address) {
        this.d_address = d_address;
    }

    public String getCustid() {
        return custid;
    }

    public void setCustid(String custid) {
        this.custid = custid;
    }

    public Integer getPay_method() {
        return pay_method;
    }

    public void setPay_method(Integer pay_method) {
        this.pay_method = pay_method;
    }

    public String getExpress_type() {
        return express_type;
    }

    public void setExpress_type(String express_type) {
        this.express_type = express_type;
    }

    public Integer getParcel_quantity() {
        return parcel_quantity;
    }

    public void setParcel_quantity(Integer parcel_quantity) {
        this.parcel_quantity = parcel_quantity;
    }

    public BigDecimal getCargo_length() {
        return cargo_length;
    }

    public void setCargo_length(BigDecimal cargo_length) {
        this.cargo_length = cargo_length;
    }

    public BigDecimal getCargo_width() {
        return cargo_width;
    }

    public void setCargo_width(BigDecimal cargo_width) {
        this.cargo_width = cargo_width;
    }

    public BigDecimal getCargo_height() {
        return cargo_height;
    }

    public void setCargo_height(BigDecimal cargo_height) {
        this.cargo_height = cargo_height;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getCargo_total_weight() {
        return cargo_total_weight;
    }

    public void setCargo_total_weight(BigDecimal cargo_total_weight) {
        this.cargo_total_weight = cargo_total_weight;
    }

    public Date getSendstarttime() {
        return sendstarttime;
    }

    public void setSendstarttime(Date sendstarttime) {
        this.sendstarttime = sendstarttime;
    }

    public Integer getIs_docall() {
        return is_docall;
    }

    public void setIs_docall(Integer is_docall) {
        this.is_docall = is_docall;
    }

    public String getNeed_return_tracking_no() {
        return need_return_tracking_no;
    }

    public void setNeed_return_tracking_no(String need_return_tracking_no) {
        this.need_return_tracking_no = need_return_tracking_no;
    }

    public String getReturn_tracking() {
        return return_tracking;
    }

    public void setReturn_tracking(String return_tracking) {
        this.return_tracking = return_tracking;
    }

    public Integer getTemp_range() {
        return temp_range;
    }

    public void setTemp_range(Integer temp_range) {
        this.temp_range = temp_range;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getOneself_pickup_flg() {
        return oneself_pickup_flg;
    }

    public void setOneself_pickup_flg(Integer oneself_pickup_flg) {
        this.oneself_pickup_flg = oneself_pickup_flg;
    }

    public String getSpecial_delivery_type_code() {
        return special_delivery_type_code;
    }

    public void setSpecial_delivery_type_code(String special_delivery_type_code) {
        this.special_delivery_type_code = special_delivery_type_code;
    }

    public String getSpecial_delivery_value() {
        return special_delivery_value;
    }

    public void setSpecial_delivery_value(String special_delivery_value) {
        this.special_delivery_value = special_delivery_value;
    }

    public String getRealname_num() {
        return realname_num;
    }

    public void setRealname_num(String realname_num) {
        this.realname_num = realname_num;
    }

    public Integer getRoutelabelForReturn() {
        return routelabelForReturn;
    }

    public void setRoutelabelForReturn(Integer routelabelForReturn) {
        this.routelabelForReturn = routelabelForReturn;
    }

    public Integer getRoutelabelService() {
        return routelabelService;
    }

    public void setRoutelabelService(Integer routelabelService) {
        this.routelabelService = routelabelService;
    }

    public Integer getIs_unified_waybill_no() {
        return is_unified_waybill_no;
    }

    public void setIs_unified_waybill_no(Integer is_unified_waybill_no) {
        this.is_unified_waybill_no = is_unified_waybill_no;
    }

    public Cargo getCargo() {
        return Cargo;
    }

    public void setCargo(Cargo cargo) {
        Cargo = cargo;
    }

    public PlaceOrderParameter.AddedService getAddedService() {
        return AddedService;
    }

    public void setAddedService(PlaceOrderParameter.AddedService addedService) {
        AddedService = addedService;
    }


    @Override
    public String toString() {
        return "PlaceOrderParameter{" +
                "orderid='" + orderid + '\'' +
                ", mailno='" + mailno + '\'' +
                ", j_company='" + j_company + '\'' +
                ", j_contact='" + j_contact + '\'' +
                ", j_tel='" + j_tel + '\'' +
                ", j_mobile='" + j_mobile + '\'' +
                ", j_province='" + j_province + '\'' +
                ", j_city='" + j_city + '\'' +
                ", j_county='" + j_county + '\'' +
                ", j_address='" + j_address + '\'' +
                ", d_company='" + d_company + '\'' +
                ", d_contact='" + d_contact + '\'' +
                ", d_tel='" + d_tel + '\'' +
                ", d_mobile='" + d_mobile + '\'' +
                ", d_province='" + d_province + '\'' +
                ", d_city='" + d_city + '\'' +
                ", d_county='" + d_county + '\'' +
                ", d_address='" + d_address + '\'' +
                ", custid='" + custid + '\'' +
                ", pay_method=" + pay_method +
                ", express_type='" + express_type + '\'' +
                ", parcel_quantity=" + parcel_quantity +
                ", cargo_length=" + cargo_length +
                ", cargo_width=" + cargo_width +
                ", cargo_height=" + cargo_height +
                ", volume=" + volume +
                ", cargo_total_weight=" + cargo_total_weight +
                ", sendstarttime=" + sendstarttime +
                ", is_docall=" + is_docall +
                ", need_return_tracking_no='" + need_return_tracking_no + '\'' +
                ", return_tracking='" + return_tracking + '\'' +
                ", temp_range=" + temp_range +
                ", template='" + template + '\'' +
                ", remark='" + remark + '\'' +
                ", oneself_pickup_flg=" + oneself_pickup_flg +
                ", special_delivery_type_code='" + special_delivery_type_code + '\'' +
                ", special_delivery_value='" + special_delivery_value + '\'' +
                ", realname_num='" + realname_num + '\'' +
                ", routelabelForReturn=" + routelabelForReturn +
                ", routelabelService=" + routelabelService +
                ", is_unified_waybill_no=" + is_unified_waybill_no +
                ", Cargo=" + Cargo +
                ", AddedService=" + AddedService +
                '}';
    }


    class AddedService{
        private String name;        //增值服务名,如COD等。
        private String value;       //增值服务扩展属性,参考增值服务传值说明。
        private String value1;      //增值服务扩展属性
        private String value2;      //增值服务扩展属性2
        private String value3;      //增值服务扩展属性3
        private String Value4;      //增值服务扩展属性4


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValue1() {
            return value1;
        }

        public void setValue1(String value1) {
            this.value1 = value1;
        }

        public String getValue2() {
            return value2;
        }

        public void setValue2(String value2) {
            this.value2 = value2;
        }

        public String getValue3() {
            return value3;
        }

        public void setValue3(String value3) {
            this.value3 = value3;
        }

        public String getValue4() {
            return Value4;
        }

        public void setValue4(String value4) {
            Value4 = value4;
        }

        @Override
        public String toString() {
            return "AddedService{" +
                    "name='" + name + '\'' +
                    ", value='" + value + '\'' +
                    ", value1='" + value1 + '\'' +
                    ", value2='" + value2 + '\'' +
                    ", value3='" + value3 + '\'' +
                    ", Value4='" + Value4 + '\'' +
                    '}';
        }
    }

}
