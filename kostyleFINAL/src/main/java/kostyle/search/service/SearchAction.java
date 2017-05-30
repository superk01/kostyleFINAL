package search.action;

import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Category.model.CategoryDao;
import Category.model.Customer_taste_Select;
import Category.model.Product;
import search.model.JsoupThread;
import search.model.SearchDao;
import search.model.SearchResult;
import search.model.SearchThread;
import search.model.Searching;
import search.model.UrlHtml;

public class SearchAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("SearchAction시작");
		String search = request.getParameter("search");
		HttpSession session = request.getSession();
		List<SearchResult> SearchResult = new ArrayList<SearchResult>();
		
		System.out.println("SearchAction-search:"+search);
		
		SearchDao dao = SearchDao.getInstance();
		List<String> list = dao.getS_SearchUrl();
		
		System.out.println("SearchAction-listsize():"+list.size());
		System.out.println("SearchAction-list:"+list);
		/*String result = "";*/
		
		/*SearchThread thread=null;*/
		List<JsoupThread> thread =new ArrayList<JsoupThread>();
		
		
		
		/*JsoupThread t1 = new JsoupThread(list.get(0)+search);
		t1.start();
		String str = t1.getResult();
		System.out.println("SearchAction-str:"+str);*/
		
		for(int i=0; i<list.size()-1; i++){
			thread.add(new JsoupThread(list.get(i)+search));
			thread.get(i).start();
			
		}
		
		//여기 바꿔!!!
				List<SearchResult> resultList = null;
				for(int i=0; i<list.size()-1; i++){
					/*System.out.println("for문"+i+"번째");*/
					int count =0;
					while(count<list.size()){
						/*System.out.println("while문 내부");*/
						if(thread.get(i).getState()==State.TERMINATED){
							/*System.out.println("if문 내부");
							System.out.println("*******"+i+"번쩨 쓰레드 완료*******");
							System.out.println("result:"+thread.get(i).getResult());*/
							resultList=thread.get(i).getResult();
							for(int j=0; j<resultList.size(); j++){
								SearchResult.add(resultList.get(j));
							}
							thread.get(i).interrupt();
							count++;
							break;
						}
					}	
				}
		
		/*for(int i=0; i<list.size()-1; i++){
			thread.add(new JsoupThread(list.get(i)+search));
			thread.get(i).start();
			System.out.println(i+"번째 스레드 상태:"+thread.get(i).getState());
			thread.get(i).interrupt();
			System.out.println(i+"종료?:"+thread.get(i).getState());
			System.out.println("SearchAction:"+thread.get(i).getResult());
		}*/
		/*for(int i=0; i<list.size()-1; i++){
			while (result.size()<7) {
				if(thread.get(i).getState()==State.TERMINATED){
					result.add(thread.get(i).getResult());
					
				}
			}
		}*/
		
		
		//정규표현식 쓰기전 상태
		/*for(int i=0; i<list.size()-1; i++){
			thread.add(new SearchThread(list.get(i)+search));
			System.out.println("path:"+list.get(i)+search);
			thread.get(i).start();
			
			
			System.out.println(i+"번째 스레드 상태:"+thread.get(i).getState());
		}
		
		
		
		
		
		for(int i=0; i<list.size()-1; i++){
			System.out.println("for문"+i+"번째");
			while(result.size()<7){
				System.out.println("while문 내부");
				if(thread.get(i).getState()==State.TERMINATED){
					System.out.println("if문 내부");
					System.out.println("*******"+i+"번쩨 쓰레드 완료*******");
					System.out.println("result:"+thread.get(i).getResult());
					result.add(thread.get(i).getResult());
					thread.get(i).interrupt();
					break;
				}
			}	
		}
		ImgUrl html = new ImgUrl();
		List<String> imgUrl = new ArrayList<String>();
		for(int i=0; i<result.size(); i++){
			imgUrl.add(html.getImgUrl(result.get(i)));
		}
		
		System.out.println("imgUrl:"+imgUrl);*/
		/*System.out.println("SearchAction-thread:"+thread);
		for(int i=0; i<thread.size(); i++){
		System.out.println(i+"번 쓰레드 상태:"+thread.get(0).getState());}
		System.out.println("0번쓰레드 결과값:"+thread.get(0).getResult());
		result.add(thread.get(0).getResult());*/
		/*while(result.size()>6){
			for(int i=0; i<list.size(); i++){
				if((thread.get(i).isAlive())){
					result.add(thread.get(i).getResult());
				}
			}
		}*/
		
	/*	System.out.println("SearchAction-result0:"+result[0]);
		System.out.println("SearchAction-result1:"+result[1]);
		System.out.println("SearchAction-result2:"+result[2]);
		System.out.println("SearchAction-result3:"+result[3]);*/
		/*System.out.println("SearchAction-result.size():"+result.size());*/
		System.out.println("result:"+SearchResult);
		String c_num = (String)session.getAttribute("c_num");
		SearchResult = setCustomer_taste_result(SearchResult, c_num);
		session.setAttribute("SearchResult", SearchResult);
		
		/*for(int i=0; i<result.size(); i++){
		System.out.println("result"+i+"번째:"+result.get(i)+"\n");}*/
		
		ActionForward forward = new ActionForward();
		forward.setPath("../main/template.jsp?body=../search/result.jsp");
		forward.setRedirect(false);
		return forward;
	}
	
	public List<SearchResult> setCustomer_taste_result(List<SearchResult> product_list, String c_num){
		CategoryDao dao = CategoryDao.getDao();
		List<Customer_taste_Select> c_click_keywordList = new ArrayList<Customer_taste_Select>();
		List<SearchResult> new_product_list = new ArrayList<SearchResult>();
		
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
