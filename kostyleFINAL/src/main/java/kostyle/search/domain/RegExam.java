package search.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExam {

	public static void main(String[] args) {
				
		String arr[] ={
				"http://66girls.co.kr/product/search.html?banner_action=&keyword=바지",
				"http://www.stylenanda.com/product/search.html?banner_action=&keyword=바지",
				"http://ggsing.com/product/search.html?banner_action=&keyword=바지",	
				"http://loveloveme.com/product/search.html?banner_action=&keyword=바지",
				"http://hotping.co.kr/product/search.html?banner_action=&order_by=favor&keyword=바지",
				"http://www.dejou.co.kr/product/search.html?banner_action=&keyword=바지",
				"http://imvely.com/product/search.html?view_type=&supplier_code=&category_no=&search_type=product_name&keyword=바지"};
		
		String url = "http://hotping.co.kr/product/search.html?banner_action=&order_by=favor&keyword=바지";
		
		/*Pattern pattern = Pattern.compile("^http:\\/\\/");
		Matcher matcher = pattern.matcher(url);*/
		
		/*System.out.println(url.matches("^(http:)\\/\\/"));*/
		/*System.out.println(matcher);*/
		
		/*for(int i=0; i<7; i++){
		Matcher matcher = pattern.matcher(arr[i]);
		System.out.println(matcher.group(i));
		}*/
		/*int index = url.indexOf(".kr/");
		String str =url.substring(0, index+3);
		
		System.out.println(str);*/
		
		
		
		int index = 0;
		
		if(url.indexOf(".com/")>0){
			index = url.indexOf(".com/");
		}else{
			index = url.indexOf("o.kr/");
		}
		
		String str = url.substring(0, index+4);
		System.out.println(str);
		/*Pattern pattern = Pattern.compile("^(http:)\\/\\/");


		Matcher mc = pattern.matcher(url);
		System.out.println(mc.toString());
		System.out.println(mc.groupCount());
		if(mc.matches()){
			System.out.println(mc.group(1));
			
		}*/
	}

}
