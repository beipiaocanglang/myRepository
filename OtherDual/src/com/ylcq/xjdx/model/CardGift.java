package com.ylcq.xjdx.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**   
 *    
 * 项目名称：activityWeb   
 * 类名称：CardGift   
 * 描述：   兑换券类型。
 * 创建人：zhangzhongwen   
 * 创建时间：2017年4月12日 下午4:53:24   
 * @version 1.0       
 */
public class CardGift extends CardBase implements Serializable {
	
	private static final long serialVersionUID = -2083426191442940798L;
	//兑换券专用，填写兑换内容的名称。
	private String gift;

	public String getGift() {
		return gift;
	}

	public void setGift(String gift) {
		this.gift = gift;
	}
	
	public static void main(String[] args) {
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date =	format.parse("2017-07-31 23:59:59");
			
			System.out.println("" +date.getTime()/1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
	}
	
}
