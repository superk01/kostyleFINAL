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

public class CategoryFilter implements Action{
	//세션셋팅
	public void setSession(HttpServletRequest request, HttpServletResponse response, List<Product> product_list)throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.removeAttribute("product_list");
		session.setAttribute("product_list", product_list);
		response.sendRedirect("main/template.jsp?body=../CategorysearchResult/CategorysearchResult.jsp");
	}
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		List<Product> product_list = new ArrayList<Product>();
		HttpSession session = request.getSession();
		product_list = (List<Product>) session.getAttribute("product_list");
		CategoryDao dao = CategoryDao.getDao();
		
		if(request.getParameter("sortstandard").equals("random")){	
			Collections.shuffle(product_list);
			setSession(request,response,product_list);
		}else if(request.getParameter("sortstandard").equals("maxprice")){
			Product_compare pcomp = new Product_compare();
			Collections.sort(product_list,pcomp);
			setSession(request,response,product_list);
		}else if(request.getParameter("sortstandard").equals("minprice")){
			Product_compare_min pcomp_min = new Product_compare_min();
			Collections.sort(product_list,pcomp_min);
			setSession(request,response,product_list);
		}else if(request.getParameter("sortstandard").equals("hotproduct")){
			List<PickProduct_List> pickProduct_List = new ArrayList<PickProduct_List>();
			pickProduct_List = dao.getPickProduct_List();
			product_list = getPictProduct_List_Sort(product_list, pickProduct_List);
			setSession(request,response,product_list);
		}
	}
	
	public List<Product> getPictProduct_List_Sort(List<Product> product_list, List<PickProduct_List> pickProduct_List){
		List<Product> method_product_list = new ArrayList<Product>();
		
		//찜 높은순으로 새로운 list에 추가
		for(int i=0; i<product_list.size(); i++){
			for(int j=0; j<pickProduct_List.size(); j++){	
				System.out.println("A : " + pickProduct_List.get(j).getClo_prdurl());
				System.out.println("B : " + product_list.get(i).getProduct_link());
				if( pickProduct_List.get(j).getClo_prdurl().equals(product_list.get(i).getProduct_link())){
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
