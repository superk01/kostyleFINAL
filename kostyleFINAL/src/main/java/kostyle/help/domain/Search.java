package kostyle.help.domain;

import java.io.Serializable;
import java.util.Arrays;

public class Search implements Serializable{
	private String area;
	private String searchKey;
	private String searchMine;
	
	public Search(){}
	
	

	public Search(String area, String searchKey, String searchMine) {
		super();
		this.area = area;
		this.searchKey = searchKey;
		this.searchMine = searchMine;
	}



	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchMine() {
		return searchMine;
	}

	public void setSearchMine(String searchMine) {
		this.searchMine = searchMine;
	}

	@Override
	public String toString() {
		return "Search [area=" + area + ", searchKey=" + searchKey + ", searchMine=" + searchMine + "]";
	}

	
}
