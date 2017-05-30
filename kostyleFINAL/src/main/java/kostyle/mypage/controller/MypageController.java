package mypage.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mypage.action.Action;
import mypage.action.ActionWithdrawal;
import mypage.action.ModifySuccess;
import mypage.action.oneAction;
import mypage.action.ActionForward;
import mypage.action.ActionModify;
import mypage.action.ActionModifyCheck;

@WebServlet("*.mypage")
public class MypageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MypageController() {
        super();
    }

    public void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
    	
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String command = requestURI.substring(contextPath.length() + 1);
 
    	String url = request.getRequestURL().toString();
    	System.out.println(command);
    	
    	
    	
    	ActionForward forward = null;
    	Action action = null;
    	
    	
    	if(command.equals("mypage/WithdrawalAction.mypage")){
    		System.out.println("회원정보삭제요청");
    		
    		action = new ActionWithdrawal();
    		try {
    			forward = action.execute(request, response);
    		} catch(Exception e){
    		e.printStackTrace();
    		}
    	}else if(command.equals("mypage/ModifyCheckAction.mypage")){
    		System.out.println("회원정보 수정검사");
    		
    		action = new ActionModifyCheck();
    		try {
    			
				forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
    		
    	}else if(command.equals("mypage/ActionModify.mypage")){
    		System.out.println("회원 정보 수정페이지 요청");
    		action = new ActionModify();
    		try {
    			forward = action.execute(request, response);
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if(url.indexOf("mypage/sessionTestForm.mypage") != -1){
    		System.out.println("회원탈퇴 페이지 요청");
    		action = new oneAction();
    		String c_num = request.getParameter("c_num");
    		
        	HttpSession session = request.getSession();

        	session.setAttribute("c_num", c_num);
    		try {
    			forward = action.execute(request, response);
    		}catch (Exception e) {
    			e.printStackTrace();
    		}
    	}else if(command.equals("mypage/ModifySuccess.mypage")){
    		System.out.println("회원정보 수정 완료");
    		action = new ModifySuccess();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}

    	}
    	
    	if(forward != null) {
    		if(forward.isRedirect()){
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
    	
    }
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
