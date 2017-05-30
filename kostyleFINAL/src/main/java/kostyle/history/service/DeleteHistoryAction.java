package history.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.binding.MapperMethod.ParamMap;

import history.model.HistoryDao;

public class DeleteHistoryAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("DeleteHistoryAction시작");
		String[] arrH_num = request.getParameter("h_num").toString().split(",");
		System.out.println("DeleteHistoryAction-arrH_num:"+arrH_num);
		String num = request.getParameter("c_num");
		int c_num = 0;
		if(num != null){
			c_num = Integer.parseInt(num);
		}
		System.out.println("DeleteHistoryAction-c_num:"+c_num);
		
		HistoryDao dao = HistoryDao.getInstance();
		for(int i=0; i<arrH_num.length; i++){
			dao.deleteHistory(Integer.parseInt(arrH_num[i]));
		}
		
		ActionForward forward = new ActionForward();
		forward.setPath("listAction.history?c_num="+c_num);
		forward.setRedirect(false);
		return forward;
	}

}
