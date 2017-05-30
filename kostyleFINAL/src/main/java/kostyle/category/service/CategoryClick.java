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

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Category.model.Adshoppingmall;
import Category.model.CategoryDao;
import Category.model.Categorysmall;
import Category.model.Customer_taste_Select;
import Category.model.Product;
import Category.model.Product_list;


public class CategoryClick implements Action{

	private List<Product> product_list = new ArrayList<Product>();
	private List<Product_list> product_list_total = new ArrayList<Product_list>();
	CategoryDao dao = CategoryDao.getDao();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = "";
		String strkeyword = "";
		keyword = request.getParameter("keyword"); 
		
		strkeyword = request.getParameter("strkeyword");
		//
		CategoryDao dao = CategoryDao.getDao();
		dao.click_CategoryList(strkeyword);		
		//
		
		List<Categorysmall> keywordList = new ArrayList<Categorysmall>(); //키워드 저장 리스트
		List<Categorysmall> onesave = new ArrayList<Categorysmall>(); //대분류일시 임시로 키워드 저장해서 다시 키워드 리스트에 넘겨줘야함
		List<String> midnumList = new ArrayList<String>(); //중분류 키워드 번호 저장
		
		//키워드를 String 리스트로 변환
		List<String> strkeywordList = new ArrayList<String>();
		
		//키워드 구분
		if( (keyword != null)){
			int keywordInt = Integer.parseInt(keyword);
			if( keywordInt % 100 == 0){ 
				//대분류 키워드 처리
				midnumList = dao.categorymidSelect(keyword);
				for(int i=0; i<midnumList.size(); i++){
					onesave = dao.categorysmallSelect(midnumList.get(i));
					for(int j=0; j<onesave.size(); j++){
						keywordList.add(onesave.get(j));
					}
				} 
			}else{
				//소분류 키워드 구분
				keywordList = dao.categorysmallSelect(keyword);
			}
			
			for(int i=0; i<keywordList.size(); i++){
				strkeywordList.add(keywordList.get(i).getCs_name());
			}
		}
		
		if( strkeyword != null){
			strkeywordList.add(strkeyword);
		}

		//검색할 쇼핑몰 리스트 받기
		List<Adshoppingmall> adsList = new ArrayList<Adshoppingmall>();
		adsList = dao.getAdShoppingmallJoin();
		
		//스레드 생성해서 미리 url다 연결한다음 완료한건 데이터받고 해당 스레드 삭제한다
		List<MainActionThread> threadList = new ArrayList<MainActionThread>();
		int threadcount = 0;
		
		for(int searchList = 0; searchList<adsList.size(); searchList++){
			//shorurl = 끝에 / 추가
			//frontURL = 앞에 http:// 추가 끝에 키워드단어 추가
			for(int searchKeyList=0; searchKeyList<strkeywordList.size(); searchKeyList++){
				String frontURL =  adsList.get(searchList).getS_searchurl()+strkeywordList.get(searchKeyList); 
				String shopurl = adsList.get(searchList).getS_shopurl();
				
				frontURL = frontURL.replace("\n", "");
				shopurl = shopurl.replace("\n", "");
				frontURL = frontURL.replace("\\n", "");
				shopurl = shopurl.replace("\\n", "");
				shopurl = shopurl.trim();
				frontURL = frontURL.trim();		
										
				threadList.add(new MainActionThread(frontURL,shopurl));
				threadList.get(threadcount).start();	
				threadcount ++;
			}
		}

		while(  threadList.size() != 0){
			for(int i=0; i<threadList.size(); i++){
				if(threadList.get(i).getState() == State.TERMINATED){
					System.out.println(i + " 번째 쇼핑몰 완료 ThreadList 삭제");
					product_list_total.add(new Product_list(threadList.get(i).getMain_product_list()));
					threadList.remove(i);
				}
			}
		}	
		
		
		HttpSession session = request.getSession();
		if( session.getAttribute("product_list") != null){
			session.removeAttribute("product_list");
		}
		
		 for(int z=0; z<product_list_total.size(); z++){
			 for(int x=0; x<product_list_total.get(z).getList().size(); x++){
				 product_list.add(product_list_total.get(z).getList().get(x));
			 }
		 }
		 //옷 재정렬		 
		 System.out.println("카테고리 검색 결과 재정렬");
		 Collections.shuffle(product_list);
		 //사용자 취향 분석
		 String c_num = (String)session.getAttribute("c_num");
		 product_list = setCustomer_taste_result(product_list, c_num);
		 session.setAttribute("product_list", product_list);
		 session.setAttribute("product_list_size", product_list.size());
		response.sendRedirect("main/template.jsp?body=../CategorysearchResult/CategorysearchResult.jsp");
		System.out.println("----------------완료--------------");
		
		/*
		RequestDispatcher re = request.getRequestDispatcher("main/template.jsp?body=../CategorysearchResult/CategorysearchResult.jsp");
		re.forward(request, response);
		*/
		
	}    
	
	//사용자 취향 분석 메소드
	public List<Product> setCustomer_taste_result(List<Product> product_list, String c_num){
		List<Customer_taste_Select> c_click_keywordList = new ArrayList<Customer_taste_Select>();
		List<Product> new_product_list = new ArrayList<Product>();
		
		c_click_keywordList = dao.getCustomer_taste_Select(c_num);
		for(int i=0; i<product_list.size(); i++){
			for(int j=0; j<c_click_keywordList.size(); j++){
				if(product_list.get(i).getProduct_name().indexOf(c_click_keywordList.get(j).getC_click_keyword()) != -1){
					new_product_list.add(product_list.get(i));
					System.out.println("패턴에맞는 List 추가 : " + i + " 번째" + "c_num : " + c_num);
					product_list.remove(i);
				}
			}
		}
		
		//나머지 다시 추가
		for(int i=0; i<product_list.size(); i++){
			new_product_list.add(product_list.get(i));
		}
		return new_product_list;
	}
	
}
