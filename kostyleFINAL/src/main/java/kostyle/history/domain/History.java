package history.model;

import java.io.Serializable;
import java.sql.Date;

public class History implements Serializable {
	private String h_Num;
	private String c_Num;
	private String h_Name;
	private String h_Prdurl;
	private String h_Imgurl;
	private Date h_Date;
	
	public History(){}

	public History(String h_Num, String c_Num, String h_Name, String h_Prdurl, String h_Imgurl, Date h_Date) {
		super();
		this.h_Num = h_Num;
		this.c_Num = c_Num;
		this.h_Name = h_Name;
		this.h_Prdurl = h_Prdurl;
		this.h_Imgurl = h_Imgurl;
		this.h_Date = h_Date;
	}

	public String getH_Num() {
		return h_Num;
	}

	public void setH_Num(String h_Num) {
		this.h_Num = h_Num;
	}

	public String getC_Num() {
		return c_Num;
	}

	public void setC_Num(String c_Num) {
		this.c_Num = c_Num;
	}

	public String getH_Name() {
		return h_Name;
	}

	public void setH_Name(String h_Name) {
		this.h_Name = h_Name;
	}

	public String getH_Prdurl() {
		return h_Prdurl;
	}

	public void setH_Prdurl(String h_Prdurl) {
		this.h_Prdurl = h_Prdurl;
	}

	public String getH_Imgurl() {
		return h_Imgurl;
	}

	public void setH_Imgurl(String h_Imgurl) {
		this.h_Imgurl = h_Imgurl;
	}

	public Date getH_Date() {
		return h_Date;
	}

	public void setH_Date(Date h_Date) {
		this.h_Date = h_Date;
	}

	@Override
	public String toString() {
		return "History [h_Num=" + h_Num + ", c_Num=" + c_Num + ", h_Name=" + h_Name + ", h_Prdurl=" + h_Prdurl
				+ ", h_Imgurl=" + h_Imgurl + ", h_Date=" + h_Date + "]";
	}
	
	
	
}
