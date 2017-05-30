package stats.model;

import java.io.Serializable;

public class HitCount implements Serializable{
	private String s_num;
	private String cnt_prdurl;
	private String cnt_date;
	private String c_num;
	
	private int seoul;
	private int busan;
	private int jeju;
	
	private int female;
	private int male;
	
	public HitCount(){}

	public HitCount(String s_num, String cnt_prdurl, String cnt_date, String c_num, int seoul, int busan, int jeju,
			int female, int male) {
		super();
		this.s_num = s_num;
		this.cnt_prdurl = cnt_prdurl;
		this.cnt_date = cnt_date;
		this.c_num = c_num;
		this.seoul = seoul;
		this.busan = busan;
		this.jeju = jeju;
		this.female = female;
		this.male = male;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public String getCnt_prdurl() {
		return cnt_prdurl;
	}

	public void setCnt_prdurl(String cnt_prdurl) {
		this.cnt_prdurl = cnt_prdurl;
	}

	public String getCnt_date() {
		return cnt_date;
	}

	public void setCnt_date(String cnt_date) {
		this.cnt_date = cnt_date;
	}

	public String getC_num() {
		return c_num;
	}

	public void setC_num(String c_num) {
		this.c_num = c_num;
	}

	public int getSeoul() {
		return seoul;
	}

	public void setSeoul(int seoul) {
		this.seoul = seoul;
	}

	public int getBusan() {
		return busan;
	}

	public void setBusan(int busan) {
		this.busan = busan;
	}

	public int getJeju() {
		return jeju;
	}

	public void setJeju(int jeju) {
		this.jeju = jeju;
	}

	public int getFemale() {
		return female;
	}

	public void setFemale(int female) {
		this.female = female;
	}

	public int getMale() {
		return male;
	}

	public void setMale(int male) {
		this.male = male;
	}
	
	
	

	
}
