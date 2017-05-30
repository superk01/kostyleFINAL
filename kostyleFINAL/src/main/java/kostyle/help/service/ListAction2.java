package kostyle.help.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Customer.model.C_Board;
import Customer.model.C_BoardDAO;
import Customer.model.ListModel;
import Customer.model.Search;

public class ListAction2 implements Action {
	private static final int PAGE_SIZE = 3;
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		C_BoardDAO dao = C_BoardDAO.getInstance();
		Search search = new Search();
		
		String c_num = (String)session.getAttribute("c_num");
		if(request.getParameter("reset")!=null){
			session.removeAttribute("search");
		}
		
		String temp = request.getParameter("temp");
		if(temp != null){
			session.removeAttribute("search");
		}
		if(request.getParameter("area") != null){
		String area = request.getParameter("area");
		String searchKey = request.getParameter("searchKey");
		String c_Num = dao.getC_Num(searchKey);
		search.setArea(area);
		search.setSearchKey("%"+c_Num+"%");
		session.setAttribute("search", search);
		}else{
			search=(Search)session.getAttribute("search");
		}
		
		String num = request.getParameter("requestPage");
		System.out.println(num);
		int requestPage = 1;
		if(num != null){
			requestPage = Integer.parseInt(num);
		}
		
		int totalRow = dao.countBoard(search);
		int totalPage = totalRow/PAGE_SIZE;
		if(totalRow%PAGE_SIZE>0){
			totalPage++;
		}
		int startPage = requestPage -(requestPage-1)%5;
		int endPage = startPage +4;
		
		if(endPage>totalPage){
			endPage = totalPage;
		}
		int startRow = (requestPage-1)*PAGE_SIZE;
		
		List<C_Board> list = dao.list(search,startRow);
		if(session.getAttribute("listModel") != null){
			session.removeAttribute("listModel");
		}
		ListModel listModel = new ListModel(list, totalRow, totalPage, startPage, endPage);
		System.out.println("listModel:"+listModel);
		session.setAttribute("listModel", listModel);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("../main/template.jsp?body=../customer/forcustomer.jsp");
		
		return forward;
		
	}

}
