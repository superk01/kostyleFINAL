package Category.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Category.model.CategoryDao;
import Category.model.PickProduct_List;
import Category.model.Product;
import search.model.SearchResult;

public class CategoryFilter2 implements Action{
	//세션셋팅
	public void setSession(HttpServletRequest request, HttpServletResponse response, List<SearchResult> SearchResult)throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.removeAttribute("SearchResult");
		session.setAttribute("SearchResult", SearchResult);
		response.sendRedirect("main/template.jsp?body=../search/result.jsp");
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<SearchResult> SearchResult = new ArrayList<SearchResult>();
		HttpSession session = request.getSession();
		SearchResult = (List<SearchResult>) session.getAttribute("SearchResult");
		CategoryDao dao = CategoryDao.getDao();
		
		if(request.getParameter("sortstandard").equals("random")){	
			Collections.shuffle(SearchResult);
			setSession(request,response,SearchResult);
		}else if(request.getParameter("sortstandard").equals("maxprice")){
			Product_compare2 pcomp = new Product_compare2();
			Collections.sort(SearchResult,pcomp);
			setSession(request,response,SearchResult);
		}else if(request.getParameter("sortstandard").equals("minprice")){
			Product_compare_min2 pcomp_min = new Product_compare_min2();
			Collections.sort(SearchResult,pcomp_min);
			setSession(request,response,SearchResult);
		}else if(request.getParameter("sortstandard").equals("hotproduct")){
			List<PickProduct_List> pickProduct_List = new ArrayList<PickProduct_List>();
			System.out.println("여기");
			pickProduct_List = dao.getPickProduct_List();
			
			SearchResult = getPictProduct_List_Sort(SearchResult, pickProduct_List);
			setSession(request,response,SearchResult);
		}
	}
	
	public List<SearchResult> getPictProduct_List_Sort(List<SearchResult> product_list, List<PickProduct_List> pickProduct_List){
		List<SearchResult> method_product_list = new ArrayList<SearchResult>();
		//찜 높은순으로 새로운 list에 추가
		for(int i=0; i<product_list.size(); i++){
			for(int j=0; j<pickProduct_List.size(); j++){
				String getzzim =  pickProduct_List.get(j).getClo_prdurl();	
				System.out.println("A 비교 " + getzzim);
				System.out.println("B 비교 " + product_list.get(i).getProduct_link());
				if(product_list.get(i).getProduct_link().indexOf(getzzim) != -1){
					System.out.println("찾음"); 
					method_product_list.add(product_list.get(i));
					product_list.remove(i);
				}
			}
		}
		
		//찜 없는거 마지막으로 추가
		for(int i=0; i<product_list.size(); i++){
			method_product_list.add(product_list.get(i));
		}
	
		return method_product_list;
	}
}
