package history.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import history.model.History;
import history.model.HistoryDao;

public class DeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DeleteAction시작");
		String num = request.getParameter("c_num");
		int c_num = 0;
		if(num != null){
			c_num = Integer.parseInt(num);
		}
		System.out.println("DeleteAction-c_num:"+c_num);
	
		String history_num = request.getParameter("h_num");
		int h_num;
		if(history_num != null){
			h_num = Integer.parseInt(history_num);
		}else{
			h_num = 0;
		}
		System.out.println("DeleteAction-h_num:"+h_num);
		HistoryDao dao = HistoryDao.getInstance();
		dao.deleteHistory(h_num);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("remoconAction.history?c_num="+c_num);
		return forward;
		
	}

}
