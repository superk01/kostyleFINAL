package mypage.action;

import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.model.Customer;
import mypage.model.CustomerDao;

public class ActionWithdrawal implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		CustomerDao dao = CustomerDao.getInstance();
		Customer customer = new Customer();
		
		HttpSession session = request.getSession();
		System.out.println(request.getParameter("c_pass"));
		String c_name = (String)session.getAttribute("c_id");
		
		
		customer.setC_pass(request.getParameter("c_pass"));
		
		
		
		
		int re = dao.withdrawalCustomer(customer);		
		
		ActionForward forward = new ActionForward();
		
		if(re > 0){
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>alert('회원정보가 삭제되었습니다.'); </script>");
			out.flush(); 
			System.out.println(c_name+"님의 회원정보가 삭제되었습니다.");
			forward.setRedirect(true); 
			forward.setPath("/0507_J/mypage/MypageMain.jsp");
			
			//session.invalidate();
			//여기다가 삭제된 후에 초기페이지로 이동해야함.
		}
		else {
			System.out.println("비밀번호가 불일치합니다.");
			forward.setRedirect(true);
			forward.setPath("../mypage/MypageBody.jsp?body=WithdrawalCheck.jsp");
			response.setContentType("text/html; charset=UTF-8");

			PrintWriter out = response.getWriter();
			out.println("<script>alert('비밀번호가 틀립니다.'); history.go(-1);</script>");
			out.flush(); 
		}


		return forward;
	}


}
