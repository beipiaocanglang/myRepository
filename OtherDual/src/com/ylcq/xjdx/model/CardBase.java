package com.ylcq.xjdx.model;

import java.io.Serializable;
import java.util.List;

/**   
 *    
 * 项目名称：activityWeb   
 * 类名称：CardBase   
 * 描述：   卡券基类
 * 创建人：zhangzhongwen   
 * 创建时间：2017年4月12日 下午4:52:01   
 * @version 1.0       
 */
public class CardBase implements Serializable {

	private static final long serialVersionUID = 4469204416433909007L;
	
	//--------------------------必填字段信息start----------------------		
	private String card_type; 
	private String base_info; //
	private String logo_url;//卡券的商户logo，建议像素为300*300。
	private String code_type;//Code展示类型，"CODE_TYPE_TEXT"，文本；"CODE_TYPE_BARCODE"，一维码 ；"CODE_TYPE_QRCODE"，二维码；"CODE_TYPE_ONLY_QRCODE",二维码无code显示；"CODE_TYPE_ONLY_BARCODE",一维码无code显示；CODE_TYPE_NONE，不显示code和条形码类型，须开发者传入"立即使用"自定义cell完成线上券核销
	private String brand_name;//商户名字,字数上限为12个汉字。
	private String title;//卡券名，字数上限为9个汉字。(建议涵盖卡券属性、服务及金额)。
	private String sub_title;//券名，字数上限为18个汉字。
	private String color;//券颜色。按色彩规范标注填写Color010-Color100。
	private String notice;//卡券使用提醒，字数上限为16个汉字。
	private String description;//卡券使用说明，字数上限为1024个汉字。
		
	private int quantity;//卡券库存的数量，上限为100000000。
	//private String date_info;//使用日期，有效期的信息 JSON结构
	private String type;//使用时间的类型，旧文档采用的1和2依然生效。
	private long begin_timestamp;//type为DATE_TYPE_FIX_TIME_RANGE时专用，表示起用时间。从1970年1月1日00:00:00至起用时间的秒数，最终需转换为字符串形态传入。（东八区时间，单位为秒）
	private long end_timestamp;//ype为DATE_TYPE_FIX_TIME_RANGE时，表示卡券统一的结束时间，建议设置为截止日期的23:59:59过期。（东八区时间，单位为秒）
	private long fixed_term;//type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天内有效，不支持填写0。
	private long fixed_begin_term;//type为DATE_TYPE_FIX_TERM时专用，表示自领取后多少天开始生效，领取后当天生效填写0。（单位为天）
	private long fixed_end_timestamp;//可用于DATE_TYPE_FIX_TERM时间类型，表示卡券统一过期时间，建议设置为截止日期的23:59:59过期。（东八区时间，单位为秒），设置了fixed_term卡券，当时间达到end_timestamp时卡券统一过期
	//--------------------------必填字段信息end-------------------------
	
	//--------------------------非必填字段信息start----------------------
	
	private boolean use_custom_code = false;//是否自定义Code码。填写true或false，默认为false。通常自有优惠码系统的开发者选择自定义Code码，并在卡券投放时带入Code码，详情见是否自定义Code码。
	private boolean bind_openid = false;//是否指定用户领取，填写true或false。默认为false。通常指定特殊用户群体投放卡券或防止刷券时选择指定用户领取。
	private String service_phone;//客服电话。
	private List<String> location_id_list;//门店位置poiid。调用POI门店管理接口获取门店位置poiid。具备线下门店的商户为必填。
	private String source;//第三方来源名，例如同程旅游、大众点评。
	private String custom_url_name;//自定义跳转外链的入口名字。
	private String center_title;//卡券顶部居中的按钮，仅在卡券状态正常(可以核销)时显示，建议开发者设置此按钮时code_type选择CODE_TYPE_NONE类型。
	private String center_sub_title;//显示在入口下方的提示语，仅在卡券状态正常(可以核销)时显示。
	private String center_url;//顶部居中的url，仅在卡券状态正常(可以核销)时显示。
	private String custom_url;//自定义跳转的URL。
	private String custom_url_sub_title;//显示在入口右侧的提示语
	private String promotion_url_name;//营销场景的自定义入口名称。
	private String promotion_url;//入口跳转外链的地址链接。
	private String promotion_url_sub_title;//显示在营销入口右侧的提示语
	private int get_limit;//每人可领券的数量限制,不填写默认为50
	private boolean can_share = true;//卡券领取页面是否可分享。
	private boolean can_give_friend = true;//卡券是否可转赠。
	
	//--------------------------非必填字段信息END----------------------
	
	
	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getBase_info() {
		return base_info;
	}

	public void setBase_info(String base_info) {
		this.base_info = base_info;
	}

	public String getLogo_url() {
		return logo_url;
	}


	public void setLogo_url(String logo_url) {
		this.logo_url = logo_url;
	}

	public String getCode_type() {
		return code_type;
	}

	public void setCode_type(String code_type) {
		this.code_type = code_type;
	}

	public String getBrand_name() {
		return brand_name;
	}

	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

	public String getService_phone() {
		return service_phone;
	}

	public void setService_phone(String service_phone) {
		this.service_phone = service_phone;
	}

	

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getCustom_url_name() {
		return custom_url_name;
	}

	public void setCustom_url_name(String custom_url_name) {
		this.custom_url_name = custom_url_name;
	}

	public String getCenter_title() {
		return center_title;
	}

	public void setCenter_title(String center_title) {
		this.center_title = center_title;
	}
	public String getCenter_sub_title() {
		return center_sub_title;
	}

	public void setCenter_sub_title(String center_sub_title) {
		this.center_sub_title = center_sub_title;
	}

	public String getCenter_url() {
		return center_url;
	}

	public void setCenter_url(String center_url) {
		this.center_url = center_url;
	}

	public String getCustom_url() {
		return custom_url;
	}

	public void setCustom_url(String custom_url) {
		this.custom_url = custom_url;
	}

	public String getCustom_url_sub_title() {
		return custom_url_sub_title;
	}

	public void setCustom_url_sub_title(String custom_url_sub_title) {
		this.custom_url_sub_title = custom_url_sub_title;
	}

	public String getPromotion_url_name() {
		return promotion_url_name;
	}

	public void setPromotion_url_name(String promotion_url_name) {
		this.promotion_url_name = promotion_url_name;
	}

	public String getPromotion_url() {
		return promotion_url;
	}

	public void setPromotion_url(String promotion_url) {
		this.promotion_url = promotion_url;
	}

	public String getPromotion_url_sub_title() {
		return promotion_url_sub_title;
	}

	public void setPromotion_url_sub_title(String promotion_url_sub_title) {
		this.promotion_url_sub_title = promotion_url_sub_title;
	}


	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public long getBegin_timestamp() {
		return begin_timestamp;
	}

	public void setBegin_timestamp(long begin_timestamp) {
		this.begin_timestamp = begin_timestamp;
	}

	public long getEnd_timestamp() {
		return end_timestamp;
	}

	public void setEnd_timestamp(long end_timestamp) {
		this.end_timestamp = end_timestamp;
	}

	public long getFixed_term() {
		return fixed_term;
	}

	public void setFixed_term(long fixed_term) {
		this.fixed_term = fixed_term;
	}

	public long getFixed_begin_term() {
		return fixed_begin_term;
	}

	public void setFixed_begin_term(long fixed_begin_term) {
		this.fixed_begin_term = fixed_begin_term;
	}

	public long getFixed_end_timestamp() {
		return fixed_end_timestamp;
	}

	public void setFixed_end_timestamp(long fixed_end_timestamp) {
		this.fixed_end_timestamp = fixed_end_timestamp;
	}

	public boolean getUse_custom_code() {
		return use_custom_code;
	}

	public void setUse_custom_code(boolean use_custom_code) {
		this.use_custom_code = use_custom_code;
	}

	public boolean getBind_openid() {
		return bind_openid;
	}

	public void setBind_openid(boolean bind_openid) {
		this.bind_openid = bind_openid;
	}

	public List<String> getLocation_id_list() {
		return location_id_list;
	}

	public void setLocation_id_list(List<String> location_id_list) {
		this.location_id_list = location_id_list;
	}

	public int getGet_limit() {
		return get_limit;
	}

	public void setGet_limit(int get_limit) {
		this.get_limit = get_limit;
	}

	public boolean getCan_share() {
		return can_share;
	}

	public void setCan_share(boolean can_share) {
		this.can_share = can_share;
	}

	public boolean getCan_give_friend() {
		return can_give_friend;
	}

	public void setCan_give_friend(boolean can_give_friend) {
		this.can_give_friend = can_give_friend;
	}

	@Override
	public String toString() {
		return "CardBase [card_type=" + card_type + ", base_info=" + base_info
				+ ", logo_url=" + logo_url + ", code_type=" + code_type
				+ ", brand_name=" + brand_name + ", title=" + title
				+ ", sub_title=" + sub_title + ", color=" + color + ", notice="
				+ notice + ", description=" + description
				+ ", quantity=" + quantity
				+ ", type=" + type + ", begin_timestamp=" + begin_timestamp
				+ ", end_timestamp=" + end_timestamp + ", fixed_term="
				+ fixed_term + ", fixed_begin_term=" + fixed_begin_term
				+ ", fixed_end_timestamp=" + fixed_end_timestamp
				+ ", use_custom_code=" + use_custom_code + ", bind_openid="
				+ bind_openid + ", service_phone=" + service_phone
				+ ", location_id_list=" + location_id_list + ", source="
				+ source + ", custom_url_name=" + custom_url_name
				+ ", center_title=" + center_title + ", center_sub_title="
				+ center_sub_title + ", center_url=" + center_url
				+ ", custom_url=" + custom_url + ", custom_url_sub_title="
				+ custom_url_sub_title + ", promotion_url_name="
				+ promotion_url_name + ", promotion_url=" + promotion_url
				+ ", promotion_url_sub_title=" + promotion_url_sub_title
				+ ", get_limit=" + get_limit + ", can_share=" + can_share
				+ ", can_give_friend=" + can_give_friend + "]";
	}
	
	
	
	
	
}
