package stats.model;

import java.io.Serializable;

public class StatsGender implements Serializable{
	private String s_num;
	private int F;
	private int M;
	
	public StatsGender(){}

	public StatsGender(String s_num, int f, int m) {
		super();
		this.s_num = s_num;
		F = f;
		M = m;
	}

	public String getS_num() {
		return s_num;
	}

	public void setS_num(String s_num) {
		this.s_num = s_num;
	}

	public int getF() {
		return F;
	}

	public void setF(int f) {
		F = f;
	}

	public int getM() {
		return M;
	}

	public void setM(int m) {
		M = m;
	}
	
	
}
