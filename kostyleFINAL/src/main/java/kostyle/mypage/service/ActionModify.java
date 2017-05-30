package mypage.action;

import java.net.URLDecoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.model.Customer;
import mypage.model.CustomerDao;

public class ActionModify implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String c_num =(String)session.getAttribute("c_num");
		
		
		System.out.println("이 값은"+c_num);
		CustomerDao dao = CustomerDao.getInstance();
		Customer customer = new Customer();
		
		customer = dao.modifyCustomer(c_num);
		System.out.println(customer.toString());
		
		request.setAttribute("c_num", customer.getC_num());
		request.setAttribute("c_id", customer.getC_id());
		request.setAttribute("c_name", customer.getC_name());
		request.setAttribute("c_pass", customer.getC_pass());
		request.setAttribute("c_birth", customer.getC_birth());
		request.setAttribute("c_zipcode", customer.getC_zipcode());
		request.setAttribute("c_adress", customer.getC_adress());
		request.setAttribute("c_email", customer.getC_email());
		request.setAttribute("c_phonenumber", customer.getC_phonenumber());
		request.setAttribute("c_sms", customer.getC_sms());
		request.setAttribute("p_powernum", customer.getP_powernum());
		request.setAttribute("c_gender", customer.getC_gender());
		
	    ActionForward forward = new ActionForward();
	    forward.setRedirect(false);//dispatcher(기존요청의 확장)
	    forward.setPath("../mypage/MypageBody.jsp?body=ModifyMyInfo.jsp");
		
		return forward;
	}
	

}
