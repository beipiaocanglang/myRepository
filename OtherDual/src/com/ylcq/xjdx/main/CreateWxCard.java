package com.ylcq.xjdx.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.ylcq.xjdx.model.CardGift;
import com.ylcq.xjdx.utils.Model517Utils.WX;
import com.ylcq.xjdx.utils.WxUtils;

/**   
 * 创建微信卡券   
 * 项目名称：OtherDual   
 * 类名称：CreateWxCard   
 * 描述：   
 * 创建人：zhangzhongwen   
 * 创建时间：2017年4月18日 下午5:16:00   
 * @version 1.0       
 */
public class CreateWxCard {
	
	public enum CARD_TYPE{
		
		GROUPON("团购券类型","cardGroupon.properties"),
		CASH("代金券类型","cardCash.properties"),
		DISCOUNT("折扣券类型","cardDiscount.properties"),
		GIFT("兑换券类型","cardGift.properties"),
		GENERAL_COUPON("优惠券类型","");
		
		private String value;
		private String propertieName;
		
		public String getPropertieName(){
			return propertieName;
		}
		
		public String getValue(){
			return value;
		}
		
		CARD_TYPE(String pvalue,String propertieName){
			this.value = pvalue;
			this.propertieName = propertieName;
		} 
		
		
	}
	
	Properties cardGiftPro;

	public Properties getCardGiftPro() {
		return cardGiftPro;
	}

	public void setCardGiftPro(Properties cardGiftPro) {
		this.cardGiftPro = cardGiftPro;
	}

	CreateWxCard(Properties cardGiftPro){
		super();
		this.cardGiftPro = cardGiftPro;
		initCardInfo();
	}
	
	List<CardGift> giftCards = new ArrayList<CardGift>();
	
	
	private String[] split_str(String str,String regex){
		String[] obj = null; 
		str=str.replaceAll(" +", "");
		if(null!=str && !"".equals(str)){
			obj = str.split(regex);
		}
		return obj;
	}
	
	protected void initCardInfo(){
		System.out.println("------开始初始化劵信息-----------------------");
		CardGift cardGift = null;
		String card_type = cardGiftPro.getProperty("card_type", null);

		System.out.println("------获取到card_type:" + card_type);
		String logo_url = cardGiftPro.getProperty("logo_url", null);
		System.out.println("------获取到logo_url:" + logo_url);
		String code_type = cardGiftPro.getProperty("code_type", null);
		System.out.println("------获取到code_type:" + code_type);
		String[] code_types = split_str(code_type,"##"); 
		
		String brand_name = cardGiftPro.getProperty("brand_name", null);
		System.out.println("------获取到brand_name:" + brand_name);
		String[] brand_names = split_str(brand_name,"##"); 
		
		String sub_title = cardGiftPro.getProperty("sub_title", null);
		System.out.println("------获取到sub_title:" + sub_title);
		String[] sub_titles = split_str(sub_title,"##"); 
		
		String color = cardGiftPro.getProperty("color", null);
		System.out.println("------获取到color:" + color);
		String[] colors = split_str(color,"##"); 
		
		String notice = cardGiftPro.getProperty("notice", null);
		System.out.println("------获取到notice:" + notice);
		String[] notices = split_str(notice,"##"); 
		
		String description = cardGiftPro.getProperty("description", null);
		System.out.println("------获取到description:" + description);
		String[] descriptions = split_str(description,"##"); 
		
		String quantity = cardGiftPro.getProperty("quantity", null);
		System.out.println("------获取到quantity:" + quantity);
		String[] quantitys = split_str(quantity,"##"); 
		
		String type = cardGiftPro.getProperty("type", null);
		System.out.println("------获取到type:" + type);
		String[] types = split_str(type,"##"); 
		
		/*String begin_timestamp = cardGiftPro.getProperty("begin_timestamp", null);
		String[] begin_timestamps = split_str(begin_timestamp,"##");*/   //开始时间默认当前时间 结束时间默认向后推30天 
		
		String end_timestamp = cardGiftPro.getProperty("end_timestamp", null);
		String[] end_timestamps = split_str(end_timestamp,"##"); 
		
		String fixed_term = cardGiftPro.getProperty("fixed_term", null);
		System.out.println("------获取到fixed_term:" + fixed_term);
		String[]fixed_terms = split_str(fixed_term,"##"); 
		
		String fixed_begin_term = cardGiftPro.getProperty("fixed_begin_term", null);
		System.out.println("------获取到fixed_begin_term:" + fixed_begin_term);
		String[] fixed_begin_terms = split_str(fixed_begin_term,"##"); 
		
		
		String title = cardGiftPro.getProperty("title", null);
		System.out.println("------获取到title:" + title);
		String[] titles = split_str(title,"##"); 
		
		if(card_type == null || "".equals(card_type)
				|| logo_url == null || "".equals(logo_url)
				|| code_type == null || "".equals(code_type)
				|| card_type == null || "".equals(card_type)
				|| brand_name == null || "".equals(brand_name)
				|| color == null || "".equals(color)
				|| title == null || "".equals(title)
				|| notice == null || "".equals(notice)
				|| description == null || "".equals(description)
				|| quantity == null || "".equals(quantity)
				|| type == null || "".equals(type)
				){
			
			new RuntimeException("必要参数没有配置,请检查配置文件......");
			System.out.println("必要参数没有配置,系统退出...");
			System.exit(1);
			return;
		}
		
		
		
		/*String fixed_end_timestamp = cardGiftPro.getProperty("fixed_end_timestamp", null);
		String[] fixed_end_timestamps = split_str(fixed_end_timestamp,"##"); */
		
		String use_custom_code = cardGiftPro.getProperty("use_custom_code", null);
		System.out.println("------获取到use_custom_code:" + use_custom_code);
		String[] use_custom_codes = split_str(use_custom_code,"##"); 
		
		String bind_openid = cardGiftPro.getProperty("bind_openid", null);
		System.out.println("------获取到bind_openid:" + bind_openid);
		String[] bind_openids = split_str(bind_openid,"##"); 
		
		String service_phone = cardGiftPro.getProperty("service_phone", null);
		System.out.println("------获取到service_phone:" + service_phone);
		String[] service_phones = split_str(service_phone,"##"); 
		
		//String location_id_list = cardGiftPro.getProperty("location_id_list", null);
		//String[] code_types = split_str(code_type,"##"); 
		
		String source = cardGiftPro.getProperty("source", null);
		System.out.println("------获取到source:" + source);
		String[] sources = split_str(source,"##"); 
		
		String center_title = cardGiftPro.getProperty("center_title", null);
		System.out.println("------获取到center_title:" + center_title);
		String[] center_titles = split_str(center_title,"##"); 
		
		String center_sub_title = cardGiftPro.getProperty("center_sub_title", null);
		System.out.println("------获取到center_sub_title:" + center_sub_title);
		String[] center_sub_titles = split_str(center_sub_title,"##"); 
		
		String center_url = cardGiftPro.getProperty("center_url", null);
		System.out.println("------获取到center_url:" + center_url);
		String[] center_urls = split_str(center_url,"##"); 
		
		String custom_url_name = cardGiftPro.getProperty("custom_url_name", null);
		System.out.println("------获取到custom_url_name:" + custom_url_name);
		String[] custom_url_names = split_str(custom_url_name,"##"); 
		
		String custom_url = cardGiftPro.getProperty("custom_url", null);
		System.out.println("------获取到custom_url:" + custom_url);
		String[] custom_urls = split_str(custom_url,"##"); 
		
		String custom_url_sub_title = cardGiftPro.getProperty("custom_url_sub_title", null);
		System.out.println("------获取到custom_url_sub_title:" + custom_url_sub_title);
		String[] custom_url_sub_titles = split_str(custom_url_sub_title,"##"); 
		
		String promotion_url_name = cardGiftPro.getProperty("promotion_url_name", null);
		System.out.println("------获取到promotion_url_name:" + promotion_url_name);
		String[] promotion_url_names = split_str(promotion_url_name,"##"); 
		
		String promotion_url = cardGiftPro.getProperty("promotion_url", null);
		System.out.println("------获取到promotion_url:" + promotion_url);
		String[] promotion_urls = split_str(promotion_url,"##"); 
		
		String promotion_url_sub_title = cardGiftPro.getProperty("promotion_url_sub_title", null);
		System.out.println("------获取到promotion_url_sub_title:" + promotion_url_sub_title);
		String[] promotion_url_sub_titles = split_str(promotion_url_sub_title,"##"); 
		
		String get_limit = cardGiftPro.getProperty("get_limit", null);
		System.out.println("------获取到get_limit:" + get_limit);
		String[] get_limits = split_str(get_limit,"##"); 
		
		String can_share = cardGiftPro.getProperty("can_share", null);
		System.out.println("------获取到can_share:" + can_share);
		String[] can_shares = split_str(can_share,"##"); 
		
		String can_give_friend = cardGiftPro.getProperty("can_give_friend", null);
		System.out.println("------获取到can_give_friend:" + can_give_friend);
		String[] can_give_friends = split_str(can_give_friend,"##"); 
		
		
	
		String gift = cardGiftPro.getProperty("gift", null);
		System.out.println("------获取到gift:" + gift);
		String[] gifts = split_str(gift,"##"); 
	
		System.out.println("------获取信息完毕-----------------------");
		System.out.println("");
		System.out.println("------此次需要创建的卡券数量为" + titles.length + " 张");
		System.out.println("------开始装载卡券对象-----------------------");
		
		try {
			
			for(int i = 0;i<titles.length;i++){
				System.out.println("-------------------------------------------------------");
				System.out.println("------装载卡券对象"+i+1+"-----------------------");
				cardGift = new CardGift();
				cardGift.setCard_type(card_type);
				System.out.println("------卡券card_type:" + card_type + ",装载成功....");
				cardGift.setLogo_url(logo_url);
				System.out.println("------卡券logo_url:" + logo_url + ",装载成功....");
				if(null!=brand_names){
					cardGift.setBrand_name(brand_names[i]);
					System.out.println("------卡券Brand_name:" + brand_names[i] + ",装载成功....");
				}
				if(null!=code_types){
					cardGift.setCode_type(code_types[i]);
					System.out.println("------卡券Code_type:" + code_types[i] + ",装载成功....");
				}
				if(null!=titles){
					cardGift.setTitle(titles[i]);
					System.out.println("------卡券Title:" + titles[i] + ",装载成功....");
				}
				if(null!=sub_titles){
					cardGift.setSub_title(sub_titles[i]);
					System.out.println("------卡券Sub_title:" + sub_titles[i] + ",装载成功....");
				}
				if(null!=colors){
					cardGift.setColor(colors[i]);
					System.out.println("------卡券Color:" + colors[i] + ",装载成功....");
				}
				if(null!=notices){
					cardGift.setNotice(notices[i]);
					System.out.println("------卡券Notice:" + notices[i] + ",装载成功....");
				}
				if(null!=service_phones){
					cardGift.setService_phone(service_phones[i]);
					System.out.println("------卡券Service_phone:" + service_phones[i] + ",装载成功....");
				}
				if(null!=descriptions){
					cardGift.setDescription(descriptions[i]);
					System.out.println("------卡券Description:" + descriptions[i] + ",装载成功....");
				}
				if(null!=types){
					cardGift.setType(types[i]);
					System.out.println("------卡券时间Type:" + types[i] + ",装载成功....");
					if("DATE_TYPE_FIX_TIME_RANGE".equals(types[i])){
						cardGift.setBegin_timestamp(System.currentTimeMillis()/1000);
						System.out.println("------卡券时间Begin_timestamp,装载成功....");
						cardGift.setEnd_timestamp(Long.parseLong(end_timestamps[i]));//结束加30天
						System.out.println("------卡券时间End_timestamp,装载成功....");
					}
					if("DATE_TYPE_FIX_TERM".equals(types[i])){
						cardGift.setFixed_term(Long.parseLong(fixed_terms[i]));
						System.out.println("------卡券Fixed_term:" + fixed_terms[i] + ",装载成功....");
						cardGift.setFixed_begin_term(Long.parseLong(fixed_begin_terms[i]));
						System.out.println("------卡券Fixed_begin_term:" + fixed_begin_terms[i] + ",装载成功....");
					}
				}
		
				if(null!=quantitys){
					cardGift.setQuantity(Integer.parseInt(quantitys[i]));
					System.out.println("------卡券Quantity:" + quantitys[i] + ",装载成功....");
				}
				if(null!=get_limits){
					cardGift.setGet_limit(Integer.parseInt(get_limits[i]));
					System.out.println("------卡券Get_limit:" + get_limits[i] + ",装载成功....");
				}
				if(null!=use_custom_codes){
					cardGift.setUse_custom_code(Boolean.parseBoolean(use_custom_codes[i])); //是否自定义code码
					System.out.println("------卡券Use_custom_code:" + use_custom_codes[i] + ",装载成功....");
				}
				if(null!=bind_openids){
					cardGift.setBind_openid(Boolean.parseBoolean(bind_openids[i]));//绑定的openid
					System.out.println("------卡券Bind_openid:" + bind_openids[i] + ",装载成功....");
				}
				if(null!=can_shares){
					cardGift.setCan_share(Boolean.parseBoolean(can_shares[i])); //是否可分享
					System.out.println("------卡券Can_share:" + can_shares[i] + ",装载成功....");
				}
				if(null!=can_give_friends){
					cardGift.setCan_give_friend(Boolean.parseBoolean(can_give_friends[i])); //是否可转赠朋友
					System.out.println("------卡券Can_give_friend:" + can_give_friends[i] + ",装载成功....");
				}
				if(null!=center_titles){
					cardGift.setCenter_title(center_titles[i]);
					System.out.println("------卡券Center_title:" + center_titles[i] + ",装载成功....");
				}
				if(null!=center_sub_titles){
					cardGift.setCenter_sub_title(center_sub_titles[i]);
					System.out.println("------卡券Center_sub_title:" + center_sub_titles[i] + ",装载成功....");
				}
				if(null!=center_urls){
					cardGift.setCenter_url(center_urls[i]);
					System.out.println("------卡券Center_url:" + center_urls[i] + ",装载成功....");
				}
				if(null!=custom_url_names){
					cardGift.setCustom_url_name(custom_url_names[i]);
					System.out.println("------卡券Custom_url_name:" + custom_url_names[i] + ",装载成功....");
				}
				if(null!=custom_urls){
					cardGift.setCustom_url(custom_urls[i]);
					System.out.println("------卡券Custom_url:" + custom_urls[i] + ",装载成功....");
				}
				if(null!=custom_url_sub_titles){
					cardGift.setCustom_url_sub_title(custom_url_sub_titles[i]);
					System.out.println("------卡券Custom_url_sub_title:" + custom_url_sub_titles[i] + ",装载成功....");
				}
				
				if(null!=promotion_url_names){
					cardGift.setPromotion_url_name(promotion_url_names[i]);
					System.out.println("------卡券Promotion_url_name:" + promotion_url_names[i] + ",装载成功....");
				}
				
				if(null!=promotion_urls){
					cardGift.setPromotion_url(promotion_urls[i]);
					System.out.println("------卡券Promotion_url:" + promotion_urls[i] + ",装载成功....");
				}
				if(null!=promotion_url_sub_titles){
					cardGift.setPromotion_url_sub_title(promotion_url_sub_titles[i]);
					System.out.println("------卡券Promotion_url_sub_title:" + promotion_url_sub_titles[i] + ",装载成功....");
				}
				if(null!=sources){
					cardGift.setSource(sources[i]);
					System.out.println("------卡券Source:" + sources[i] + ",装载成功....");
				}
				if(null!=gifts){
					cardGift.setGift(gifts[i]);
					System.out.println("------卡券Gift:" + gifts[i] + ",装载成功....");
				}

				giftCards.add(cardGift);
				System.out.println("------卡券对象" + i+1 + "成功装载....");

			}
			
			System.out.println("初始化劵信息over....");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			System.out.println("初始化劵信息过程中出错:" + e.getMessage());
			
			System.exit(1);
		}
	
	}
	
	
	
	/**          
	 * 方法描述：创建卡券 	   
	 * 创建人：zhangzhongwen   
	 * 创建时间：2017年4月19日 下午1:27:23   
	 * @version 1.0       
	 */
	public void startCreateCardGift(){

		try {
			
			System.out.println("------需要创建" + giftCards.size() + "张卡券----------");
			if(null != giftCards && giftCards.size() > 0){
				//String cardJson = null;
				System.out.println("------获取接入微信临时票据---------");
				String access_token = WxUtils.getWXAccessTokenByJt(
						WX.APPID.getValue(), WX.APPSECRET.getValue(), WX.GRANT_TYPE.getValue());;
				System.out.println("------获取接入微信临时票据access_token成功:" + access_token);
				
				if(null != access_token){
					File file = new File("card_list.log");
					FileWriter os = new  FileWriter(file);
					int i = 1;
					System.out.println("------开始创建兑换劵....");
					
					StringBuffer sb = new StringBuffer();
					
					for(CardGift giftCard :giftCards){
						/*cardJson = WxUtils.getCreateCardJsonStr(giftCard, giftCard.getCard_type());
						System.out.println("cardJson:" + cardJson);*/
						System.out.println("--------------------------------------------");
						System.out.println("------开始正式创建第"+i+"张卡券-------------");
						String create_result = WxUtils.create_card(access_token, giftCard, giftCard.getCard_type());
						System.out.println("------创建卡券结果create_result为:" + create_result);
						sb.append("卡券名称:" + giftCard.getTitle() + ",创建结果:" + create_result + "\r\n");
						i++;
						break;
					}
					
					os.write(sb.toString());
					os.flush();
					os.close();
	
					System.out.println("------卡券创建完毕------------------------");
				}else{
					System.out.println("------不能创建微信卡券,获取到临时调用凭证access_token为:" + access_token);
				}

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("创建卡券出错:"+e.getMessage());
		}
		
		
	}

	public static void main(String[] args) {
		System.out.println("------进入main............");
		Properties pro = new Properties();
		
		try {
			System.out.println("------开始加载配置文件"+CARD_TYPE.GIFT.getPropertieName()+".........");
			pro.load(new InputStreamReader(
					new FileInputStream(CARD_TYPE.GIFT.getPropertieName()), "UTF-8") );
			System.out.println("------配置文件加载完毕.........");
			
			System.out.println("------配置文件加载完毕.........");
			CreateWxCard createWxCard = new  CreateWxCard(pro);
			
			createWxCard.startCreateCardGift();	
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("------找不到配置文件:" + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("------文件读取异常:" + e.getMessage());
		}

	}

}
