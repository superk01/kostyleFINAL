package kostyle.help.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Customer.model.C_Board;
import Customer.model.C_BoardDAO;
import Customer.model.ListModel;
import Customer.model.Search;

public class SearchMineAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*String num = request.getParameter("c_num");*/
		HttpSession session = request.getSession();
		String num = (String)session.getAttribute("c_num");
		int c_num = 0;
		if(num != null){
			c_num = Integer.parseInt(num);
		}
		
		
		Search search = new Search();
		search.setSearchMine(num);
		
		session.setAttribute("search", search);
		System.out.println("SearchMineAction:"+search);
		/*C_BoardDAO dao = C_BoardDAO.getInstance();
		List<C_Board> list = dao.searchMine(c_num);
		
		request.setAttribute("list", list);
		System.out.println("SearchMineAction:"+list);*/
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("listAction2.a");
		
		return forward;
	}

}
