package Category.model;

import java.io.Serializable;

public class Customer_taste_Select implements Serializable{
	private int count = 0;
	private String c_click_keyword = "";
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getC_click_keyword() {
		return c_click_keyword;
	}
	public void setC_click_keyword(String c_click_keyword) {
		this.c_click_keyword = c_click_keyword;
	}
	public Customer_taste_Select(int count, String c_click_keyword) {
		this.count = count;
		this.c_click_keyword = c_click_keyword;
	}
	public Customer_taste_Select(){}
}
