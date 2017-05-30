package Category.model;

import java.io.Serializable;

public class Category implements Serializable{
	private String name = "";
	private int hitcount = 0;
	private int id = 0;
	
	public Category(String name, int hitcount, int id) {
		super();
		this.name = name;
		this.hitcount = hitcount;
		this.id = id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHitcount() {
		return hitcount;
	}
	public void setHitcount(int hitcount) {
		this.hitcount = hitcount;
	}
	 
	Category(){}
	
}
