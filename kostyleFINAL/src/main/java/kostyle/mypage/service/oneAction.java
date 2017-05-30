package mypage.action;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.synth.SynthScrollBarUI;

import mypage.model.Customer;
import mypage.model.CustomerDao;

public class oneAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			
		HttpSession session = request.getSession();
		
		CustomerDao dao = CustomerDao.getInstance();
		Customer customer = new Customer();
		
		System.out.println((String)session.getAttribute("c_num"));
	
		if(session.getAttribute("c_num") != null) {
			customer = dao.modifyCustomer((String)session.getAttribute("c_num"));
			session.setAttribute("c_id", customer.getC_id());	//번호에 맞는 아이디를 세션에 등록
			session.setAttribute("c_pass", customer.getC_pass());
			System.out.println("얻어온 id값 : "+session.getAttribute("c_id"));
			System.out.println("얻어온 pw값 : "+session.getAttribute("c_pass"));
		}
		
	    ActionForward forward = new ActionForward();
	    forward.setRedirect(true);
	    forward.setPath("/Kostylemall_final/mypage/MypageMain.jsp");
		
		return forward;
	}
	

}
