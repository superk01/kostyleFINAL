package closet.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import closet.model.Closet;
import closet.model.ClosetDao;
import closet.model.ClosetPrd;

public class DeleteClosetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClosetDao dao = ClosetDao.getInstance();
		Closet closet = new Closet();
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		int re1 = -1;
		int re2 = -2;
		//받은값 : clo_nums 문자열
		System.out.println("request.getParameter+clo_nums값: "+request.getParameter("clo_nums"));
		String clo_nums = (String)request.getParameter("clo_nums");
		System.out.println("Delete클로젯액션의 clo_nums: "+clo_nums);
		System.out.println("Delete클로젯액션의 clo_num: "+request.getParameter("clo_num"));
		
		String[] clo_numArray = clo_nums.split(",");
		
		closet.setC_num((String)session.getAttribute("c_num"));
		for(int i=0; i<clo_numArray.length; i++){
			System.out.println("clo_numArray쪼개기: "+clo_numArray[i]);
			closet.setClo_num(Integer.parseInt(clo_numArray[i]));
			re1 = dao.deleteSameCloset_prds(closet);
			re2 = dao.deleteCloset(closet);
		/*	if( re > 0){
				forward.setRedirect(true);
				forward.setPath("ClosetAction.closet");
			}else{
				request.setAttribute("msg", "오류가 발생했습니다. re: "+re);
			}
			*/
			
			forward.setRedirect(false);
			forward.setPath("ClosetAction.closet");
		}
		
		return forward;
	}

}
