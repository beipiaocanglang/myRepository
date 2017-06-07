package com.ylcq.xjdx.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Formatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.ylcq.xjdx.model.CardBase;
import com.ylcq.xjdx.model.CardCash;
import com.ylcq.xjdx.model.CardDiscount;
import com.ylcq.xjdx.model.CardGeneralCoupon;
import com.ylcq.xjdx.model.CardGift;
import com.ylcq.xjdx.model.CardGroupon;
import com.ylcq.xjdx.utils.Model517Utils.CREATE_CART_TYPE;
import com.ylcq.xjdx.utils.Model517Utils.WX;


/**   
 *    
 * 项目名称：activityWeb   
 * 类名称：WxUtils   
 * 描述： 微信相关的操作类  
 * 创建人：zhangzhongwen   
 * 创建时间：2016年10月19日 下午4:27:03   
 * @version 1.0       
 */
public class WxUtils {

	private static final Logger log = Logger.getLogger(WxUtils.class);
	
	/**
	 * 根据openid、accessToken获取基本信息URL
	 */
	public static final String WX_BASIC_INFO_URL = "https://101.227.162.120/cgi-bin/user/info?access_token=";
	
	
	
	
	/**          
	 * 方法描述：从集团获取AccessToken值
	 * @param appid 公众号的appid
	 * @param appSecret 公众号应用密钥
	 * @param grant_type 默认client_credential
	 * @return 从集团获取的AccessToken值 
	 * 创建人：zhangzhongwen   
	 * 创建时间：2016年10月19日 下午5:04:40   
	 * @version 1.0       
	 */
	public static String getWXAccessTokenByJt(
						String appid,String appSecret,String grant_type){
		
		log.info("in getWXAccessTokenByJt method........");
		String accessToken = null;
		log.info("parameter of appid : " + appid);
		log.info("parameter of appSecret : " + appSecret);
		log.info("parameter of grant_type : " + grant_type);
		
		if(appid == null || "".equals(appid)
				|| appSecret == null || "".equals(appSecret)) return accessToken;
		
		if(grant_type == null || "".equals(grant_type)) grant_type = "client_credential";
		
		//集团取得tokenURL地址
		String url = "http://42.99.16.171/cgi-bin/token?grant_type="+grant_type+"&appid="+appid+"&secret="+appSecret;
		log.info("request group url : " + url);
		GetMethod getMethod = new GetMethod(url);
		HttpClient httpClient = new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true));
		 try{
		    httpClient.executeMethod(getMethod);
		    //集团范围参数
			String responseStr = new String(getMethod.getResponseBodyAsString().getBytes());	
			log.info("The group reponse info : " + responseStr);
			Map<String, String> result= new HashMap<String, String>();
			
			Model517Utils.getReturnMapFromResponse(responseStr, result);
			
			accessToken = result.get("access_token"); 
			log.info("filter group reponse info get access_token : " + accessToken);
			/*if(null != accessToken && !"".equals(accessToken)){
				log.info("set redis key:" + appid+":AccessToken" + ",value:" + accessToken);
				//放入缓存
				int expires_in = result.get("expires_in")==null ? 7200:Integer.parseInt(result.get("expires_in"));
				boolean res = RedisUtils.getRedisUtils().setByKey(appid+":AccessToken", accessToken,expires_in);
				log.info("set redis result : " + res);
			}*/
			
		 }catch(Exception e){
			log.info("request group getAccess_token interface error :",e);
	    	e.printStackTrace();
	     }finally{
	    	getMethod.releaseConnection();
	    	log.info("release connection success.....");
		 }
		 log.info("getWXAccessTokenByJt method result return accessToken is :" + accessToken);
		return accessToken;
	}
	
	
	
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
			log.error(e.getMessage());
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
	 * @param url 请求地址(可带)
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
			log.error(e.getMessage());
			e.printStackTrace();
		}finally{
			getMethod.releaseConnection();
		}
		return retn_str;
	}
	
	
	/**          
	 * 方法描述：
	 * @param api_ticket
	 * @param card_id
	 * @param code
	 * @param openid
	 * @return 	   
	 * 创建人：zhangzhongwen   
	 * 创建时间：2017年4月17日 下午7:50:55   
	 * @version 1.0       
	 */
	public static Map<String, String> getWxCardSignature(String api_ticket,
			String card_id,String code,String openid){
		
		Map<String, String> ret = null;
		
		List<String> parms = new ArrayList<String>();
		parms.add(api_ticket);
		parms.add(card_id);
		
		if(null!=code&&!"".equals(code)){
			parms.add(code);
		}
		if(null!=openid&&!"".equals(openid)){
			parms.add(openid);
		}
		
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		parms.add(nonce_str);
		parms.add(timestamp);
		
		System.out.println("排序前list:" + Arrays.toString(parms.toArray()));
		
		Collections.sort(parms);
		
		String arrayStr = Arrays.toString(parms.toArray());
		
		System.out.println("排序后list:" + arrayStr);
		
		try {

			String signature = sha1(arrayStr);
			System.out.println("signature:" + signature);
			ret = new HashMap<String,String>();
			ret.put("api_ticket", api_ticket);
			ret.put("card_id", card_id);
			if(code!=null&&!"".equals(code)){
				ret.put("code", code);
			}
			if(openid!=null&&!"".equals(openid)){
				ret.put("openid", openid);
			}
			ret.put("nonce_str", nonce_str);
			ret.put("timestamp", timestamp);
			ret.put("signature", signature);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ret;
	}
	
	/**          
	 * 方法描述：获取微信js调用凭证
	 * @param jsapi_ticket
	 * @param url
	 * @return 	   
	 * 创建人：zhangzhongwen   
	 * 创建时间：2017年4月13日 上午10:56:19   
	 * @version 1.0       
	 */
	public static Map<String, String> getWxJsSign(String jsapi_ticket, String url){
		
		Map<String, String> ret = null;
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		//注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket +
				"&noncestr=" + nonce_str +
				"&timestamp=" + timestamp +
				"&url=" + url;
		System.out.println(string1);
	
		try
		{
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
			
			ret = new HashMap<String, String>();
			ret.put("url", url);
			ret.put("jsapi_ticket", jsapi_ticket);
			ret.put("noncestr", nonce_str);
			ret.put("timestamp", timestamp);
			ret.put("signature", signature);
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		
		return ret;
	}

	/**          
	 * 方法描述：调用集团接口获取ticket
	 * @param appid
	 * @param appSecret
	 * @param grant_type
	 * @return 	   
	 * 创建人：zhangzhongwen   
	 * 创建时间：2017年4月13日 上午10:49:42   
	 * @version 1.0       
	 */
	public static String getJsApiTicket(String appid,String appSecret,String grant_type){
		//返回值
		String responseStr = "";
		String url="http://wt.10006.info/cgi-bin/ticket/getticket";
		//String grant_type="client_credential";
		//String appSecret="c06e0c2d8157388c1b6d6a7e4a6bbf75";
		url+=("?grant_type="+grant_type+"&appid="+appid+"&secret="+appSecret);

		GetMethod postMethod = null;
		try {
			postMethod = new GetMethod(url);
			postMethod.getParams().setContentCharset("UTF-8");
			HttpClient httpClient = new HttpClient(new HttpClientParams(),new SimpleHttpConnectionManager(true));
			//httpClient.setTimeout(10000);
			httpClient.executeMethod(postMethod);
			responseStr = new String(postMethod.getResponseBodyAsString().getBytes());

			if(null != responseStr && !"".equals(responseStr)){
				JSONObject json1=JSONObject.parseObject(responseStr);
				if(json1 != null){
					responseStr=json1.getString("ticket");
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();

		}finally{
			postMethod.releaseConnection();
		}
		return responseStr;
	}

	//http://shop.xj.189.cn:9091/activityWeb/activity/WxController/getApi_ticket.do
	
	
	public static String getApi_ticketByActivityWeb(){
		
		String res = null;
		String api_ticket_url = "http://shop.xj.189.cn:9091/activityWeb/activity/WxController/getApi_ticket.do";
		
		try {
			
			String 	api_ticket_info = Model517Utils.getCallUrlJson(api_ticket_url);
			
			if(null != api_ticket_info && !"".equals(api_ticket_info)){
				
				
				JSONObject obj = JSONObject.parseObject(api_ticket_info);
				
				if(obj.getString("code").equals("200")){
					res = obj.getString("ticket");
				};
				
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return res;
		
	}
	
	
	
	
	

	/**          
	 * 方法描述：获取卡券接口签名的api_ticket
	 * @param access_token 调用令牌
	 * @return json 格式的api_ticket信息	   
	 * 创建人：zhangzhongwen   
	 * 创建时间：2017年4月12日 下午5:52:26   
	 * @version 1.0       
	 */
	public static String getApi_ticketByWx(String access_token){
		String api_ticket_info = null;
		String api_ticket_url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+access_token+"&type=wx_card";
		try {
			boolean flag = true;
			int cnt = 50;
			while(flag && cnt > 0){
				api_ticket_info = Model517Utils.getCallUrlJson(api_ticket_url);
				if(null != api_ticket_info){
					JSONObject card_obj = JSONObject.parseObject(api_ticket_info);
					if(null!=card_obj){
						if(card_obj.getIntValue("errcode")==0){
							api_ticket_info = card_obj.getString("ticket");
							
							flag = false;
						};
					}
				}else{
					cnt--;
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return api_ticket_info;
	}

	/**          
	 * 方法描述：创建卡包
	 * @param access_token 调用令牌
	 * @param card 创建的卡包对象
	 * @return 	   
	 * 创建人：zhangzhongwen   
	 * 创建时间：2017年4月12日 下午5:56:18   
	 * @version 1.0       
	 */
	public static String create_card(String access_token , CardBase card,String type){
		String create_return_info = null;
		if(null == access_token || null == card || null == type )
			new RuntimeException("创建微信卡包必要参数缺失access_token=" + access_token+
					",对象CardBase:" + card.toString() + ",type=" + type);
		try {
			
			String create_card_url = "https://api.weixin.qq.com/card/create?access_token="+access_token+"";
			System.out.println("------请求的卡券接口地址:" + create_card_url);
			String paramsJson =	getCreateCardJsonStr(card,type);
			System.out.println("------获取请求的json数据:" + paramsJson);
			if(null != paramsJson){
				create_return_info = Model517Utils.postCallUrlJson(create_card_url, paramsJson);
			}

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return create_return_info;

	}

	/**          
	 * 方法描述：
	 * @param card
	 * @param type
	 * @return 	   
	 * 创建人：zhangzhongwen   
	 * 创建时间：2017年4月12日 下午7:59:14   
	 * @version 1.0       
	 */
	public static String getCreateCardJsonStr(CardBase card,String type){


		if(null == card || null == type || "".equals(type)) 
			new RuntimeException("必要参数缺失,card=" + card + ",type=" + type);

		JSONObject card_obj = new JSONObject();
		JSONObject card_values = new JSONObject();
		JSONObject groupon_values = new JSONObject();
		JSONObject base_info_values = new JSONObject();

		//JSONObject advanced_info_values = new JSONObject(); //高级设置

		base_info_values.put("logo_url", card.getLogo_url());
		base_info_values.put("brand_name", card.getBrand_name());
		base_info_values.put("code_type", card.getCode_type());
		base_info_values.put("title", card.getTitle());
		if(card.getSub_title() != null){
			base_info_values.put("sub_title", card.getSub_title());
		}
		base_info_values.put("color", card.getColor());
		base_info_values.put("notice", card.getNotice());
		base_info_values.put("service_phone", card.getService_phone());
		base_info_values.put("description", card.getDescription());

		JSONObject date_info_values = new JSONObject();
		
		if("DATE_TYPE_FIX_TIME_RANGE".equals(card.getType())){
			date_info_values.put("end_timestamp", card.getEnd_timestamp());
			date_info_values.put("begin_timestamp", card.getBegin_timestamp());
		}else if("DATE_TYPE_FIX_TERM".equals(card.getType())){
			date_info_values.put("fixed_begin_term", card.getFixed_begin_term());
			date_info_values.put("fixed_term", card.getFixed_term());
			//date_info_values.put("end_timestamp", card.getFixed_end_timestamp());
			date_info_values.put("type", card.getType());
		}

		base_info_values.put("date_info", date_info_values);

		JSONObject sku_values = new JSONObject();
		sku_values.put("quantity", card.getQuantity());
		base_info_values.put("sku", sku_values);
		if(card.getGet_limit() == 0){
			base_info_values.put("get_limit", 1);
		}else{
			base_info_values.put("get_limit", card.getGet_limit());
		}

		base_info_values.put("use_custom_code", card.getUse_custom_code());
		base_info_values.put("bind_openid", card.getBind_openid());
		base_info_values.put("can_share", card.getCan_share());
		base_info_values.put("can_give_friend", card.getCan_give_friend());

		if(card.getLocation_id_list() != null){
			base_info_values.put("location_id_list", card.getLocation_id_list());
		}
		if(card.getCenter_title() != null){
			base_info_values.put("center_title", card.getCenter_title());
		}
		if(card.getCustom_url_sub_title() != null){
			base_info_values.put("center_sub_title", card.getCustom_url_sub_title());
		}

		if(card.getCenter_url() != null){
			base_info_values.put("center_url", card.getCenter_url());
		}
		if(card.getCustom_url_name() != null){
			base_info_values.put("custom_url_name", card.getCustom_url_name());
		}
		if(card.getCustom_url() != null){
			base_info_values.put("custom_url", card.getCustom_url());
		}
		if(card.getCustom_url_sub_title() != null){
			base_info_values.put("custom_url_sub_title", card.getCustom_url_sub_title());
		}
		if(card.getPromotion_url_name() != null){
			base_info_values.put("promotion_url_name", card.getPromotion_url_name());
		}

		if(card.getPromotion_url() != null){
			base_info_values.put("promotion_url", card.getPromotion_url());
		}
		if(card.getSource() != null){
			base_info_values.put("source", card.getSource());
		}

		groupon_values.put("base_info", base_info_values);
		//groupon_values.put("advanced_info", value); //高级设置

		card_values.put("card_type", type);

		if(type.equals(CREATE_CART_TYPE.GROUPON+"")){ //团购券类型
			CardGroupon cardGroupon	= (CardGroupon)card;
			groupon_values.put("deal_detail", cardGroupon.getDeal_detail());

		}else if(type.equals(CREATE_CART_TYPE.CASH+"")){ //代金券类型。
			CardCash cardCash = (CardCash)card;
			groupon_values.put("least_cost", cardCash.getLeast_cost());
			groupon_values.put("reduce_cost", cardCash.getReduce_cost());

		}else if(type.equals(CREATE_CART_TYPE.DISCOUNT+"")){//折扣券类型。
			CardDiscount cardDiscount = (CardDiscount)card;
			groupon_values.put("discount", cardDiscount.getDiscount());
		}else if(type.equals(CREATE_CART_TYPE.GIFT+"")){//兑换券类型。
			CardGift cardGift = (CardGift)card;
			groupon_values.put("gift", cardGift.getGift());

		}else if(type.equals(CREATE_CART_TYPE.GENERAL_COUPON+"")){ //优惠券类型。
			CardGeneralCoupon cardGeneralCoupon = (CardGeneralCoupon)card;
			groupon_values.put("default_detail", cardGeneralCoupon.getDefault_detail());
		}

		
		card_values.put(card.getCard_type().toLowerCase(), groupon_values);
		card_obj.put("card", card_values);


		return card_obj.toString();
	}

	
	private static String create_nonce_str() {
        return UUID.randomUUID().toString();
    }

    private static String create_timestamp() {
        return Long.toString(System.currentTimeMillis() / 1000);
    }
	
    
    public static String sha1(String string1) throws NoSuchAlgorithmException, UnsupportedEncodingException{
    
    	MessageDigest crypt = MessageDigest.getInstance("SHA-1");
		crypt.reset();
		crypt.update(string1.getBytes("UTF-8"));
		return	byteToHex(crypt.digest());
    	
    }
    
    private static String byteToHex(final byte[] hash) {
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result;
    }
    public static void main(String[] args) {
    	String access_token = getWXAccessTokenByJt(
				WX.APPID.getValue(), WX.APPSECRET.getValue(), WX.GRANT_TYPE.getValue());;
				
		System.out.println("access_token:"+access_token);		
	}
	
}
