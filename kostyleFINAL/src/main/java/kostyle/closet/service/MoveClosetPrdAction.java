package closet.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import closet.model.Closet;
import closet.model.ClosetDao;
import closet.model.ClosetPrd;

public class MoveClosetPrdAction implements ActionVoid {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ClosetDao dao = ClosetDao.getInstance();
		ClosetPrd closetPrd = new ClosetPrd();
		HttpSession session = request.getSession();
		ActionForward forward = new ActionForward();
		int re = -1;
		int move_clo_num = Integer.parseInt(request.getParameter("move_clo_num"));
		
		//넘어오는 값 : clo_detail_nums, move_clo_num
		String clo_detail_nums = (String)request.getParameter("clo_detail_nums");
		System.out.println("Move클로젯Prd액션의 clo_detail_nums: "+clo_detail_nums);
		System.out.println("Move클로젯Prd액션의 move_clo_num: "+request.getParameter("move_clo_num"));
		
		closetPrd.setClo_num(move_clo_num);
		closetPrd.setC_num((String)session.getAttribute("c_num"));
		
		String[] clo_detail_numArray = clo_detail_nums.split(",");
		for(int i=0; i<clo_detail_numArray.length; i++){
			System.out.println("clo_detail_numArray쪼개기: "+clo_detail_numArray[i]);
			closetPrd.setClo_detail_num(Integer.parseInt(clo_detail_numArray[i]));
			System.out.println("MoveClosetPrd 보낼 prd정보: "+closetPrd);
			re = dao.moveClosetPrd(closetPrd);
			
		}
	}

}
