package kostyle.help.domain;

import java.util.List;

public class ListModel {
	private List<C_Board> list;
	private int requestPage;
	private int totalPage;
	private int startPage;
	private int endPage;
	
	public ListModel(){}

	public ListModel(List<C_Board> list, int requestPage, int totalPage, int startPage, int endPage) {
		super();
		this.list = list;
		this.requestPage = requestPage;
		this.totalPage = totalPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}

	public List<C_Board> getList() {
		return list;
	}

	public void setList(List<C_Board> list) {
		this.list = list;
	}

	public int getrequestPage() {
		return requestPage;
	}

	public void setrequestPage(int requestPage) {
		this.requestPage = requestPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "ListModel [list=" + list + ", requestPage=" + requestPage + ", totalPage=" + totalPage + ", startPage="
				+ startPage + ", endPage=" + endPage + "]";
	}
	
}
