package com.dxt.ht.svc.daddress.service;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.daddress.dao.HtMainPointMapper;
import com.dxt.ht.svc.daddress.entity.HtMainPoint;
import com.dxt.ht.svc.sf.SfService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@RestController
@Api(tags="收件地址模块")
@RequestMapping("api/daddress")
public class HtMainPointService {
    @Autowired
    private HtMainPointMapper htMainPointMapper;

    @Autowired
    private SfService sfService;

    /**
     *
     * @param id 订单id
     * @return 维修网点地址
     */
    @GetMapping("findPostAddress")
    @ApiOperation(value = "获取维修网点地址", notes = "获取维修网点地址方法")
    @ApiImplicitParam(name ="id", value = "订单id",dataType = "string", paramType = "query")
    public ApiResult<HtMainPoint> findDaddress(String id) {
        if (StringUtils.isBlank(id)) {
            return ApiResultFactory.newInstance(ResultStatus.INVALID_PARAM, "订单id不能为空");
        }
        HtMainPoint type = htMainPointMapper.findType(id);
        System.out.println(type.getFormType());
        if (type.getFormType().equals("1")) {
            HtMainPoint newAddress = htMainPointMapper.findChangdeAddress(id);
            newAddress.setContactNumberOne("18511019009");
            newAddress.setContactOne("焦金辉");
            newAddress.setProvince("北京市");
            newAddress.setCity("北京市");
            newAddress.setArea("朝阳区");
            newAddress.setMaintenancePointName("");
            newAddress.setAddress("北京市朝阳区霄云路36号国航大厦1303室");
            return ApiResultFactory.getSuccess(newAddress);
        }
        HtMainPoint postAddress = htMainPointMapper.findPostAddress(id);
            if (postAddress == null) {
                return ApiResultFactory.newInstance(ResultStatus.NONE_DATA);
            }
            StrBuilder strBuilderDaddress = new StrBuilder();
            strBuilderDaddress.append(postAddress.getProvince()).append(postAddress.getCity()).append(postAddress.getArea()).append(postAddress.getAddress());
            postAddress.setAddress(strBuilderDaddress.toString());
            return ApiResultFactory.getSuccess(postAddress);

    }

}
