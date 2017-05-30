package mypage.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.model.Customer;
import mypage.model.CustomerDao;

public class ActionModifyCheck implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CustomerDao dao = CustomerDao.getInstance();
		Customer customer = new Customer();
		
		HttpSession session = request.getSession();
		String c_id = (String)session.getAttribute("c_id");
		String c_pass = (String)request.getParameter("c_pass");
		
		customer.setC_id(c_id);
		customer.setC_pass(c_pass);
		
		System.out.println("이거"+c_id);
		System.out.println("이거"+c_pass);
		
		int re = dao.modifyCheckCustomer(customer);		
		System.out.println(re);
		
		
		ActionForward forward = new ActionForward();
		
		
		if(re > 0){
			forward.setRedirect(true);
			forward.setPath("ActionModify.mypage");
			
			System.out.println("입력 완료");
		} else{
			forward.setRedirect(true);
			forward.setPath("../mypage/MypageBody.jsp?body=ModifyMyInfoCheck.jsp");
			System.out.println("비밀번호 틀림.");
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 틀립니다.'); history.go(-1);</script>");
			out.flush(); 
		}
		
		return forward;
		
	}

}
