package search.model;

import java.io.Serializable;

public class SearchResult implements Serializable{
	private String product_ImageLink;
	private String product_link;
	private String product_price;
	private String product_name;
	
	
	public SearchResult() {
		super();
	}

	
	
	public SearchResult(String product_ImageLink, String product_link, String product_price, String product_name) {
		super();
		this.product_ImageLink = product_ImageLink;
		this.product_link = product_link;
		this.product_price = product_price;
		this.product_name = product_name;
	}



	public String getProduct_ImageLink() {
		return product_ImageLink;
	}


	public void setProduct_ImageLink(String product_ImageLink) {
		this.product_ImageLink = product_ImageLink;
	}


	public String getProduct_link() {
		return product_link;
	}


	public void setProduct_link(String product_link) {
		this.product_link = product_link;
	}


	public String getProduct_price() {
		return product_price;
	}


	public void setProduct_price(String product_price) {
		this.product_price = product_price;
	}


	public String getProduct_name() {
		return product_name;
	}


	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}



	@Override
	public String toString() {
		return "SearchResult [product_ImageLink=" + product_ImageLink + ", product_link=" + product_link
				+ ", product_price=" + product_price + ", product_name=" + product_name + "]";
	}


	
	
	
	
}
