package Category.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.Thread.State;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Category.model.Category;
import Category.model.CategoryDao;
import Category.model.Product;
import Category.model.Product_list;
import Category.model.Weather;

public  class MainAction implements Action{
	private List<Product_list> product_list_total = new ArrayList<Product_list>();
	private List<Product> main_product_list = new ArrayList<Product>();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDao dao = CategoryDao.getDao();
		List<String> list = new ArrayList<String>();
		 Weather csh_weather = null; 
		 
		 System.out.println("메인엑션연결");
		 String resultweather = "";
		 String resulttime = "";
		 String resultC = "";
		 String navstr = "";
		 String resultweatherimg = "";
		 
		 HttpSession session = request.getSession();
		 if(session.getAttribute("csh_weather") == null){
			  try{
			   URL url = new URL("http://weather.naver.com/rgn/townWetr.nhn");
			   URLConnection con = url.openConnection();
			   BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			   String tmp;
		
			   while( (tmp = br.readLine()) != null){
				   list.add(tmp);			
			   }		   		  
			  }catch (Exception e){
				  e.printStackTrace();
			  } 
	 	   int startrow = 0;
		   for(int i=0; i<list.size(); i++){
		  	if(list.get(i).indexOf("class=\"w_now2\"") != -1){
		    		startrow = i;
		    		break;
		    	}
		   }

		   for(int i=0; i<list.size(); i++){
			   	if(list.get(i).indexOf("class=\"first\"") != -1){
			   		navstr = list.get(i+3);
			   		navstr = navstr.trim();
			   	}
		   }
		   
		   for(int i=startrow; i<startrow+20; i++){
		   	if(list.get(i).indexOf("<img src") != -1){
		   		String row = list.get(i);
		   		row = row.trim();
		   		resultweatherimg = row.substring(10,row.length());
		   		System.out.println(resultweatherimg);
		   		resultweatherimg = resultweatherimg.substring(0,resultweatherimg.indexOf(".gif")+4);
		   		System.out.println(resultweatherimg);
		   		resultweather= list.get(i).replaceAll("[^가-힣]","");
		   	}else if(list.get(i).indexOf("<h5><span>") != -1){
		   		String row = list.get(i);
		   		String pattern = "<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>";
		   	   	String result = row.replaceAll(pattern, "");
		   	   	result = result.trim();
		   	   	resulttime = result.substring(4, result.length()-5);
		   	   	resultC = list.get(i+8);
		   	   	resultC = resultC.trim();
		   	}
		   }
		   csh_weather = new Weather(resultweather,resulttime,resultC,navstr,resultweatherimg);
		   session.setAttribute("csh_weather", csh_weather);
	 	}
		 //날씨끝
		 
		 //온도에 따라 옷 추천
		 if(csh_weather != null){
			 System.out.println("옷추천");
			 int ondo = Integer.parseInt(csh_weather.getResultC().trim());
			 System.out.println("온도는 " + ondo);
			 if(ondo <= 20 && ondo > 10){
				 getTotalTog("가디건");
			 }else if(ondo <= 10 ){
				 getTotalTog("점퍼");
			 }else if(ondo > 20 && ondo <= 25){
				 getTotalTog("맨투맨");
			 }else if(ondo > 25){
				 getTotalTog("칠부");
				 getTotalTog("민소매");
			 }else if(ondo > 30){
				 getTotalTog("반팔");
			 }
			 
			 if(session.getAttribute("product_list_total") == null){
				 //온도 옷 main으로 보내기
				 for(int i=0; i<product_list_total.size(); i++){
					 Collections.shuffle(product_list_total.get(i).getList()); //하나의쇼핑몰온도옷리스트 무작위정렬
					 
					 if(product_list_total.get(i).getList().size() == 0){
						 System.out.println("검색값 없는 쇼핑몰 삭제");
						 product_list_total.remove(i);
					 }
					 
					 if(product_list_total.get(i).getList().size() > 3){ //아래 기능 예외처리
						 for(int j=3; j<product_list_total.get(i).getList().size(); j++){ //옷 많으면 리스트중에 세개만 남긴다.
							 product_list_total.get(i).getList().remove(j);
						 }	 
					 }
				 }
				 for(int z=0; z<product_list_total.size(); z++){
					 for(int x=0; x<product_list_total.get(z).getList().size(); x++){
						 main_product_list.add(product_list_total.get(z).getList().get(x));
					 }
				 }
				 //옷 재정렬
				 Collections.shuffle(main_product_list);
				 session.setAttribute("main_product_list", main_product_list);
			 }
		 }
		 //온도옷추천끝
		
		 //카테고리리스트 받기
		 List<Category> categoryList = new ArrayList<Category>();		 
		 categoryList = dao.getCategoryList(); //1~10순위 카테고리 리스트		 
		  session.setAttribute("categoryList", categoryList);
		  
		  //11~n 순위 카테고리리스트
			List<Category> categoryList_brother = new ArrayList<Category>();
			categoryList_brother = dao.getCategorylist_brother();
			if(session.getAttribute("categroylist_brother") != null){
				session.removeAttribute("categroylist_brother");
			}
			session.setAttribute("categroylist_brother", categoryList_brother);
 
		 //마지막 실행문구
	   response.sendRedirect("main/template.jsp?body=main.jsp");
	 }
	
	
	   //String frontURL = "http://www.stylenanda.com/product/search.html?banner_action=&keyword=코트"; //스타일난다
	   //String frontURL = "http://www.66girls.co.kr/product/search.html?banner_action=&keyword=코트"; //육육걸즈
	   //String frontURL = "http://ggsing.com/product/search.html?banner_action=&keyword=코트";//고고싱
	   //String frontURL = "http://imvely.com/product/search.html?view_type=&supplier_code=&category_no=&search_type=product_name&keyword=코트"; // 임블리	   
	   //String frontURL = "http://loveloveme.com/product/search.html?banner_action=&keyword=코트"; //러브러브미
	   //String frontURL = "http://hotping.co.kr/product/search.html?banner_action=&order_by=favor&keyword=코트"; //핫핑
	 //String frontURL = "http://www.dejou.co.kr/product/search.html?banner_action=&keyword=코트"; //더데이즈
	  //String shopurl = "www.dejou.co.kr/";
	
	public void getTotalTog(String keyword){
		
		System.out.println(keyword + "값 받음 스레드 시작");
		String shopurlList[]= {
				"www.dejou.co.kr/",
				"hotping.co.kr/",
				"loveloveme.com/",
				"imvely.com/",
				"ggsing.com/",
				"www.66girls.co.kr/",
				"www.stylenanda.com/"
		};
		
		String frontURLList[] ={
				"http://www.dejou.co.kr/product/search.html?banner_action=&keyword=코트"+keyword,
				"http://hotping.co.kr/product/search.html?banner_action=&order_by=favor&keyword="+keyword,
				"http://loveloveme.com/product/search.html?banner_action=&keyword="+keyword,
				"http://imvely.com/product/search.html?view_type=&supplier_code=&category_no=&search_type=product_name&keyword="+keyword,				
				"http://ggsing.com/product/search.html?banner_action=&keyword="+keyword,
				"http://www.66girls.co.kr/product/search.html?banner_action=&keyword="+keyword,
				"http://www.stylenanda.com/product/search.html?banner_action=&keyword="+keyword								
		};
		
		//스레드 생성해서 미리 url다 연결한다음 완료한건 데이터받고 해당 스레드 삭제한다
		List<MainActionThread> threadList = new ArrayList<MainActionThread>();
		for(int i=0; i<frontURLList.length; i++){
			threadList.add(new MainActionThread(frontURLList[i],shopurlList[i]));
			threadList.get(i).start();
			System.out.println("쇼핑몰 연결중" + i + " 번쨰");
		}
		
		while(  threadList.size() != 0){
			for(int i=0; i<threadList.size(); i++){
				if(threadList.get(i).getState() == State.TERMINATED){
					System.out.println(i + " 번째 쇼핑몰 ThreadList삭제");
					product_list_total.add(new Product_list(threadList.get(i).getMain_product_list()));
					threadList.remove(i);
				}
			}
		}
	
	}	
}
