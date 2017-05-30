package stats.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import stats.model.SearchKeyword;
import stats.model.StatsDao;

public class SearchKeywordInsertAction implements StatsAction{

	@Override
	public StatsActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatsDao dao = StatsDao.getInstance();
		SearchKeyword searchKeyword = new SearchKeyword();
		
		searchKeyword.setC_num("C001");
		searchKeyword.setSk_searchkey(request.getParameter("query"));
		
		dao.insertSearchKeyword(searchKeyword);
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("searchKeywordJ") != null){
			session.removeAttribute("searchKeywordJ");
		}
		
		session.setAttribute("searchKeywordJ", searchKeyword.getSk_searchkey());
		
		StatsActionForward forward = new StatsActionForward();
		forward.setisRedirect(true);
		forward.setPath("searchkeywordlist.ju");
		
		return forward;
	}

}
