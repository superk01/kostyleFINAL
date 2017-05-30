package join.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class Join implements Serializable{
	
	private String c_num;//자동으로 번호 부여
	private String c_name;
	private String c_birth;
	private String c_phonenumber;
	private String c_gender; //(남,여)
	private String c_email;
	private int p_powernum; //자동으로 고객은 2
	private String c_id; 
	private String c_pass;
	private int c_zipcode;
	private String c_adress;
	private String c_sms = "off"; //check:0, uncheck:1

	public Join(){}

	public Join(String c_num, String c_name, String c_birth, String c_phonenumber, String c_gender, String c_email,
			int p_powernum, String c_id, String c_pass, int c_zipcode, String c_adress, String c_sms) {
		super();
		this.c_num = c_num;
		this.c_name = c_name;
		this.c_birth = c_birth;
		this.c_phonenumber = c_phonenumber;
		this.c_gender = c_gender;
		this.c_email = c_email;
		this.p_powernum = p_powernum;
		this.c_id = c_id;
		this.c_pass = c_pass;
		this.c_zipcode = c_zipcode;
		this.c_adress = c_adress;
		this.c_sms = c_sms;
	}

	public String getC_num() {
		return c_num;
	}

	public void setC_num(String c_num) {
		this.c_num = c_num;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public String getC_birth() {
		return c_birth;
	}

	public void setC_birth(String c_birth) {
		this.c_birth = c_birth;
	}

	public String getC_phonenumber() {
		return c_phonenumber;
	}

	public void setC_phonenumber(String c_phonenumber) {
		this.c_phonenumber = c_phonenumber;
	}

	public String getC_gender() {
		return c_gender;
	}

	public void setC_gender(String c_gender) {
		this.c_gender = c_gender;
	}

	public String getC_email() {
		return c_email;
	}

	public void setC_email(String c_email) {
		this.c_email = c_email;
	}

	public int getP_powernum() {
		return p_powernum;
	}

	public void setP_powernum(int p_powernum) {
		this.p_powernum = p_powernum;
	}

	public String getC_id() {
		return c_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public String getC_pass() {
		return c_pass;
	}

	public void setC_pass(String c_pass) {
		this.c_pass = c_pass;
	}

	public int getC_zipcode() {
		return c_zipcode;
	}

	public void setC_zipcode(int c_zipcode) {
		this.c_zipcode = c_zipcode;
	}

	public String getC_adress() {
		return c_adress;
	}

	public void setC_adress(String c_adress) {
		this.c_adress = c_adress;
	}

	public String getC_sms() {
		return c_sms;
	}

	public void setC_sms(String c_sms) {
		this.c_sms = c_sms;
	}

	@Override
	public String toString() {
		return "Join [c_num=" + c_num + ", c_name=" + c_name + ", c_birth=" + c_birth + ", c_phonenumber="
				+ c_phonenumber + ", c_gender=" + c_gender + ", c_email=" + c_email + ", p_powernum=" + p_powernum
				+ ", c_id=" + c_id + ", c_pass=" + c_pass + ", c_zipcode=" + c_zipcode + ", c_adress=" + c_adress
				+ ", c_sms=" + c_sms + "]";
	}

	
	
	
	

}
