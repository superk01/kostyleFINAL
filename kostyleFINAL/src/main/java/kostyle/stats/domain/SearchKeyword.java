package stats.model;

import java.io.Serializable;

public class SearchKeyword implements Serializable{
	private String c_num;
	private String sk_searchkey;
	private String sk_date;
	
	public SearchKeyword(){}

	public SearchKeyword(String c_num, String sk_searchkey, String sk_date) {
		super();
		this.c_num = c_num;
		this.sk_searchkey = sk_searchkey;
		this.sk_date = sk_date;
	}

	public String getC_num() {
		return c_num;
	}

	public void setC_num(String c_num) {
		this.c_num = c_num;
	}

	public String getSk_searchkey() {
		return sk_searchkey;
	}

	public void setSk_searchkey(String sk_searchkey) {
		this.sk_searchkey = sk_searchkey;
	}

	public String getSk_date() {
		return sk_date;
	}

	public void setSk_date(String sk_date) {
		this.sk_date = sk_date;
	}
	

	
}
