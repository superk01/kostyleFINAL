package Category.model;

import java.io.Serializable;

public class Adshoppingmall implements Serializable{
	private String s_num;
	private String s_manager;
	private String s_shopurl;
	private String s_searchurl;

	
	public Adshoppingmall(String s_num, String s_manager, String s_shopurl, String s_searchurl) {
		super();
		this.s_num = s_num;
		this.s_manager = s_manager;
		this.s_shopurl = s_shopurl;
		this.s_searchurl = s_searchurl;
	}

	public String getS_manager() {
		return s_manager;
	}

	public void setS_manager(String s_manager) {
		this.s_manager = s_manager;
	}

	public Adshoppingmall(){}
	
	public Adshoppingmall(String s_num, String s_shopurl, String s_searchurl) {
		super();
		this.s_num = s_num;
		this.s_shopurl = s_shopurl;
		this.s_searchurl = s_searchurl;
	}
	public String getS_num() {
		return s_num;
	}
	public void setS_num(String s_num) {
		this.s_num = s_num;
	}
	public String getS_shopurl() {
		return s_shopurl;
	}
	public void setS_shopurl(String s_shopurl) {
		this.s_shopurl = s_shopurl;
	}
	public String getS_searchurl() {
		return s_searchurl;
	}
	public void setS_searchurl(String s_searchurl) {
		this.s_searchurl = s_searchurl;
	}
	
	
}
