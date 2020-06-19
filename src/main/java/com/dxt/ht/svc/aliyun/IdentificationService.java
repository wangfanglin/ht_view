package com.dxt.ht.svc.aliyun;




import com.dxt.ht.svc.aliyun.entity.IdCardInformation;
import com.dxt.ht.svc.aliyun.utils.ALliYunUtils;
import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 身份证识别接口
 *
 * @author wangfanlgin
 * @date 20202/04/13
 **/


@RestController
@RequestMapping("api/identification")
@Api(tags = "身份证识别")
public class IdentificationService {

    @GetMapping(value = "getIdentification")
    @ApiOperation(value = "身份证识别",notes = "身份证识别")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileImgPath",value = "图片路径",dataType="string", paramType = "query",required = true),
            @ApiImplicitParam(name = "faceOrBack",value = "身份证正反面",dataType="string", paramType = "query",required = true)})
    public ApiResult<IdCardInformation> getIdentification(String fileImgPath, String faceOrBack){
        try {
            //IdCardInformation idCardInformation = ALliYunUtils.gets(fileImgPath,faceOrBack);
            IdCardInformation idCardInformation = getinfo();
            if(ObjectUtils.isEmpty(idCardInformation)){
                return ApiResultFactory.newInstance(ResultStatus.PARSE_FAILURE,"身份证解析失败,请重新上传");
            }
            return ApiResultFactory.getSuccess(idCardInformation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private IdCardInformation getinfo() {
        IdCardInformation idCardInformation = new IdCardInformation();
        idCardInformation.setAddress("浙江省杭州市余杭区文一西路969号");
        idCardInformation.setBirth("20000101");
        idCardInformation.setEnd_date("19800101");
        idCardInformation.setIssue("杭州市公安局");
        idCardInformation.setName("张三");
        idCardInformation.setSex("女");
        idCardInformation.setNum("120106198805251534");
        idCardInformation.setNationality("汉");
        idCardInformation.setStart_date("19700101");
        return idCardInformation;
    }
}
