package history.action;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import history.model.History;
import history.model.HistoryDao;


public class ListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("ListAction시작");
		String num = request.getParameter("c_num");
		
		
		int c_num = 0;
		if(num != null){
			c_num = Integer.parseInt(num);
		}
		System.out.println("ListAction-c_num:"+c_num);
		HistoryDao dao = HistoryDao.getInstance();
		List<History> list = dao.listHistory(c_num);
		
		System.out.println("ListAction-list:"+list);
		
		request.setAttribute("list", list);
		
		
		ActionForward forward = new ActionForward(); 
		forward.setPath("../main/template.jsp?body=../history/history.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
