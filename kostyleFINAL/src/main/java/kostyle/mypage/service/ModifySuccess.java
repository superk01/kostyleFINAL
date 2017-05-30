package mypage.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.model.Customer;
import mypage.model.CustomerDao;

public class ModifySuccess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		/*
		HttpSession session = request.getSession();
		String c_num = (String)session.getAttribute("c_num");*/
		
		CustomerDao dao = CustomerDao.getInstance();
		Customer customer = new Customer();
		
		
		String c_num = (String)request.getParameter("c_num");	
		String c_name = (String)request.getParameter("c_name");
		String c_adress = (String)request.getParameter("c_adress");

		String c_email = (String)request.getParameter("c_email");
		String c_gender = (String)request.getParameter("c_gender");
		String c_id = (String)request.getParameter("c_id");
		String c_pass = (String)request.getParameter("c_pass");
		String c_phonenumber = request.getParameter("c_phonenumber");
		String c_sms = (String)request.getParameter("c_sms");
		int c_zipcode = Integer.parseInt(request.getParameter("c_zipcode"));
		int p_powernum = Integer.parseInt(request.getParameter("p_powernum"));
		String c_birth = (String)request.getParameter("c_birth");
		
		
		customer.setC_num(c_num);
		customer.setC_name(c_name);
		customer.setC_adress(c_adress);
		customer.setC_birth(c_birth);
		customer.setC_email(c_email);
		customer.setC_gender(c_gender);
		customer.setC_id(c_id);
		customer.setC_pass(c_pass);
		customer.setC_phonenumber(c_phonenumber);;
		customer.setC_sms(c_sms);
		customer.setC_zipcode(c_zipcode);
		customer.setP_powernum(p_powernum);
		
		
		/*	customer.setC_adress(c_adress);
		customer.setC_birth(c_birth);
		customer.set
		customer.setC_gender(c_gender);*/
		
		dao.ModifySuccess(customer);
			
		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		forward.setPath("/Kostylemall_final/mypage/MypageMain.jsp");

		return forward;
	}


}