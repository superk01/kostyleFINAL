package Category.action;

import java.util.Comparator;

import Category.model.PickProduct_List;

public class PickProduct_compare  implements Comparator<PickProduct_List>{
	//Product_List의 각 객체의 가격을 비교해 정렬하는것
	@Override
	public int compare(PickProduct_List p1, PickProduct_List p2) {
		
		int firstPrice = p1.getClo_zzim();
		int lastPrice = p2.getClo_zzim();
		
		if(firstPrice > lastPrice){
			return 1;
		}else if( firstPrice < lastPrice){
			return -1;
		}else{
			return 0;
		}
	}
}
