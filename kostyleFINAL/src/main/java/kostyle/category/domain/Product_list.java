package Category.model;

import java.util.List;

public class Product_list {
	private List<Product> list;

	public Product_list(List<Product> list) {
		super();
		this.list = list;
	}

	public List<Product> getList() {
		return list;
	}

	public void setList(List<Product> list) {
		this.list = list;
	}
	
}
