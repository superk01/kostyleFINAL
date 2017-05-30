package closet.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import closet.action.Action;
import closet.action.ActionForward;
import closet.action.ActionVoid;
import closet.action.CDSessionAttributeAction;
import closet.action.ClosetAction;
import closet.action.DeleteClosetAction;
import closet.action.DeleteClosetPrdAction;
import closet.action.DuplicationCheckClosetPrdAction;
import closet.action.InsertPrdAction;
import closet.action.MoveClosetPrdAction;
import closet.action.SaveClosetAction;
import closet.action.TabClosetAction;
import closet.action.UpdateClosetAction;

/**
 * Servlet implementation class BoardController
 */
@WebServlet("*.closet")
public class closetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public closetController() {
        super();
    }
    
    public void doProcess(HttpServletRequest request, HttpServletResponse response)throws Exception {
    	//컨트롤러는 식별을 하는 역할. 요청과 관련된 객체인 request에 url을 식별할 수 있는 함수가 있다.
    	String requestURI = request.getRequestURI();
    	//System.out.println(requestURI); // /MVC_Project/insert.do 이렇게 뜨는데, 앞에 /MVC_Project/를 지우고 비교하고싶다.
    	response.setContentType("text/html;charset=UTF-8"); 
    	String contextPath = request.getContextPath(); //컨텍스트의 경로를 알려준다. length를 구하기위해 호출
    	String command = requestURI.substring(contextPath.length()+1);
    	System.out.println(command); //insert.closet출력
    	
    	ActionForward forward= null;
    	Action action = null;
    	ActionVoid actionVoid = null;
    	
    	
    	if(command.equals("ClosetAction.closet")){
    		System.out.println("ClosetAction.closet 진입");
    		action = new ClosetAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("TabClosetAction.closet")){
    		System.out.println("TabClosetAction.closet진입");
    		action = new TabClosetAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("DeleteClosetPrdAction.closet")){
    		System.out.println("DeleteClosetPrdAction.closet진입");
    		actionVoid = new DeleteClosetPrdAction();
    		try {
				actionVoid.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("DeleteClosetAction.closet")){
    		System.out.println("DeleteClosetAction.closet진입");
    		action = new DeleteClosetAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("UpdateClosetAction.closet")){
    		System.out.println("UpdateClosetAction.closet진입");
    		action = new UpdateClosetAction(); 
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	else if(command.equals("MoveClosetPrdAction.closet")){
    		System.out.println("MoveClosetPrdAction.closet진입");
    		actionVoid = new MoveClosetPrdAction(); 
    		try {
				actionVoid.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	else if(command.equals("InsertPrdAction.closet")){
    		System.out.println("InsertClosetPrdAction.closet진입");
    		actionVoid = new InsertPrdAction(); 
    		try {
				actionVoid.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	else if(command.equals("DuplicationCheckClosetPrdAction.closet")){
    		System.out.println("DuplicationCheckClosetPrdAction.closet진입");
    		actionVoid = new DuplicationCheckClosetPrdAction(); 
    		try {
    			actionVoid.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("CDSessionAttributeAction.closet")){
    		System.out.println("CreateSessionAttributeAction.closet진입");
    		actionVoid = new CDSessionAttributeAction();
    		try {
    			actionVoid.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("SaveClosetAction.closet")){
    		System.out.println("SaveClosetAction.closet진입");
    		actionVoid = new SaveClosetAction();
    		try {
				actionVoid.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("")){
    		System.out.println("진입");
    		//action = 
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("")){
    		System.out.println("진입");
    		//action = 
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("")){
    		System.out.println("진입");
    		//action = 
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("")){
    		System.out.println("진입");
    		//action = 
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else if(command.equals("")){
    		System.out.println("진입");
    		//action = 
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    	
    		
    /*	if(command.equals("insertForm.do")){
    		action = new InsertFormAction();
    		try{
    			forward = action.execute(request, response);
    		}catch(Exception e){
    			e.printStackTrace();
    		}
    	}else if(command.equals("insertAction.do")){
    		action = new InsertAction();
    		try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
 
//액션이 끝나면 무조건 오게됨. 이동해주는 부분.    	
    	if(forward != null){
    		if(forward.isRedirect()){
    			response.sendRedirect(forward.getPath());
    		}else{
    			RequestDispatcher dispatcher = 
    					request.getRequestDispatcher(forward.getPath());
    			dispatcher.forward(request, response);
    		}
    	}
    	
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProcess(request,response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
