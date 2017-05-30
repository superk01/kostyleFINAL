package history.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import history.model.History;
import history.model.HistoryDao;
import history.model.ListModel;

public class RemoconAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("RemoconAction시작");
		String num = request.getParameter("c_num");
		
		
		int c_num = 0;
		if(num != null){
			c_num = Integer.parseInt(num);
		}
		System.out.println("ListAction-c_num:"+c_num);
		HistoryDao dao = HistoryDao.getInstance();
		List<History> list = dao.listHistory(c_num);
		int history_Num = dao.countHistory(c_num);
		
		List<ListModel> outerList = new ArrayList<ListModel>();
		List<History> innerList = new ArrayList<History>();;
		ListModel listModel = null;
		for(int i=0; i<list.size(); i++){
			innerList.add(list.get(i));
			if(i%3==2){
				listModel = new ListModel(innerList);
				outerList.add(listModel);
				for(int j=0; j<innerList.size(); j++){
				innerList.remove(j);
				}
			}
		}
		
		request.setAttribute("list", list);
		request.setAttribute("c_Num", c_num);
		request.setAttribute("history_Num", history_Num);
		
		System.out.println("RemoconAction-outerList:"+outerList);
		ActionForward forward = new ActionForward(); 
		forward.setPath("../main/template.jsp?body=../history/test.jsp");
		forward.setRedirect(false);
		return null;
	
	}

}
