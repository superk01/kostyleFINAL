package login.model;

import java.io.Serializable;

public class AdShopMember implements Serializable {
	private String s_num;
	private int ad_id;
	private String ad_pass;
	private int ad_hitcount;
	private int s_grade;
	private int p_powernum;
	
	public AdShopMember(){}

	public AdShopMember (String s_num, int ad_id, String ad_pass, int ad_hitcount, int s_grade, int p_powernum) {
		super();
		this.s_num = s_num;
		this.ad_id = ad_id;
		this.ad_pass = ad_pass;
		this.ad_hitcount = ad_hitcount;
		this.s_grade = s_grade;
		this.p_powernum = p_powernum;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public int getAd_id() {
		return ad_id;
	}

	public void setAd_id(int ad_id) {
		this.ad_id = ad_id;
	}

	public String getAd_pass() {
		return ad_pass;
	}

	public void setAd_pass(String ad_pass) {
		this.ad_pass = ad_pass;
	}

	public int getAd_hitcount() {
		return ad_hitcount;
	}

	public void setAd_hitcount(int ad_hitcount) {
		this.ad_hitcount = ad_hitcount;
	}

	public int getS_grade() {
		return s_grade;
	}

	public void setS_grade(int s_grade) {
		this.s_grade = s_grade;
	}

	public int getP_powernum() {
		return p_powernum;
	}

	public void setP_powernum(int p_powernum) {
		this.p_powernum = p_powernum;
	}

	@Override
	public String toString() {
		return "AdShopMember [s_num=" + s_num + ", ad_id=" + ad_id + ", ad_pass=" + ad_pass + ", ad_hitcount="
				+ ad_hitcount + ", s_grade=" + s_grade + ", p_powernum=" + p_powernum + "]";
	}
	


	
}
