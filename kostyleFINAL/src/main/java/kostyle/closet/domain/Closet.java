package closet.model;

import java.io.Serializable;

public class Closet implements Serializable{
	private int clo_num;
	private String c_num;
	private String clo_name;
	
	public Closet(){}
	public Closet(int clo_num, String c_num, String clo_name) {
		super();
		this.clo_num = clo_num;
		this.c_num = c_num;
		this.clo_name = clo_name;
	}
	public int getClo_num() {
		return clo_num;
	}
	public void setClo_num(int clo_num) {
		this.clo_num = clo_num;
	}
	public String getC_num() {
		return c_num;
	}
	public void setC_num(String c_num) {
		this.c_num = c_num;
	}
	public String getClo_name() {
		return clo_name;
	}
	public void setClo_name(String clo_name) {
		this.clo_name = clo_name;
	}
	@Override
	public String toString() {
		return "Closet [clo_num=" + clo_num + ", c_num=" + c_num + ", clo_name=" + clo_name + "]";
	}

	
	
	
	
}//class
