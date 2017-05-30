package login.model;

public class CustomerMember {
	private String c_num;
	private int c_birth;
	private String c_phonenumber;
	private String c_gender;
	private String c_email;
	private int p_powernum;
	private String c_id;
	private String c_pass;
	private int c_zipcode;
	private String c_address;
	private int c_sms;
	
	public CustomerMember(){}

	public CustomerMember(String c_num, int c_birth, String c_phonenumber, String c_gender, String c_email, int p_powernum,
			String c_id, String c_pass, int c_zipcode, String c_address, int c_sms) {
		super();
		this.c_num = c_num;
		this.c_birth = c_birth;
		this.c_phonenumber = c_phonenumber;
		this.c_gender = c_gender;
		this.c_email = c_email;
		this.p_powernum = p_powernum;
		this.c_id = c_id;
		this.c_pass = c_pass;
		this.c_zipcode = c_zipcode;
		this.c_address = c_address;
		this.c_sms = c_sms;
	}

	public String getC_num() {
		return c_num;
	}

	public void setC_num(String c_num) {
		this.c_num = c_num;
	}

	public int getC_birth() {
		return c_birth;
	}

	public void setC_birth(int c_birth) {
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

	public String getC_address() {
		return c_address;
	}

	public void setC_address(String c_address) {
		this.c_address = c_address;
	}

	public int getC_sms() {
		return c_sms;
	}

	public void setC_sms(int c_sms) {
		this.c_sms = c_sms;
	}

	@Override
	public String toString() {
		return "Customer [c_num=" + c_num + ", c_birth=" + c_birth + ", c_phonenumber=" + c_phonenumber + ", c_gender="
				+ c_gender + ", c_email=" + c_email + ", p_powernum=" + p_powernum + ", c_id=" + c_id + ", c_pass="
				+ c_pass + ", c_zipcode=" + c_zipcode + ", c_address=" + c_address + ", c_sms=" + c_sms + "]";
	}
	


}//Class
