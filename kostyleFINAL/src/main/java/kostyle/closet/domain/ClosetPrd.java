package closet.model;

import java.io.Serializable;

public class ClosetPrd implements Serializable{
	private String c_num;
	private int clo_detail_num;
	private int clo_num;
	private String clo_name;
	private String clo_prdUrl;
	private String clo_imgUrl;
	private String clo_prdName;
	private String clo_price;
	private String s_sname;
	private String clo_date;
	private int clo_zzim;
	
	public ClosetPrd(){}

	public ClosetPrd(String c_num, int clo_detail_num, int clo_num, String clo_name, String clo_prdUrl, String clo_imgUrl,
			String clo_prdName, String clo_price, String s_sname, String clo_date,int clo_zzim) {
		super();
		this.c_num = c_num;
		this.clo_detail_num = clo_detail_num;
		this.clo_num = clo_num;
		this.clo_prdUrl = clo_prdUrl;
		this.clo_imgUrl = clo_imgUrl;
		this.clo_prdName = clo_prdName;
		this.clo_price = clo_price;
		this.s_sname = s_sname;
		this.clo_date = clo_date;
		this.clo_zzim = clo_zzim;
	}

	
	public String getC_num() {
		return c_num;
	}

	public void setC_num(String c_num) {
		this.c_num = c_num;
	}

	public int getClo_detail_num() {
		return clo_detail_num;
	}

	public void setClo_detail_num(int clo_detail_num) {
		this.clo_detail_num = clo_detail_num;
	}

	public int getClo_num() {
		return clo_num;
	}

	public void setClo_num(int clo_num) {
		this.clo_num = clo_num;
	}

	public String getClo_name() {
		return clo_name;
	}

	public void setClo_name(String clo_name) {
		this.clo_name = clo_name;
	}

	public String getClo_prdUrl() {
		return clo_prdUrl;
	}

	public void setClo_prdUrl(String clo_prdUrl) {
		this.clo_prdUrl = clo_prdUrl;
	}

	public String getClo_imgUrl() {
		return clo_imgUrl;
	}

	public void setClo_imgUrl(String clo_imgUrl) {
		this.clo_imgUrl = clo_imgUrl;
	}

	public String getClo_prdName() {
		return clo_prdName;
	}

	public void setClo_prdName(String clo_prdName) {
		this.clo_prdName = clo_prdName;
	}

	public String getClo_price() {
		return clo_price;
	}

	public void setClo_price(String clo_price) {
		this.clo_price = clo_price;
	}

	public String getS_sname() {
		return s_sname;
	}

	public void setS_sname(String s_sname) {
		this.s_sname = s_sname;
	}

	public String getClo_date() {
		return clo_date;
	}

	public void setClo_date(String clo_date) {
		this.clo_date = clo_date;
	}
	
	public int getClo_zzim() {
		return clo_zzim;
	}

	public void setClo_zzim(int clo_zzim) {
		this.clo_zzim = clo_zzim;
	}

	@Override
	public String toString() {
		return "ClosetPrd [c_num=" + c_num + ", clo_detail_num=" + clo_detail_num + ", clo_num=" + clo_num
				+ ", clo_prdUrl=" + clo_prdUrl + ", clo_imgUrl=" + clo_imgUrl + ", clo_prdName=" + clo_prdName
				+ ", clo_price=" + clo_price + ", s_sname=" + s_sname + ", clo_date=" + clo_date + ", clo_zzim="
				+ clo_zzim + "]";
	}

	
	
}
