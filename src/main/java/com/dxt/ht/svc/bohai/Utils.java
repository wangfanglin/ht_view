package com.dxt.ht.svc.bohai;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dxt.ht.svc.apply.entity.ReportCaseParameter;
import com.dxt.ht.svc.common.result.ApiResult;
import com.dxt.ht.svc.common.result.ApiResultFactory;
import com.dxt.ht.svc.common.result.ResultStatus;
import com.dxt.ht.svc.common.utils.HttpReqUtils;
import com.dxt.pass.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.util.Map;

/**
 * 渤海工具类
 *
 * @author wangfanglin
 * @since 2020/04/27
 */
@Slf4j
public class Utils {

    private static final String REPROT_SUBMIT = "http://117.8.230.48:9080/esi/data.do?method=esData&esType=hfReportSubmit ";
    private static final String REPROT_SUBMIT_OTHER = "http://117.8.230.48:9080/esi/data.do?method=esData&esType=queryCaseList";
    private static final String UPLOAD_IMAGE_URL = "http://117.8.230.48:9080/esi/data.do?method=esData&esType=upLoadImage";
    /**
     *  报案接口
     * @param parameter 估损提交入参
     * @return
     */
    public static Map<String,Object> reprotSubmit(ReportCaseParameter parameter)  {
        try {
            String resultStr = call(REPROT_SUBMIT,"hfReportSubmit",parameter);
            Map<String, Object> jsonStr = GsonUtils.fromJson(resultStr, Map.class);
            return jsonStr;
        }catch (Exception e){
            log.error("调用报案接口失败");
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 检查报案接口的返回值是否成功
     * @param reprotSubmitResult
     * @return true 成功 false 失败
     */
    public static boolean checkReprotSubmitResult(Map<String,Object> reprotSubmitResult){
        return reprotSubmitResult!=null &&"000000".equals(reprotSubmitResult.get("code"));
    }


    /**
     * 调用接口
     * @param parameter 入参
     * @param method    方法名
     * @return          返回值
     * @throws UnsupportedEncodingException
     */
    private static String call(String url,String method,Object parameter) throws UnsupportedEncodingException {
        String parameterJson = GsonUtils.toJson(parameter);
        String encoderParam = java.net.URLEncoder.encode(parameterJson,"UTF-8");
        String resultStr = HttpReqUtils.sendPostJson(url, encoderParam, getToken(parameterJson));
        return resultStr;
    }

    /**
     * 获取 token
     * @param json
     * @return
     */
    private  static  String getToken(String json) {
        //appid=1，randomNumber=随机数，timestamp=时间戳
        //目前先生成6位随机数
        return MD5.toMD5("1"+"65537"+System.currentTimeMillis()+Math.random()*1000000+json);
    }


    /**
     * 影像资料上传
     * @param imgUrl 图片数组多张
     * @param clmNo  渤海案件号
     * @return
     */
    public static boolean uploadImage(String imgUrl,String clmNo){
        String[] imgArr= StringUtils.split(imgUrl,"|");
        for (int i = 0; i < imgArr.length; i++) {
            String imageType = StringUtils.substringAfterLast(imgArr[i],".");
            String base64Image = imageToBase64Html(imgArr[i],imageType);
            String resultImage = upLoadImage(new UpLoadImageParameter(base64Image,clmNo,clmNo));
            if(StringUtils.isEmpty(resultImage)){
                return false;
            }
        }
        return  true;
    }

    /**
     *  3.6.影像上传
     * @param parameter 估损提交入参
     * @return  Result.data:图片ID
     */
    public static String upLoadImage(UpLoadImageParameter parameter)  {
        try {
            String resultStr = call(UPLOAD_IMAGE_URL,"upLoadImage",parameter);
            if(!StringUtils.isNotBlank(resultStr))  return null;
            Map<String, Object> jsonStr =GsonUtils.fromJson(resultStr, Map.class);
            String code = (String) jsonStr.get("code");
            Map<String, String> objMap =  (Map<String, String>) jsonStr.get("obj");
            return objMap.get("imgId");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    /**
     *
     * 功能描述：img 标签中显示base64位数据图片
     *
     * @param imgPath
     * @return
     *
     * @author 孙梦启
     *
     * @since 2018年12月13日
     *
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     */
    public static String imageToBase64Html(String imgPath,String imgType){

        String base64Str="";
        if(imgPath.indexOf("http")==0){
            base64Str=imgURLBase64(imgPath,imgType);
        }else{
            base64Str=imageToBase64Str(imgPath);
        }

        int v = base64Str.indexOf("base64,");
        if (v != -1) {
            base64Str = base64Str.substring(v + 7, base64Str.length());
        }

        return "data:image/"+imgType+";base64,"+base64Str;
    }
    /**
     *
     * 功能描述： 图片转base64位数组
     *
     * @param imgPath
     * @return
     *
     * @author 孙梦启
     *
     * @since 2018年12月11日
     *
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     */
    public static String  imageToBase64Str( String imgPath){
        String base64Str="";
        if(StringUtils.isNotBlank(imgPath)){
            File f =null;
            FileInputStream fis =null;
            ByteArrayOutputStream out =null;
            try{
                f= new File(imgPath);
                fis=new FileInputStream(f);
                out = new ByteArrayOutputStream();
                byte[] buffer = new byte[fis.available()];
                int n = 0;
                while ((n = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, n);
                }
                BASE64Encoder encoder = new BASE64Encoder();
                base64Str=encoder.encode(buffer);
            }catch (Exception e) {
                e.printStackTrace();
                base64Str="";
            }finally{
                try {
                    if(out !=null){
                        out.close();
                    }
                    if(fis !=null){
                        fis.close();
                    }
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return base64Str;
    }
    /**
     *
     * 功能描述：网络图片转换成base64  压缩！
     *
     * @param imgURL
     * @return
     *
     * @author 孙梦启
     *
     * @since 2019年2月22日
     *
     * @update:[变更日期YYYY-MM-DD][更改人姓名][变更描述]
     */
    public static String imgURLBase64(String imgURL,String fileType) {
        URL url;
        String base64Str="";
        ByteArrayOutputStream baos =null;
        try {
            url = new URL(imgURL);
            BufferedImage bufImage= ImageIO.read(url);
            baos= new ByteArrayOutputStream();
            ImageIO.write(bufImage,fileType, baos);
            /*if(baos.size()>(2*1024*1024)){
                Builder<BufferedImage> builder = Thumbnails.of(bufImage).scale(0.5f);
                BufferedImage buf=builder.asBufferedImage();
                ImageIO.write(buf,fileType, baos);
            }*/
            byte[] bytes=baos.toByteArray();
            BASE64Encoder encoder = new BASE64Encoder();
            base64Str=encoder.encode(bytes).replaceAll("\r|\n", "");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                if(baos !=null){
                    baos.flush();
                    baos.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return base64Str;
    }




}
