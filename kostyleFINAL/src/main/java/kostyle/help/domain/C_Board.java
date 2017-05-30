package kostyle.help.domain;

import java.io.Serializable;
import java.util.Date;


public class C_Board implements Serializable{
	private int q_Num;
	private String q_Title;
	private String q_Content;
	private Date q_Date;
	private String q_Sort;
	private String c_Num;
	private String c_Id;
	private String s_Name;
	private String s_Num;
	private	int answerNum;
	
	public C_Board(){}

	public int getQ_Num() {
		return q_Num;
	}

	public void setQ_Num(int q_Num) {
		this.q_Num = q_Num;
	}

	public String getQ_Title() {
		return q_Title;
	}

	public void setQ_Title(String q_Title) {
		this.q_Title = q_Title;
	}

	public String getQ_Content() {
		return q_Content;
	}

	public void setQ_Content(String q_Content) {
		this.q_Content = q_Content;
	}

	public Date getQ_Date() {
		return q_Date;
	}

	public void setQ_Date(Date q_Date) {
		this.q_Date = q_Date;
	}

	public String getC_Id() {
		return c_Id;
	}

	public void setC_Id(String c_Id) {
		this.c_Id = c_Id;
	}

	public String getS_Name() {
		return s_Name;
	}

	public void setS_Name(String s_Name) {
		this.s_Name = s_Name;
	}

	public String gets_Num() {
		return s_Num;
	}

	public void sets_Num(String s_Num) {
		this.s_Num = s_Num;
	}

	public int getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(int answerNum) {
		this.answerNum = answerNum;
	}

	public String getQ_Sort() {
		return q_Sort;
	}

	public void setQ_Sort(String q_Sort) {
		this.q_Sort = q_Sort;
	}

/*	public String getS_Num() {
		return s_Num;
	}

	public void setS_Num(String s_Num) {
		this.s_Num = s_Num;
	}*/

	public String getC_Num() {
		return c_Num;
	}

	public void setC_Num(String c_Num) {
		this.c_Num = c_Num;
	}

	@Override
	public String toString() {
		return "C_Board [q_Num=" + q_Num + ", q_Title=" + q_Title + ", q_Content=" + q_Content + ", q_Date=" + q_Date
				+ ", c_Id=" + c_Id + ", s_Name=" + s_Name + ", s_Num=" + s_Num + ", q_Sort=" + q_Sort + ", c_Num="
				+ c_Num + ", answerNum=" + answerNum + "]";
	}

}
