package kostyle.help.domain;

import java.io.Serializable;

public class AdShoppingMall implements Serializable{
	private String s_Name;
	private String ad_Id;
	private String ad_Pass;
	private int ad_Hitcount;
	private int s_Grade;
	private int p_PowerNum;
	
	public AdShoppingMall(){}

	public AdShoppingMall(String s_Name, String ad_Id, String ad_Pass, int ad_Hitcount, int s_Grade, int p_PowerNum) {
		super();
		this.s_Name = s_Name;
		this.ad_Id = ad_Id;
		this.ad_Pass = ad_Pass;
		this.ad_Hitcount = ad_Hitcount;
		this.s_Grade = s_Grade;
		this.p_PowerNum = p_PowerNum;
	}

	public String gets_Name() {
		return s_Name;
	}

	public void sets_Name(String s_Name) {
		this.s_Name = s_Name;
	}

	public String getAd_Id() {
		return ad_Id;
	}

	public void setAd_Id(String ad_Id) {
		this.ad_Id = ad_Id;
	}

	public String getAd_Pass() {
		return ad_Pass;
	}

	public void setAd_Pass(String ad_Pass) {
		this.ad_Pass = ad_Pass;
	}

	public int getAd_Hitcount() {
		return ad_Hitcount;
	}

	public void setAd_Hitcount(int ad_Hitcount) {
		this.ad_Hitcount = ad_Hitcount;
	}

	public int getS_Grade() {
		return s_Grade;
	}

	public void setS_Grade(int s_Grade) {
		this.s_Grade = s_Grade;
	}

	public int getP_PowerNum() {
		return p_PowerNum;
	}

	public void setP_PowerNum(int p_PowerNum) {
		this.p_PowerNum = p_PowerNum;
	}

	@Override
	public String toString() {
		return "AdShoppingMall [s_Name=" + s_Name + ", ad_Id=" + ad_Id + ", ad_Pass=" + ad_Pass + ", ad_Hitcount="
				+ ad_Hitcount + ", s_Grade=" + s_Grade + ", p_PowerNum=" + p_PowerNum + "]";
	}
	
	
}
