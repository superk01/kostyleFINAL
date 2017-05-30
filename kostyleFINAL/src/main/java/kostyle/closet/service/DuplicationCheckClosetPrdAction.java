package closet.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import closet.model.Closet;
import closet.model.ClosetDao;
import closet.model.ClosetPrd;

public class DuplicationCheckClosetPrdAction implements ActionVoid {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("실제액션클래스: DuplicationCheckClosetPrdAction진입");
		ClosetDao dao = ClosetDao.getInstance();
		ClosetPrd closetPrd = new ClosetPrd();
		HttpSession session = request.getSession();
		int dupliCount = -1;

		//6. 미친건지.. c_num이 인식이 안된다! Integer로 잡혀서 일단은 인티저->스트링으로 변환
		System.out.println("c_num변환: "+ request.getSession().getAttribute("c_num").toString());
		String c_num = session.getAttribute("c_num").toString();
		//String c_num =(String)request.getSession().getAttribute("c_num");
		//System.out.println("c_num캐스팅...테스트 String "+c_num);
		
		String prdUrl = request.getParameter("prdUrl");
		//prdUrl의 http://자르기(순서상 다른것들 구한 후에 해야함.)
		prdUrl = dao.prdUrlRepair(prdUrl);
		System.out.println("자른 prdUrl: "+prdUrl);
		closetPrd.setClo_prdUrl(prdUrl);
		
		closetPrd.setC_num(c_num);
		
		System.out.println("dupllicationCheck 보낼 closetPrd값: "+closetPrd);
		
		dupliCount = dao.check_duplication(closetPrd);
		System.out.println("받은 dupliCount: "+dupliCount);
		
		try {
			//에이작스에 보낼 데이터 심기.
			response.getWriter().print(dupliCount);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
