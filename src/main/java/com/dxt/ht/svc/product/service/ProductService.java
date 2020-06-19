package com.dxt.ht.svc.product.service;

import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.CommentConstant;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.form.dao.HtFormInfoMapper;
import com.dxt.ht.svc.form.dao.JsSysDictDataMapper;
import com.dxt.ht.svc.form.entity.HtFormInfo;
import com.dxt.ht.svc.form.entity.JsSysDictData;
import com.dxt.ht.svc.policy.dao.HtPolicyInfoMapper;
import com.dxt.ht.svc.policy.entity.HtPolicyInfo;
import com.dxt.ht.svc.product.dao.*;
import com.dxt.ht.svc.product.entity.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.*;

/**
 *产品服务接口
 *
 * @author wangfanglin
 * @date 2020/04/19
 */
@RestController
@Api(tags = "产品模块")
@RequestMapping(value = "api/ht/product")
public class ProductService {

    @Autowired
    private HtPolicyInfoMapper htPolicyInfoMapper;

    @Autowired
    private HtChannelProductInfoMapper htChannelProductInfoMapper;

    @Autowired
    private HtGroupProductInfoMapper htGroupProductInfoMapper;

    @Autowired
    private HtGroupProductChildMapper htGroupProductChildMapper;

    @Autowired
    private HtProductChildMiddleMapper htProductChildMiddleMapper;

    @Autowired
    private HtProductInfoMapper htProductInfoMapper;

    @Autowired
    private JsSysDictDataMapper jsSysDictDataMapper;

    private final static String NUMBER = "1";


    @GetMapping("getGroupProductRule")
    @ApiOperation(value = "获取组合产品规则",notes = "获取组合产品规则")
    @ApiImplicitParam(name = "policyId",value = "保单id",dataType="string", paramType = "query",required = true)
    public ApiResult<HtGroupProductRule> getProductRule(String policyId){
        HtPolicyInfo htPolicyInfo = htPolicyInfoMapper.selectByPrimaryKey(policyId);
        if(ObjectUtils.isEmpty(htPolicyInfo)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"查询数据为空");
        }
        HtChannelProductInfo htChannelProductInfo = htChannelProductInfoMapper.selectByPrimaryKey(htPolicyInfo.getChannelProductId());
        if(ObjectUtils.isEmpty(htChannelProductInfo)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"查询数据为空");
        }
        HtGroupProductInfo htGroupProductInfo = htGroupProductInfoMapper.selectByPrimaryKey(htChannelProductInfo.getGroupProductId());
        if(ObjectUtils.isEmpty(htGroupProductInfo)){
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA,"查询数据为空");
        }
        HtGroupProductRule htGroupProductRule = new HtGroupProductRule();
        Map<String, String> params = new HashMap<>();
        params.put("groupProductId", htGroupProductInfo.getId());
        List<HtProductChildMiddle> htProductChildMiddleList = htProductChildMiddleMapper.select(params);
        if (CollectionUtils.isEmpty(htProductChildMiddleList)) {
            return ApiResultFactory.newInstance(ResultStatus.NONE_DATA, "查询数据为空");
        }
        List<String> groupProductChildIds = htProductChildMiddleList.stream().map(HtProductChildMiddle::getGroupProductChildId).collect(Collectors.toList());
        //神奇。。。。系统默认是 htGroupProductChildren  而不是 HtGroupProductChildList 原来 child 的复数形式是 children, TODO 后期优化 查询次数太多。
        List<HtGroupProductChild> htGroupProductChildren = htGroupProductChildMapper.selectByIds(groupProductChildIds);
        List<String> productIds = htGroupProductChildren.stream().map(HtGroupProductChild :: getProductId).collect(Collectors.toList());
        List<HtProductInfo> htProductInfos = htProductInfoMapper.selectByProductIds(productIds);
        Map<String,String> htProductInfoMap = htProductInfos.stream().collect(Collectors.toMap(HtProductInfo :: getId,HtProductInfo::getProductType));
        htGroupProductChildren.stream().forEach(e->{
            if(htProductInfoMap.containsKey(e.getProductId())){
                e.setProductType(htProductInfoMap.get(e.getProductId()));
            }
        });
        Map<String,String> map = new HashMap<>();
        map.put("dictType","product_type");
        List<JsSysDictData> jsSysDictDataList = jsSysDictDataMapper.select(map);
        Map<String,String> jsSysDictDataMap = jsSysDictDataList.stream().collect(Collectors.toMap(JsSysDictData :: getDictValue, JsSysDictData::getDictLabel));
        htGroupProductChildren.stream().forEach(e->{
            if(jsSysDictDataMap.containsKey(e.getProductType())){
                e.setProductType(jsSysDictDataMap.get(e.getProductType()));
            }
        });
        htGroupProductRule.setHtGroupProductInfo(htGroupProductInfo);
        htGroupProductRule.setHtGroupProductChildren(htGroupProductChildren);
        htGroupProductRule.setServiceValidity(formatServiceValidity(htGroupProductRule));
        return ApiResultFactory.getSuccess(htGroupProductRule);
    }

    /**
     * 服务有效期显示内容
     *
     * @param htGroupProductRule
     */
    private String formatServiceValidity(HtGroupProductRule htGroupProductRule) {
        //服务有效期不一致性
        if(!NUMBER.equals(htGroupProductRule.getHtGroupProductInfo().getIsAccordance())){
            StringBuilder sb = new StringBuilder();
            htGroupProductRule.getHtGroupProductChildren().stream().forEach(e->{
                    sb.append("全面保产品中的")
                            .append(e.getProductType())
                            .append("类产品有效期自购买完成后")
                            .append(effectDate(e.getTakeDay()))
                            .append("生效，有效期")
                            .append(e.getValidity())
                            .append("个月;");
            });
            return sb.toString();
        }
        //服务有效期一致
        StringBuilder sb = new StringBuilder();
        sb.append("服务购买完成后")
                .append(effectDate(htGroupProductRule.getHtGroupProductChildren().get(0).getTakeDay()))
                .append("生效，有效期")
                .append(htGroupProductRule.getHtGroupProductChildren().get(0).getValidity())
                .append("个月");
        return sb.toString();
    }

    private String effectDate(Integer takeDay) {
        if(takeDay == null){
            return "即刻";
        }
        if(takeDay.intValue() == 1){
            return "次日凌晨";
        }
        if(2 <= takeDay.intValue()){
            StringBuilder sb = new StringBuilder();
            sb.append("第").append(takeDay).append("日凌晨");
            return sb.toString();
        }
        //默认
        return "即刻";
    }


}
