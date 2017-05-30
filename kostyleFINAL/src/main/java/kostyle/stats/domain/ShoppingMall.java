package stats.model;

import java.io.Serializable;

public class ShoppingMall implements Serializable{
	private String s_num;
	private String s_manager;
	private String s_shopurl;
	private String s_shopreg;
	private String s_sname;
	private String s_email;
	private String s_age;
	private String s_phonenumber;
	
	public ShoppingMall(){}

	public ShoppingMall(String s_num, String s_manager, String s_shopurl, String s_shopreg, String s_sname,
			String s_email, String s_age, String s_phonenumber) {
		super();
		this.s_num = s_num;
		this.s_manager = s_manager;
		this.s_shopurl = s_shopurl;
		this.s_shopreg = s_shopreg;
		this.s_sname = s_sname;
		this.s_email = s_email;
		this.s_age = s_age;
		this.s_phonenumber = s_phonenumber;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public String getS_manager() {
		return s_manager;
	}

	public void setS_manager(String s_manager) {
		this.s_manager = s_manager;
	}

	public String getS_shopurl() {
		return s_shopurl;
	}

	public void setS_shopurl(String s_shopurl) {
		this.s_shopurl = s_shopurl;
	}

	public String getS_shopreg() {
		return s_shopreg;
	}

	public void setS_shopreg(String s_shopreg) {
		this.s_shopreg = s_shopreg;
	}

	public String getS_sname() {
		return s_sname;
	}

	public void setS_sname(String s_sname) {
		this.s_sname = s_sname;
	}

	public String getS_email() {
		return s_email;
	}

	public void setS_email(String s_email) {
		this.s_email = s_email;
	}

	public String getS_age() {
		return s_age;
	}

	public void setS_age(String s_age) {
		this.s_age = s_age;
	}

	public String getS_phonenumber() {
		return s_phonenumber;
	}

	public void setS_phonenumber(String s_phonenumber) {
		this.s_phonenumber = s_phonenumber;
	}
	
	
}
