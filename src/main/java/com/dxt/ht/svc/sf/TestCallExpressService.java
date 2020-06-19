package com.dxt.ht.svc.sf;


import com.sf.csim.express.service.CallExpressServiceTools;
import org.dom4j.DocumentException;

import java.util.Map;

public class TestCallExpressService {
	
	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		String orderId = "TEST2_13333";
		String expressType = "1";
		String jContact = "汤超";
		String jMobile = "15010788869";
		String jAddress = "北京市海淀区上地国际创业软件园";
		String dCompany = "上海维修";
		String dContact = "汤超";
		String dTel = "13333333333";
		String dAddress = "上海 天天小飞侠";
		PlaceOrderParameters parameters = new PlaceOrderParameters(orderId,expressType,jContact,jMobile,
				jAddress,dCompany,dContact,dTel,dAddress,"2019-12-11 12:00:00");
		String reqURL="https://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";
		String clientCode="HDXT";//此处替换为您在丰桥平台获取的顾客编码
		String checkword="FpoWqRh6A1vPPtc2Ghe7x5kz5aUdnfl3";//此处替换为您在丰桥平台获取的校验码
        CallExpressServiceTools client=CallExpressServiceTools.getInstance();
        String myReqXML=SfXmlUtils.getPlaceOrderParameter(clientCode,parameters);
        System.out.println("请求报文："+myReqXML);
        String respXml= CallExpressServiceTools.callSfExpressServiceByCSIM(reqURL, myReqXML, clientCode, checkword);


		 if (respXml != null) {
             System.out.println("--------------------------------------");
             System.out.println("返回报文: "+ respXml);
             System.out.println("--------------------------------------");
         }

		Map<String,Object> asd = null;
		try {
			asd = XmlUtils.xml2mapWithAttr(respXml,true);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		System.out.println(asd);
	}








	

}
