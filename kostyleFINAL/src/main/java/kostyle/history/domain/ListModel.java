package history.model;

import java.io.Serializable;
import java.util.List;

public class ListModel implements Serializable{
	private List<History> list;
	
	public ListModel(){}

	public ListModel(List<History> list) {
		super();
		this.list = list;
	}

	public List<History> getList() {
		return list;
	}

	public void setList(List<History> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "ListModel [list=" + list + "]";
	}
	
	
}
