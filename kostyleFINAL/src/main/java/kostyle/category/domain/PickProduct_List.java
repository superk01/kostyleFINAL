package Category.model;

import java.io.Serializable;

public class PickProduct_List implements Serializable{
	private String clo_prdurl = "";
	private int clo_zzim;
	
	public String getClo_prdurl() {
		return clo_prdurl;
	}
	public void setClo_prdurl(String clo_prdurl) {
		this.clo_prdurl = clo_prdurl;
	}
	public int getClo_zzim() {
		return clo_zzim;
	}
	public void setClo_zzim(int clo_zzim) {
		this.clo_zzim = clo_zzim;
	}
	public PickProduct_List(String clo_prdurl, int clo_zzim) {
		this.clo_prdurl = clo_prdurl;
		this.clo_zzim = clo_zzim;
	}
	
	public PickProduct_List(){}
	
}
