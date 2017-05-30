package kostyle.help.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Customer.action.Action;
import Customer.action.ActionForward;
import Customer.action.DeleteAction;
import Customer.action.DetailAction;
import Customer.action.InsertAction;
import Customer.action.ListAction;
import Customer.action.ListAction2;
import Customer.action.SearchMineAction;
import Customer.action.UpdateAction;
import Customer.action.UpdateFormAction;
import Customer.action.WriteAction;
import Customer.model.C_Board;
import Customer.model.C_BoardDAO;

/**
 * Servlet implementation class C_boardcontroller
 */
@WebServlet("*.a")
public class C_boardcontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public C_boardcontroller() {
        super();
    }
    
    public void doProcess(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
    	/*String requestURI = request.getRequestURI();
    	System.out.println(requestURI);
    	String contextPath = request.getContextPath();
		System.out.println(contextPath);
		String command = requestURI.substring(contextPath.length()+10);
		System.out.println(command);*/
		
    	
    	String url = request.getRequestURL().toString();
		Action action = null;
		ActionForward forward = null;
		
		if(url.indexOf("listAction.a")!=-1){
			action = new ListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(url.indexOf("listAction2.a")!=-1){
			action = new ListAction2();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else if(url.indexOf("insertAction.a")!=-1){
			action = new InsertAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(url.indexOf("detailAction.a")!=-1){
			action = new DetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(url.indexOf("updateAction.a")!=-1){
			action = new UpdateAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(url.indexOf("updateForm.a")!=-1){
			action = new UpdateFormAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(url.indexOf("deleteAction.a")!=-1){
			action = new DeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(url.indexOf("searchMineAction.a")!=-1){
			action = new SearchMineAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(url.indexOf("writeAction.a")!=-1){
			action = new WriteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		if(forward != null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 doProcess(request, response);
	 
	 /*//����ڰ�?? ��û�� �ּ�	
	 String url = request.getRequestURL().toString();
	 System.out.println(url);
	 //DAO��ü����
	 C_BoardDAO dao = C_BoardDAO.getInstance();
	 //list.do�̸�
	 if(url.indexOf("list.do") !=-1){
	 //�Խù� ���?? ����	 
	 List<C_Board> list = dao.list();
	 request.setAttribute("list", list);
	 //���� �ּҷ� ������(�ּҰ���, ȭ�� ��ȯ, ������ ����).
	 String page = "/customer/forcustomer.jsp";
	 RequestDispatcher rd = request.getRequestDispatcher(page);
	 rd.forward(request, response);
	 }*/
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
