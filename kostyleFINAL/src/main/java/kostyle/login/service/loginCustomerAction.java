package login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import login.model.CustomerMember;
import login.model.LoginDao;

public class loginCustomerAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		LoginDao dao = LoginDao.getInstance();
		CustomerMember cus = new CustomerMember();
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		String c_id = request.getParameter("c_id");
		System.out.println("request.getRequestURL()결과: "+request.getRequestURL());
		if(dao.checkCustomerId(c_id)==1){
			cus.setC_id(request.getParameter("c_id"));
			cus.setC_pass(request.getParameter("c_pass"));
			
			CustomerMember customer = dao.selectCustomer(cus);
			System.out.println("조회해 온 customer: "+ customer);
			System.out.println("조회해 온 c_num: "+ customer.getC_num());
			session.setAttribute("c_num", customer.getC_num());
			
			forward.setRedirect(true);
			forward.setPath("../login/loginShop.jsp");
			
			
		}else if(dao.checkCustomerId(c_id)==0){
			System.out.println("loginCustomerAction.else if내부");
			request.setAttribute("msg", "해당하는 아이디가 존재하지 않습니다.");
			forward.setRedirect(false);
			forward.setPath("../login/loginCustomer.jsp");			
		}else{
			System.out.println("loginCustomerAction.else내부");
			request.setAttribute("msg", "어떤 오류인지 알 수 없습니다.");
			
			forward.setRedirect(false);
			forward.setPath("../login/loginCustomer.jsp");
		}
		
		
		return forward;
		
		
		
	}

}//class
