package login.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import login.action.Action;
import login.action.ActionForward;
import login.action.loginCustomerAction;
import login.action.loginShopAction;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.bsylogin")
public class loginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public loginController() {
        super();
    }
    
    public void doProcess(HttpServletRequest request, HttpServletResponse response)throws Exception {
    	//컨트롤러는 식별을 하는 역할. 요청과 관련된 객체인 request에 url을 식별할 수 있는 함수가 있다.
    	String requestURI = request.getRequestURI();
    	//System.out.println(requestURI); // /MVC_Project/insert.do 이렇게 뜨는데, 앞에 /MVC_Project/를 지우고 비교하고싶다.
    	
    	String contextPath = request.getContextPath(); //컨텍스트의 경로를 알려준다. length를 구하기위해 호출
    	String command = requestURI.substring(contextPath.length()+7);
    	System.out.println(command); //insert.do 출력.
    	
    	ActionForward forward= null;
    	Action action = null;
    	
    	
    	
    	if(command.equals("loginCustomerAction.bsylogin")){
    		action  = new loginCustomerAction();
    		try{
    			forward = action.execute(request, response);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}else if(command.equals("loginShopAction.bsylogin")){
    		action = new loginShopAction();
    		try{
    			forward = action.execute(request, response);
    		}catch(Exception e){
    			e.printStackTrace();
    		} 
    		
    	}else if(command.equals("insertAction.do")){
    		//action = new InsertAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    		
    		
    			
 //forward해주는 이동함수   	
    		if(forward != null){
    			if(forward.isRedirect()){
    			response.sendRedirect(forward.getPath());
    			}else{
    			RequestDispatcher dispatcher = 
    			request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    			}
    		}
    	
    	
    }//end method

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

