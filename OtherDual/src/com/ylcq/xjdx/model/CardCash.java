package com.ylcq.xjdx.model;

import java.io.Serializable;

import com.ylcq.xjdx.utils.Model517Utils.CREATE_CART_COLORSELECT;
import com.ylcq.xjdx.utils.Model517Utils.CREATE_CART_TYPE;

/**   
 *    
 * 项目名称：activityWeb   
 * 类名称：CardCash   
 * 描述：   代金券类型。
 * 创建人：zhangzhongwen   
 * 创建时间：2017年4月12日 下午4:52:25   
 * @version 1.0       
 */
public class CardCash extends CardBase implements Serializable  {

	private static final long serialVersionUID = 2776665612240922276L;
	//代金券专用，表示起用金额（单位为分）,如果无起用门槛则填0。
	private int least_cost; 
	//代金券专用，表示减免金额。（单位为分）
	private int reduce_cost;
	
	public int getLeast_cost() {
		return least_cost;
	}
	public void setLeast_cost(int least_cost) {
		this.least_cost = least_cost;
	}
	public int getReduce_cost() {
		return reduce_cost;
	}
	public void setReduce_cost(int reduce_cost) {
		this.reduce_cost = reduce_cost;
	}
	
	public static void main(String[] args) {
		 System.out.println("" + CREATE_CART_TYPE.CASH);
		 System.out.println("" + CREATE_CART_COLORSELECT.Color010);
		 
	}
}
