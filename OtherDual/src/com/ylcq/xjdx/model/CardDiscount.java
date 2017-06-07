package com.ylcq.xjdx.model;

import java.io.Serializable;

/**   
 *    
 * 项目名称：activityWeb   
 * 类名称：CardDiscount   
 * 描述：   折扣券类型
 * 创建人：zhangzhongwen   
 * 创建时间：2017年4月12日 下午4:52:48   
 * @version 1.0       
 */
public class CardDiscount extends CardBase implements Serializable {
	
	private static final long serialVersionUID = 887814573169662636L;
	//折扣券专用，表示打折额度（百分比）。填30就是七折
	private int discount;

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}
}
