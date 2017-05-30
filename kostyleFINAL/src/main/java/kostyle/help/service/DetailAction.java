package kostyle.help.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import Customer.action.Action;
import Customer.action.ActionForward;
import Customer.model.Answer;
import Customer.model.C_Board;
import Customer.model.C_BoardDAO;

public class DetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		C_BoardDAO dao = C_BoardDAO.getInstance();
		String num = request.getParameter("q_num");
		/*System.out.println(num);*/
		int q_num =0;
		if(num!=null){
			q_num = Integer.parseInt(num);
		}
		/*System.out.println(q_num);*/
		C_Board board = dao.detailBoard(q_num);
		request.setAttribute("board", board);
		
		String as_content = request.getParameter("as_content");
		/*System.out.println("DetailAction:"+as_content);*/
		String as_title = request.getParameter("as_title");
		/*System.out.println("DetailAction:"+as_title);*/
		Answer answer = new Answer();
		answer.setQ_Num(q_num);
		answer.setAc_Content(as_content);
		answer.setAs_Title(as_title);
		
		if(answer.getAs_Content() !=null){
		dao.insertAnswer(answer);
		}
		
		List<Answer> list = dao.ListAnswer(q_num);
		
		
		request.setAttribute("list", list);
		ActionForward forward =new ActionForward();
		forward.setRedirect(false);
		forward.setPath("../main/template.jsp?body=../customer/detail.jsp");
		return forward;
	}

}
