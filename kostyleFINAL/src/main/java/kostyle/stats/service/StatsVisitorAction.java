package stats.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import stats.model.HitCount;
import stats.model.StatsDao;

public class StatsVisitorAction implements StatsAction {

	@Override
	public StatsActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		StatsDao dao = StatsDao.getInstance();
		
		String statsSearchShop = request.getParameter("statsSearchShop");
		String statsSearchStartDate = request.getParameter("statsSearchStartDate");
		String statsSearchEndDate = request.getParameter("statsSearchEndDate");
		String chartFor = request.getParameter("chartFor");
		
		List<HitCount> list = dao.statsDate(statsSearchShop, statsSearchStartDate, statsSearchEndDate, chartFor);
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("statsVisitorJ") != null){
			session.removeAttribute("statsVisitorJ");
		}
		
		session.setAttribute("statsVisitorJ", list);
		
		StatsActionForward forward = new StatsActionForward();
		forward.setisRedirect(true);
		
		if(chartFor.equals("area")){
			forward.setPath("main/template.jsp?body=../stats/statsindex.jsp?statsbody=statsVisitor_area_line.jsp");
		}else if(chartFor.equals("gender")){
			forward.setPath("main/template.jsp?body=../stats/statsindex.jsp?statsbody=statsVisitor_gender_line.jsp");
		}
		
		return forward;
	}

}
