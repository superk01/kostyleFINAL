package Category.model;

import java.io.Serializable;

public class Customer_taste_stack implements Serializable{
	private String c_num = "";
	private String c_click_keyword = "";
	private String p_url = "";
	
	public String getC_num() {
		return c_num;
	}
	public void setC_num(String c_num) {
		this.c_num = c_num;
	}
	public String getC_click_keyword() {
		return c_click_keyword;
	}
	public void setC_click_keyword(String c_click_keyword) {
		this.c_click_keyword = c_click_keyword;
	}
	public String getP_url() {
		return p_url;
	}
	public void setP_url(String p_url) {
		this.p_url = p_url;
	}
	public Customer_taste_stack(String c_num, String c_click_keyword, String p_url) {
		this.c_num = c_num;
		this.c_click_keyword = c_click_keyword;
		this.p_url = p_url;
	}
	@Override
	public String toString() {
		return "Customer_taste_stack [c_num=" + c_num + ", c_click_keyword=" + c_click_keyword + ", p_url=" + p_url
				+ "]";
	}
}
