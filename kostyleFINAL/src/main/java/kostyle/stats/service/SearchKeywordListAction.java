package stats.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import stats.model.SearchKeyword;
import stats.model.StatsDao;

public class SearchKeywordListAction implements StatsAction{

	@Override
	public StatsActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		StatsDao dao = StatsDao.getInstance();
		List<SearchKeyword> list = dao.listSearchKeyword();
		request.setAttribute("list", list);
		
		HttpSession session = request.getSession();
		if(session.getAttribute("SearchKeywordListJ") != null){
			session.removeAttribute("SearchKeywordListJ");
		}
		
		session.setAttribute("SearchKeywordListJ", list);
		
		StatsActionForward forward = new StatsActionForward();
		forward.setisRedirect(true);
		forward.setPath("main/template.jsp?value="+session.getAttribute("searchKeywordJ")+"&body=../stats/searchkeywordlist.jsp");

		return forward;
	}
	
}
