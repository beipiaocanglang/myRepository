package com.ylcq.xjdx.model;

import java.io.Serializable;

/**   
 *    
 * 项目名称：activityWeb   
 * 类名称：CardGroupon   
 * 描述：   团购券类型。
 * 创建人：zhangzhongwen   
 * 创建时间：2017年4月12日 下午4:53:36   
 * @version 1.0       
 */
public class CardGroupon extends CardBase implements Serializable {
	
	
	private static final long serialVersionUID = -2320488231401323803L;
	private String deal_detail;

	public String getDeal_detail() {
		return deal_detail;
	}

	public void setDeal_detail(String deal_detail) {
		this.deal_detail = deal_detail;
	}
	
	
}
