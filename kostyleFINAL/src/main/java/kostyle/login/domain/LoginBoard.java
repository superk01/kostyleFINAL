package login.model;

import java.io.Serializable;

public class LoginBoard implements Serializable{
	private String p_num;
	private String c_num;
	private String s_num;
	private String p_prdURL;
	private String p_imgURL;
	private String cl_num;
	private String p_name;
	
	public LoginBoard(){}

	public LoginBoard(String p_num, String c_num, String s_num, String p_prdURL, String p_imgURL,
			String cl_num, String p_name) {
		super();
		this.p_num = p_num;
		this.c_num = c_num;
		this.s_num = s_num;
		this.p_prdURL = p_prdURL;
		this.p_imgURL = p_imgURL;
		this.cl_num = cl_num;
		this.p_name = p_name;
	}

	public String getP_num() {
		return p_num;
	}

	public void setP_num(String p_num) {
		this.p_num = p_num;
	}

	public String getC_num() {
		return c_num;
	}

	public void setC_num(String c_num) {
		this.c_num = c_num;
	}

	public String gets_num() {
		return s_num;
	}

	public void sets_num(String s_num) {
		this.s_num = s_num;
	}

	public String getp_prdURL() {
		return p_prdURL;
	}

	public void setp_prdURL(String p_prdURL) {
		this.p_prdURL = p_prdURL;
	}

	public String getp_imgURL() {
		return p_imgURL;
	}

	public void setp_imgURL(String p_imgURL) {
		this.p_imgURL = p_imgURL;
	}

	public String getCl_num() {
		return cl_num;
	}

	public void setCl_num(String cl_num) {
		this.cl_num = cl_num;
	}

	public String getP_name() {
		return p_name;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	

	
	
	
}
