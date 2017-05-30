package favorite.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Favorite implements Serializable{
	
	private String f_num;
	private String f_coment;
	private Timestamp f_date;
	private String s_num;
	private String c_num;
	
	private String s_image;
	private String s_sname;
	private String s_shopurl;
	
	
	public Favorite(){}


	public Favorite(String f_num, String f_coment, Timestamp f_date, String s_num, String c_num, String s_image,
			String s_sname, String s_shopurl) {
		super();
		this.f_num = f_num;
		this.f_coment = f_coment;
		this.f_date = f_date;
		this.s_num = s_num;
		this.c_num = c_num;
		this.s_image = s_image;
		this.s_sname = s_sname;
		this.s_shopurl = s_shopurl;
	}


	public String getF_num() {
		return f_num;
	}


	public void setF_num(String f_num) {
		this.f_num = f_num;
	}


	public String getF_coment() {
		return f_coment;
	}


	public void setF_coment(String f_coment) {
		this.f_coment = f_coment;
	}


	public Timestamp getF_date() {
		return f_date;
	}


	public void setF_date(Timestamp f_date) {
		this.f_date = f_date;
	}


	public String getS_num() {
		return s_num;
	}


	public void setS_num(String s_num) {
		this.s_num = s_num;
	}


	public String getC_num() {
		return c_num;
	}


	public void setC_num(String c_num) {
		this.c_num = c_num;
	}


	public String getS_image() {
		return s_image;
	}


	public void setS_image(String s_image) {
		this.s_image = s_image;
	}


	public String getS_sname() {
		return s_sname;
	}


	public void setS_sname(String s_sname) {
		this.s_sname = s_sname;
	}


	public String getS_shopurl() {
		return s_shopurl;
	}


	public void setS_shopurl(String s_shopurl) {
		this.s_shopurl = s_shopurl;
	}

	
	
	

}
