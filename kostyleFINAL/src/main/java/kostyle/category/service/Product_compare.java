package Category.action;

import java.util.Comparator;

import Category.model.Product;

public class Product_compare implements Comparator<Product>{
	//Product_List의 각 객체의 가격을 비교해 정렬하는것
	@Override
	public int compare(Product p1, Product p2) {
		String friststr = p1.getProduct_price();
		String laststr = p2.getProduct_price();
		friststr = friststr.replaceAll("[,가-힣]","");
		laststr = laststr.replaceAll("[,가-힣]","");
		friststr = friststr.trim();
		laststr = laststr.trim();
		
		int firstPrice = Integer.parseInt(friststr);
		int lastPrice = Integer.parseInt(laststr);
		
		if(firstPrice > lastPrice){
			return -1;
		}else if( firstPrice < lastPrice){
			return 1;
		}else{
			return 0;
		}
	}
}
