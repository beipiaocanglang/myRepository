package com.ylcq.xjdx.utils;

import java.util.Iterator;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;

public class Model517Utils {
	/**
	 * 描述：HTTP POST 方式调用远程接口
	 * @author zhangzhongwen
	 * @date 2017-04-11
	 * @param url 请求地址
	 * @param paramsJson 参数：为JSON格式
	 * @return 请求返回的数据
	 */
	public static String postCallUrlJson(String url, String paramsJson) {
		if(url==null || "".equals(url)) new RuntimeException("缺少必要参数url:" + url);
		String retn_str = null;
		PostMethod postMethod = null;
		try {
			postMethod = new PostMethod(url);
			HttpClient httpClient = new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true) );
			postMethod.setRequestHeader("Content-Type","application/json; charset=UTF-8");
			if (paramsJson != null && !paramsJson.trim().equals("")) {
				RequestEntity requestEntity = new StringRequestEntity(paramsJson,"text/xml","UTF-8");
				postMethod.setRequestEntity(requestEntity);
			}
			httpClient.executeMethod(postMethod);
			retn_str =  postMethod.getResponseBodyAsString();
		} catch(Exception e) {
			e.printStackTrace();
		}finally{
			postMethod.releaseConnection();
		}
		
		return retn_str;
	}
	
	/**
	 * 描述：HTTP GET 方式调用远程接口
	 * @author zhangzhongwen
	 * @date 2017-04-11
	 * @param url 请求地址(可带参数)
	 * @return 请求返回的数据
	 */
	public static String getCallUrlJson(String url) {
		if(url==null || "".equals(url)) new RuntimeException("缺少必要参数url:" + url);
		String retn_str = null;
		GetMethod getMethod = null;
		try {
			getMethod = new GetMethod(url);
			HttpClient httpClient = new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true));
			httpClient.executeMethod(getMethod);
			retn_str =  getMethod.getResponseBodyAsString();
		} catch(Exception e) {
			e.printStackTrace();
		}finally{
			getMethod.releaseConnection();
		}
		return retn_str;
	}
	
	public enum CREATE_CART_COLORSELECT{
		
		Color010("#63b359"),	
		Color020("#2c9f67"),
		Color030("#509fc9"),
		Color040("#5885cf"),
		Color050("#9062c0"),
		Color060("#d09a45"),
		Color070("#e4b138"),
		Color080("#ee903c"),
		Color081("#f08500"),
		Color082("#a9d92d"),
		Color090("#dd6549"),
		Color100("#cc463d"),
		Color101("#cf3e36"),
		Color102("#5E6671");
		
		private String value;
		
		public String getValue(){
			return value;
		}
		
		CREATE_CART_COLORSELECT(String pvalue){
			this.value = pvalue;
		} 
		
	}
	
	public enum CREATE_CART_TYPE{
		
		GROUPON("团购券类型"),
		CASH("代金券类型"),
		DISCOUNT("折扣券类型"),
		GIFT("兑换券类型"),
		GENERAL_COUPON("优惠券类型");
		
		private String value;
		
		public String getValue(){
			return value;
		}
		
		CREATE_CART_TYPE(String pvalue){
			this.value = pvalue;
		} 
	}
	
	public enum WX{
		
		APPID("wxa3eb40e114f3d4c2"),
		APPSECRET("c06e0c2d8157388c1b6d6a7e4a6bbf75"),
		GRANT_TYPE("client_credential"),
		API_TICKET_KEY("api_ticket");
	
		private String value;
		
		public String getValue(){
			return value;
		}
		
		WX(String pvalue){
			this.value = pvalue;
		} 
	}
	
	
	/**
	 * 封装结果到Map
	 * 
	 * @param retCode
	 * @param result
	 */
	public static void getReturnMapFromResponse(String retCode, Map<String, String> result) {
		result.clear();
		JSONArray array = JSONArray.fromObject("[" + retCode + "]");
		Iterator it = array.iterator();
		while (it.hasNext()) {
			JSONObject json = JSONObject.fromObject(it.next());
			Iterator keyIt = json.keys();
			while (keyIt.hasNext()) {
				String key = (String) keyIt.next();
				String kvalue = json.getString(key);
				result.put(key, kvalue);
			}
		}
	}
	
}
