package closet.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import closet.model.Closet;
import closet.model.ClosetDao;
import closet.model.ClosetPrd;

public class DeleteClosetPrdAction implements ActionVoid {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClosetDao dao = ClosetDao.getInstance();
		ClosetPrd closetPrd = new ClosetPrd();
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		int re = -1;

		//받은값: clo_detail_nums 배열
	/*	String[] clo_detail_numArray = request.getParameterValues("clo_detail_nums");
		System.out.println("String배열 clo_detail_numArray: "+clo_detail_numArray);
		for(int i=0; i<clo_detail_numArray.length; i++){
			System.out.println("for문진입+실행중인clo_detail_num: "+clo_detail_numArray[i]);
			
			re = dao.deleteClosetPrd(Integer.parseInt(clo_detail_numArray[i]));
			if( re > 0){
				forward.setRedirect(true);
				forward.setPath("ClosetAction.closet");
			}else{
				request.setAttribute("msg", "오류가 발생했습니다. re: "+re);
				forward.setRedirect(true);
				forward.setPath("ClosetAction.closet");
			}
		}*/
		
		//받은값 : clo_detail_nums 문자열
		String clo_detail_nums = (String)request.getParameter("clo_detail_nums");
		System.out.println("Delete클로젯Prd액션의 clo_detail_nums: "+clo_detail_nums);
		System.out.println("Delete클로젯Prd액션의 clo_num: "+request.getParameter("clo_num"));
		
		String[] clo_detail_numArray = clo_detail_nums.split(",");

		for(int i=0; i<clo_detail_numArray.length; i++){
			System.out.println("clo_detail_numArray쪼개기: "+clo_detail_numArray[i]);
			re = dao.deleteClosetPrd(Integer.parseInt(clo_detail_numArray[i]));
			
		}

	}

}
