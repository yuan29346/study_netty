/**
 * 
 */
package com.hanxuan.bank;

/**
 * @author Administrator
 *
 */
public enum CustomerType {

	COMMON,EXPRESS,VIP;
	
	public String toString(){
		switch(this){
		case COMMON : return "��ͨ";
		case EXPRESS :return "����" ;
		case VIP : return name();
		}
		return null;
	}
}
