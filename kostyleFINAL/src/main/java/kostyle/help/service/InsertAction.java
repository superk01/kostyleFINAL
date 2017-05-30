package kostyle.help.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;
import org.apache.ibatis.session.SqlSession;

import Customer.model.C_Board;
import Customer.model.C_BoardDAO;

public class InsertAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		System.out.println("InsertAction?‹œ?ž‘");
		HttpSession session = request.getSession();
		String str = (String)session.getAttribute("c_num");
		int c_num = Integer.parseInt(str);
		
		
		C_BoardDAO dao = C_BoardDAO.getInstance();
		C_Board board = new C_Board();
		String num =request.getParameter("q_num");
		System.out.println("InsertAction-q_num:"+num);
		
		int q_num = 0;
		if(num != null){
			q_num = Integer.parseInt(num);
		}
		String q_title = request.getParameter("title");
		String q_content = request.getParameter("content");
		String selectshop = request.getParameter("selectshop");
		String selectsort = request.getParameter("selectsort");
		
		
		
		System.out.println(q_num);
		System.out.println(q_title);
		System.out.println(q_content);
		System.out.println("InsertAction selectshop:"+selectshop);
		System.out.println("InsertAction selectsort:"+selectsort);
		
		String s_num = dao.getS_Num(selectshop);
		String c_Id = dao.getC_Id(c_num);
		/*System.out.println("InsertAction : "+s_num);*/
		
		board.setQ_Num(q_num);
		board.setQ_Title(q_title);
		board.setQ_Content(q_content);
		board.setQ_Sort(selectsort);
		board.setS_Name(selectshop);
		board.sets_Num(s_num);
		board.setC_Id(c_Id);
		board.setC_Num(c_num+"");
		
		System.out.println("InsertAction:"+board);
		
		dao.insertBoard(board, c_num);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("listAction2.a");
		
		return forward;
		
	}

}
