package com.ylcq.xjdx.model;

import java.io.Serializable;

/**   
 *    
 * 项目名称：activityWeb   
 * 类名称：CardGeneralCoupon   
 * 描述： 优惠券类型。  
 * 创建人：zhangzhongwen   
 * 创建时间：2017年4月12日 下午4:53:03   
 * @version 1.0       
 */
public class CardGeneralCoupon extends CardBase implements Serializable {

	private static final long serialVersionUID = 6266409187056710249L;
	
	//优惠券专用，填写优惠详情。
	private String default_detail;

	public String getDefault_detail() {
		return default_detail;
	}

	public void setDefault_detail(String default_detail) {
		this.default_detail = default_detail;
	}
	
	
}
