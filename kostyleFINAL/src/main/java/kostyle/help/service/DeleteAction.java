package kostyle.help.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

import Customer.model.C_BoardDAO;

public class DeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


		
		String num = request.getParameter("q_num");
		int q_num = 0;
		if(num != null){
			q_num = Integer.parseInt(num);
		}
		
		C_BoardDAO dao = C_BoardDAO.getInstance();
		dao.deleteBoard(q_num);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("listAction2.a");
		return forward;
	}

}
