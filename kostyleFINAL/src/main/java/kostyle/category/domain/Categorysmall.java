package Category.model;

import java.io.Serializable;

public class Categorysmall implements Serializable{
	private String cm_num;
	private String cs_num;
	private String cs_name;
	
	public Categorysmall(){}
	
	public Categorysmall(String cm_num, String cs_num, String cs_name) {
		super();
		this.cm_num = cm_num;
		this.cs_num = cs_num;
		this.cs_name = cs_name;
	}

	public String getCm_num() {
		return cm_num;
	}

	public void setCm_num(String cm_num) {
		this.cm_num = cm_num;
	}

	public String getCs_num() {
		return cs_num;
	}

	public void setCs_num(String cs_num) {
		this.cs_num = cs_num;
	}

	public String getCs_name() {
		return cs_name;
	}

	public void setCs_name(String cs_name) {
		this.cs_name = cs_name;
	}

	@Override
	public String toString() {
		return "Categorysmall [cm_num=" + cm_num + ", cs_num=" + cs_num + ", cs_name=" + cs_name + "]\n";
	}
	
	
	
}
